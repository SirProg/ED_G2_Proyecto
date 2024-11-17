package com.example.dsproyect_p1.data.model;

import com.example.dsproyect_p1.data.structures.*;
import java.util.List;

public class Company extends Contact {
  private final String name;
  private final String description;

  public Company(
      UUID id,
      String name,
      String description,
      String residencyCountry,
      List<Telephone> telephones,
      List<Address> addresses,
      List<Email> emails,
      List<EventDate> eventDates,
      List<Contact> associateContacts,
      List<SocialMediaAccount> socialMediaAccounts) {
    super(
        id,
        residencyCountry,
        telephones,
        addresses,
        emails,
        eventDates,
        associateContacts,
        socialMediaAccounts);
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("First name cannot be null or empty");
    }
    if (description == null || description.isEmpty()) {
      throw new IllegalArgumentException("First name cannot be null or empty");
    }
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public Company copyWith(
      String name,
      String description,
      String residencyCountry,
      List<Telephone> newTelephones,
      List<Address> newAddresses,
      List<Email> newEmails,
      List<EventDate> newEventDates,
      List<Contact> newAssociateContacts,
      List<SocialMediaAccount> newSocialMediaAccounts) {

    return new Company(
        getId(),
        name != null ? name : this.name,
        description != null ? description : this.description,
        residencyCountry != null ? residencyCountry : getResidencyCountry(),
        newTelephones != null ? new CustomArrayList<>(newTelephones) : getTelephones(),
        newAddresses != null ? new CustomArrayList<>(newAddresses) : getAddresses(),
        newEmails != null ? new CustomArrayList<>(newEmails) : getEmails(),
        newEventDates != null ? new CustomArrayList<>(newEventDates) : getEventDates(),
        newAssociateContacts != null
            ? new CustomArrayList<>(newAssociateContacts)
            : getAssociateContacts(),
        newSocialMediaAccounts != null
            ? new CustomArrayList<>(newSocialMediaAccounts)
            : getSocialMediaAccounts());
  }
}
