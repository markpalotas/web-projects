package com.example.programmerfoxclubredo.services;

import org.springframework.stereotype.Service;

@Service
public class MainService {

  public boolean userNameEmpty(String userName) {
    if (userName == null || userName.isEmpty()) {
      return true;
    }
    return false;
  }
}
