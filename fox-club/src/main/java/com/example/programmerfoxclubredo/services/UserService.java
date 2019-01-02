package com.example.programmerfoxclubredo.services;

import com.example.programmerfoxclubredo.models.User;
import com.example.programmerfoxclubredo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public boolean validatePassword(User user) {
    return user.getPassword1().equals(user.getPassword2());
  }

  public void saveUser(User user) {
    userRepository.save(user);
  }
}
