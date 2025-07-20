package com.hoan.orderservice.dto.response;

import com.hoan.orderservice.dto.UserDto;
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
public class OrderResponse {
  private Long orderId;
  private String product;
  private Double price;
  private UserDto user;
}
