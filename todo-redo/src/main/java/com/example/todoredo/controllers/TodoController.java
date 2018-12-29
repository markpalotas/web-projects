package com.example.todoredo.controllers;

import com.example.todoredo.models.Assignee;
import com.example.todoredo.models.Todo;
import com.example.todoredo.services.AssigneeService;
import com.example.todoredo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequestMapping("/todo")
public class TodoController {
  TodoService todoService;
  AssigneeService assigneeService;

  @Autowired
  public TodoController(TodoService todoService, AssigneeService assigneeService) {
    this.todoService = todoService;
    this.assigneeService = assigneeService;
  }

  @GetMapping({"/", "/list"})
  public String list(Model model,
                     @RequestParam(name = "isActive", required = false) Boolean isActive,
                     @RequestParam(name = "searchText", required = false) String searchText) {
    if (isActive == null && searchText == null) {
      model.addAttribute("todos", todoService.findAll());
    } else if (searchText == null) {
      model.addAttribute("todos", todoService.findAllActive(isActive));
    } else if (isActive == null) {
      model.addAttribute("todos", todoService.findAllBySearchString(searchText));
    }
    return "todolist";
  }

  @GetMapping("/add")
  public String viewAdd() {
    return "todoadd";
  }

  @PostMapping("/add")
  public String addTodo(@ModelAttribute Todo todo) {
    todoService.saveTodo(todo);
    return "redirect:/todo/";
  }

  @PostMapping("/delete")
  public String deleteTodo(@ModelAttribute Todo todo) {
    todoService.deleteTodo(todo);
    return "redirect:/todo/";
  }

  @GetMapping("/{id}/edit")
  public String showEdit(@PathVariable Long id, Model model) {
    model.addAttribute("todo", todoService.findTodoByID(id).get());
    model.addAttribute("assignees", assigneeService.getAllAssignees());
    return "edit";
  }

  @PostMapping("/{id}/edit")
  public String editTodo(@ModelAttribute Todo todo,
                         @RequestParam(name = "name") String name) {
    //TODO impl unassignment
    todo.setAssignee(assigneeService.findAssigneeByName(name));
    todoService.saveTodo(todo);
    return "redirect:/todo/";
  }

  @GetMapping("/dueDate")
  public String filterDueDate(@RequestParam(name = "dueDate") String dueDate,
                              Model model) {
    //TODO convert date from string
    model.addAttribute("todos", todoService.findByDueDate(dueDate));
    return "todolist";
  }

  @GetMapping("/creationDate")
  public String filterCreationDate(@RequestParam(name = "creationDate") String date,
                                   Model model) {
    model.addAttribute("todos", todoService.findByDateAdded(todoService.convertDate(date)));
    return "todolist";
  }

  @GetMapping("/showByAssignee")
  public String filterByAssignee(@RequestParam(name = "name") String name,
                                 Model model) {

    model.addAttribute("todos", todoService.findByAssigneeName(name));
    return "todoList";
  }
}
