package com.example.dsproyect_p1;

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
import com.example.dsproyect_p1.modules.adapter.AdapterRecyclerView;
import com.example.dsproyect_p1.modules.add_company.AddCompanyActivity;
import com.example.dsproyect_p1.modules.add_person.AddPersonActivity;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.inject.Inject;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
  private RecyclerView recyclerView;
  private Button btnContacts, btnFavorite, btnOrder;
  private Button btnAddContact;
  private AdapterRecyclerView adapterRecyclerView;

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

    adapterRecyclerView = new AdapterRecyclerView(this, List.of());
    recyclerView.setAdapter(adapterRecyclerView);

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
                    adapterRecyclerView = new AdapterRecyclerView(MainActivity.this, persons);
                    recyclerView.setAdapter(adapterRecyclerView);
                  });
            })
        .exceptionally(
            ex -> {
              runOnUiThread(
                  () -> {
                    Toast.makeText(MainActivity.this, "Failed to load contacts", Toast.LENGTH_SHORT)
                        .show();
                  });
              return null;
            });
  }

  public void openPopupMenuAdd(View anchorView) {
    View viewPopup = LayoutInflater.from(MainActivity.this).inflate(R.layout.menu_item_addcontact, null);
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
            Intent intent = new Intent(MainActivity.this, AddPersonActivity.class);
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
            Intent intent = new Intent(MainActivity.this, AddCompanyActivity.class);
            startActivity(intent);
          }
        });
    popupWindow.showAtLocation(anchorView, Gravity.CENTER, 0, 0);
  }
}
