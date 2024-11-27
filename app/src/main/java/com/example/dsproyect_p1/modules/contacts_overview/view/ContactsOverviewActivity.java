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
import com.example.dsproyect_p1.data.model.Contact;
import com.example.dsproyect_p1.data.model.ContactType;
import com.example.dsproyect_p1.data.repository.*;
import com.example.dsproyect_p1.modules.add_contact.view.AddContactActivity;
import com.example.dsproyect_p1.modules.contact_details.view.ContactDetailsActivity;
import com.example.dsproyect_p1.modules.contacts_overview.adapter.ContactRecyclerView;
import dagger.hilt.android.AndroidEntryPoint;
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

    recyclerViewContact = findViewById(R.id.recyclerViewContacts);
    recyclerViewContact.setLayoutManager(new LinearLayoutManager(this));

    contactRecyclerView = new ContactRecyclerView(List.of(), contact -> moveToDescriptionContact(contact));
    recyclerViewContact.setAdapter(contactRecyclerView);

    fetchContacts();

    btnAddContact = findViewById(R.id.openPopUpAdd);
    btnAddContact.setOnClickListener(view -> openPopupMenuAdd(btnAddContact));
  }

  public void moveToDescriptionContact(Contact contact) {
    Intent intent = new Intent(ContactsOverviewActivity.this, ContactDetailsActivity.class);
    intent.putExtra("ContactDetailsActivity", contact);
    startActivity(intent);
  }


  @Override
  protected void onResume() {
    super.onResume();
    fetchContacts();
  }

  private void injectContacts() {
    Contact contact1 = new Contact(null, ContactType.COMPANY, "Chevrolet", null, null, null, null, null, null, null);
    Contact contact2 = new Contact(null, ContactType.PERSON, "Juan", null, null, null, null, null, null, null);
    Contact contact3 = new Contact(null, ContactType.COMPANY, "Ferrari", null, null, null, null, null, null, null);
    Contact contact4 = new Contact(null, ContactType.PERSON, "Juafra", null, null, null, null, null, null, null);
    Contact contact5 = new Contact(null, ContactType.COMPANY, "Telconet", null, null, null, null, null, null, null);
    Contact contact6 = new Contact(null, ContactType.PERSON, "Kevin", null, null, null, null, null, null, null);
    Contact contact7 = new Contact(null, ContactType.COMPANY, "Audi", null, null, null, null, null, null, null);
    Contact contact8 = new Contact(null, ContactType.PERSON, "Daniela", null, null, null, null, null, null, null);
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
    intent.putExtra("COMPANY", contact);
    startActivity(intent);
  }

  private void fetchContacts() {
    CompletableFuture<List<Contact>> future = contactRepository.getContacts();
    future
        .thenAccept(
            contacts -> {
              runOnUiThread(
                  () -> {
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

  public void openPopupMenuAdd(View anchorView) {
    View viewPopup = LayoutInflater.from(ContactsOverviewActivity.this)
        .inflate(R.layout.menu_item_addcontact, null);
    PopupWindow popupWindow = new PopupWindow(
        viewPopup,
        LinearLayout.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT,
        true);


    ImageButton iBtnContactPerson = viewPopup.findViewById(R.id.addPerson);
    iBtnContactPerson.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            popupWindow.dismiss();
            Intent intent = new Intent(ContactsOverviewActivity.this, AddContactActivity.class);
            intent.putExtra("ContactType", ContactType.PERSON);
            startActivity(intent);
          }
        });
    ImageButton iBtnContactCompany = viewPopup.findViewById(R.id.addCompany);
    iBtnContactCompany.setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                popupWindow.dismiss();
                Intent intent = new Intent(ContactsOverviewActivity.this, AddContactActivity.class);
                intent.putExtra("ContactType", ContactType.COMPANY);
                startActivity(intent);
              }
            });
    popupWindow.showAtLocation(anchorView, Gravity.CENTER, 0, 0);
  }
}
