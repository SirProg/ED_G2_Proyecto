package com.example.dsproyect_p1.data.local;

import com.example.dsproyect_p1.data.api.CompanyApi;
import com.example.dsproyect_p1.data.model.Company;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LocalStorageCompanyApi implements CompanyApi {

  private static final String filename = "companies.json";
  private final File file;
  private final Gson gson;
  private final Executor executor;

  public LocalStorageCompanyApi(Context context) {
    this.file = new File(context.getFilesDir(), filename);
    this.gson = new Gson();
    this.executor = Executors.newFixedThreadPool(4);
  }

  private List<Company> readCompaniesFromFile() throws IOException {
    if (!file.exists()) {
      return new ArrayList<>();
    }
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      Type listType = new TypeToken<List<Company>>() {
      }.getType();
      List<Company> companies = gson.fromJson(reader, listType);
      return companies != null ? companies : new ArrayList<>();
    }
  }

  private void writeCompaniesToFile(List<Company> companies) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      gson.toJson(companies, writer);
    }
  }

  public CompletableFuture<Company> getCompany(UUID id) {
    return CompletableFuture.supplyAsync(
        () -> {
          List<Company> companies = readPersonsFromFile();
          return companies.stream()
              .filter(person -> companies.getId().equals(id))
              .findFirst()
              .orElseThrow(() -> new Exception("Company not found"));
        },
        executor);
  }

  public CompletableFuture<List<Company>> getCompanies() {
  }

  public CompletableFuture<Void> saveCompany(Company company) {
  }

  public CompletableFuture<Void> deleteCompany(UUID id) {
  }
}
