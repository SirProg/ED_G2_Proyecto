package com.example.dsproyect_p1.data.model;

import com.example.dsproyect_p1.data.structures.*;
import java.util.List;
import java.util.UUID;

public class Person extends Contact {
  private final String firstName;
  private final String lastName;

  public Person(
      UUID id,
      String firstName,
      String lastName,
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
    if (firstName == null || firstName.isEmpty()) {
      throw new IllegalArgumentException("First name cannot be null or empty");
    }
    if (lastName == null || lastName.isEmpty()) {
      throw new IllegalArgumentException("Last name cannot be null or empty");
    }
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public Person copyWith(
      String firstName,
      String lastName,
      String residencyCountry,
      List<Telephone> newTelephones,
      List<Address> newAddresses,
      List<Email> newEmails,
      List<EventDate> newEventDates,
      List<Contact> newAssociateContacts,
      List<SocialMediaAccount> newSocialMediaAccounts) {

    return new Person(
        getId(),
        firstName != null ? firstName : this.firstName,
        lastName != null ? lastName : this.lastName,
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
