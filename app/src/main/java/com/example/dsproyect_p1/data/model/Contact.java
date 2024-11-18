package com.example.dsproyect_p1.data.model;

import com.example.dsproyect_p1.data.structures.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public abstract class Contact {

  private final UUID id;
  private final String residencyCountry;
  private final List<Telephone> telephones;
  private final List<Address> addresses;
  private final List<Email> emails;
  private final List<EventDate> eventDates;
  private final List<AssociateContact> associateContacts;
  private final List<SocialMediaAccount> socialMediaAccounts;

  public Contact(
      UUID id,
      String residencyCountry,
      List<Telephone> telephones,
      List<Address> addresses,
      List<Email> emails,
      List<EventDate> eventDates,
      List<AssociateContact> associateContacts,
      List<SocialMediaAccount> socialMediaAccounts) {
    this.id = id != null ? id : UUID.randomUUID();
    this.residencyCountry = residencyCountry != null ? residencyCountry : "";
    this.telephones = telephones != null ? new CustomArrayList<>(telephones) : new CustomArrayList<>();
    this.addresses = addresses != null ? new CustomArrayList<>(addresses) : new CustomArrayList<>();
    this.emails = emails != null ? new CustomArrayList<>(emails) : new CustomArrayList<>();
    this.eventDates = eventDates != null ? new CustomArrayList<>(eventDates) : new CustomArrayList<>();
    this.associateContacts = associateContacts != null
        ? new CustomArrayList<>(associateContacts)
        : new CustomArrayList<>();
    this.socialMediaAccounts = socialMediaAccounts != null
        ? new CustomArrayList<>(socialMediaAccounts)
        : new CustomArrayList<>();
  }

  public UUID getId() {
    return id;
  }

  public String getResidencyCountry() {
    return residencyCountry;
  }

  public List<Telephone> getTelephones() {
    return Collections.unmodifiableList(telephones);
  }

  public List<Address> getAddresses() {
    return Collections.unmodifiableList(addresses);
  }

  public List<Email> getEmails() {
    return Collections.unmodifiableList(emails);
  }

  public List<EventDate> getEventDates() {
    return Collections.unmodifiableList(eventDates);
  }

  public List<AssociateContact> getAssociateContacts() {
    return Collections.unmodifiableList(associateContacts);
  }

  public List<SocialMediaAccount> getSocialMediaAccounts() {
    return Collections.unmodifiableList(socialMediaAccounts);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Contact))
      return false;
    Contact contact = (Contact) o;
    return id.equals(contact.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
