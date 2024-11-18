package com.example.dsproyect_p1.data.api;

import com.example.dsproyect_p1.data.model.Company;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface CompanyApi {
  public CompletableFuture<Company> getCompany(UUID id);

  public CompletableFuture<List<Company>> getCompanies();

  public CompletableFuture<Void> saveCompany(Company company);

  public CompletableFuture<Void> deleteCompany(UUID id);
}
