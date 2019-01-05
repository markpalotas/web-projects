package com.example.programmerfoxclubredo.services;

import org.springframework.stereotype.Service;

@Service
public class MainService {

  public boolean foxNameEmpty(String foxName) {
    if (foxName == null || foxName.isEmpty()) {
      return true;
    }
    return false;
  }
}
