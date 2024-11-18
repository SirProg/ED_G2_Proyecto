package com.example.dsproyect_p1.data.model;

import android.util.Log;

import com.example.dsproyect_p1.data.structures.*;
import java.util.List;
import java.util.UUID;
import java.io.*;

public class Company {
  UUID id;
  String name;
  List<Telephone> telephones = new CustomArrayList<>();
  List<Address> addresses = new CustomArrayList<>();
  List<Email> emails = new CustomArrayList<>();
  private static final String fileName = "Company.ser";

  public Company(String name) {
    this.id = UUID.randomUUID();
    this.name = name;
  }


}
