package com.example.dsproyect_p1.data.model;

public class Person extends Contact {
  private String firstName;
  private String lastName;

  public Person(final String firstName, final String lastName) {
    super();
    this.firstName = firstName;
    this.lastName = lastName;
  }
}
