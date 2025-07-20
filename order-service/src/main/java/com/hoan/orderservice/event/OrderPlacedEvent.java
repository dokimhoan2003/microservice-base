package com.hoan.orderservice.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderPlacedEvent {
  private Long orderId;
  private Long userId;
  private Double total;
}
