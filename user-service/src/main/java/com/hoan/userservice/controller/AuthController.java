package com.hoan.userservice.controller;

import com.hoan.userservice.model.User;
import com.hoan.userservice.service.JwtService;
import com.hoan.userservice.service.UserService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
  private final UserService userService;
  private final JwtService jwtService;
  private final BCryptPasswordEncoder passwordEncoder;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
    String email = body.get("email");
    String password = body.get("password");

    User user = userService.getByEmail(email);

    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
    }

    String token = jwtService.generateToken(user);
    return ResponseEntity.ok(Map.of("token", token));
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody Map<String, String> body) {
    String email = body.get("email");
    String password = body.get("password");
    String name = body.get("name");

    if (userService.getByEmail(email) != null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already in use");
    }

    User newUser = new User();
    newUser.setEmail(email);
    newUser.setPassword(passwordEncoder.encode(password));
    newUser.setName(name);

    userService.createUser(newUser);

    String token = jwtService.generateToken(newUser);
    return ResponseEntity.ok(Map.of("token", token));
  }
}
