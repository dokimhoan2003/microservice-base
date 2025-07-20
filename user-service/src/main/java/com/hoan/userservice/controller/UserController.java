package com.hoan.userservice.controller;

import com.hoan.userservice.dto.UserDto;
import com.hoan.userservice.model.User;
import com.hoan.userservice.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<Void> createUser(@RequestBody User user) {
    userService.createUser(user);
    return ResponseEntity.ok().build();
  }

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    return ResponseEntity.ok().body(userService.getUsers());
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
    UserDto userDto = userService.getUserById(id);
    return ResponseEntity.ok().body(userDto);
  }
}
