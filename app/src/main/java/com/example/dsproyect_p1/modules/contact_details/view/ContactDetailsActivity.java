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
import com.example.dsproyect_p1.modules.edit_contact.view.EditContactActivity;

import java.util.List;

public class ContactDetailsActivity extends AppCompatActivity {
    LinearLayout telephoneContent, adressContent, emailContent, eventDateContent, associateContactContent, socialMediaContent;
    TextView name, residencyCountry;
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

        Contact contact = getIntent().getParcelableExtra("ContactDetailsActivity", Contact.class);
        if(contact != null){
            loadContact(contact);
        }else{
            Toast.makeText(this, "Error al cargar el contacto", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public void loadContact(Contact contact){
        name.setText(contact.getName());
        Toast.makeText(this, " " + contact.getResidencyCountry(), Toast.LENGTH_SHORT).show();
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
                TextView textTelephoneC = new TextView(this);
                textTelephoneC.setId(View.generateViewId());
                textTelephoneC.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                textTelephoneC.setTextColor(ContextCompat.getColor(this, R.color.md_theme_onSurface));
                textTelephoneC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textTelephoneC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textTelephoneC.setPadding(5, 5, 5, 5);
                textTelephoneC.setText(telephone.getNumber());
                TextView textLabelC = new TextView(this);
                textLabelC.setId(View.generateViewId());
                textLabelC.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                textLabelC.setTextColor(ContextCompat.getColor(this, R.color.md_theme_onSurface));
                textLabelC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textLabelC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textLabelC.setPadding(5, 5, 5, 5);
                textLabelC.setText(telephone.getLabel());

                telephoneContent.addView(textTelephoneC);
                telephoneContent.addView(textLabelC);
            }
        }

    }

    public void loadAddress(List<Address> addresses){
        if(!addresses.isEmpty()){
            for(Address address: addresses) {
                TextView textAdressC = new TextView(this);
                textAdressC.setId(View.generateViewId());
                textAdressC.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                textAdressC.setTextColor(ContextCompat.getColor(this, R.color.md_theme_onSurface));
                textAdressC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textAdressC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textAdressC.setPadding(5, 5, 5, 5);
                textAdressC.setText(address.getDescription());

                TextView textAdressLabelC = new TextView(this);
                textAdressLabelC.setId(View.generateViewId());
                textAdressLabelC.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                textAdressLabelC.setTextColor(ContextCompat.getColor(this, R.color.md_theme_onSurface));
                textAdressLabelC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textAdressLabelC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textAdressLabelC.setPadding(5, 5, 5, 5);
                textAdressLabelC.setText(address.getLabel());

                adressContent.addView(textAdressC);
                adressContent.addView(textAdressLabelC);
            }
        }
    }

    public void loadEmails(List<Email> emails){
        if(!emails.isEmpty()){
            for(Email email : emails) {
                TextView textEmailC = new TextView(this);
                textEmailC.setId(View.generateViewId());
                textEmailC.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                textEmailC.setTextColor(ContextCompat.getColor(this, R.color.md_theme_onSurface));
                textEmailC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textEmailC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textEmailC.setPadding(5, 5, 5, 5);
                textEmailC.setText(email.getEmail());

                TextView textEmailLabelC = new TextView(this);
                textEmailLabelC.setId(View.generateViewId());
                textEmailLabelC.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                textEmailLabelC.setTextColor(ContextCompat.getColor(this, R.color.md_theme_onSurface));
                textEmailLabelC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textEmailLabelC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textEmailLabelC.setPadding(5, 5, 5, 5);
                textEmailLabelC.setText(email.getLabel());

                emailContent.addView(textEmailC);
                emailContent.addView(textEmailLabelC);
            }
        }
    }

    public void loadSocialMedia(List<SocialMediaAccount> socialMediaAccounts){
        if(!socialMediaAccounts.isEmpty()){
            for(SocialMediaAccount socialMediaAccount: socialMediaAccounts){
                TextView textSocialMediaC = new TextView(this);
                textSocialMediaC.setId(View.generateViewId());
                textSocialMediaC.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                textSocialMediaC.setTextColor(ContextCompat.getColor(this, R.color.md_theme_onSurface));
                textSocialMediaC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textSocialMediaC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textSocialMediaC.setPadding(5, 5, 5, 5);
                textSocialMediaC.setText(socialMediaAccount.getSocialMedia().name());

                TextView textSocialMediaLabelC = new TextView(this);
                textSocialMediaLabelC.setId(View.generateViewId());
                textSocialMediaLabelC.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                textSocialMediaLabelC.setTextColor(ContextCompat.getColor(this, R.color.md_theme_onSurface));
                textSocialMediaLabelC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textSocialMediaLabelC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textSocialMediaLabelC.setPadding(5, 5, 5, 5);
                textSocialMediaLabelC.setText(socialMediaAccount.getUser());

                socialMediaContent.addView(textSocialMediaC);
                socialMediaContent.addView(textSocialMediaLabelC);
            }
        }

    }

    public void loadAssociateContacts(List<AssociateContact> associateContacts){
        if(!associateContacts.isEmpty()){
            for(AssociateContact associateContact : associateContacts) {
                TextView textAssociateC = new TextView(this);
                textAssociateC.setId(View.generateViewId());
                textAssociateC.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                textAssociateC.setTextColor(ContextCompat.getColor(this, R.color.md_theme_onSurface));
                textAssociateC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textAssociateC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textAssociateC.setPadding(5, 5, 5, 5);
                textAssociateC.setText(associateContact.getName());

                TextView textAssociateLabelC = new TextView(this);
                textAssociateLabelC.setId(View.generateViewId());
                textAssociateLabelC.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                textAssociateLabelC.setTextColor(ContextCompat.getColor(this, R.color.md_theme_onSurface));
                textAssociateLabelC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textAssociateLabelC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textAssociateLabelC.setPadding(5, 5, 5, 5);
                textAssociateLabelC.setText(associateContact.getTelephone());

                associateContactContent.addView(textAssociateC);
                associateContactContent.addView(textAssociateLabelC);
            }
        }
    }

    public void loadEventDates(List<EventDate> eventDates){
        if(!eventDates.isEmpty()){
            for(EventDate eventDate : eventDates) {
                TextView textEventDateC = new TextView(this);
                textEventDateC.setId(View.generateViewId());
                textEventDateC.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                textEventDateC.setTextColor(ContextCompat.getColor(this, R.color.md_theme_onSurface));
                textEventDateC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textEventDateC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textEventDateC.setPadding(5, 5, 5, 5);
                textEventDateC.setText(eventDate.getDate().toString());

                TextView textEventDateLabelC = new TextView(this);
                textEventDateLabelC.setId(View.generateViewId());
                textEventDateLabelC.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                textEventDateLabelC.setTextColor(ContextCompat.getColor(this, R.color.md_theme_onSurface));
                textEventDateLabelC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textEventDateLabelC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textEventDateLabelC.setPadding(5, 5, 5, 5);
                textEventDateLabelC.setText(eventDate.getLabel());

                eventDateContent.addView(textEventDateC);
                eventDateContent.addView(textEventDateLabelC);
            }
        }
    }

    public void closeDetail(View view){
        finish();
    }

    public void editContact(View view){
        Intent intent = new Intent(ContactDetailsActivity.this, EditContactActivity.class);
        intent.putExtra("EditContact", getIntent().getParcelableExtra("ContactDetailsActivity", Contact.class));
        startActivity(intent);
    }
}
