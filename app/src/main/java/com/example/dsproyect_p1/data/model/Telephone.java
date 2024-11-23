package com.example.dsproyect_p1.data.model;

public class Telephone {
  private String label;
  private String number;

  public Telephone(String label, String number) {
    this.label = label;
    this.number = number;
  }

  public String getLabel() {
    return label;
  }

  public String getNumber() {
    return number;
  }
}
