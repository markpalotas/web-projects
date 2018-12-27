package com.example.programmerfoxclubredo.controllers;

import com.example.programmerfoxclubredo.services.FoxService;
import com.example.programmerfoxclubredo.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {
  MainService mainService;
  FoxService foxService;

  @Autowired
  public MainController(MainService mainService, FoxService foxService) {
    this.mainService = mainService;
    this.foxService = foxService;
  }

  @GetMapping("/")
  public String home(@RequestParam(name = "name", required = false) String userName,
                     Model model) {
    if (mainService.userNameEmpty(userName)) {
      return "login";
    }
    model.addAttribute("fox", foxService.findFox(userName).get());
    return "index";
  }

  @GetMapping("/login")
  public String showLogin() {
    return "login";
  }

  @PostMapping("/login")
  public String login(@RequestParam(name = "name") String userName,
                      RedirectAttributes attributes) {
    attributes.addAttribute("name", userName);
    if (!foxService.findFox(userName).isPresent()) {
      foxService.addFox(userName);
    }
    return "redirect:/";
  }
}
