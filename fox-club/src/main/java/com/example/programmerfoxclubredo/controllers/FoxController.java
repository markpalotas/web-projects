package com.example.programmerfoxclubredo.controllers;

import com.example.programmerfoxclubredo.models.Fox;
import com.example.programmerfoxclubredo.services.FoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FoxController {
  FoxService foxService;

  @Autowired
  public FoxController(FoxService foxService) {
    this.foxService = foxService;
  }

  @GetMapping("/nutritionStore")
  public String displayNutrition(@RequestParam(name = "name") String userName,
                                 Model model) {
    model.addAttribute("fox", foxService.findFox(userName).get());
    return "nutrition";
  }

  @PostMapping("/nutritionStore")
  public String changeNutrition(@ModelAttribute("food") String food,
                                @ModelAttribute("drink") String drink,
                                @RequestParam(name = "name") String userName,
                                RedirectAttributes attributes) {
    foxService.findFox(userName).get().setFood(food);
    foxService.findFox(userName).get().setDrink(drink);
    attributes.addAttribute("name", userName);
    return "redirect:/";
  }

  @GetMapping("/trickCenter")
  public String displayTrickCenter(@RequestParam(name = "name") String userName,
                                   Model model) {
    model.addAttribute("fox", foxService.findFox(userName).get());
    model.addAttribute("tricks", foxService.getAvailableTricks());
    return "tricks";
  }

  @PostMapping("/trickCenter")
  public String updateTricks(@RequestParam(name = "name") String userName,
                             @ModelAttribute(name = "trick") String trick,
                             RedirectAttributes attributes) {
    Fox fox = foxService.findFox(userName).get();
    if (!foxService.isDuplicateTrick(fox, trick)) {
      fox.addTrick(trick);
    }
    attributes.addAttribute("name", userName);
    return "redirect:/";
  }
}
