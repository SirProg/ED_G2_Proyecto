package com.example.dsproyect_p1.modules.contacts_overview.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dsproyect_p1.R;
import com.example.dsproyect_p1.data.model.Contact;
import com.example.dsproyect_p1.data.model.ContactType;
import com.example.dsproyect_p1.data.model.Telephone;
import com.example.dsproyect_p1.data.repository.*;
import com.example.dsproyect_p1.data.structures.ContactComparators;
import com.example.dsproyect_p1.data.structures.CustomArrayList;
import com.example.dsproyect_p1.modules.add_contact.view.AddContactActivity;
import com.example.dsproyect_p1.modules.contact_details.view.ContactDetailsActivity;
import com.example.dsproyect_p1.modules.contacts_overview.adapter.ContactRecyclerView;
import dagger.hilt.android.AndroidEntryPoint;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.inject.Inject;

@AndroidEntryPoint
public class ContactsOverviewActivity extends AppCompatActivity
    implements  ContactRecyclerView.onItemClickListener {
  private RecyclerView recyclerViewContact;
  private Button btnContacts, btnFavorite, btnOrder;
  private Button btnAddContact;
  private ContactRecyclerView contactRecyclerView;
  private Spinner spinnerOrderBy;
  private List<Contact> contactsList;

  @Inject
  ContactRepository contactRepository;

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

    injectContacts();
    spinnerOrderBy = findViewById(R.id.spinnerOrderBy);
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.order,android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinnerOrderBy.setAdapter(adapter);

    recyclerViewContact = findViewById(R.id.recyclerViewContacts);
    recyclerViewContact.setLayoutManager(new LinearLayoutManager(this));

    contactRecyclerView = new ContactRecyclerView(List.of(), contact -> moveToDescriptionContact(contact));
    recyclerViewContact.setAdapter(contactRecyclerView);

    fetchContacts();
  }

  public void moveToDescriptionContact(Contact contact) {
    Intent intent = new Intent(ContactsOverviewActivity.this, ContactDetailsActivity.class);
    intent.putExtra("CONTACT", contact);
    startActivity(intent);
  }


  @Override
  protected void onResume() {
    super.onResume();
    fetchContacts();
  }

  private void injectContacts() {
    List<Telephone> telephones1 = new CustomArrayList<>();
    telephones1.add(new Telephone("0932488380", "Casa"));
    telephones1.add(new Telephone("0215487960", "Trabajo"));
    Contact contact1 = new Contact(null, ContactType.COMPANY, "Chevrolet", "Ecuador", telephones1, null, null, null, null, null);
    Contact contact2 = new Contact(null, ContactType.PERSON, "Juan", "Peru", null, null, null, null, null, null);
    Contact contact3 = new Contact(null, ContactType.COMPANY, "Ferrari", "Ecuador", null, null, null, null, null, null);
    Contact contact4 = new Contact(null, ContactType.PERSON, "Juafra", "Italia", null, null, null, null, null, null);
    Contact contact5 = new Contact(null, ContactType.COMPANY, "Telconet", "Mexico", null, null, null, null, null, null);
    Contact contact6 = new Contact(null, ContactType.PERSON, "Kevin", "Mexico", null, null, null, null, null, null);
    Contact contact7 = new Contact(null, ContactType.COMPANY, "Audi", "Ecuador", null, null, null, null, null, null);
    Contact contact8 = new Contact(null, ContactType.PERSON, "Daniela", "Panama", null, null, null, null, null, null);
    CompletableFuture<Void> future1 = contactRepository.saveContact(contact1);
    CompletableFuture<Void> future2 = contactRepository.saveContact(contact2);
    CompletableFuture<Void> future3 = contactRepository.saveContact(contact3);
    CompletableFuture<Void> future4 = contactRepository.saveContact(contact4);
    CompletableFuture<Void> future5 = contactRepository.saveContact(contact5);
    CompletableFuture<Void> future6 = contactRepository.saveContact(contact6);
    CompletableFuture<Void> future7 = contactRepository.saveContact(contact7);
    CompletableFuture<Void> future8 = contactRepository.saveContact(contact8);
    CompletableFuture.allOf(future1, future2, future3, future4, future5,future6,future7,future8).join();
  }

  @Override
  public void onItemClickContact(Contact contact) {
    Intent intent = new Intent(ContactsOverviewActivity.this, ContactDetailsActivity.class);
    intent.putExtra("CONTACT", contact);
    startActivity(intent);
  }

  private void fetchContacts() {
    CompletableFuture<List<Contact>> future = contactRepository.getContacts();
    future
        .thenAccept(
            contacts -> {
              runOnUiThread(
                  () -> {
                    contactsList = contacts;
                    contactRecyclerView.updateData(contacts);
                    Toast.makeText(this, "Se cargaron los datos", Toast.LENGTH_SHORT).show();
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

  public void addContact(View view){
    Intent intent = new Intent(ContactsOverviewActivity.this, AddContactActivity.class);
    startActivity(intent);
  }

  public void openSpinnerOrder(View view){
    if(spinnerOrderBy.getVisibility() == View.GONE){
      spinnerOrderBy.setVisibility(View.VISIBLE);
      spinnerOrderBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
          String selectedItem = adapterView.getItemAtPosition(i).toString();
          switch (selectedItem){
            case "Todos":
              updateRecyclerView(contactsList);
              break;
            case "Nombres":
              List<Contact> sortedName = new CustomArrayList<>(contactsList);
              sortedName.sort(ContactComparators.BY_NAME);
              updateRecyclerView(sortedName);
              break;
            case "Tipo Contacto":
              List<Contact> sortedType = new CustomArrayList<>(contactsList);
              sortedType.sort(ContactComparators.BY_CONTACT_TYPE);
              updateRecyclerView(sortedType);
              break;
            case "Pais":
              List<Contact> sortedCountry = new CustomArrayList<>(contactsList);
              sortedCountry.sort(ContactComparators.BY_RESIDENCY_COUNTRY);
              updateRecyclerView(sortedCountry);
              break;
          }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
      });
    }else{
      spinnerOrderBy.setVisibility(View.GONE);
    }
  }

  @SuppressLint("NotifyDataSetChanged")
  public void updateRecyclerView(List<Contact> contacts){
    contactRecyclerView.updateData(contacts);
    contactRecyclerView.notifyDataSetChanged();
  }

}
