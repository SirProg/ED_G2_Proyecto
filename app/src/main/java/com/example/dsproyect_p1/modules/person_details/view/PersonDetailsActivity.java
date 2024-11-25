package com.example.dsproyect_p1.modules.person_details.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.dsproyect_p1.R;
import com.example.dsproyect_p1.data.model.Address;
import com.example.dsproyect_p1.data.model.Email;
import com.example.dsproyect_p1.data.model.Person;
import com.example.dsproyect_p1.data.model.Telephone;
import com.example.dsproyect_p1.modules.edit_person.view.EditPersonActivity;

import java.util.List;

public class PersonDetailsActivity extends AppCompatActivity {
  LinearLayout telephone, emails, adress;
  Person personEdit;
  TextView name, lastName;
  List<Telephone> telephones;
  List<Address> addresses;
  List<Email> emailsList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_person_details);
    ViewCompat.setOnApplyWindowInsetsListener(
        findViewById(R.id.main),
        (v, insets) -> {
          Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
          v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
          return insets;
        });

    @SuppressWarnings("deprecation")
    Person person =(Person)getIntent().getSerializableExtra("PERSON");
    personEdit = person;
    name = findViewById(R.id.idNameDetailContatc);
    name.setText(person.getFirstName());

    lastName = findViewById(R.id.idLastLastNameDetailContatc);
    lastName.setText(person.getLastName());

    telephone = findViewById(R.id.telephonesPerson);
    telephones = person.getTelephones();

    adress = findViewById(R.id.adressesPerson);
    addresses = person.getAddresses();

    emails = findViewById(R.id.EmailsPerson);
    emailsList = person.getEmails();

    loadPersonDetails();
  }

  @Override
  protected void onResume() {
    super.onResume();
    loadPersonDetails();
  }

  public void loadPersonDetails(){

    for(Telephone telephoneData: telephones){
      TextView textTelephoneC = new TextView(this);
      textTelephoneC.setId(View.generateViewId());
      textTelephoneC.setLayoutParams(
              new LinearLayout.LayoutParams(
                      LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
      textTelephoneC.setTextColor(ContextCompat.getColor(this, R.color.md_theme_onSurface));
      textTelephoneC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
      textTelephoneC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
      textTelephoneC.setPadding(5, 5, 5, 5);
      textTelephoneC.setText(telephoneData.getNumber());
      TextView textLabelC = new TextView(this);
      textLabelC.setId(View.generateViewId());
      textLabelC.setLayoutParams(
              new LinearLayout.LayoutParams(
                      LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
      textLabelC.setTextColor(ContextCompat.getColor(this, R.color.md_theme_onSurface));
      textLabelC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
      textLabelC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
      textLabelC.setPadding(5, 5, 5, 5);
      textLabelC.setText(telephoneData.getLabel());

      telephone.addView(textTelephoneC);
      telephone.addView(textLabelC);
    }

    for(Address addressData: addresses){
      TextView textAdressC = new TextView(this);
      textAdressC.setId(View.generateViewId());
      textAdressC.setLayoutParams(
              new LinearLayout.LayoutParams(
                      LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
      textAdressC.setTextColor(ContextCompat.getColor(this, R.color.md_theme_onSurface));
      textAdressC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
      textAdressC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
      textAdressC.setPadding(5, 5, 5, 5);
      textAdressC.setText(addressData.getDescription());

      TextView textAdressLabelC = new TextView(this);
      textAdressLabelC.setId(View.generateViewId());
      textAdressLabelC.setLayoutParams(
              new LinearLayout.LayoutParams(
                      LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
      textAdressLabelC.setTextColor(ContextCompat.getColor(this, R.color.md_theme_onSurface));
      textAdressLabelC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
      textAdressLabelC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
      textAdressLabelC.setPadding(5, 5, 5, 5);
      textAdressLabelC.setText(addressData.getLabel());

      adress.addView(textAdressC);
      adress.addView(textAdressLabelC);
    }

    for(Email emailData: emailsList){
      TextView textEmailC = new TextView(this);
      textEmailC.setId(View.generateViewId());
      textEmailC.setLayoutParams(
              new LinearLayout.LayoutParams(
                      LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
      textEmailC.setTextColor(ContextCompat.getColor(this, R.color.md_theme_onSurface));
      textEmailC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
      textEmailC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
      textEmailC.setPadding(5, 5, 5, 5);
      textEmailC.setText(emailData.getEmail());

      TextView textEmailLabelC = new TextView(this);
      textEmailLabelC.setId(View.generateViewId());
      textEmailLabelC.setLayoutParams(
              new LinearLayout.LayoutParams(
                      LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
      textEmailLabelC.setTextColor(ContextCompat.getColor(this, R.color.md_theme_onSurface));
      textEmailLabelC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
      textEmailLabelC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
      textEmailLabelC.setPadding(5, 5, 5, 5);
      textEmailLabelC.setText(emailData.getLabel());

      emails.addView(textEmailC);
      emails.addView(textEmailLabelC);
    }
  }

  public void closeDetail(View view) {
    finish();
  }

  public void editContact(View view) {
    Intent intent = new Intent(PersonDetailsActivity.this, EditPersonActivity.class);
    intent.putExtra("PERSON_EDIT", personEdit);
    startActivity(intent);
  }
}
