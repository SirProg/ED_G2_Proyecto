package com.example.dsproyect_p1.modules.contacts_overview.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dsproyect_p1.R;
import com.example.dsproyect_p1.data.model.Company;
import com.example.dsproyect_p1.data.model.Person;
import com.example.dsproyect_p1.data.repository.*;
import com.example.dsproyect_p1.modules.add_company.view.AddCompanyActivity;
import com.example.dsproyect_p1.modules.add_person.view.AddPersonActivity;
import com.example.dsproyect_p1.modules.company_details.view.CompanyDetailsActivity;
import com.example.dsproyect_p1.modules.contacts_overview.adapter.CompanyRecyclerView;
import com.example.dsproyect_p1.modules.contacts_overview.adapter.PersonRecyclerView;
import com.example.dsproyect_p1.modules.person_details.view.PersonDetailsActivity;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.inject.Inject;

@AndroidEntryPoint
public class ContactsOverviewActivity extends AppCompatActivity {
  private RecyclerView recyclerView, recyclerViewCompany;
  private Button btnContacts, btnFavorite, btnOrder;
  private Button btnAddContact;
  private PersonRecyclerView personRecyclerView;
  private CompanyRecyclerView companyRecyclerView;

  @Inject
  PersonRepository personRepository;
  @Inject
  CompanyRepository companyRepository;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_contacts_overview);

    ViewCompat.setOnApplyWindowInsetsListener(
        findViewById(R.id.main),
        (v, insets) -> {
          Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
          v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
          return insets;
        });

    injectPersons();
    injectCompanies();

    recyclerView = findViewById(R.id.recyclerViewContacts);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    personRecyclerView = new PersonRecyclerView(List.of(), person -> moveToDescriptionPerson(person));
    recyclerView.setAdapter(personRecyclerView);

    fetchPersons();

    recyclerViewCompany = findViewById(R.id.recyclerViewCompany);
    recyclerViewCompany.setLayoutManager(new LinearLayoutManager(this));

    companyRecyclerView = new CompanyRecyclerView(List.of(), company -> moveToDescriptionCompany(company));
    recyclerViewCompany.setAdapter(companyRecyclerView);

    fetchCompanies();

    btnAddContact = findViewById(R.id.openPopUpAdd);
    btnAddContact.setOnClickListener(view -> openPopupMenuAdd(btnAddContact));
  }

  public void moveToDescriptionCompany(Company company) {
    Intent intent = new Intent(ContactsOverviewActivity.this, CompanyDetailsActivity.class);
    intent.putExtra("CompanyDetailsActivity", company);
    startActivity(intent);
  }

  public void moveToDescriptionPerson(Person person) {
    Intent intent = new Intent(ContactsOverviewActivity.this, PersonDetailsActivity.class);
    intent.putExtra("PersonDetailsActivity", person);
    startActivity(intent);
  }

  private void fetchPersons() {
    CompletableFuture<List<Person>> future = personRepository.getPersons();
    future
        .thenAccept(
            persons -> {
              runOnUiThread(
                  () -> {
                    personRecyclerView.updateData(persons);
                  });
            })
        .exceptionally(
            ex -> {
              runOnUiThread(
                  () -> {
                    Toast.makeText(
                        ContactsOverviewActivity.this,
                        "Failed to load persons",
                        Toast.LENGTH_SHORT)
                        .show();
                  });
              return null;
            });
  }

  private void injectCompanies() {
    Company company1 = new Company(null, "Chevrolet", null, null, null, null, null, null, null, null);
    Company company2 = new Company(null, "Ferrari", null, null, null, null, null, null, null, null);
    Company company3 = new Company(null, "Audi", null, null, null, null, null, null, null, null);
    Company company4 = new Company(null, "Telconet", null, null, null, null, null, null, null, null);
    Company company5 = new Company(null, "Mazda", null, null, null, null, null, null, null, null);
    CompletableFuture<Void> future1 = companyRepository.saveCompany(company1);
    CompletableFuture<Void> future2 = companyRepository.saveCompany(company2);
    CompletableFuture<Void> future3 = companyRepository.saveCompany(company3);
    CompletableFuture<Void> future4 = companyRepository.saveCompany(company4);
    CompletableFuture<Void> future5 = companyRepository.saveCompany(company5);
    CompletableFuture.allOf(future1, future2, future3, future4, future5).join();
  }

  private void injectPersons() {
    Person person1 = new Person(null, "Juan", null, null, null, null, null, null, null, null);
    Person person2 = new Person(null, "Julio", null, null, null, null, null, null, null, null);
    Person person3 = new Person(null, "Kevin", null, null, null, null, null, null, null, null);
    Person person4 = new Person(null, "Daniela", null, null, null, null, null, null, null, null);
    Person person5 = new Person(null, "Juanfra", null, null, null, null, null, null, null, null);
    CompletableFuture<Void> future1 = personRepository.savePerson(person1);
    CompletableFuture<Void> future2 = personRepository.savePerson(person2);
    CompletableFuture<Void> future3 = personRepository.savePerson(person3);
    CompletableFuture<Void> future4 = personRepository.savePerson(person4);
    CompletableFuture<Void> future5 = personRepository.savePerson(person5);
    CompletableFuture.allOf(future1, future2, future3, future4, future5).join();
  }

  private void fetchCompanies() {
    CompletableFuture<List<Company>> future = companyRepository.getCompanies();
    future
        .thenAccept(
            companies -> {
              runOnUiThread(
                  () -> {
                    companyRecyclerView.updateData(companies);
                  });
            })
        .exceptionally(
            ex -> {
              runOnUiThread(
                  () -> {
                    Toast.makeText(
                        ContactsOverviewActivity.this,
                        "Failed to load companies",
                        Toast.LENGTH_SHORT)
                        .show();
                  });
              return null;
            });
  }

  public void openPopupMenuAdd(View anchorView) {
    View viewPopup = LayoutInflater.from(ContactsOverviewActivity.this)
        .inflate(R.layout.menu_item_addcontact, null);
    PopupWindow popupWindow = new PopupWindow(
        viewPopup,
        LinearLayout.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT,
        true);

    ImageButton iBtnPerson = viewPopup.findViewById(R.id.addPerson);
    iBtnPerson.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent intent = new Intent(ContactsOverviewActivity.this, AddPersonActivity.class);
            startActivity(intent);
            popupWindow.dismiss();
          }
        });

    ImageButton iBtnCompany = viewPopup.findViewById(R.id.addCompany);
    iBtnCompany.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            popupWindow.dismiss();
            Intent intent = new Intent(ContactsOverviewActivity.this, AddCompanyActivity.class);
            startActivity(intent);
          }
        });
    popupWindow.showAtLocation(anchorView, Gravity.CENTER, 0, 0);
  }
}
