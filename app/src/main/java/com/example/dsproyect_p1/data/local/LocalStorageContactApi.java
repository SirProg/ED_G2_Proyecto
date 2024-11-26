package com.example.dsproyect_p1.data.local;

import android.content.Context;

import com.example.dsproyect_p1.data.api.ContactApi;
import com.example.dsproyect_p1.data.model.Contact;
import com.example.dsproyect_p1.data.structures.CustomArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.inject.Inject;

public class LocalStorageContactApi implements ContactApi {

  private static final String filename = "contacts.json";
  private final File file;
  private final Gson gson;
  private final Executor executor;

  @Inject
  public LocalStorageContactApi(@ApplicationContext Context context) {
    this.file = new File(context.getFilesDir(), filename);
    this.gson = new Gson();
    this.executor = Executors.newSingleThreadExecutor();
  }

  private List<Contact> readContactsFromFile() throws IOException {
    if (!file.exists()) {
      return new CustomArrayList<>();
    }
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      Type listType = new TypeToken<List<Contact>>() {}.getType();
      List<Contact> contacts = gson.fromJson(reader, listType);
      return contacts != null ? contacts : new CustomArrayList<>();
    }
  }

  private void writeContactsToFile(List<Contact> contacts) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      gson.toJson(contacts, writer);
    }
  }

  @Override
  public CompletableFuture<Contact> getContact(UUID id) {
    return CompletableFuture.supplyAsync(
        () -> {
          try {
            List<Contact> contacts = readContactsFromFile();
            return contacts.stream()
                .filter(contact -> contact.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Contact not found"));
          } catch (IOException e) {
            throw new RuntimeException("Failed to retrieve contact", e);
          }
        },
        executor);
  }

  @Override
  public CompletableFuture<List<Contact>> getContacts() {
    return CompletableFuture.supplyAsync(
        () -> {
          try {
            return readContactsFromFile();
          } catch (IOException e) {
            throw new RuntimeException("Failed to retrieve contacts", e);
          }
        },
        executor);
  }

  @Override
  public CompletableFuture<Void> saveContact(Contact contact) {
    return CompletableFuture.runAsync(
        () -> {
          try {
            List<Contact> contacts = readContactsFromFile();
            boolean updated = false;

            for (int i = 0; i < contacts.size(); i++) {
              if (contacts.get(i).getId().equals(contact.getId())) {
                contacts.set(i, contact);
                updated = true;
                break;
              }
            }

            if (!updated) {
              contacts.add(contact);
            }

            writeContactsToFile(contacts);
          } catch (IOException e) {
            throw new RuntimeException("Failed to save contact", e);
          }
        },
        executor);
  }

  @Override
  public CompletableFuture<Void> deleteContact(UUID id) {
    return CompletableFuture.runAsync(
        () -> {
          try {
            List<Contact> contacts = readContactsFromFile();
            boolean removed = contacts.removeIf(contact -> contact.getId().equals(id));
            if (!removed) {
              throw new RuntimeException("Contact not found");
            }
            writeContactsToFile(contacts);
          } catch (IOException e) {
            throw new RuntimeException("Failed to delete contact", e);
          }
        },
        executor);
  }
}
