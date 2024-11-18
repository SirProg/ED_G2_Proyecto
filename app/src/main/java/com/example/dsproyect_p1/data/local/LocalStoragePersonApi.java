package com.example.dsproyect_p1.data.local;

import com.example.dsproyect_p1.data.api.PersonApi;
import com.example.dsproyect_p1.data.model.Person;
import com.example.dsproyect_p1.data.structures.CustomArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LocalStoragePersonApi implements PersonApi {

  private static final String filename = "persons.json";
  private final File file;
  private final Gson gson;
  private final Executor executor;

  public LocalStoragePersonApi(Context context) {
    this.file = new File(context.getFilesDir(), filename);
    this.gson = new Gson();
    this.executor = Executors.newSingleThreadExecutor();
  }

  private List<Person> readPersonsFromFile() throws IOException {
    if (!file.exists()) {
      return new CustomArrayList<>();
    }
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      Type listType = new TypeToken<List<Person>>() {}.getType();
      List<Person> persons = gson.fromJson(reader, listType);
      return persons != null ? persons : new CustomArrayList<>();
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
          try {
            List<Person> persons = readPersonsFromFile();
            return persons.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Person not found"));
          } catch (IOException e) {
            throw new RuntimeException("Failed to retrieve person", e);
          }
        },
        executor);
  }

  @Override
  public CompletableFuture<List<Person>> getPersons() {
    return CompletableFuture.supplyAsync(
        () -> {
          try {
            return readPersonsFromFile();
          } catch (IOException e) {
            throw new RuntimeException("Failed to retrieve persons", e);
          }
        },
        executor);
  }

  @Override
  public CompletableFuture<Void> savePerson(Person person) {
    return CompletableFuture.runAsync(
        () -> {
          try {
            List<Person> persons = readPersonsFromFile();
            boolean updated = false;

            for (int i = 0; i < persons.size(); i++) {
              if (persons.get(i).getId().equals(person.getId())) {
                persons.set(i, person);
                updated = true;
                break;
              }
            }

            if (!updated) {
              persons.add(person);
            }

            writePersonsToFile(persons);
          } catch (IOException e) {
            throw new RuntimeException("Failed to save person", e);
          }
        },
        executor);
  }

  @Override
  public CompletableFuture<Void> deletePerson(UUID id) {
    return CompletableFuture.runAsync(
        () -> {
          try {
            List<Person> persons = readPersonsFromFile();
            boolean removed = persons.removeIf(person -> person.getId().equals(id));
            if (!removed) {
              throw new RuntimeException("Person not found");
            }
            writePersonsToFile(persons);
          } catch (IOException e) {
            throw new RuntimeException("Failed to delete person", e);
          }
        },
        executor);
  }
}
