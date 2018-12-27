package com.example.todoredo;

import com.example.todoredo.models.Todo;
import com.example.todoredo.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoRedoApplication implements CommandLineRunner {

  @Autowired
  TodoRepository todoRepository;

  public static void main(String[] args) {
    SpringApplication.run(TodoRedoApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    todoRepository.save(new Todo("Clean the room"));
    todoRepository.save(new Todo("Clean the fox"));
    todoRepository.save(new Todo("Clean the table"));

    Todo todo1 = new Todo("Test 1");
    todo1.setDone(true);
    todo1.setUrgent(true);
    todoRepository.save(todo1);
  }
}

