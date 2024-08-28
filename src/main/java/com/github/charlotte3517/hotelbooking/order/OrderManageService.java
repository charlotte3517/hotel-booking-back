package com.github.charlotte3517.hotelbooking.order;

import com.github.charlotte3517.hotelbooking.dao.HotelDao;
import com.github.charlotte3517.hotelbooking.dao.RoomTypeDao;
import com.github.charlotte3517.hotelbooking.dao.OrderDao;
import com.github.charlotte3517.hotelbooking.dao.OrderDetailDao;
import com.github.charlotte3517.hotelbooking.exception.HBException;
import com.github.charlotte3517.hotelbooking.order.model.Order;
import com.github.charlotte3517.hotelbooking.order.model.OrderDetail;
import com.github.charlotte3517.hotelbooking.payment.Payment;
import com.github.charlotte3517.hotelbooking.payment.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderManageService {

    private static final Logger logger = LoggerFactory.getLogger(OrderManageService.class);

    private static final Integer DEFAULT_USER_ID = 0;
    private static final String CONFIRM = "C";

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private HotelDao hotelDao;

    @Autowired
    private RoomTypeDao roomTypeDao;

    public List<Order> getAllOrders() {
        try {
            return orderDao.getAllOrders();
        } catch (Exception e) {
            logger.error("Error occurred while fetching all orders", e);
            throw new HBException("Failed to fetch all orders");
        }
    }

    public List<OrderDetail> getAllOrderDetailsByOrderId(Integer orderId) {
        try {
            return orderDetailDao.getOrderDetailsByOrderId(orderId);
        } catch (Exception e) {
            logger.error("Error occurred while fetching order details by order ID", e);
            throw new HBException("Failed to fetch order details by order ID");
        }
    }

    public Order getOrderById(Integer orderId) {
        try {
            return orderDao.getOrderById(orderId);
        } catch (Exception e) {
            logger.error("Error occurred while fetching order by ID", e);
            throw new HBException("Failed to fetch order by ID");
        }
    }

    public Integer createOrderWithPayment(Order order) {
        try {
            Integer orderId = createOrderWithDetails(order);

            Payment payment = new Payment();
            payment.setOrderId(orderId);
            payment.setPaymentAmount(order.getTotalPrice());
            payment.setPaymentMethod(order.getPaymentMethod());
            paymentService.createPayment(payment);

            updateOrderStatusAndPaymentStatus(orderId, CONFIRM, 1);

            return orderId;
        } catch (Exception e) {
            logger.error("Error occurred while creating order with payment", e);
            throw new HBException("Failed to create order with payment");
        }
    }

    public Integer createOrderWithDetails(Order order) {
        try {
            BigDecimal totalPrice = calculateTotalPrice(order);
            order.setTotalPrice(totalPrice);

            int affectedRows = orderDao.insertOrder(order, DEFAULT_USER_ID);
            Integer orderId = order.getOrderId();
            insertOrderDetails(orderId, order.getOrderDetails(), DEFAULT_USER_ID);
            return orderId;
        } catch (Exception e) {
            logger.error("Error occurred while creating order with details", e);
            throw new HBException("Failed to create order with details");
        }
    }

    private BigDecimal calculateTotalPrice(Order order) {
        return order.getOrderDetails().stream()
                .map(OrderDetail::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void insertOrderDetails(Integer orderId, List<OrderDetail> orderDetails, Integer userId) {
        try {
            for (OrderDetail orderDetail : orderDetails) {
                orderDetail.setOrderId(orderId);
                orderDetailDao.insertOrderDetail(orderDetail, userId);
            }
        } catch (Exception e) {
            logger.error("Error occurred while inserting order details", e);
            throw new HBException("Failed to insert order details");
        }
    }

    private void updateOrderStatusAndPaymentStatus(Integer orderId, String status, Integer isPaid) {
        try {
            orderDao.updateOrderStatusAndPaymentStatus(orderId, status, isPaid, DEFAULT_USER_ID);
        } catch (Exception e) {
            logger.error("Error occurred while updating order status and payment status", e);
            throw new HBException("Failed to update order status and payment status");
        }
    }

    public void updateOrderWithDetails(Integer orderId, List<OrderDetail> list) {
        try {
            Order order = orderDao.getOrderById(orderId);
            List<OrderDetail> originDetails = orderDetailDao.getOrderDetailsByOrderId(orderId);

            if (order == null) {
                throw new HBException("Order not found");
            }

            for (OrderDetail detail : list) {
                if (detail.getOrderId() == null) {
                    orderDetailDao.insertOrderDetail(detail, DEFAULT_USER_ID);
                }

                boolean existsInOriginDetails = originDetails.stream()
                        .anyMatch(originDetail -> originDetail.getOrderDetailId().equals(detail.getOrderDetailId()));

                if (!existsInOriginDetails) {
                    orderDetailDao.insertOrderDetail(detail, DEFAULT_USER_ID);
                } else {
                    orderDetailDao.updateOrderDetail(detail);
                }
            }
        } catch (Exception e) {
            logger.error("Error occurred while updating order with details", e);
            throw new HBException("Failed to update order with details");
        }
    }

    public void deleteOrder(Integer orderId) {
        try {
            orderDao.deleteOrder(orderId);
        } catch (Exception e) {
            logger.error("Error occurred while deleting order", e);
            throw new HBException("Failed to delete order");
        }
    }
}
