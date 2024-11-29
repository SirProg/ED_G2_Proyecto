package com.example.dsproyect_p1.modules.contact_details.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dsproyect_p1.R;
import com.example.dsproyect_p1.data.model.Address;
import com.example.dsproyect_p1.data.model.AssociateContact;
import com.example.dsproyect_p1.data.model.Contact;
import com.example.dsproyect_p1.data.model.Email;
import com.example.dsproyect_p1.data.model.EventDate;
import com.example.dsproyect_p1.data.model.SocialMediaAccount;
import com.example.dsproyect_p1.data.model.Telephone;
import com.example.dsproyect_p1.data.repository.ContactRepository;
import com.example.dsproyect_p1.modules.edit_contact.view.EditContactActivity;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ContactDetailsActivity extends AppCompatActivity {
    LinearLayout telephoneContent, adressContent, emailContent, eventDateContent, associateContactContent, socialMediaContent;
    TextView name, residencyCountry;
    @Inject
    ContactRepository contactRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contacts_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        name = findViewById(R.id.idNameDetailContatc);
        residencyCountry = findViewById(R.id.idResidencyDetailContatc);
        telephoneContent = findViewById(R.id.idTelephoneContact);
        adressContent = findViewById(R.id.idAddressContact);
        emailContent = findViewById(R.id.idEmailContact);
        eventDateContent = findViewById(R.id.idEventDateContact);
        associateContactContent = findViewById(R.id.idAssociateContact);
        socialMediaContent = findViewById(R.id.idSocialMediaContact);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Contact contact = getIntent().getParcelableExtra("CONTACT", Contact.class);
        if(contact != null){
            loadContact(contact);
        }else{
            Toast.makeText(this, "Error al cargar el contacto", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public void loadContact(Contact contact){
        name.setText(contact.getName());
        residencyCountry.setText(contact.getResidencyCountry());
        loadTelephone(contact.getTelephones());
        loadEmails(contact.getEmails());
        loadAddress(contact.getAddresses());
        loadEventDates(contact.getEventDates());
        loadAssociateContacts(contact.getAssociateContacts());
        loadSocialMedia(contact.getSocialMediaAccounts());
    }

    public void loadTelephone(List<Telephone> telephones){
        if(!telephones.isEmpty()){
            for(Telephone telephone : telephones){
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                TextView textTelephoneC = new TextView(this);
                textTelephoneC.setId(View.generateViewId());
                textTelephoneC.setLayoutParams(new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1
                ));
                textTelephoneC.setTextColor(ContextCompat.getColor(this, R.color.md_theme_onSurface));
                textTelephoneC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textTelephoneC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textTelephoneC.setPadding(5, 5, 5, 5);
                textTelephoneC.setText(telephone.getNumber());
                TextView textLabelC = new TextView(this);
                textLabelC.setId(View.generateViewId());
                textLabelC.setLayoutParams(new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1
                ));
                textLabelC.setTextColor(ContextCompat.getColor(this, R.color.md_theme_onSurface));
                textLabelC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textLabelC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textLabelC.setPadding(5, 5, 5, 5);
                textLabelC.setText(telephone.getLabel());

                linearLayout.addView(textTelephoneC);
                linearLayout.addView(textLabelC);

                telephoneContent.addView(linearLayout);
            }
        }

    }

    public void loadAddress(List<Address> addresses){
        if(!addresses.isEmpty()){
            for(Address address: addresses) {
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                TextView textAdressC = new TextView(this);
                textAdressC.setId(View.generateViewId());
                textAdressC.setLayoutParams(new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1
                ));
                textAdressC.setTextColor(ContextCompat.getColor(this, R.color.md_theme_onSurface));
                textAdressC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textAdressC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textAdressC.setPadding(5, 5, 5, 5);
                textAdressC.setText(address.getDescription());

                TextView textAdressLabelC = new TextView(this);
                textAdressLabelC.setId(View.generateViewId());
                textAdressLabelC.setLayoutParams(new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1
                ));
                textAdressLabelC.setTextColor(ContextCompat.getColor(this, R.color.md_theme_onSurface));
                textAdressLabelC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textAdressLabelC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textAdressLabelC.setPadding(5, 5, 5, 5);
                textAdressLabelC.setText(address.getLabel());

                linearLayout.addView(textAdressC);
                linearLayout.addView(textAdressLabelC);

                adressContent.addView(linearLayout);
            }
        }
    }

    public void loadEmails(List<Email> emails){
        if(!emails.isEmpty()){
            for(Email email : emails) {
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                TextView textEmailC = new TextView(this);
                textEmailC.setId(View.generateViewId());
                textEmailC.setLayoutParams(new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1
                ));
                textEmailC.setTextColor(ContextCompat.getColor(this, R.color.md_theme_onSurface));
                textEmailC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textEmailC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textEmailC.setPadding(5, 5, 5, 5);
                textEmailC.setText(email.getEmail());

                TextView textEmailLabelC = new TextView(this);
                textEmailLabelC.setId(View.generateViewId());
                textEmailLabelC.setLayoutParams(new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1
                ));
                textEmailLabelC.setTextColor(ContextCompat.getColor(this, R.color.md_theme_onSurface));
                textEmailLabelC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textEmailLabelC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textEmailLabelC.setPadding(5, 5, 5, 5);
                textEmailLabelC.setText(email.getLabel());

                linearLayout.addView(textEmailC);
                linearLayout.addView(textEmailLabelC);

                emailContent.addView(linearLayout);
            }
        }
    }

    public void loadSocialMedia(List<SocialMediaAccount> socialMediaAccounts){
        if(!socialMediaAccounts.isEmpty()){
            for(SocialMediaAccount socialMediaAccount: socialMediaAccounts){
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                TextView textSocialMediaC = new TextView(this);
                textSocialMediaC.setId(View.generateViewId());
                textSocialMediaC.setLayoutParams(new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1
                ));
                textSocialMediaC.setTextColor(ContextCompat.getColor(this, R.color.md_theme_onSurface));
                textSocialMediaC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textSocialMediaC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textSocialMediaC.setPadding(5, 5, 5, 5);
                textSocialMediaC.setText(socialMediaAccount.getSocialMedia().name());

                TextView textSocialMediaLabelC = new TextView(this);
                textSocialMediaLabelC.setId(View.generateViewId());
                textSocialMediaLabelC.setLayoutParams(new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1
                ));
                textSocialMediaLabelC.setTextColor(ContextCompat.getColor(this, R.color.md_theme_onSurface));
                textSocialMediaLabelC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textSocialMediaLabelC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textSocialMediaLabelC.setPadding(5, 5, 5, 5);
                textSocialMediaLabelC.setText(socialMediaAccount.getUser());

                linearLayout.addView(textSocialMediaC);
                linearLayout.addView(textSocialMediaLabelC);

                socialMediaContent.addView(linearLayout);
            }
        }

    }

    public void loadAssociateContacts(List<AssociateContact> associateContacts){
        if(!associateContacts.isEmpty()){
            for(AssociateContact associateContact : associateContacts) {
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                TextView textAssociateC = new TextView(this);
                textAssociateC.setId(View.generateViewId());
                textAssociateC.setLayoutParams(new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1
                ));
                textAssociateC.setTextColor(ContextCompat.getColor(this, R.color.md_theme_onSurface));
                textAssociateC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textAssociateC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textAssociateC.setPadding(5, 5, 5, 5);
                textAssociateC.setText(associateContact.getName());

                TextView textAssociateLabelC = new TextView(this);
                textAssociateLabelC.setId(View.generateViewId());
                textAssociateLabelC.setLayoutParams(new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1
                ));
                textAssociateLabelC.setTextColor(ContextCompat.getColor(this, R.color.md_theme_onSurface));
                textAssociateLabelC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textAssociateLabelC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textAssociateLabelC.setPadding(5, 5, 5, 5);
                textAssociateLabelC.setText(associateContact.getTelephone());

                linearLayout.addView(textAssociateC);
                linearLayout.addView(textAssociateLabelC);

                associateContactContent.addView(linearLayout);
            }
        }
    }

    public void loadEventDates(List<EventDate> eventDates){
        if(!eventDates.isEmpty()){
            for(EventDate eventDate : eventDates) {
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                TextView textEventDateC = new TextView(this);
                textEventDateC.setId(View.generateViewId());
                textEventDateC.setLayoutParams(new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1
                ));
                textEventDateC.setTextColor(ContextCompat.getColor(this, R.color.md_theme_onSurface));
                textEventDateC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textEventDateC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textEventDateC.setPadding(5, 5, 5, 5);
                textEventDateC.setText(eventDate.getDate().toString());

                TextView textEventDateLabelC = new TextView(this);
                textEventDateLabelC.setId(View.generateViewId());
                textEventDateLabelC.setLayoutParams(new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1
                ));
                textEventDateLabelC.setTextColor(ContextCompat.getColor(this, R.color.md_theme_onSurface));
                textEventDateLabelC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textEventDateLabelC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textEventDateLabelC.setPadding(5, 5, 5, 5);
                textEventDateLabelC.setText(eventDate.getLabel());

                linearLayout.addView(textEventDateC);
                linearLayout.addView(textEventDateLabelC);

                eventDateContent.addView(linearLayout);
            }
        }
    }

    public void closeDetail(View view){
        finish();
    }

    public void deleteContact(View view){
        contactRepository.deleteContact(getIntent().getParcelableExtra("CONTACT", Contact.class).getId()).thenRun(()->{
            runOnUiThread(() -> {
                Toast.makeText(this, "Contacto eliminado", Toast.LENGTH_SHORT).show();
                finish();
            });
        });
    }

    public void editContact(View view){
        Intent intent = new Intent(ContactDetailsActivity.this, EditContactActivity.class);
        intent.putExtra("EditContact", getIntent().getParcelableExtra("CONTACT", Contact.class));
        startActivity(intent);
    }
}
