package com.github.charlotte3517.hotelbooking.controller;

import com.github.charlotte3517.hotelbooking.order.OrderManageService;
import com.github.charlotte3517.hotelbooking.order.model.Order;
import com.github.charlotte3517.hotelbooking.order.model.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderManageService orderService;

    @GetMapping("/")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable Integer orderId) {
        return orderService.getOrderById(orderId);
    }

    @PostMapping("/")
    public Map<String, Object> createOrder(@RequestBody Order order) {
        Integer orderId = orderService.createOrderWithPayment(order);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Order created successfully");
        response.put("orderId", orderId);
        return response;
    }

    @PutMapping("/{orderId}")
    public String updateOrder(@PathVariable Integer orderId, @RequestBody List<OrderDetail> orderDetails) {
        orderService.updateOrderWithDetails(orderId, orderDetails);
        return "Order updated successfully";
    }

    @DeleteMapping("/{orderId}")
    public String deleteOrder(@PathVariable Integer orderId) {
        orderService.deleteOrder(orderId);
        return "Order deleted successfully";
    }
}
