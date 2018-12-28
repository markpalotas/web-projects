package com.example.todoredo.controllers;

import com.example.todoredo.models.Assignee;
import com.example.todoredo.services.AssigneeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/todo")
public class AssigneeController {
  AssigneeService assigneeService;

  @Autowired
  public AssigneeController(AssigneeService assigneeService) {
    this.assigneeService = assigneeService;
  }

  @GetMapping("/assignees")
  public String showAssignees(Model model) {
    model.addAttribute("assignees", assigneeService.getAllAssignees());
    return "assignees";
  }

  @PostMapping("/assignees/delete")
  public String deleteAssignee(@ModelAttribute Assignee assignee) {
    assigneeService.deleteAssignee(assignee);
    return "redirect:/todo/assignees";
  }

  @GetMapping("/assignees/{id}/edit")
  public String showEditForAssignee(@PathVariable Long id, Model model) {
    model.addAttribute("assignee", assigneeService.findAssigneeById(id).get());
    return "edit-assignee";
  }

  @PostMapping("/assignees/{id}/edit")
  public String editAssignee(@ModelAttribute Assignee assignee) {
    assigneeService.saveAssignee(assignee);
    return "redirect:/todo/assignees";
  }

  @GetMapping("/assignees/add")
  public String showAddAssignee() {
    return "add-assignee";
  }

  @PostMapping("/assignee/add")
  public String addAssignee(@ModelAttribute Assignee assignee) {
    assigneeService.saveAssignee(assignee);
    return "redirect:/todo/assignees";
  }
}
