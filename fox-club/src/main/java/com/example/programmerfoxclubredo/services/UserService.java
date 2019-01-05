package com.example.programmerfoxclubredo.services;

import com.example.programmerfoxclubredo.models.User;
import com.example.programmerfoxclubredo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
  UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public boolean validatePassword(User user) {
    if (user.getPassword1().trim().isEmpty() || user.getPassword2().trim().isEmpty()) {
      return false;
    } else {
      return user.getPassword1().equals(user.getPassword2());
    }
  }

  public void saveUser(User user) {
    userRepository.save(user);
  }

  public boolean isRegisteredUser(User user) {
    return userRepository.findUserByUsername(user.getUsername()).isPresent();
  }

  public Long findUserId(String userName) {
    return userRepository.findUserByUsername(userName).get().getId();
  }

  public Optional<User> findUserById(Long id) {
    return userRepository.findUserById(id);
  }
}
