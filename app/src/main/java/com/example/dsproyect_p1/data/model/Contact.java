package com.example.dsproyect_p1.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.dsproyect_p1.data.structures.CustomArrayList;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Contact implements Parcelable {
  private final UUID id;
  private final ContactType contactType;
  private final String name;
  private final String residencyCountry;
  private final List<Telephone> telephones;
  private final List<Address> addresses;
  private final List<Email> emails;
  private final List<EventDate> eventDates;
  private final List<AssociateContact> associateContacts;
  private final List<SocialMediaAccount> socialMediaAccounts;

  public Contact(
    UUID id,
    ContactType contactType,
    String name,
    String residencyCountry,
    List<Telephone> telephones,
    List<Address> addresses,
    List<Email> emails,
    List<EventDate> eventDates,
    List<AssociateContact> associateContacts,
    List<SocialMediaAccount> socialMediaAccounts) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty");
    }
    if (contactType == null) {
      throw new IllegalArgumentException("Contact type cannot be null or empty");
    }
    this.id = id != null ? id : UUID.randomUUID();
    this.contactType = contactType;
    this.name = name;
    this.residencyCountry = residencyCountry != null ? residencyCountry : "";
    this.telephones =
      telephones != null ? new CustomArrayList<>(telephones) : new CustomArrayList<>();
    this.addresses = addresses != null ? new CustomArrayList<>(addresses) : new CustomArrayList<>();
    this.emails = emails != null ? new CustomArrayList<>(emails) : new CustomArrayList<>();
    this.eventDates =
      eventDates != null ? new CustomArrayList<>(eventDates) : new CustomArrayList<>();
    this.associateContacts =
      associateContacts != null
        ? new CustomArrayList<>(associateContacts)
        : new CustomArrayList<>();
    this.socialMediaAccounts =
      socialMediaAccounts != null
        ? new CustomArrayList<>(socialMediaAccounts)
        : new CustomArrayList<>();
  }

  public UUID getId() {
    return id;
  }

  public ContactType getContactType() {
    return contactType;
  }

  public String getName() {
    return name;
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
    if (this == o) return true;
    if (!(o instanceof Contact)) return false;
    Contact contact = (Contact) o;
    return id.equals(contact.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  public Contact copyWith(
    ContactType contactType,
    String name,
    String residencyCountry,
    List<Telephone> newTelephones,
    List<Address> newAddresses,
    List<Email> newEmails,
    List<EventDate> newEventDates,
    List<AssociateContact> newAssociateContacts,
    List<SocialMediaAccount> newSocialMediaAccounts) {

    return new Contact(
      getId(),
      contactType != null ? contactType : this.contactType,
      name != null ? name : this.name,
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

  protected Contact(Parcel in) {
    id = (UUID) in.readSerializable();
    contactType = ContactType.valueOf(in.readString());
    name = in.readString();
    residencyCountry = in.readString();
    telephones = in.createTypedArrayList(Telephone.CREATOR);
    addresses = in.createTypedArrayList(Address.CREATOR);
    emails = in.createTypedArrayList(Email.CREATOR);
    eventDates = in.createTypedArrayList(EventDate.CREATOR);
    associateContacts = in.createTypedArrayList(AssociateContact.CREATOR);
    socialMediaAccounts = in.createTypedArrayList(SocialMediaAccount.CREATOR);
  }

  public static final Creator<Contact> CREATOR = new Creator<Contact>() {
    @Override
    public Contact createFromParcel(Parcel in) {
      return new Contact(in);
    }

    @Override
    public Contact[] newArray(int size) {
      return new Contact[size];
    }
  };

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(@NonNull Parcel dest, int flags) {
    dest.writeSerializable(id);
    dest.writeString(contactType.name());
    dest.writeString(name);
    dest.writeString(residencyCountry);
    dest.writeTypedList(telephones);
    dest.writeTypedList(addresses);
    dest.writeTypedList(emails);
    dest.writeTypedList(eventDates);
    dest.writeTypedList(associateContacts);
    dest.writeTypedList(socialMediaAccounts);
  }
}
