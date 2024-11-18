package com.example.dsproyect_p1.data.local;

import com.example.dsproyect_p1.data.api.CompanyApi;
import com.example.dsproyect_p1.data.model.Company;
import com.example.dsproyect_p1.data.structures.CustomArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.inject.Inject;

public class LocalStorageCompanyApi implements CompanyApi {

  private static final String filename = "companies.json";
  private final File file;
  private final Gson gson;
  private final Executor executor;

  @Inject
  public LocalStorageCompanyApi(@ApplicationContext Context context) {
    this.file = new File(context.getFilesDir(), filename);
    this.gson = new Gson();
    this.executor = Executors.newSingleThreadExecutor();
  }

  private List<Company> readCompaniesFromFile() throws IOException {
    if (!file.exists()) {
      return new CustomArrayList<>();
    }
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      Type listType = new TypeToken<List<Company>>() {}.getType();
      List<Company> companies = gson.fromJson(reader, listType);
      return companies != null ? companies : new CustomArrayList<>();
    }
  }

  private void writeCompaniesToFile(List<Company> companies) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      gson.toJson(companies, writer);
    }
  }

  @Override
  public CompletableFuture<Company> getCompany(UUID id) {
    return CompletableFuture.supplyAsync(
        () -> {
          try {
            List<Company> companies = readCompaniesFromFile();
            return companies.stream()
                .filter(company -> company.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Company not found"));
          } catch (IOException e) {
            throw new RuntimeException("Failed to retrieve company", e);
          }
        },
        executor);
  }

  @Override
  public CompletableFuture<List<Company>> getCompanies() {
    return CompletableFuture.supplyAsync(
        () -> {
          try {
            return readCompaniesFromFile();
          } catch (IOException e) {
            throw new RuntimeException("Failed to retrieve companies", e);
          }
        },
        executor);
  }

  @Override
  public CompletableFuture<Void> saveCompany(Company company) {
    return CompletableFuture.runAsync(
        () -> {
          try {
            List<Company> companies = readCompaniesFromFile();
            boolean updated = false;

            for (int i = 0; i < companies.size(); i++) {
              if (companies.get(i).getId().equals(company.getId())) {
                companies.set(i, company);
                updated = true;
                break;
              }
            }

            if (!updated) {
              companies.add(company);
            }

            writeCompaniesToFile(companies);
          } catch (IOException e) {
            throw new RuntimeException("Failed to save company", e);
          }
        },
        executor);
  }

  @Override
  public CompletableFuture<Void> deleteCompany(UUID id) {
    return CompletableFuture.runAsync(
        () -> {
          try {
            List<Company> companies = readCompaniesFromFile();
            boolean removed = companies.removeIf(company -> company.getId().equals(id));
            if (!removed) {
              throw new RuntimeException("Company not found");
            }
            writeCompaniesToFile(companies);
          } catch (IOException e) {
            throw new RuntimeException("Failed to delete company", e);
          }
        },
        executor);
  }
}
