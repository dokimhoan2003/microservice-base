package com.hoan.userservice.service;

import com.hoan.userservice.model.User;
import io.jsonwebtoken.Claims;

public interface JwtService {
  String generateToken(User user);
  Claims extractClaims(String token);
  boolean isTokenValid(String token);
  Long extractUserId(String token);
}
