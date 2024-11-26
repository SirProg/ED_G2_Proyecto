package com.example.dsproyect_p1.data.api;

import com.example.dsproyect_p1.data.model.Contact;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface ContactApi {
  public CompletableFuture<Contact> getContact(UUID id);

  public CompletableFuture<List<Contact>> getContacts();

  public CompletableFuture<Void> saveContact(Contact contact);

  public CompletableFuture<Void> deleteContact(UUID id);
}
