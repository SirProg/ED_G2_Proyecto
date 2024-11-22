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
import com.example.dsproyect_p1.data.repository.*;
import com.example.dsproyect_p1.modules.add_company.view.AddCompanyActivity;
import com.example.dsproyect_p1.modules.add_person.view.AddPersonActivity;
import com.example.dsproyect_p1.modules.company_details.view.CompanyDetailsActivity;
import com.example.dsproyect_p1.modules.contacts_overview.adapter.PersonRecyclerView;
import com.example.dsproyect_p1.modules.contacts_overview.adapter.CompanyRecyclerView;
import com.example.dsproyect_p1.data.model.Person;
import com.example.dsproyect_p1.data.model.Company;
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

    recyclerView = findViewById(R.id.recyclerViewContacts);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    personRecyclerView = new PersonRecyclerView(this, List.of());
    recyclerView.setAdapter(personRecyclerView);

    fetchPersons();

    recyclerViewCompany = findViewById(R.id.recyclerViewCompany);
    recyclerViewCompany.setLayoutManager(new LinearLayoutManager(this));

    companyRecyclerView = new CompanyRecyclerView(this, List.of(), new CompanyRecyclerView.onItemClickListener() {
        @Override
        public void onItemClick(Company company) {
            moveToDescriptionCompany(company);
        }
    });
    recyclerViewCompany.setAdapter(companyRecyclerView);

    fetchCompanies();

    btnAddContact = findViewById(R.id.openPopUpAdd);
    btnAddContact.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            openPopupMenuAdd(btnAddContact);
          }
        });
  }

  public void moveToDescriptionCompany(Company company){
        Intent intent = new Intent(ContactsOverviewActivity.this, CompanyDetailsActivity.class);
        intent.putExtra("CompanyDetailsActivity", company);
        startActivity(intent);
  }

    public void moveToDescriptionPerson(Person person){
        Intent intent = new Intent(ContactsOverviewActivity.this, PersonDetailsActivity.class);
        intent.putExtra("CompanyDetailsActivity", person);
        startActivity(intent);
    }

  private void fetchPersons() {
    CompletableFuture<List<Person>> future = personRepository.getPersons();
    future
        .thenAccept(
            persons -> {
              runOnUiThread(
                  () -> {
                    personRecyclerView = new PersonRecyclerView(ContactsOverviewActivity.this, persons);
                    recyclerView.setAdapter(personRecyclerView);
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

  private void fetchCompanies() {
    CompletableFuture<List<Company>> future = companyRepository.getCompanies();
    future
        .thenAccept(
            companies -> {
              runOnUiThread(
                  () -> {
                    companyRecyclerView = new CompanyRecyclerView(ContactsOverviewActivity.this, companies, new CompanyRecyclerView.onItemClickListener() {
                        @Override
                        public void onItemClick(Company company) {
                            moveToDescriptionCompany(company);
                        }
                    });
                    recyclerView.setAdapter(companyRecyclerView);
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
