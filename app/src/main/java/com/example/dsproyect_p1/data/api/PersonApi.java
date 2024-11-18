package com.example.dsproyect_p1.data.api;

import com.example.dsproyect_p1.data.model.Person;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface PersonApi {
  public CompletableFuture<Person> getPerson(UUID id);

  public CompletableFuture<List<Person>> getPersons();

  public CompletableFuture<Void> savePerson(Person person);

  public CompletableFuture<Void> deletePerson(UUID id);
}
