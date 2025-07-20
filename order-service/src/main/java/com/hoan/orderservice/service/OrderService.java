package com.hoan.orderservice.service;

import com.hoan.orderservice.model.Order;
import java.util.List;

public interface OrderService {
  void createOrder(Order order);
  List<Order> getOrders();
  Order  getOrderById(Long id);
}
