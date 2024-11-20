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
import com.example.dsproyect_p1.data.repository.*;
import com.example.dsproyect_p1.modules.add_company.view.AddCompanyActivity;
import com.example.dsproyect_p1.modules.add_person.view.AddPersonActivity;
import com.example.dsproyect_p1.modules.contacts_overview.view.adapter.PersonRecyclerView;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.inject.Inject;

@AndroidEntryPoint
public class ContactsOverviewActivity extends AppCompatActivity {
  private RecyclerView recyclerView;
  private Button btnContacts, btnFavorite, btnOrder;
  private Button btnAddContact;
  private PersonRecyclerView personRecyclerView;

  @Inject
  PersonRepository personRepository;
  @Inject
  CompanyRepository companyRepository;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_main);
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

    fetchContacts();

    btnAddContact = findViewById(R.id.openPopUpAdd);
    btnAddContact.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            openPopupMenuAdd(btnAddContact);
          }
        });
  }

  private void fetchContacts() {
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
                        "Failed to load contacts",
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
