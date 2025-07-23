package com.hoan.userservice.service.Impl;

import com.hoan.userservice.dto.UserDto;
import com.hoan.userservice.model.User;
import com.hoan.userservice.repository.UserRepository;
import com.hoan.userservice.service.UserService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;


  @Override
  @CacheEvict(value = "allUsers", allEntries = true)
  public void createUser(User user) {
    userRepository.save(user);
  }

  @Override
  @Cacheable("allUsers")
  public List<User> getUsers() {
    System.out.println("---Query DB...---");
    return userRepository.findAll();
  }

  @Override
  public UserDto getUserById(Long id) {
    User user = userRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("User not found"));
    return UserDto.builder()
      .id(user.getId())
      .name(user.getName())
      .email(user.getEmail()).build();
  }

  @Override
  public User getByEmail(String email) {
    return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
  }
}
