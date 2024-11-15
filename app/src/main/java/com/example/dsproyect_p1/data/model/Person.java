package com.example.dsproyect_p1.data.model;

import com.example.dsproyect_p1.data.structures.*;
import java.util.List;
import java.util.UUID;

public class Person {
  private UUID id;
  private String firstName;
  private String lastName;
  private List<Telephone> telephones = new CustomArrayList<>();
  private List<Address> addresses = new CustomArrayList<>();
  private List<Email> emails = new CustomArrayList<>();

  public Person(final String firstName, final String lastName) {
    this.id = UUID.randomUUID();
    this.firstName = firstName;
    this.lastName = lastName;
  }
}
