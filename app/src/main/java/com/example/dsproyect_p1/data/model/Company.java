package com.example.dsproyect_p1.data.model;

import com.example.dsproyect_p1.data.structures.*;
import java.util.List;
import java.util.UUID;

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
      List<AssociateContact> associateContacts,
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
      throw new IllegalArgumentException("Name cannot be null or empty");
    }
    if (description == null || description.isEmpty()) {
      throw new IllegalArgumentException("Description cannot be null or empty");
    }
    this.name = name;
    this.description = description;
  }

  public String getName() {
    return name;
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
      List<AssociateContact> newAssociateContacts,
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
