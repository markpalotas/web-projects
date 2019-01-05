package com.example.programmerfoxclubredo.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Fox {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  @ElementCollection
  private List<String> tricks;
  private String food;
  private String drink;

  @OneToOne(mappedBy = "fox", fetch = FetchType.EAGER)
  private User user;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Fox(String name) {
    this.name = name;
    this.tricks = new ArrayList<>();
    this.food = "Salad";
    this.drink = "Water";
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Fox() {
    this.tricks = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getTricks() {
    return tricks;
  }

  public void setTricks(List<String> tricks) {
    this.tricks = tricks;
  }

  public String getFood() {
    return food;
  }

  public void setFood(String food) {
    this.food = food;
  }

  public String getDrink() {
    return drink;
  }

  public void setDrink(String drink) {
    this.drink = drink;
  }

  public void addTrick(String trick) {
    tricks.add(trick);
  }
}
