package com.example.dsproyect_p1.data.model;

public class Company extends Contact {
  private String name;
  private String description;

  public Company(String name, String description) {
    super();
    this.name = name;
    this.description = description;
  }

  public String getName() {
    return name;
  }
}
