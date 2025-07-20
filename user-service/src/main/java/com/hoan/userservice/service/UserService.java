package com.hoan.userservice.service;


import com.hoan.userservice.dto.UserDto;
import com.hoan.userservice.model.User;
import java.util.List;

public interface UserService {
  void createUser(User user);
  List<User> getUsers();
  UserDto getUserById(Long id);
}
