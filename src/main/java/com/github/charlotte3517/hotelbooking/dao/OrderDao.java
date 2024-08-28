package com.github.charlotte3517.hotelbooking.dao;

import com.github.charlotte3517.hotelbooking.order.model.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderDao {

    List<Order> getAllOrders();

    Order getOrderById(@Param("orderId") Integer orderId);

    Integer insertOrder(@Param("order") Order order, @Param("userId") Integer userId);

    void updateOrder(@Param("order") Order order, @Param("userId") Integer userId);

    void deleteOrder(@Param("orderId") Integer orderId);

    void updateOrderStatusAndPaymentStatus(@Param("orderId") Integer orderId, @Param("status") String status,
                                           @Param("isPaid") Integer isPaid, @Param("userId") Integer userId);
}