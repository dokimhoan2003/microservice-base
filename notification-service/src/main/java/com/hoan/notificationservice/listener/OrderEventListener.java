package com.hoan.notificationservice.listener;


import com.hoan.notificationservice.event.OrderPlacedEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class OrderEventListener {
  @KafkaListener(topics = "order-topic", groupId = "notification-group", containerFactory = "orderPlacedEventListenerFactory")
  public void handleOrderEvent(OrderPlacedEvent event) {
    log.warn("Receive Kafka event: {}", event);
  }
}
