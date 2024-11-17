package com.example.dsproyect_p1.data.repository;

import com.example.dsproyect_p1.data.api.Callback;
import com.example.dsproyect_p1.data.api.PersonApi;
import com.example.dsproyect_p1.data.models.Person;
import java.util.List;
import java.util.UUID;

public class PersonRepository {
  private PersonApi personApi;

  public PersonRepository(PersonApi personApi) {
    this.personApi = personApi;
  }

  public void getPerson(UUID id, Callback<Person> callback) {
    personApi.getPerson(id, callback);
  }

  public void getPersons(Callback<List<Person>> callback) {
    personApi.getPersons(callback);
  }

  public void savePerson(Person person, Callback<Void> callback) {
    personApi.savePerson(person, callback);
  }

  public void deletePerson(UUID id, Callback<Void> callback) {
    personApi.deletePerson(id, callback);
  }
}
