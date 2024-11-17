package com.example.dsproyect_p1.data.model;

import com.example.dsproyect_p1.data.structures.*;
import java.util.List;
import java.util.UUID;
import java.util.Collections;

public abstract class Contact {

  private UUID id;
  private String residencyCountry;
  private List<Telephone> telephones = new CustomArrayList<>();
  private List<Address> addresses = new CustomArrayList<>();
  private List<Email> emails = new CustomArrayList<>();
  private List<EventDate> eventDates = new CustomArrayList<>();
  private List<Contact> associateContacts = new CustomArrayList<>();
  private List<SocialMediaAccount> socialMediaAccounts = new CustomArrayList<>();

  public Contact() {
    this.id = UUID.randomUUID();
  }

   
  public UUID getId() {
    return id;
  }

  public String getResidencyCountry() {
    return residencyCountry;
  }

  public void setResidencyCountry(String residencyCountry) {
    this.residencyCountry = residencyCountry;
  }

  public List<Telephone> getTelephones() {
    return Collections.unmodifiableList(telephones);
  }

  public void addTelephone(Telephone telephone) {
    telephones.add(telephone);
  }

  public List<Address> getAddresses() {
    return Collections.unmodifiableList(addresses);
  }

  public void addAddress(Address address) {
    addresses.add(address);
  }

  public List<Email> getEmails() {
    return Collections.unmodifiableList(emails);
  }

  public void addEmail(Email email) {
    emails.add(email);
  }

  public List<EventDate> getEventDates() {
    return Collections.unmodifiableList(eventDates);
  }

  public void addEventDate(EventDate eventDate) {
    eventDates.add(eventDate);
  }

  public List<Contact> getAssociateContacts() {
    return Collections.unmodifiableList(associateContacts);
  }

  public void addAssociateContact(Contact contact) {
    associateContacts.add(contact);
  }

  public List<SocialMediaAccount> getSocialMediaAccounts() {
    return Collections.unmodifiableList(socialMediaAccounts);
  }

  public void addSocialMediaAccount(SocialMediaAccount socialMediaAccount) {
    socialMediaAccounts.add(socialMediaAccount);
  }
}
