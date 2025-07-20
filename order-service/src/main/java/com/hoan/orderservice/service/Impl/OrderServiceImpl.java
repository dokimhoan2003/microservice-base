package com.hoan.orderservice.service.Impl;

import com.hoan.orderservice.event.OrderPlacedEvent;
import com.hoan.orderservice.model.Order;
import com.hoan.orderservice.repository.OrderRepository;
import com.hoan.orderservice.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

  @Override
  public void createOrder(Order order) {
    Order newOrder = orderRepository.save(order);

    // Send event to Kafka
    OrderPlacedEvent event = OrderPlacedEvent.builder()
      .orderId(newOrder.getId())
      .userId(newOrder.getUserId())
      .total(newOrder.getTotal())
      .build();

    kafkaTemplate.send("order-topic", event);
    log.info("Send Kafka event: {}", event);
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
