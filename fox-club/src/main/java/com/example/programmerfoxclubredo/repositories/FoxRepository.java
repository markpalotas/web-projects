package com.example.programmerfoxclubredo.repositories;

import com.example.programmerfoxclubredo.models.Fox;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FoxRepository extends CrudRepository<Fox, Long> {
  Optional<Fox> findByName(String name);
}
