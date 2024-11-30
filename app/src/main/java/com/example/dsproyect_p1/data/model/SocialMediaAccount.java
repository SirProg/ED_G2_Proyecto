package com.example.dsproyect_p1.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class SocialMediaAccount implements Parcelable {

  private SocialMedia socialMedia;
  private String user;

  public SocialMediaAccount(final SocialMedia socialMedia, final String user) {
    this.socialMedia = socialMedia;
    this.user = user;
  }

  public SocialMedia getSocialMedia() {
    return socialMedia;
  }

  public String getUser() {
    return user;
  }

  protected SocialMediaAccount(Parcel parcel) {
    socialMedia = SocialMedia.valueOf(parcel.readString());
    user = parcel.readString();
  }

  public static final Creator<SocialMediaAccount> CREATOR = new Creator<SocialMediaAccount>() {
    @Override
    public SocialMediaAccount createFromParcel(Parcel parcel) {
      return new SocialMediaAccount(parcel);
    }

    @Override
    public SocialMediaAccount[] newArray(int i) {
      return new SocialMediaAccount[i];
    }
  };

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(@NonNull Parcel parcel, int i) {
    parcel.writeString(socialMedia.name());
    parcel.writeString(user);
  }
}
