package com.example.dsproyect_p1.modules.contacts_overview.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.dsproyect_p1.modules.filter.Filter;

import dagger.hilt.android.AndroidEntryPoint;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javax.inject.Inject;

@AndroidEntryPoint
public class ContactsOverviewActivity extends AppCompatActivity
    implements  ContactRecyclerView.onItemClickListener {
  private RecyclerView recyclerViewContact;
  private Button btnContacts, btnFavorite, btnOrder;
  private Button btnAddContact,filtro, noFiltro;
  private ContactRecyclerView contactRecyclerView;
  private Spinner spinnerOrderBy, spinnerFilter;
  private List<Contact> contactsList;
  private ContactFilter contactFilter;
  private RadioGroup radioGroupFiltros;
  private List<String> tipo,especial,ciudades,emptyData;
  private String selectedSpinnerOption = "";
  private int filtroSeleccionado;
  ContactApi contactApi;
  private ContactType tipoC;
  private SocialMedia socialMedia;

  @Inject
  ContactRepository contactRepository;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_contacts_overview);



    filtro = findViewById(R.id.filtro);
    radioGroupFiltros= findViewById(R.id.radioGroupFiltros);
    noFiltro = findViewById(R.id.SinFiltroContacto);

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

    noFiltro.setOnClickListener(v -> quitarFiltro());

    recyclerViewContact = findViewById(R.id.recyclerViewContacts);
    recyclerViewContact.setLayoutManager(new LinearLayoutManager(this));

    contactRecyclerView = new ContactRecyclerView(List.of(), contact -> moveToDescriptionContact(contact));
    recyclerViewContact.setAdapter(contactRecyclerView);

    filtro.setOnClickListener(v -> {
      if (radioGroupFiltros.getVisibility() == View.GONE) {
        radioGroupFiltros.setVisibility(View.VISIBLE);
      } else {
        radioGroupFiltros.setVisibility(View.GONE);
      }
    });
    tipo = new CustomArrayList<>(Arrays.asList("Person", "Company"));
    especial = new CustomArrayList<>(Arrays.asList("Instagram","Facebook","YouTube","Twitter"));


    spinnerFilter = findViewById(R.id.spinnerFiltrosContact);
    radioGroupFiltros.setOnCheckedChangeListener((group, checkedId) -> {
      if (checkedId == R.id.botonCity) {
        filtroSeleccionado = 1;
        showContactCiudades();
      } else if (checkedId == R.id.botonType) {
        showContactTypeData();
        filtroSeleccionado =2;
      } /*else if (checkedId == R.id.botonNoFilter) {
        //Toast.makeText(this, "ingresa", Toast.LENGTH_SHORT).show();
        //hideSpinner();
        //filtroSeleccionado =4;
        //updateRecyclerView(contactsList);
        try {
          Toast.makeText(this, "ingresa", Toast.LENGTH_SHORT).show();
          filtroSeleccionado = 4;
          hideSpinner();
          if (contactsList != null) {
            updateRecyclerView(contactsList);
          } else {
            Log.e("Error", "La lista de contactos es null");
          }
        } catch (Exception e) {
          Log.e("Error", "Error al manejar el botón No Filtro", e);
        }
      }*/ else if (checkedId == R.id.botonEspecial) {
        filtroSeleccionado=3;
        showEspecial();
      } else {
        hideSpinner();
      }
    });
    

    spinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedSpinnerOption = parent.getItemAtPosition(position).toString();
        switch (filtroSeleccionado){
          case 1:
            List<Contact> filtro1 = contactFilter.filtrarPorCiudad(contactsList,selectedSpinnerOption);
            updateRecyclerView(filtro1);
            break;
          case 2:
            if(selectedSpinnerOption.equalsIgnoreCase("Person")){
              tipoC = ContactType.PERSON;
            }else{
              tipoC = ContactType.COMPANY;
            }
            List<Contact> filtro2 = contactFilter.filtrarPorTipo(contactsList,tipoC);
            updateRecyclerView(filtro2);
            break;
          case 3:
            if(selectedSpinnerOption.equals("Instagram")){
              socialMedia = SocialMedia.INSTAGRAM;
            } else if (selectedSpinnerOption.equalsIgnoreCase("Facebook")) {
              socialMedia = SocialMedia.FACEBOOK;
            } else if (selectedSpinnerOption.equalsIgnoreCase("YouTube")) {
              socialMedia = SocialMedia.YOUTUBE;
            } else {
              socialMedia = SocialMedia.TWITTER;
            }
            List<Contact> filtro3 = contactFilter.filtrarPorRedSocial(contactsList,socialMedia);
            updateRecyclerView(filtro3);
            break;

          default:
            updateRecyclerView(contactsList);
            break;



        }
        //Log.d("Filtro", "Filtro seleccionado: " + filtroSeleccionado + " - " + selectedSpinnerOption);

        // Aplica el filtro cada vez que se selecciona una opción en el Spinner
        //applyFilter(selectedFilterType, selectedSpinnerOption);
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

  private void quitarFiltro(){
    updateRecyclerView(contactsList);
    spinnerFilter.setVisibility(View.GONE);
    radioGroupFiltros.setVisibility(View.GONE);


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
