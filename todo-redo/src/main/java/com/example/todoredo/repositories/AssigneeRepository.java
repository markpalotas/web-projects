package com.example.todoredo.repositories;

import com.example.todoredo.models.Assignee;
import org.springframework.data.repository.CrudRepository;

public interface AssigneeRepository extends CrudRepository <Assignee, Long> {
  Assignee findAssigneeByName(String name);
}
