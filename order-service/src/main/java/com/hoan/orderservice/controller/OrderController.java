package com.hoan.orderservice.controller;

import com.hoan.orderservice.client.UserClient;
import com.hoan.orderservice.dto.UserDto;
import com.hoan.orderservice.dto.response.OrderResponse;
import com.hoan.orderservice.model.Order;
import com.hoan.orderservice.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  private final UserClient userClient;

  @PostMapping
  public ResponseEntity<Void> createOrder(@RequestBody Order order) {
    orderService.createOrder(order);
    return ResponseEntity.ok().build();
  }

  @GetMapping
  public ResponseEntity<List<Order>> getAllOrders() {
    return ResponseEntity.ok().body(orderService.getOrders());
  }

  @GetMapping("/{id}")
  public ResponseEntity<OrderResponse> getOrderById(@PathVariable("id") Long id) {
    Order order = orderService.getOrderById(id);

    UserDto user = userClient.getUserById(order.getUserId());

    OrderResponse res = OrderResponse.builder()
      .orderId(order.getId())
      .product(order.getProduct())
      .price(order.getPrice())
      .user(user)
      .build();
    return ResponseEntity.ok().body(res);
  }

}
