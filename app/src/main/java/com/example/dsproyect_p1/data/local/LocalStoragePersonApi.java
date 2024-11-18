package com.example.dsproyect_p1.data.local;

import com.example.dsproyect_p1.data.api.PersonApi;
import com.example.dsproyect_p1.data.model.Person;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LocalStoragePersonApi implements PersonApi {

  private final File file;
  private final Gson gson;
  private final Executor executor;

  public LocalStoragePersonApi(Context context, String filename) {
    this.file = new File(context.getFilesDir(), filename);
    this.gson = new Gson();
    this.executor = Executors.newFixedThreadPool(4);
  }

  private List<Person> readPersonsFromFile() throws IOException {
    if (!file.exists()) {
      return new ArrayList<>();
    }
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      Type listType = new TypeToken<List<Person>>() {
      }.getType();
      List<Person> persons = gson.fromJson(reader, listType);
      return persons != null ? persons : new ArrayList<>();
    }
  }

  private void writePersonsToFile(List<Person> persons) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      gson.toJson(persons, writer);
    }
  }

  @Override
  public CompletableFuture<Person> getPerson(UUID id) {
    return CompletableFuture.supplyAsync(
        () -> {
          List<Person> persons = readPersonsFromFile();
          return persons.stream()
              .filter(person -> person.getId().equals(id))
              .findFirst()
              .orElseThrow(() -> new Exception("Person not found"));
        },
        executor);
  }

  @Override
  public CompletableFuture<List<Person>> getPersons() {
    return CompletableFuture.supplyAsync(
        () -> {
          List<Person> persons = readPersonsFromFile();
          return persons;
        },
        executor);
  }

  @Override
  public CompletableFuture<Void> savePerson(Person person) {
    return CompletableFuture.supplyAsync(
        () -> {
          List<Person> persons = readPersonsFromFile();
          persons.add(person);
          writePersonsToFile(persons);
        },
        executor);
  }

  @Override
  public CompletableFuture<Void> deletePerson(UUID id) {
    return CompletableFuture.supplyAsync(
        () -> {
          List<Person> persons = readPersonsFromFile();
          persons.remove(person);
          writePersonsToFile(persons);
        },
        executor);
  }
}
