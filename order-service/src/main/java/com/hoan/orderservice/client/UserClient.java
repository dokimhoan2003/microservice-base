package com.hoan.orderservice.client;

import com.hoan.orderservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {

  @GetMapping("api/v1/user/{id}")
  UserDto getUserById(@PathVariable Long id);

}
