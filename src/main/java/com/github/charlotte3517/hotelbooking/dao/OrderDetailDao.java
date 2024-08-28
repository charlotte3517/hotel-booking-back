package com.github.charlotte3517.hotelbooking.dao;

import com.github.charlotte3517.hotelbooking.order.model.OrderDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderDetailDao {

    List<OrderDetail> getAllOrderDetails();

    List<OrderDetail> getOrderDetailsByOrderId(@Param("orderId") Integer orderId);

    OrderDetail getOrderDetailById(@Param("detailId") Integer detailId);

    void insertOrderDetail(@Param("orderDetail") OrderDetail orderDetail, @Param("userId") Integer userId);

    void updateOrderDetail(@Param("orderDetail") OrderDetail orderDetail);

    void deleteOrderDetail(@Param("detailId") Integer detailId);
}