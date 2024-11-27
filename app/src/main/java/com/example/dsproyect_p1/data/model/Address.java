package com.example.dsproyect_p1.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Address implements Parcelable {
  private String label;
  private String description;

  public Address(String label, String description) {
    this.label = label;
    this.description = description;
  }

  protected  Address(Parcel in) {
    label = in.readString();
    description = in.readString();
  }

  public String getLabel() {
    return label;
  }

  public String getDescription() {
    return description;
  }

  public static final Creator<Address> CREATOR = new Creator<Address>() {
    @Override
    public Address createFromParcel(Parcel in) {
      return new Address(in);
    }

    @Override
    public Address[] newArray(int size) {
      return new Address[size];
    }
  };

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(@NonNull Parcel parcel, int i) {
    parcel.writeString(label);
    parcel.writeString(description);
  }
}
