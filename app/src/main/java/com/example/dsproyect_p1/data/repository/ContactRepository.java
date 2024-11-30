package com.example.dsproyect_p1.data.repository;

import com.example.dsproyect_p1.data.api.ContactApi;
import com.example.dsproyect_p1.data.model.Contact;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ContactRepository {
  private ContactApi contactApi;

  @Inject
  public ContactRepository(ContactApi contactApi) {
    this.contactApi = contactApi;
  }

  public CompletableFuture<Contact> getContact(UUID id) {
    return contactApi.getContact(id);
  }

  public CompletableFuture<List<Contact>> getContacts() {
    return contactApi.getContacts();
  }

  public CompletableFuture<Void> saveContact(Contact contact) {
    return contactApi.saveContact(contact);
  }

  public CompletableFuture<Void> deleteContact(UUID id) {
    return contactApi.deleteContact(id);
  }


  public CompletableFuture<List<String>> getAvailableCountries() {
    return contactApi.getContacts()
            .thenApply(contacts -> contacts.stream()
                    .map(Contact::getResidencyCountry)
                    .filter(country -> country != null && !country.isEmpty())
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList()));
  }

}
