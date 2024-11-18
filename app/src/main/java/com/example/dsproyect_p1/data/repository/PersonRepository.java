package com.example.dsproyect_p1.data.repository;

import com.example.dsproyect_p1.data.api.PersonApi;
import com.example.dsproyect_p1.data.models.Person;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class PersonRepository {
  private PersonApi personApi;

  public PersonRepository(PersonApi personApi) {
    this.personApi = personApi;
  }

  public CompletableFuture<Person> getPerson(UUID id) {
    return personApi.getPerson(id);
  }

  public CompletableFuture<List<Person>> getPersons() {
    return personApi.getPersons();
  }

  public CompletableFuture<Void> savePerson(Person person) {
    return personApi.savePerson(person);
  }

  public CompletableFuture<Void> deletePerson(UUID id) {
    return personApi.deletePerson(id);
  }
}
