package com.hoan.orderservice.service.Impl;

import com.hoan.orderservice.model.Order;
import com.hoan.orderservice.repository.OrderRepository;
import com.hoan.orderservice.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  @Override
  public void createOrder(Order order) {
    orderRepository.save(order);
  }

  @Override
  public List<Order> getOrders() {
    return orderRepository.findAll();
  }

  @Override
  public Order getOrderById(Long id) {
    return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
  }
}
