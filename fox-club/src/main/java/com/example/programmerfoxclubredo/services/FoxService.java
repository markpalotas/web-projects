package com.example.programmerfoxclubredo.services;

import com.example.programmerfoxclubredo.models.Fox;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FoxService {
  private List<Fox> foxes;
  private List<String> availableTricks;

  public FoxService() {
    this.foxes = new ArrayList<>();
    this.availableTricks = new ArrayList<>();
    availableTricks.add("Write HTML");
    availableTricks.add("Write XML");
    availableTricks.add("Write Pyhton");
    availableTricks.add("Write sum code");
  }

  public void addFox(String name) {
    foxes.add(new Fox(name));
  }

  public Optional<Fox> findFox(String name) {
    for (Fox fox : foxes) {
      if (fox.getName().equals(name)) {
        return Optional.of(fox);
      }
    }
    return Optional.empty();
  }

  public int foxesSize() {
    return foxes.size();
  }

  public boolean isDuplicateTrick(Fox fox, String trick) {
    return fox.getTricks().contains(trick);
  }

  public List<String> getAvailableTricks() {
    return availableTricks;
  }
}
