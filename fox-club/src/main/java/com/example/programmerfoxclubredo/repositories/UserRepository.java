package com.example.programmerfoxclubredo.repositories;

import com.example.programmerfoxclubredo.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository <User, Long>  {
  Optional<User> findUserByUsername(String userName);
  Optional<User> findUserById(Long id);
}
