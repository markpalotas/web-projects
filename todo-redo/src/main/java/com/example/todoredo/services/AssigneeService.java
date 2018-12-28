package com.example.todoredo.services;

import com.example.todoredo.models.Assignee;
import com.example.todoredo.repositories.AssigneeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssigneeService {
  AssigneeRepository assigneeRepository;

  @Autowired
  public AssigneeService(AssigneeRepository assigneeRepository) {
    this.assigneeRepository = assigneeRepository;
  }

  public List<Assignee> getAllAssignees() {
    return (List<Assignee>) assigneeRepository.findAll();
  }

  public void deleteAssignee(Assignee assignee) {
    assigneeRepository.delete(assignee);
  }

  public Optional<Assignee> findAssigneeById(Long id) {
    return assigneeRepository.findById(id);
  }

  public void saveAssignee(Assignee assignee) {
    assigneeRepository.save(assignee);
  }

  public Assignee findAssigneeByName(String name) {
    return assigneeRepository.findAssigneeByName(name);
  }
}
