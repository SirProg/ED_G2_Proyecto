package com.example.dsproyect_p1.data.model;

import java.time.LocalDate;

public class EventDate {
  private String label;
  private LocalDate date;

  public EventDate(final String label, final LocalDate date) {
    this.label = label;
    this.date = date;
  }

  public String getLabel() {
    return label;
  }

  public LocalDate getDate() {
    return date;
  }
}
