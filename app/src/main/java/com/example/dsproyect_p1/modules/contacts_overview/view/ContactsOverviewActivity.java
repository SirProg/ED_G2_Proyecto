package com.example.dsproyect_p1.modules.contacts_overview.view;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.RadioGroup;

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
import com.example.dsproyect_p1.data.api.ContactApi;
import com.example.dsproyect_p1.data.model.Contact;
import com.example.dsproyect_p1.data.model.ContactType;
import com.example.dsproyect_p1.data.model.SocialMedia;
import com.example.dsproyect_p1.data.model.Telephone;
import com.example.dsproyect_p1.data.repository.*;
import com.example.dsproyect_p1.data.structures.ContactComparators;
import com.example.dsproyect_p1.data.structures.ContactFilter;
import com.example.dsproyect_p1.data.structures.CustomArrayList;
import com.example.dsproyect_p1.modules.add_contact.view.AddContactActivity;
import com.example.dsproyect_p1.modules.contact_details.view.ContactDetailsActivity;
import com.example.dsproyect_p1.modules.contacts_overview.adapter.ContactRecyclerView;


import dagger.hilt.android.AndroidEntryPoint;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javax.inject.Inject;

@AndroidEntryPoint
public class ContactsOverviewActivity extends AppCompatActivity
        implements ContactRecyclerView.onItemClickListener {
  private RecyclerView recyclerViewContact;
  private Button  btnFavorite, btnOrder;
  private Button  filtro, noFiltro;
  private ContactRecyclerView contactRecyclerView;
  private Spinner spinnerOrderBy, spinnerFilter;
  private List<Contact> contactsList;

  private ContactFilter contactFilter;
  private RadioGroup radioGroupFiltros;
  private List<String> tipo, especial, ciudades, emptyData;
  private String selectedSpinnerOption = "";
  private int filtroSeleccionado;
  private ContactType tipoC;
  private SocialMedia socialMedia;

  private boolean isInjected;

  @Inject
  ContactRepository contactRepository;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_contacts_overview);


    filtro = findViewById(R.id.filtro);
    radioGroupFiltros = findViewById(R.id.radioGroupFiltros);
    noFiltro = findViewById(R.id.SinFiltroContacto);

    ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main),
            (v, insets) -> {
              Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
              v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
              return insets;
            });

    if (!isInjected) {
      injectContacts();
    }
    spinnerOrderBy = findViewById(R.id.spinnerOrderBy);
    ArrayAdapter<CharSequence> adapter =
            ArrayAdapter.createFromResource(this, R.array.order, android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinnerOrderBy.setAdapter(adapter);

    noFiltro.setOnClickListener(v -> quitarFiltro());

    recyclerViewContact = findViewById(R.id.recyclerViewContacts);
    recyclerViewContact.setLayoutManager(new LinearLayoutManager(this));

    contactRecyclerView =
            new ContactRecyclerView(List.of(), contact -> moveToDescriptionContact(contact));
    recyclerViewContact.setAdapter(contactRecyclerView);

    filtro.setOnClickListener(v -> {
      if (radioGroupFiltros.getVisibility() == View.GONE) {
        radioGroupFiltros.setVisibility(View.VISIBLE);
      } else {
        radioGroupFiltros.setVisibility(View.GONE);
      }
    });
    tipo = new CustomArrayList<>(Arrays.asList("Person", "Company"));
    especial = new CustomArrayList<>(Arrays.asList("Instagram", "Facebook", "YouTube", "Twitter"));


    spinnerFilter = findViewById(R.id.spinnerFiltrosContact);
    radioGroupFiltros.setOnCheckedChangeListener((group, checkedId) -> {
      if (checkedId == R.id.botonCity) {
        filtroSeleccionado = 1;
        showContactCiudades();
      } else if (checkedId == R.id.botonType) {
        showContactTypeData();
        filtroSeleccionado = 2;
      } else if (checkedId == R.id.botonEspecial) {
        filtroSeleccionado = 3;
        showEspecial();
      } else {
        hideSpinner();
      }
    });


    spinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedSpinnerOption = parent.getItemAtPosition(position).toString();
        switch (filtroSeleccionado) {
          case 1:
            List<Contact> filtro1 = contactFilter.filtrarPorCiudad(contactsList, selectedSpinnerOption);
            contactRecyclerView.updateData(filtro1);
            break;
          case 2:
            if (selectedSpinnerOption.equalsIgnoreCase("Person")) {
              tipoC = ContactType.PERSON;
            } else {
              tipoC = ContactType.COMPANY;
            }
            List<Contact> filtro2 = contactFilter.filtrarPorTipo(contactsList, tipoC);
            contactRecyclerView.updateData(filtro2);
            break;
          case 3:
            if (selectedSpinnerOption.equals("Instagram")) {
              socialMedia = SocialMedia.INSTAGRAM;
            } else if (selectedSpinnerOption.equalsIgnoreCase("Facebook")) {
              socialMedia = SocialMedia.FACEBOOK;
            } else if (selectedSpinnerOption.equalsIgnoreCase("YouTube")) {
              socialMedia = SocialMedia.YOUTUBE;
            } else {
              socialMedia = SocialMedia.TWITTER;
            }
            List<Contact> filtro3 = contactFilter.filtrarPorRedSocial(contactsList, socialMedia);
            contactRecyclerView.updateData(filtro3);
            break;
          default:
            contactRecyclerView.updateData(contactsList);
            break;
        }
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {
        selectedSpinnerOption = ""; // Reinicia la selección
      }
    });

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
    telephones1.add(new Telephone("Casa", "0932488380"));
    telephones1.add(new Telephone("Trabajo", "0215487960"));
    UUID id1 = UUID.fromString("f47c0c7d-3e62-4560-8b93-2b9c7f3d4b08");
    UUID id2 = UUID.fromString("f1c9ed5c-34d7-466e-a6f8-295cf7b3d4c7");
    UUID id3 = UUID.fromString("e43232a2-ccd3-43c5-8db5-3e7b0f09cf10");
    UUID id4 = UUID.fromString("7c3a7e4f-b5e5-4b7b-8e94-d7e080e6ad");
    UUID id5 = UUID.fromString("5d6b15c7-35a0-4b5f-a42f-9739e0b9cd8e");
    UUID id6 = UUID.fromString("9a5f6d9a-12e4-4e8f-a8a7-b6f607f4b3b0");
    UUID id7 = UUID.fromString("3bcce62d-6ec9-4577-b24d-73a22b1b83c7");
    UUID id8 = UUID.fromString("d8f82e88-f6cd-49c5-bcfa-5b93cfb923bf");
    Contact contact1 =
            new Contact(
                    id1,
                    ContactType.COMPANY,
                    "Chevrolet",
                    "Ecuador",
                    telephones1,
                    null,
                    null,
                    null,
                    null,
                    null);
    Contact contact2 =
            new Contact(id2, ContactType.PERSON, "Juan", "Peru", null, null, null, null, null, null);
    Contact contact3 =
            new Contact(
                    id3, ContactType.COMPANY, "Ferrari", "Ecuador", null, null, null, null, null, null);
    Contact contact4 =
            new Contact(
                    id4, ContactType.PERSON, "Juafra", "Italia", null, null, null, null, null, null);
    Contact contact5 =
            new Contact(
                    id5, ContactType.COMPANY, "Telconet", "Mexico", null, null, null, null, null, null);
    Contact contact6 =
            new Contact(id6, ContactType.PERSON, "Kevin", "Mexico", null, null, null, null, null, null);
    Contact contact7 =
            new Contact(
                    id7, ContactType.COMPANY, "Audi", "Ecuador", null, null, null, null, null, null);
    Contact contact8 =
            new Contact(
                    id8, ContactType.PERSON, "Daniela", "Panama", null, null, null, null, null, null);
    CompletableFuture<Void> future1 = contactRepository.saveContact(contact1);
    CompletableFuture<Void> future2 = contactRepository.saveContact(contact2);
    CompletableFuture<Void> future3 = contactRepository.saveContact(contact3);
    CompletableFuture<Void> future4 = contactRepository.saveContact(contact4);
    CompletableFuture<Void> future5 = contactRepository.saveContact(contact5);
    CompletableFuture<Void> future6 = contactRepository.saveContact(contact6);
    CompletableFuture<Void> future7 = contactRepository.saveContact(contact7);
    CompletableFuture<Void> future8 = contactRepository.saveContact(contact8);
    CompletableFuture.allOf(future1, future2, future3, future4, future5, future6, future7, future8)
            .join();
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

  public void addContact(View view) {
    Intent intent = new Intent(ContactsOverviewActivity.this, AddContactActivity.class);
    startActivity(intent);
  }

  private void quitarFiltro() {
    contactRecyclerView.updateData(contactsList);
    spinnerFilter.setVisibility(View.GONE);
    radioGroupFiltros.setVisibility(View.GONE);
  }

  public void openSpinnerOrder(View view) {
    if (spinnerOrderBy.getVisibility() == View.GONE) {
      spinnerOrderBy.setVisibility(View.VISIBLE);
      contactRecyclerView.updateData(
              getSortedContacts(spinnerOrderBy.getSelectedItem().toString()));
      spinnerOrderBy.setOnItemSelectedListener(
              new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                  String selectedItem = adapterView.getItemAtPosition(i).toString();
                  List<Contact> sortedContacts = getSortedContacts(selectedItem);
                  contactRecyclerView.updateData(sortedContacts);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                  contactRecyclerView.updateData(contactsList);
                }
              });
    } else {
      contactRecyclerView.updateData(contactsList);
      spinnerOrderBy.setVisibility(View.GONE);
    }
  }

  private List<Contact> getSortedContacts(String selectedItem) {
    String[] orderArray = getResources().getStringArray(R.array.order);
    List<Contact> sortedContacts = new CustomArrayList<>(contactsList);

    if (selectedItem.equals(orderArray[0])) {
      return sortedContacts;
    } else if (selectedItem.equals(orderArray[1])) {
      sortedContacts.sort(ContactComparators.BY_NAME);
    } else if (selectedItem.equals(orderArray[2])) {
      sortedContacts.sort(ContactComparators.BY_RESIDENCY_COUNTRY);
    } else if (selectedItem.equals(orderArray[3])) {
      sortedContacts.sort(ContactComparators.BY_CONTACT_TYPE);
    }
    return sortedContacts;
  }

  private void updateSpinner(List<String> data) {
    ArrayAdapter<String> adapter = new ArrayAdapter<>(
            this,
            android.R.layout.simple_spinner_item,
            data
    );
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinnerFilter.setAdapter(adapter);
  }

  private List<String> getUniqueCountries() {
    if (contactsList == null || contactsList.isEmpty()) {
      return new CustomArrayList<>();
    }

    return contactsList.stream()
            .map(Contact::getResidencyCountry)
            .filter(country -> country != null && !country.isEmpty())
            .distinct()
            .sorted()
            .collect(Collectors.toList());
  }

  private void showContactCiudades() {
    spinnerFilter.setVisibility(View.VISIBLE);
    List<String> ciud = getUniqueCountries();
    updateSpinner(ciud);
  }

  private void showContactTypeData() {
    spinnerFilter.setVisibility(View.VISIBLE);
    updateSpinner(tipo);
  }

  private void showEspecial() {
    spinnerFilter.setVisibility(View.VISIBLE);
    updateSpinner(especial);
  }

  private void hideSpinner() {
    spinnerFilter.setVisibility(View.GONE);
    updateSpinner(emptyData);
  }
}


