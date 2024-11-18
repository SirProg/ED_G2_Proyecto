package com.example.dsproyect_p1.data.model;

public class AssociateContact {
  private final String name;
  private final String telephone;

  public AssociateContact(String name, String telephone) {
    this.name = name;
    this.telephone = telephone;
  }

  public String getName() {
    return name;
  }

  public String getTelephone() {
    return telephone;
  }
}
