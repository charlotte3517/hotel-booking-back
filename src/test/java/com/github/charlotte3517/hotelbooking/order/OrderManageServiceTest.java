package com.github.charlotte3517.hotelbooking.order;

import com.github.charlotte3517.hotelbooking.dao.HotelDao;
import com.github.charlotte3517.hotelbooking.dao.OrderDao;
import com.github.charlotte3517.hotelbooking.dao.OrderDetailDao;
import com.github.charlotte3517.hotelbooking.dao.RoomTypeDao;
import com.github.charlotte3517.hotelbooking.exception.HBException;
import com.github.charlotte3517.hotelbooking.order.model.Order;
import com.github.charlotte3517.hotelbooking.order.model.OrderDetail;
import com.github.charlotte3517.hotelbooking.payment.Payment;
import com.github.charlotte3517.hotelbooking.payment.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderManageServiceTest {

    @Mock
    private PaymentService paymentService;

    @Mock
    private OrderDao orderDao;

    @Mock
    private OrderDetailDao orderDetailDao;

    @Mock
    private HotelDao hotelDao;

    @Mock
    private RoomTypeDao roomTypeDao;

    @InjectMocks
    private OrderManageService orderManageService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllOrders() {
        List<Order> orders = Arrays.asList(new Order(), new Order());
        when(orderDao.getAllOrders()).thenReturn(orders);

        List<Order> result = orderManageService.getAllOrders();

        assertEquals(2, result.size());
        verify(orderDao, times(1)).getAllOrders();
    }

    @Test
    public void testGetOrderById() {
        Order order = new Order();
        when(orderDao.getOrderById(1)).thenReturn(order);

        Order result = orderManageService.getOrderById(1);

        assertNotNull(result);
        verify(orderDao, times(1)).getOrderById(1);
    }

    @Test
    public void testCreateOrderWithPayment() {
        Order order = new Order();
        order.setOrderId(1);
        order.setTotalPrice(BigDecimal.TEN);
        order.setPaymentMethod("CREDIT_CARD");
        order.setOrderDetails(Collections.singletonList(new OrderDetail()));

        when(orderDao.insertOrder(any(Order.class), anyInt())).thenReturn(1);
        when(orderDao.getOrderById(1)).thenReturn(order);
        doNothing().when(orderDetailDao).insertOrderDetail(any(OrderDetail.class), anyInt());

        Integer orderId = orderManageService.createOrderWithPayment(order);

        assertEquals(1, orderId);
        verify(orderDao, times(1)).insertOrder(any(Order.class), anyInt());
        verify(orderDetailDao, times(1)).insertOrderDetail(any(OrderDetail.class), anyInt());
        verify(paymentService, times(1)).createPayment(any(Payment.class));
    }

    @Test
    public void testUpdateOrderWithDetails() {
        Order order = new Order();
        order.setOrderId(1);
        OrderDetail detail = new OrderDetail();
        detail.setOrderDetailId(1);
        detail.setOrderId(1);
        List<OrderDetail> details = Collections.singletonList(detail);

        when(orderDao.getOrderById(1)).thenReturn(order);
        when(orderDetailDao.getOrderDetailsByOrderId(1)).thenReturn(details);
        doNothing().when(orderDetailDao).updateOrderDetail(any(OrderDetail.class));

        orderManageService.updateOrderWithDetails(1, details);

        verify(orderDao, times(1)).getOrderById(1);
        verify(orderDetailDao, times(1)).getOrderDetailsByOrderId(1);
        verify(orderDetailDao, times(1)).updateOrderDetail(any(OrderDetail.class));
    }

    @Test
    public void testDeleteOrder() {
        doNothing().when(orderDao).deleteOrder(1);

        orderManageService.deleteOrder(1);

        verify(orderDao, times(1)).deleteOrder(1);
    }
}
