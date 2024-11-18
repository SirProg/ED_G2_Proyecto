package com.example.dsproyect_p1.data.model;

public class Email {
  private final String label;
  private final String email;

  public Email(final String label, final String email) {
    this.label = label;
    this.email = email;
  }

  public String getLabel() {
    return label;
  }

  public String getEmail() {
    return email;
  }
}
