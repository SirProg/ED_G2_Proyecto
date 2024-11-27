package com.example.dsproyect_p1.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Email implements Parcelable {
  private final String label;
  private final String email;

  public Email(final String label, final String email) {
    this.label = label;
    this.email = email;
  }

  public String getLabel() {
    return label;
  }

  public String getEmail() {
    return email;
  }

  protected Email(Parcel parcel) {
    label = parcel.readString();
    email = parcel.readString();
  }

  public static final Creator<Email> CREATOR = new Creator<Email>() {
    @Override
    public Email createFromParcel(Parcel parcel) {
      return new Email(parcel);
    }

    @Override
    public Email[] newArray(int i) {
      return new Email[i];
    }
  };

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(@NonNull Parcel parcel, int i) {
    parcel.writeString(label);
    parcel.writeString(email);
  }
}
