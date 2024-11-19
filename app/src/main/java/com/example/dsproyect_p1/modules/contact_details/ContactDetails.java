package com.example.dsproyect_p1.modules.contact_details;

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

public class ContactDetails extends AppCompatActivity {
    LinearLayout telephone, emails, adress;
    TextView name, lastName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        name = findViewById(R.id.idNameDetailContatc);
        name.setText("");

        lastName = findViewById(R.id.idLastLastNameDetailContatc);
        lastName.setText("");

        telephone = findViewById(R.id.telephonesPerson);

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

        adress = findViewById(R.id.adressesPerson);

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

        emails = findViewById(R.id.EmailsPerson);

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

        emails.addView(textEmailC);
        emails.addView(textEmailLabelC);

    }

    public void closeDetail(View view){
        finish();
    }

    public void editContact(View view){
        Intent intent = new Intent(ContactDetails.this, EditContact.class);
        startActivity(intent);
    }
}