package com.example.dsproyect_p1.data.repository;

import com.example.dsproyect_p1.data.api.CompanyApi;
import com.example.dsproyect_p1.data.model.Company;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CompanyRepository {
  private CompanyApi companyApi;

  @Inject
  public CompanyRepository(CompanyApi companyApi) {
    this.companyApi = companyApi;
  }

  public CompletableFuture<Company> getCompany(UUID id) {
    return companyApi.getCompany(id);
  }

  public CompletableFuture<List<Company>> getCompanies() {
    return companyApi.getCompanies();
  }

  public CompletableFuture<Void> saveCompany(Company company) {
    return companyApi.saveCompany(company);
  }

  public CompletableFuture<Void> deleteCompany(UUID id) {
    return companyApi.deleteCompany(id);
  }
}
