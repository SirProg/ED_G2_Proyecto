package com.example.dsproyect_p1.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class AssociateContact implements Parcelable {
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

  protected AssociateContact(Parcel parcel) {
    name = parcel.readString();
    telephone = parcel.readString();
  }

  public static final Creator<AssociateContact> CREATOR = new Creator<AssociateContact>() {
    @Override
    public AssociateContact createFromParcel(Parcel parcel) {
      return new AssociateContact(parcel);
    }

    @Override
    public AssociateContact[] newArray(int i) {
      return new AssociateContact[i];
    }
  };

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(@NonNull Parcel parcel, int i) {
    parcel.writeString(name);
    parcel.writeString(telephone);
  }
}
