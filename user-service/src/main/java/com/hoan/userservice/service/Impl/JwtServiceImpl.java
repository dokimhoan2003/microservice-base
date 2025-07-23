package com.hoan.userservice.service.Impl;

import com.hoan.userservice.model.User;
import com.hoan.userservice.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService {

  private static final String SECRET_KEY = "6vSVaHaUwouBFAIj7DtY+kTCA/fzqfGdlwoEQ1IvnoQ=";

  @Override
  public String generateToken(User user) {
    return Jwts.builder()
      .setSubject(user.getEmail())
      .claim("userId", user.getId())
      .setIssuedAt(new Date())
      .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 ngày
      .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
      .compact();
  }

  @Override
  public Claims extractClaims(String token) {
    return Jwts.parserBuilder()
      .setSigningKey(SECRET_KEY.getBytes())
      .build()
      .parseClaimsJws(token)
      .getBody();
  }

  @Override
  public boolean isTokenValid(String token) {
    return extractClaims(token).getExpiration().after(new Date());
  }

  @Override
  public Long extractUserId(String token) {
    return extractClaims(token).get("userId", Long.class);
  }
}
