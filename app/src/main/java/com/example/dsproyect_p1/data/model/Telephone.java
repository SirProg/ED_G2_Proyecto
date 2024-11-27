package com.example.dsproyect_p1.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Telephone implements Parcelable {
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

  protected Telephone(Parcel parcel) {
    label = parcel.readString();
    number = parcel.readString();
  }

  public static final Creator<Telephone> CREATOR = new Creator<Telephone>() {
    @Override
    public Telephone createFromParcel(Parcel parcel) {
      return new Telephone(parcel);
    }

    @Override
    public Telephone[] newArray(int i) {
      return new Telephone[i];
    }
  };

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(@NonNull Parcel parcel, int i) {
    parcel.writeString(label);
    parcel.writeString(number);
  }
}
