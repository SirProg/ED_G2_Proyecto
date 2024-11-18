package com.example.dsproyect_p1.data.repository;

import com.example.dsproyect_p1.data.api.Callback;
import com.example.dsproyect_p1.data.api.CompanyApi;
import com.example.dsproyect_p1.data.models.Company;
import java.util.List;
import java.util.UUID;

public class CompanyRepository {
  private CompanyApi companyApi;

  public companyRepository(CompanyApi companyApi) {
    this.companyApi = companyApi;
  }

  public void getCompany(UUID id, Callback<Company> callback) {
    companyApi.getCompany(id, callback);
  }

  public void getCompanies(Callback<List<Company>> callback) {
    companyApi.getCompanies(callback);
  }

  public void saveCompany(Company company, Callback<Void> callback) {
    companyApi.saveCompany(company, callback);
  }

  public void deleteCompany(UUID id, Callback<Void> callback) {
    companyApi.deleteCompany(id, callback);
  }
}
