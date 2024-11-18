package com.example.dsproyect_p1;

import android.app.Application;
import com.example.dsproyect_p1.data.api.*;
import com.example.dsproyect_p1.data.local.*;
import com.example.dsproyect_p1.data.repository.*;

public class App extends Application {
  private static PersonRepository personRepository;
  private static CompanyRepository companyRepository;

  @override
  public void onCreate() {
    super.onCreate();
    PersonApi personApi = new LocalStoragePersonApi(this);
    personRepository = new PersonRepository(personApi);

    CompanyApi companyApi = new LocalStorageCompanyApi(this);
    companyRepository = new CompanyRepository(companyApi);
  }

  public static PersonRepository getPersonRepository() {
    return personRepository;
  }

  public static CompanyRepository getCompanyRepository() {
    return companyRepository;
  }
}
