package com.example.dsproyect_p1.data.repository;

import com.example.dsproyect_p1.data.api.CompanyApi;
import com.example.dsproyect_p1.data.models.Company;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class CompanyRepository {
  private CompanyApi companyApi;

  public companyRepository(CompanyApi companyApi) {
    this.companyApi = companyApi;
  }

  public CompletableFuture<Company> getCompany(UUID id) {
    companyApi.getCompany(id);
  }

  public CompletableFuture<List<Company>> getCompanies() {
    companyApi.getCompanies();
  }

  public CompletableFuture<Void> saveCompany(Company company) {
    companyApi.saveCompany(company);
  }

  public CompletableFuture<Void> deleteCompany(UUID id) {
    companyApi.deleteCompany(id);
  }
}
