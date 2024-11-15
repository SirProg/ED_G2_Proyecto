package com.example.dsproyect_p1.data.model;

import com.example.dsproyect_p1.data.structures.*;
import java.util.List;
import java.util.UUID;

public class Company {
  UUID id;
  String name;
  List<Telephone> telephones = new CustomArrayList<>();
  List<Address> addresses = new CustomArrayList<>();
  List<Email> emails = new CustomArrayList<>();

  public Company(String name) {
    this.id = UUID.randomUUID();
    this.name = name;
  }
}
