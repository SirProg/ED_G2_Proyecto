package com.example.dsproyect_p1.data.model;

public class Address {
  private final String label;
  private final String description;

  public Address(final String label, final String description) {
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
