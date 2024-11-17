package com.example.dsproyect_p1.data.api;

import com.example.dsproyect_p1.data.model.Company;
import java.util.List;
import java.util.UUID;

public interface CompanyApi {
  void getCompany(UUID id, Callback<Company> callback);

  void getCompanies(Callback<List<Company>> callback);

  void saveCompany(Company company, Callback<Void> callback);

  void deleteCompany(UUID id, Callbback<Void> callback);
}
