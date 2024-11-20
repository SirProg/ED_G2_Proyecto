package com.example.dsproyect_p1.modules.company_details.view;

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
import com.example.dsproyect_p1.modules.edit_contact.EditContact;

public class CompanyDetailsActivity extends AppCompatActivity {
    LinearLayout telephone, adress, email, eventDate, associateContact, socialMedia;
    TextView name, description, residencyCountry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_company_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        name = findViewById(R.id.idNameDetailContatc);
        name.setText("");
        description = findViewById(R.id.idDescriptionDetailContatc);
        description.setText("");
        residencyCountry = findViewById(R.id.idResidencyDetailContatc);
        residencyCountry.setText("");

        telephone = findViewById(R.id.telephonesCompany);

        TextView textTelephoneC = new TextView(this);
        textTelephoneC.setId(View.generateViewId());
        textTelephoneC.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        textTelephoneC.setTextColor(ContextCompat.getColor(this, R.color.textColorPrimary));
        textTelephoneC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        textTelephoneC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textTelephoneC.setPadding(5, 5, 5, 5);
        textTelephoneC.setText("Teléfono: 123-456-7890");
        TextView textLabelC = new TextView(this);
        textLabelC.setId(View.generateViewId());
        textLabelC.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        textLabelC.setTextColor(ContextCompat.getColor(this, R.color.textColorPrimary));
        textLabelC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        textLabelC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textLabelC.setPadding(5, 5, 5, 5);
        textLabelC.setText("Etiqueta: Información de contacto");

        telephone.addView(textTelephoneC);
        telephone.addView(textLabelC);


        adress = findViewById(R.id.adressesCompany);

        TextView textAdressC = new TextView(this);
        textAdressC.setId(View.generateViewId());
        textAdressC.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        textAdressC.setTextColor(ContextCompat.getColor(this, R.color.textColorPrimary));
        textAdressC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        textAdressC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textAdressC.setPadding(5, 5, 5, 5);
        textAdressC.setText("Teléfono: 123-456-7890");

        TextView textAdressLabelC = new TextView(this);
        textAdressLabelC.setId(View.generateViewId());
        textAdressLabelC.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        textAdressLabelC.setTextColor(ContextCompat.getColor(this, R.color.textColorPrimary));
        textAdressLabelC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        textAdressLabelC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textAdressLabelC.setPadding(5, 5, 5, 5);
        textAdressLabelC.setText("Etiqueta: Información de contacto");

        adress.addView(textAdressC);
        adress.addView(textAdressLabelC);


        email = findViewById(R.id.EmailsCompany);

        TextView textEmailC = new TextView(this);
        textEmailC.setId(View.generateViewId());
        textEmailC.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        textEmailC.setTextColor(ContextCompat.getColor(this, R.color.textColorPrimary));
        textEmailC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        textEmailC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textEmailC.setPadding(5, 5, 5, 5);
        textEmailC.setText("Teléfono: 123-456-7890");

        TextView textEmailLabelC = new TextView(this);
        textEmailLabelC.setId(View.generateViewId());
        textEmailLabelC.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        textEmailLabelC.setTextColor(ContextCompat.getColor(this, R.color.textColorPrimary));
        textEmailLabelC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        textEmailLabelC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textEmailLabelC.setPadding(5, 5, 5, 5);
        textEmailLabelC.setText("Etiqueta: Información de contacto");

        email.addView(textEmailC);
        email.addView(textEmailLabelC);

        eventDate = findViewById(R.id.idEventDateContact);

        TextView textEventDateC = new TextView(this);
        textEventDateC.setId(View.generateViewId());
        textEventDateC.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        textEventDateC.setTextColor(ContextCompat.getColor(this, R.color.textColorPrimary));
        textEventDateC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        textEventDateC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textEventDateC.setPadding(5, 5, 5, 5);
        textEventDateC.setText("Teléfono: 123-456-7890");

        TextView textEventDateLabelC = new TextView(this);
        textEventDateLabelC.setId(View.generateViewId());
        textEventDateLabelC.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        textEventDateLabelC.setTextColor(ContextCompat.getColor(this, R.color.textColorPrimary));
        textEventDateLabelC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        textEventDateLabelC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textEventDateLabelC.setPadding(5, 5, 5, 5);
        textEmailLabelC.setText("Etiqueta: Información de contacto");

        eventDate.addView(textEventDateC);
        eventDate.addView(textEventDateLabelC);

        associateContact = findViewById(R.id.idAssociateContact);

        TextView textAssociateC = new TextView(this);
        textAssociateC.setId(View.generateViewId());
        textAssociateC.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        textAssociateC.setTextColor(ContextCompat.getColor(this, R.color.textColorPrimary));
        textAssociateC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        textAssociateC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textAssociateC.setPadding(5, 5, 5, 5);
        textAssociateC.setText("Teléfono: 123-456-7890");

        TextView textAssociateLabelC = new TextView(this);
        textAssociateLabelC.setId(View.generateViewId());
        textAssociateLabelC.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        textAssociateLabelC.setTextColor(ContextCompat.getColor(this, R.color.textColorPrimary));
        textAssociateLabelC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        textAssociateLabelC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textAssociateLabelC.setPadding(5, 5, 5, 5);
        textAssociateLabelC.setText("Etiqueta: Información de contacto");

        associateContact.addView(textAssociateC);
        associateContact.addView(textAssociateLabelC);


        socialMedia = findViewById(R.id.idSocialMediaContact);

        TextView textSocialMediaC = new TextView(this);
        textSocialMediaC.setId(View.generateViewId());
        textSocialMediaC.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        textSocialMediaC.setTextColor(ContextCompat.getColor(this, R.color.textColorPrimary));
        textSocialMediaC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        textSocialMediaC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textSocialMediaC.setPadding(5, 5, 5, 5);
        textSocialMediaC.setText("Teléfono: 123-456-7890");

        TextView textSocialMediaLabelC = new TextView(this);
        textSocialMediaLabelC.setId(View.generateViewId());
        textSocialMediaLabelC.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        textSocialMediaLabelC.setTextColor(ContextCompat.getColor(this, R.color.textColorPrimary));
        textSocialMediaLabelC.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        textSocialMediaLabelC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textSocialMediaLabelC.setPadding(5, 5, 5, 5);
        textSocialMediaLabelC.setText("Etiqueta: Información de contacto");

        socialMedia.addView(textSocialMediaC);
        socialMedia.addView(textSocialMediaLabelC);

    }

    public void closeDetail(View view){
        finish();
    }

    public void editContact(View view){
        Intent intent = new Intent(CompanyDetailsActivity.this, EditContact.class);
        startActivity(intent);
    }
}
