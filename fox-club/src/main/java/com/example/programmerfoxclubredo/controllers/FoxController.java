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

  @GetMapping("/{id}/nutritionStore")
  public String displayNutrition(@RequestParam(name = "name") String foxName,
                                 @PathVariable Long id,
                                 Model model) {
    model.addAttribute("fox", foxService.findFox(foxName).get());
    model.addAttribute("userId", id);
    return "nutrition";
  }

  @PostMapping("/{id}/nutritionStore")
  public String changeNutrition(@ModelAttribute("food") String food,
                                @ModelAttribute("drink") String drink,
                                @RequestParam(name = "name") String foxName,
                                @PathVariable Long id,
                                RedirectAttributes attributes) {
    System.out.println(id);
    foxService.findFox(foxName).get().setFood(food);
    foxService.findFox(foxName).get().setDrink(drink);
    foxService.addFox(foxService.findFox(foxName).get());
    attributes.addAttribute("name", foxName);
    return "redirect:/" + id;
  }

  @GetMapping("/{id}/trickCenter")
  public String displayTrickCenter(@RequestParam(name = "name") String userName,
                                   @PathVariable Long id,
                                   Model model) {
    model.addAttribute("fox", foxService.findFox(userName).get());
    model.addAttribute("tricks", foxService.getAvailableTricks());
    model.addAttribute("userId", id);
    return "tricks";
  }

  @PostMapping("{id}/trickCenter")
  public String updateTricks(@RequestParam(name = "name") String foxName,
                             @ModelAttribute(name = "trick") String trick,
                             @PathVariable Long id,
                             RedirectAttributes attributes) {
    System.out.println(trick);
    Fox fox = foxService.findFox(foxName).get();
    if (!foxService.isDuplicateTrick(fox, trick)) {
      fox.addTrick(trick);
      foxService.addFox(fox);
    }
    attributes.addAttribute("name", foxName);
    return "redirect:/" + id;
  }
}
