package com.example.programmerfoxclubredo.services;

import com.example.programmerfoxclubredo.models.Fox;
import com.example.programmerfoxclubredo.repositories.FoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FoxService {
  private List<Fox> foxes;
  private List<String> availableTricks;
  @Autowired
  FoxRepository foxRepository;


  public FoxService() {
    this.availableTricks = new ArrayList<>();
    availableTricks.add("Write HTML");
    availableTricks.add("Write XML");
    availableTricks.add("Write Pyhton");
    availableTricks.add("Write sum code");
  }

  public void addFox(Fox fox) {
    foxRepository.save(fox);
  }

  public Optional<Fox> findFox(String name) {
    return foxRepository.findByName(name);
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
