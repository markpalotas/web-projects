package com.example.todoredo.services;

import com.example.todoredo.models.Assignee;
import com.example.todoredo.models.Todo;
import com.example.todoredo.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
  TodoRepository todoRepository;

  @Autowired
  public TodoService(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  public List<Todo> findAll() {
    return (List<Todo>) todoRepository.findAll();
  }

  public List<Todo> findAllActive(Boolean isActive) {
    return todoRepository.findAllByDone(!isActive);
  }

  public void saveTodo(Todo todo) {
    todoRepository.save(todo);
  }

  public void deleteTodo(Todo todo) {
    todoRepository.delete(todo);
  }

  public Optional<Todo> findTodoByID(Long id) {
    return todoRepository.findById(id);
  }

  public List<Todo> findAllBySearchString(String searchText) {
    return todoRepository.findAllByMatchingTitle(searchText);
  }

}
