package com.example.todoredo.repositories;

import com.example.todoredo.models.Todo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Long> {
  List<Todo> findAllByDone(Boolean isActive);

  @Query("select t from Todo t where t.title like %?1%")
  List<Todo> findAllByMatchingTitle(String s);
}
