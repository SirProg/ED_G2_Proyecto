package com.example.dsproyect_p1.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.time.LocalDate;

public class EventDate implements Parcelable {
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

  protected EventDate(Parcel parcel) {
    label = parcel.readString();
    String dateString = parcel.readString();
    date = (dateString != null) ? LocalDate.parse(dateString) : null;
  }

  public static final Creator<EventDate> CREATOR = new Creator<EventDate>() {
    @Override
    public EventDate createFromParcel(Parcel parcel) {
      return new EventDate(parcel);
    }

    @Override
    public EventDate[] newArray(int i) {
      return new EventDate[i];
    }
  };

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(@NonNull Parcel parcel, int i) {
    parcel.writeString(label);
    parcel.writeString(date != null ? date.toString() : null);
  }
}
