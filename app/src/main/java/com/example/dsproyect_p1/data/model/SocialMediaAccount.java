package com.example.dsproyect_p1.data.model;

public class SocialMediaAccount {

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
}
