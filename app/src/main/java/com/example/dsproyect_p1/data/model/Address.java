package com.example.dsproyect_p1.data.model;

public class Address {
  private String label;
  private String description;

  public Address(String label, String description) {
    this.label = label;
    this.description = description;
  }

  public String getLabel() {
    return label;
  }

  public String getDescription() {
    return description;
  }
}
