package com.example.todoredo.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Todo {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String title;
  private boolean urgent;
  private boolean done;
  @Temporal(TemporalType.DATE)
  private Date dateAdded;
  private String dueDate;

  @ManyToOne
  private Assignee assignee;

  public Todo(String title) {
    this();
    this.title = title;
  }

  public Todo() {
    this.urgent = false;
    this.done = false;
    this.dateAdded = new Date();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public boolean getUrgent() {
    return urgent;
  }

  public void setUrgent(boolean urgent) {
    this.urgent = urgent;
  }

  public boolean getDone() {
    return done;
  }

  public void setDone(boolean done) {
    this.done = done;
  }

  public Date getDateAdded() {
    return dateAdded;
  }

  public void setDateAdded(Date dateAdded) {
    this.dateAdded = dateAdded;
  }

  public Assignee getAssignee() {
    return assignee;
  }

  public void setAssignee(Assignee assignee) {
    this.assignee = assignee;
  }

  public String getDueDate() {
    return dueDate;
  }

  public void setDueDate(String dueDate) {
    this.dueDate = dueDate;
  }

  @Override
  public String toString() {
    return "Todo{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", urgent=" + urgent +
            ", done=" + done +
            ", dateAdded=" + dateAdded +
            ", assignee=" + assignee +
            '}';
  }
}
