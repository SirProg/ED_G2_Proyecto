package com.example.dsproyect_p1.data.model;

import java.time.LocalDate;

public class EventDate {

  private final String label;
  private final LocalDate date;

  public EventDate(final String label, final LocalDate date) {
    this.label = label;
    this.date = date;
  }
}