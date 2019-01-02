package com.example.programmerfoxclubredo.repositories;

import com.example.programmerfoxclubredo.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <User, Long>  {
}
