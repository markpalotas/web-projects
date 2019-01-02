package com.example.programmerfoxclubredo.controllers;

import com.example.programmerfoxclubredo.models.User;
import com.example.programmerfoxclubredo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
  UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/register")
  public String showReg(@RequestParam(name = "error", required = false) String error,
                        Model model) {
    if (error == null) {
      return "register";
    } else {
      model.addAttribute("error", error);
      return "register";
    }
  }

  @PostMapping("/register")
  public String registerUser(@ModelAttribute User user, RedirectAttributes attributes) {
    if (userService.validatePassword(user)) {
      userService.saveUser(user);
      return "redirect:/";
    } else {
      attributes.addAttribute("error", "Passwords don't match!");
      return "redirect:/register";
    }

  }
}
