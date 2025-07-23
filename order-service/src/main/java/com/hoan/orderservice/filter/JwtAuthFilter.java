package com.hoan.orderservice.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthFilter extends OncePerRequestFilter {

  private static final String SECRET_KEY = "6vSVaHaUwouBFAIj7DtY+kTCA/fzqfGdlwoEQ1IvnoQ=";

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
    @NonNull HttpServletResponse response,
    @NonNull FilterChain filterChain) throws ServletException, IOException {

    String authHeader = request.getHeader("Authorization");

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    String token = authHeader.substring(7);
    Claims claims = Jwts.parserBuilder()
      .setSigningKey(SECRET_KEY.getBytes())
      .build()
      .parseClaimsJws(token)
      .getBody();

    Long userId = claims.get("userId", Long.class);

    UsernamePasswordAuthenticationToken auth =
      new UsernamePasswordAuthenticationToken(userId, null, List.of());

    SecurityContextHolder.getContext().setAuthentication(auth);
    filterChain.doFilter(request, response);
  }
}
