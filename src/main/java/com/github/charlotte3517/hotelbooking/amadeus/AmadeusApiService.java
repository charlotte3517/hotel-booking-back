package com.github.charlotte3517.hotelbooking.amadeus;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.charlotte3517.hotelbooking.amadeus.hotelBooking.request.*;
import com.github.charlotte3517.hotelbooking.amadeus.hotelBooking.response.HotelBookingResponse;
import com.github.charlotte3517.hotelbooking.amadeus.hotelOffer.Data;
import com.github.charlotte3517.hotelbooking.amadeus.hotelOffer.Offer;
import com.github.charlotte3517.hotelbooking.amadeus.hotelOffer.Price;
import com.github.charlotte3517.hotelbooking.amadeus.hotelsByCity.HotelsByCityResponse;
import com.github.charlotte3517.hotelbooking.amadeus.hotelOffer.HotelOfferResponse;
import com.github.charlotte3517.hotelbooking.amadeus.token.TokenResponse;
import com.github.charlotte3517.hotelbooking.common.ApiService;
import com.github.charlotte3517.hotelbooking.redis.RedisKeyUtil;
import com.github.charlotte3517.hotelbooking.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.*;
import java.util.function.Function;

@Service
public class AmadeusApiService extends ApiService {

    private static final Logger logger = LoggerFactory.getLogger(AmadeusApiService.class);
    private static final String DEFAULT_HOTEL_ID = "MCLONGHM";
    private static final int TOKEN_CACHE_MINUTES = 25;
    private static final int HOTELS_BY_CITY_CACHE_DAYS = 30;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private AmadeusApiProperties properties;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisKeyUtil redisKeyUtil;

    public AmadeusApiService(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public String getValidToken(boolean isNeedCallApi) {
        String tokenKey = redisKeyUtil.buildAmadeusTokenKey();
        String token;
        if (!isNeedCallApi) {
            token = redisUtil.get(tokenKey, String.class);
            if (token != null) {
                return token;
            }
        }

        TokenResponse tokenResponse = getToken();
        token = tokenResponse.getAccessToken();

        redisUtil.set(tokenKey, token, Duration.ofMinutes(TOKEN_CACHE_MINUTES));
        return token;
    }


    public TokenResponse getToken() {

        String url = UriComponentsBuilder.fromHttpUrl(properties.getBaseUrl())
                .path(AmadeusApiUrlPath.TOKEN.getPath())
                .toUriString();

        Map<String, String> request = createTokenRequest();
        TokenResponse response = callApiWithForm(url, request, TokenResponse.class, HttpMethod.POST);

        String accessToken = response.getAccessToken();
        return response;
    }

    private Map<String, String> createTokenRequest() {
        Map<String, String> formParams = new HashMap<>();
        formParams.put("grant_type", "client_credentials");
        formParams.put("client_id", properties.getClientId());
        formParams.put("client_secret", properties.getClientSecret());
        return formParams;
    }

    public <T> T callApiWithRetry( String url, Function<String, T> apiCall,
            Function<HttpClientErrorException, T> fallback ) {

        String token = getValidToken(false);

        try {
            return apiCall.apply(token);
        } catch (HttpClientErrorException.Unauthorized e) {
            logger.info("Token expired, fetching a new token and retrying...");
            token = getValidToken(true);

            try {
                return apiCall.apply(token);
            } catch (HttpClientErrorException ex) {
                if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
                    return fallback.apply(ex);
                }
                throw ex;
            }
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                return fallback.apply(e);
            }
            throw e;
        }
    }


    public HotelsByCityResponse getHotelsByCity(String cityCode, int radius) {
        String redisKey = redisKeyUtil.buildHotelsByCityKey(cityCode, radius);

        HotelsByCityResponse cachedResponse = redisUtil.get(redisKey, HotelsByCityResponse.class);
        if (cachedResponse != null) {
            return cachedResponse;
        }

        String url = UriComponentsBuilder.fromHttpUrl(properties.getBaseUrl())
                .path(AmadeusApiUrlPath.HOTELS_BY_CITY.getPath())
                .queryParam("cityCode", cityCode)
                .queryParam("radius", radius)
                .queryParam("radiusUnit", "KM")
                .queryParam("hotelSource", "ALL")
                .toUriString();

        HotelsByCityResponse response = callApiWithRetry(
                url,
                token -> callApi(url, token, null, HotelsByCityResponse.class, HttpMethod.GET),
                e -> {
                    throw e;
                }
        );

        redisUtil.set(redisKey, response, Duration.ofDays(HOTELS_BY_CITY_CACHE_DAYS));
        return response;
    }

    private String buildRedisKey(String cityCode, int radius) {
        return String.format("hotelsByCity:%s:%d", cityCode, radius);
    }

    public HotelOfferResponse getHotelOffer(String hotelIds, String hotelName, String checkInDate, String checkOutDate, int adults, int roomQuantity) {
        String paymentPolicy = "NONE";
        boolean bestRateOnly = true;

        String url = buildHotelOffersUrl(hotelIds, checkInDate, checkOutDate, adults, roomQuantity, paymentPolicy, bestRateOnly);

        return callApiWithRetry(
                url,
                token -> callApi(url, token, null, HotelOfferResponse.class, HttpMethod.GET),
                e -> handleDefaultHotelFallback(e, hotelName, checkInDate, checkOutDate, adults, roomQuantity, paymentPolicy, bestRateOnly)
        );
    }

    private HotelOfferResponse handleDefaultHotelFallback(HttpClientErrorException e, String hotelName, String checkInDate, String checkOutDate, int adults, int roomQuantity, String paymentPolicy, boolean bestRateOnly) {
        String fallbackUrl = buildUrlWithDefaultHotelId(checkInDate, checkOutDate, adults, roomQuantity, paymentPolicy, bestRateOnly);

        HotelOfferResponse fallbackResponse = callApiWithRetry(
                fallbackUrl,
                token -> callApi(fallbackUrl, token, null, HotelOfferResponse.class, HttpMethod.GET),
                ex -> {
                    throw ex;
                }
        );

        return adjustFallbackResponseForOriginalHotel(fallbackResponse, hotelName, roomQuantity);
    }

    private HotelOfferResponse adjustFallbackResponseForOriginalHotel(
            HotelOfferResponse fallbackResponse, String originalHotelName, int roomQuantity) {
        if (fallbackResponse == null || fallbackResponse.getData() == null) {
            return fallbackResponse;
        }

        List<Data> dataList = fallbackResponse.getData();

        for (Data data : dataList) {
            adjustHotelName(data, originalHotelName);
            adjustOffers(data, roomQuantity);
        }

        fallbackResponse.setData(dataList);
        return fallbackResponse;
    }

    private void adjustHotelName(Data data, String originalHotelName) {
        if (data == null || data.getHotel() == null) {
            return;
        }
        data.getHotel().setName(originalHotelName);
    }

    private void adjustOffers(Data data, int roomQuantity) {
        if (data == null || data.getOffers() == null) {
            return;
        }

        for (Offer offer : data.getOffers()) {
            if (offer == null) {
                continue;
            }
            offer.setRoomQuantity(roomQuantity);
            setCurrencyToTWD(offer);
        }
    }

    private void setCurrencyToTWD(Offer offer) {
        if (offer == null || offer.getPrice() == null) {
            return;
        }

        Price price = offer.getPrice();
        price.setCurrency("TWD");
    }

    private String buildHotelOffersUrl(String hotelIds, String checkInDate, String checkOutDate, int adults, int roomQuantity, String paymentPolicy, boolean bestRateOnly) {
        return UriComponentsBuilder.fromHttpUrl(properties.getBaseUrl())
                .path(AmadeusApiUrlPath.HOTEL_OFFERS.getPath())
                .queryParam("hotelIds", hotelIds)
                .queryParam("checkInDate", checkInDate)
                .queryParam("checkOutDate", checkOutDate)
                .queryParam("adults", adults)
                .queryParam("roomQuantity", roomQuantity)
                .queryParam("paymentPolicy", paymentPolicy)
                .queryParam("bestRateOnly", bestRateOnly)
                .toUriString();
    }

    private static String extractCodeAndDetailFromResponseBody(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(responseBody);

            JsonNode errorsNode = rootNode.path("errors");
            if (errorsNode.isArray() && errorsNode.size() > 0) {
                JsonNode errorNode = errorsNode.get(0);
                JsonNode codeNode = errorNode.path("code");
                JsonNode detailNode = errorNode.path("detail");

                String code = !codeNode.isMissingNode() ? codeNode.asText() : "Unknown code";
                String detail = !detailNode.isMissingNode() ? detailNode.asText() : "Unknown detail";

                return "Code: " + code + ", Detail: " + detail;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Unknown error occurred.";
    }

    private String buildUrlWithDefaultHotelId(String checkInDate, String checkOutDate, int adults, int roomQuantity, String paymentPolicy, boolean bestRateOnly) {
        return buildHotelOffersUrl(
                DEFAULT_HOTEL_ID,
                checkInDate,
                checkOutDate,
                adults,
                roomQuantity,
                paymentPolicy,
                bestRateOnly
        );
    }

    public HotelBookingResponse bookHotel(HotelBookingRequest bookingRequest) {
        String url = UriComponentsBuilder.fromHttpUrl(properties.getBaseUrl())
                .path(AmadeusApiUrlPath.HOTEL_BOOKING.getPath())
                .toUriString();

        return callApiWithRetry(
                url,
                token -> callApi(url, token, bookingRequest, HotelBookingResponse.class, HttpMethod.POST),
                e -> {
                    throw e;
                }
        );
    }

    public HotelBookingRequest createBookingRequestWithFakeData(HotelBookingRequest request) {
        HotelBookingRequest bookingRequest = new HotelBookingRequest();
        Data2 data = new Data2();
        data.setType("hotel-order");

        Guest guest = new Guest();
        guest.setTid(1);
        guest.setTitle("MR");
        guest.setFirstName("BOB");
        guest.setLastName("SMITH");
        guest.setPhone("+33679278416");
        guest.setEmail("bob.smith@email.com");
        data.setGuests(List.of(guest));

        TravelAgent travelAgent = new TravelAgent();
        Contact contact = new Contact();
        contact.setEmail("bob.smith@email.com");
        travelAgent.setContact(contact);
        data.setTravelAgent(travelAgent);

        RoomAssociation roomAssociation = new RoomAssociation();
        GuestReference guestReference = new GuestReference();
        guestReference.setGuestReference("1");
        roomAssociation.setGuestReferences(List.of(guestReference));
        roomAssociation.setHotelOfferId(request.getOfferId());
        data.setRoomAssociations(List.of(roomAssociation));

        Payment payment = new Payment();
        payment.setMethod("CREDIT_CARD");

        PaymentCard paymentCard = new PaymentCard();
        PaymentCardInfo paymentCardInfo = new PaymentCardInfo();
        paymentCardInfo.setVendorCode("VI");
        paymentCardInfo.setCardNumber("4151289722471370");
        paymentCardInfo.setExpiryDate("2026-08");
        paymentCardInfo.setHolderName("BOB SMITH");
        paymentCard.setPaymentCardInfo(paymentCardInfo);
        payment.setPaymentCard(paymentCard);
        data.setPayment(payment);

        bookingRequest.setData(data);

        return bookingRequest;
    }
}
