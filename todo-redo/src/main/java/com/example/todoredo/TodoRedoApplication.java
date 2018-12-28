package com.example.todoredo;

import com.example.todoredo.models.Assignee;
import com.example.todoredo.models.Todo;
import com.example.todoredo.repositories.AssigneeRepository;
import com.example.todoredo.repositories.TodoRepository;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class TodoRedoApplication implements CommandLineRunner {

  @Autowired
  TodoRepository todoRepository;

  @Autowired
  AssigneeRepository assigneeRepository;

  public static void main(String[] args) {
    SpringApplication.run(TodoRedoApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    Assignee john = new Assignee("John", "john@doe.com");
    List<Todo> todos = Arrays.asList(new Todo("Fix the code."));
    john.setTodos(todos);
    //todoRepository.saveAll(todos);
    assigneeRepository.save(john);

    Assignee jane = new Assignee("Jane", "jane@doe.com");
    List<Todo> todos_1 = Arrays.asList(new Todo("Fix the code. Again."));
    john.setTodos(todos_1);
    assigneeRepository.save(jane);

    System.out.println(assigneeRepository.findAll());

    System.out.println(todoRepository.selectAssigneeEmails());

    todoRepository.save(new Todo("Clean the room"));
    todoRepository.save(new Todo("Clean the fox"));
    todoRepository.save(new Todo("Clean the table"));


  }
}

