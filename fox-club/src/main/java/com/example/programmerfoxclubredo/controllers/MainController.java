package com.example.programmerfoxclubredo.controllers;

import com.example.programmerfoxclubredo.models.Fox;
import com.example.programmerfoxclubredo.models.User;
import com.example.programmerfoxclubredo.services.FoxService;
import com.example.programmerfoxclubredo.services.MainService;
import com.example.programmerfoxclubredo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class MainController {
  MainService mainService;
  FoxService foxService;
  UserService userService;

  @Autowired
  public MainController(MainService mainService, FoxService foxService, UserService userService) {
    this.mainService = mainService;
    this.foxService = foxService;
    this.userService = userService;
  }

  //TODO Fix URLS with pathvariable hardcoded in htmls
  @GetMapping({"/", "/{id}"})
  public String home(@RequestParam(name = "name", required = false) String foxName,
                     @PathVariable Optional<Long> id,
                     Model model) {
    if (!id.isPresent()) {
      return "redirect:/user-login";
    }
    if (mainService.foxNameEmpty(foxName)) {
      model.addAttribute("userId", id.get());
      return "login";
    }
    model.addAttribute("fox", foxService.findFox(foxName).get());
    model.addAttribute("userId", id.get());
    return "index";
  }

  @PostMapping("/{id}/login")
  public String login(@RequestParam(name = "name") String foxName,
                      @PathVariable Long id,
                      RedirectAttributes attributes) {
    attributes.addAttribute("name", foxName);
    if (!foxService.findFox(foxName).isPresent()) {
      Fox fox = new Fox(foxName);
      foxService.addFox(fox);

      User user = userService.findUserById(id).get();
      user.setFox(fox);
      userService.saveUser(user);
    }
    return "redirect:/" + id;
  }
}
