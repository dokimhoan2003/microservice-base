package com.hoan.notificationservice.event;

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

  @Override
  public String toString() {
    return "OrderPlacedEvent{" +
      "orderId=" + orderId +
      ", userId=" + userId +
      ", total=" + total +
      '}';
  }
}