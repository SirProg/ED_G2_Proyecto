package com.example.dsproyect_p1.data.api;

import com.example.dsproyect_p1.data.model.Person;
import java.util.List;
import java.util.UUID;

public interface PersonApi {
  public void getPerson(UUID id, Callback<Person> callback);

  public void getPersons(Callback<List<Person>> callback);

  public void savePerson(Person person, Callback<Void> callback);

  public void deletePerson(UUID id, Callbback<Void> callback);
}
