package com.example.dsproyect_p1.modules.edit_contact.view;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
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

import java.util.Calendar;
import java.util.List;

public class EditContactActivity extends AppCompatActivity {
    LinearLayout telephoneContent, emailContent, addressContent, socialMediaContent, associatedContactsContent, eventDateContent;
    TextView name, residency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_contacts);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Contact contact = getIntent().getParcelableExtra("EditContact", Contact.class);

        name = findViewById(R.id.editName);
        residency = findViewById(R.id.editResidency);
        telephoneContent = findViewById(R.id.editTelephone);
        emailContent = findViewById(R.id.editEmail);
        addressContent = findViewById(R.id.editAddress);
        socialMediaContent = findViewById(R.id.editSocialMedia);
        associatedContactsContent = findViewById(R.id.editAssociatedContact);
        eventDateContent = findViewById(R.id.editDate);
        if(contact != null){
            loadData(contact);
        }else{
            Toast.makeText(this, "Error al cargar los datos", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public void loadData(Contact contact){
        name.setText(contact.getName());
        residency.setText(contact.getResidencyCountry());
        loadDataTelephone(contact.getTelephones());
        loadDataEmail(contact.getEmails());
        loadDataAddress(contact.getAddresses());
        loadDataSocialMedia(contact.getSocialMediaAccounts());
        loadDataDate(contact.getEventDates());
        loadDataAssociatedContacts(contact.getAssociateContacts());
    }

    public void loadDataTelephone(List<Telephone> telephones){
        for(Telephone telephoneData : telephones){
            LinearLayout nuevoTelefono = new LinearLayout(this);
            nuevoTelefono.setOrientation(LinearLayout.HORIZONTAL);
            nuevoTelefono.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            nuevoTelefono.setPadding(0, 16, 0, 16);
            Spinner spinnerEtiqueta = new Spinner(this);
            spinnerEtiqueta.setLayoutParams(
                    new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                    this, R.array.etiquetas_telefono, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerEtiqueta.setAdapter(adapter);
            spinnerEtiqueta.setSelection(adapter.getPosition(telephoneData.getLabel()));;

            EditText editTextTelefono = new EditText(this);
            editTextTelefono.setLayoutParams(
                    new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
            editTextTelefono.setHint("Teléfono");
            editTextTelefono.setText(telephoneData.getNumber());
            editTextTelefono.setInputType(InputType.TYPE_CLASS_PHONE);

            Button botonEliminar = new Button(EditContactActivity.this);
            botonEliminar.setLayoutParams(
                    new LinearLayout.LayoutParams(75, 75)); // Ancho y alto en píxeles
            botonEliminar.setText("x");
            botonEliminar.setTextSize(8);
            botonEliminar.setBackgroundColor(Color.RED);
            botonEliminar.setTextColor(Color.WHITE);

            botonEliminar.setOnClickListener(b -> telephoneContent.removeView(nuevoTelefono));
            nuevoTelefono.addView(editTextTelefono);
            nuevoTelefono.addView(spinnerEtiqueta);
            nuevoTelefono.addView(botonEliminar);

            telephoneContent.addView(nuevoTelefono, telephoneContent.getChildCount() - 1);
        }

    }

    public void loadDataEmail(List<Email> emails){
        for(Email emailData : emails){
            LinearLayout nuevoEmail = new LinearLayout(this);
            nuevoEmail.setOrientation(LinearLayout.HORIZONTAL);
            nuevoEmail.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            nuevoEmail.setPadding(0, 16, 0, 16);

            Spinner spinnerEtiqueta = new Spinner(this);
            spinnerEtiqueta.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            0, LinearLayout.LayoutParams.WRAP_CONTENT, 1)); // Peso 1

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                    this, R.array.etiquetas_email, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerEtiqueta.setAdapter(adapter);
            spinnerEtiqueta.setSelection(adapter.getPosition(emailData.getLabel()));

            EditText editTextEmail = new EditText(this);
            editTextEmail.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            0, LinearLayout.LayoutParams.WRAP_CONTENT, 1)); // Peso 2
            editTextEmail.setHint("E-mail");
            editTextEmail.setText(emailData.getEmail());
            editTextEmail.setInputType(InputType.TYPE_CLASS_TEXT);

            Button botonEliminar = new Button(EditContactActivity.this);
            botonEliminar.setLayoutParams(
                    new LinearLayout.LayoutParams(75, 75)); // Ancho y alto en píxeles
            botonEliminar.setText("x");
            botonEliminar.setTextSize(8);
            botonEliminar.setBackgroundColor(Color.RED);
            botonEliminar.setTextColor(Color.WHITE);

            botonEliminar.setOnClickListener(b -> emailContent.removeView(nuevoEmail));

            nuevoEmail.addView(editTextEmail);
            nuevoEmail.addView(spinnerEtiqueta);
            nuevoEmail.addView(botonEliminar);

            emailContent.addView(nuevoEmail, emailContent.getChildCount() - 1);
        }
    }

    public void loadDataAddress(List<Address> addresses){
        for(Address addressData : addresses){
            LinearLayout nuevaDireccion = new LinearLayout(this);
            nuevaDireccion.setOrientation(LinearLayout.HORIZONTAL);
            nuevaDireccion.setLayoutParams(
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            nuevaDireccion.setPadding(0, 16, 0, 16);

            Spinner spinnerEtiqueta = new Spinner(this);
            spinnerEtiqueta.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            0, LinearLayout.LayoutParams.WRAP_CONTENT, 1)); // Peso 1

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                    this, R.array.etiquetas_direccion, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerEtiqueta.setAdapter(adapter);
            spinnerEtiqueta.setSelection(adapter.getPosition(addressData.getLabel()));

            EditText editTextDireccion = new EditText(this);
            editTextDireccion.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            0, LinearLayout.LayoutParams.WRAP_CONTENT, 1)); // Peso 2
            editTextDireccion.setHint("Dirección");
            editTextDireccion.setText(addressData.getDescription());
            editTextDireccion.setInputType(InputType.TYPE_CLASS_TEXT);

            Button botonEliminar = new Button(this);
            botonEliminar.setLayoutParams(new LinearLayout.LayoutParams(75, 75));
            botonEliminar.setText("x");
            botonEliminar.setTextSize(8);
            botonEliminar.setBackgroundColor(Color.RED);
            botonEliminar.setTextColor(Color.WHITE);

            botonEliminar.setOnClickListener(b -> addressContent.removeView(nuevaDireccion));

            nuevaDireccion.addView(editTextDireccion);
            nuevaDireccion.addView(spinnerEtiqueta);
            nuevaDireccion.addView(botonEliminar);

            addressContent.addView(nuevaDireccion, addressContent.getChildCount() - 1);
        }
    }

    public void loadDataSocialMedia(List<SocialMediaAccount> socialMedias){
        for(SocialMediaAccount socialMediaData : socialMedias){
            LinearLayout nuevoSocial = new LinearLayout(this);
            nuevoSocial.setOrientation(LinearLayout.HORIZONTAL);
            nuevoSocial.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            nuevoSocial.setPadding(0, 16, 0, 16);

            Spinner spinnerEtiqueta = new Spinner(this);
            spinnerEtiqueta.setLayoutParams(
                    new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                    this, R.array.etiquetas_RedSocial, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerEtiqueta.setAdapter(adapter);
            spinnerEtiqueta.setSelection(adapter.getPosition(socialMediaData.getSocialMedia().name()));

            EditText editTextSocial = new EditText(this);
            editTextSocial.setLayoutParams(
                    new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
            editTextSocial.setHint("Red Social");
            editTextSocial.setInputType(InputType.TYPE_CLASS_TEXT);
            editTextSocial.setText(socialMediaData.getUser());

            Button botonEliminar = new Button(this);
            botonEliminar.setLayoutParams(
                    new LinearLayout.LayoutParams(75, 75)); // Ancho y alto en píxeles
            botonEliminar.setText("x");
            botonEliminar.setTextSize(8);
            botonEliminar.setBackgroundColor(Color.RED);
            botonEliminar.setTextColor(Color.WHITE);

            botonEliminar.setOnClickListener(b -> socialMediaContent.removeView(nuevoSocial));

            nuevoSocial.addView(editTextSocial);
            nuevoSocial.addView(spinnerEtiqueta);
            nuevoSocial.addView(botonEliminar);

            socialMediaContent.addView(nuevoSocial, socialMediaContent.getChildCount() - 1);
        }
    }

    public void loadDataDate(List<EventDate> dates){
        for(EventDate dateData : dates){
            LinearLayout nuevaFecha = new LinearLayout(this);
            nuevaFecha.setOrientation(LinearLayout.HORIZONTAL);
            nuevaFecha.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            nuevaFecha.setPadding(0, 16, 0, 16);

            Spinner spinnerEtiqueta = new Spinner(this);
            spinnerEtiqueta.setLayoutParams(
                    new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                    this, R.array.etiquetas_fecha, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerEtiqueta.setAdapter(adapter);
            spinnerEtiqueta.setSelection(adapter.getPosition(dateData.getLabel()));

            TextView textViewFecha = new TextView(this);
            textViewFecha.setLayoutParams(
                    new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
            textViewFecha.setHint("Seleccionar fecha");
            textViewFecha.setPadding(16, 16, 16, 16);
            textViewFecha.setBackgroundResource(android.R.drawable.edit_text);
            textViewFecha.setText(dateData.getDate().toString());

            Button botonEliminar = new Button(this);
            botonEliminar.setLayoutParams(
                    new LinearLayout.LayoutParams(75, 75)); // Ancho y alto en píxeles
            botonEliminar.setText("x");
            botonEliminar.setTextSize(8);
            botonEliminar.setBackgroundColor(Color.RED);
            botonEliminar.setTextColor(Color.WHITE);

            botonEliminar.setOnClickListener(b -> eventDateContent.removeView(nuevaFecha));

            textViewFecha.setOnClickListener(
                    tv -> {
                        Calendar calendario = Calendar.getInstance();
                        int anio = calendario.get(Calendar.YEAR);
                        int mes = calendario.get(Calendar.MONTH);
                        int dia = calendario.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(
                                this,
                                (view, year, monthOfYear, dayOfMonth) -> {
                                    String fechaSeleccionada = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                    textViewFecha.setText(fechaSeleccionada);
                                    textViewFecha.setTextColor(Color.BLACK);
                                },
                                anio,
                                mes,
                                dia);
                        datePickerDialog.show();
                    });

            nuevaFecha.addView(textViewFecha);
            nuevaFecha.addView(spinnerEtiqueta);

            nuevaFecha.addView(botonEliminar);

            eventDateContent.addView(nuevaFecha, eventDateContent.getChildCount() - 1);
        }
    }

    public void loadDataAssociatedContacts(List<AssociateContact> associatedContacts){
        for(AssociateContact associatedContactData : associatedContacts){
            LinearLayout newAssociateContact = new LinearLayout(this);
            newAssociateContact.setOrientation(LinearLayout.HORIZONTAL);
            newAssociateContact.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            newAssociateContact.setPadding(0, 16, 0, 16);

            EditText editTextName = new EditText(this);
            editTextName.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
            editTextName.setHint("Name");
            editTextName.setInputType(InputType.TYPE_CLASS_TEXT);
            editTextName.setText(associatedContactData.getName());

            EditText editTextTelephone = new EditText(this);
            editTextTelephone.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
            editTextTelephone.setHint("Telephone");
            editTextTelephone.setInputType(InputType.TYPE_CLASS_TEXT);
            editTextTelephone.setText(associatedContactData.getTelephone());

            Button botonEliminar = new Button(this);
            botonEliminar.setLayoutParams(
                    new LinearLayout.LayoutParams(75, 75)); // Ancho y alto en píxeles
            botonEliminar.setText("x");
            botonEliminar.setTextSize(8);
            botonEliminar.setBackgroundColor(Color.RED);
            botonEliminar.setGravity(Gravity.CENTER);
            botonEliminar.setTextColor(Color.WHITE);

            botonEliminar.setOnClickListener(b -> associatedContactsContent.removeView(newAssociateContact));

            newAssociateContact.addView(editTextName);
            newAssociateContact.addView(editTextTelephone);
            newAssociateContact.addView(botonEliminar);

            associatedContactsContent.addView(newAssociateContact, associatedContactsContent.getChildCount() - 1);
        }
    }



    public void addEditTelephone(View viewTelephone){
        LinearLayout nuevoTelefono = new LinearLayout(this);
        nuevoTelefono.setOrientation(LinearLayout.HORIZONTAL);
        nuevoTelefono.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        nuevoTelefono.setPadding(0, 16, 0, 16);
        Spinner spinnerEtiqueta = new Spinner(this);
        spinnerEtiqueta.setLayoutParams(
                new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.etiquetas_telefono, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEtiqueta.setAdapter(adapter);

        EditText editTextTelefono = new EditText(this);
        editTextTelefono.setLayoutParams(
                new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        editTextTelefono.setHint("Teléfono");
        editTextTelefono.setInputType(InputType.TYPE_CLASS_PHONE);

        Button botonEliminar = new Button(EditContactActivity.this);
        botonEliminar.setLayoutParams(
                new LinearLayout.LayoutParams(75, 75)); // Ancho y alto en píxeles
        botonEliminar.setText("x");
        botonEliminar.setTextSize(8);
        botonEliminar.setBackgroundColor(Color.RED);
        botonEliminar.setTextColor(Color.WHITE);

        botonEliminar.setOnClickListener(b -> telephoneContent.removeView(nuevoTelefono));
        nuevoTelefono.addView(editTextTelefono);
        nuevoTelefono.addView(spinnerEtiqueta);
        nuevoTelefono.addView(botonEliminar);

        telephoneContent.addView(nuevoTelefono, telephoneContent.getChildCount() - 1);
    }

    public void addEditEmail(View viewEmail){
        LinearLayout nuevoEmail = new LinearLayout(this);
        nuevoEmail.setOrientation(LinearLayout.HORIZONTAL);
        nuevoEmail.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        nuevoEmail.setPadding(0, 16, 0, 16);

        Spinner spinnerEtiqueta = new Spinner(this);
        spinnerEtiqueta.setLayoutParams(
                new LinearLayout.LayoutParams(
                        0, LinearLayout.LayoutParams.WRAP_CONTENT, 1)); // Peso 1

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.etiquetas_email, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEtiqueta.setAdapter(adapter);

        EditText editTextEmail = new EditText(this);
        editTextEmail.setLayoutParams(
                new LinearLayout.LayoutParams(
                        0, LinearLayout.LayoutParams.WRAP_CONTENT, 1)); // Peso 2
        editTextEmail.setHint("E-mail");
        editTextEmail.setInputType(InputType.TYPE_CLASS_TEXT);

        Button botonEliminar = new Button(EditContactActivity.this);
        botonEliminar.setLayoutParams(
                new LinearLayout.LayoutParams(75, 75)); // Ancho y alto en píxeles
        botonEliminar.setText("x");
        botonEliminar.setTextSize(8);
        botonEliminar.setBackgroundColor(Color.RED);
        botonEliminar.setTextColor(Color.WHITE);

        botonEliminar.setOnClickListener(b -> emailContent.removeView(nuevoEmail));

        nuevoEmail.addView(editTextEmail);
        nuevoEmail.addView(spinnerEtiqueta);
        nuevoEmail.addView(botonEliminar);

        emailContent.addView(nuevoEmail, emailContent.getChildCount() - 1);
    }
    public void addEditAddress(View viewAddress){
        LinearLayout nuevaDireccion = new LinearLayout(this);
        nuevaDireccion.setOrientation(LinearLayout.HORIZONTAL);
        nuevaDireccion.setLayoutParams(
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        nuevaDireccion.setPadding(0, 16, 0, 16);

        Spinner spinnerEtiqueta = new Spinner(this);
        spinnerEtiqueta.setLayoutParams(
                new LinearLayout.LayoutParams(
                        0, LinearLayout.LayoutParams.WRAP_CONTENT, 1)); // Peso 1

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.etiquetas_direccion, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEtiqueta.setAdapter(adapter);

        EditText editTextDireccion = new EditText(this);
        editTextDireccion.setLayoutParams(
                new LinearLayout.LayoutParams(
                        0, LinearLayout.LayoutParams.WRAP_CONTENT, 1)); // Peso 2
        editTextDireccion.setHint("Dirección");
        editTextDireccion.setInputType(InputType.TYPE_CLASS_TEXT);

        Button botonEliminar = new Button(this);
        botonEliminar.setLayoutParams(new LinearLayout.LayoutParams(75, 75));
        botonEliminar.setText("x");
        botonEliminar.setTextSize(8);
        botonEliminar.setBackgroundColor(Color.RED);
        botonEliminar.setTextColor(Color.WHITE);

        botonEliminar.setOnClickListener(b -> addressContent.removeView(nuevaDireccion));

        nuevaDireccion.addView(editTextDireccion);
        nuevaDireccion.addView(spinnerEtiqueta);
        nuevaDireccion.addView(botonEliminar);

        addressContent.addView(nuevaDireccion, addressContent.getChildCount() - 1);
    }

    public void addEditDate(View viewDate){
        LinearLayout nuevaFecha = new LinearLayout(this);
        nuevaFecha.setOrientation(LinearLayout.HORIZONTAL);
        nuevaFecha.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        nuevaFecha.setPadding(0, 16, 0, 16);

        Spinner spinnerEtiqueta = new Spinner(this);
        spinnerEtiqueta.setLayoutParams(
                new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.etiquetas_fecha, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEtiqueta.setAdapter(adapter);

        TextView textViewFecha = new TextView(this);
        textViewFecha.setLayoutParams(
                new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        textViewFecha.setHint("Seleccionar fecha");
        textViewFecha.setPadding(16, 16, 16, 16);
        textViewFecha.setBackgroundResource(android.R.drawable.edit_text);

        Button botonEliminar = new Button(this);
        botonEliminar.setLayoutParams(
                new LinearLayout.LayoutParams(75, 75)); // Ancho y alto en píxeles
        botonEliminar.setText("x");
        botonEliminar.setTextSize(8);
        botonEliminar.setBackgroundColor(Color.RED);
        botonEliminar.setTextColor(Color.WHITE);

        botonEliminar.setOnClickListener(b -> eventDateContent.removeView(nuevaFecha));

        textViewFecha.setOnClickListener(
                tv -> {
                    Calendar calendario = Calendar.getInstance();
                    int anio = calendario.get(Calendar.YEAR);
                    int mes = calendario.get(Calendar.MONTH);
                    int dia = calendario.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(
                            this,
                            (view, year, monthOfYear, dayOfMonth) -> {
                                String fechaSeleccionada = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                textViewFecha.setText(fechaSeleccionada);
                                textViewFecha.setTextColor(Color.BLACK);
                                },
                            anio,
                            mes,
                            dia);
                    datePickerDialog.show();
                });

        nuevaFecha.addView(textViewFecha);
        nuevaFecha.addView(spinnerEtiqueta);

        nuevaFecha.addView(botonEliminar);

        eventDateContent.addView(nuevaFecha, eventDateContent.getChildCount() - 1);
    }

    public void addEditSocialMedia(View viewSocialMedia){
        LinearLayout nuevoSocial = new LinearLayout(this);
        nuevoSocial.setOrientation(LinearLayout.HORIZONTAL);
        nuevoSocial.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        nuevoSocial.setPadding(0, 16, 0, 16);

        Spinner spinnerEtiqueta = new Spinner(this);
        spinnerEtiqueta.setLayoutParams(
                new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.etiquetas_RedSocial, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEtiqueta.setAdapter(adapter);

        EditText editTextSocial = new EditText(this);
        editTextSocial.setLayoutParams(
                new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        editTextSocial.setHint("Red Social");
        editTextSocial.setInputType(InputType.TYPE_CLASS_TEXT);

        Button botonEliminar = new Button(this);
        botonEliminar.setLayoutParams(
                new LinearLayout.LayoutParams(75, 75)); // Ancho y alto en píxeles
        botonEliminar.setText("x");
        botonEliminar.setTextSize(8);
        botonEliminar.setBackgroundColor(Color.RED);
        botonEliminar.setTextColor(Color.WHITE);

        botonEliminar.setOnClickListener(b -> socialMediaContent.removeView(nuevoSocial));

        nuevoSocial.addView(editTextSocial);
        nuevoSocial.addView(spinnerEtiqueta);
        nuevoSocial.addView(botonEliminar);

        socialMediaContent.addView(nuevoSocial, socialMediaContent.getChildCount() - 1);
    }

    public void addEditAssociateContact(View viewAssociateContact){
        LinearLayout newAssociateContact = new LinearLayout(this);
        newAssociateContact.setOrientation(LinearLayout.HORIZONTAL);
        newAssociateContact.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        newAssociateContact.setPadding(0, 16, 0, 16);

        EditText editTextName = new EditText(this);
        editTextName.setLayoutParams(
                new LinearLayout.LayoutParams(
                        0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        editTextName.setHint("Name");
        editTextName.setInputType(InputType.TYPE_CLASS_TEXT);

        EditText editTextTelephone = new EditText(this);
        editTextTelephone.setLayoutParams(
                new LinearLayout.LayoutParams(
                        0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        editTextTelephone.setHint("Telephone");
        editTextTelephone.setInputType(InputType.TYPE_CLASS_TEXT);

        Button botonEliminar = new Button(this);
        botonEliminar.setLayoutParams(
                new LinearLayout.LayoutParams(75, 75)); // Ancho y alto en píxeles
        botonEliminar.setText("x");
        botonEliminar.setTextSize(8);
        botonEliminar.setBackgroundColor(Color.RED);
        botonEliminar.setGravity(Gravity.CENTER);
        botonEliminar.setTextColor(Color.WHITE);

        botonEliminar.setOnClickListener(b -> associatedContactsContent.removeView(newAssociateContact));

        newAssociateContact.addView(editTextName);
        newAssociateContact.addView(editTextTelephone);
        newAssociateContact.addView(botonEliminar);

        associatedContactsContent.addView(newAssociateContact, associatedContactsContent.getChildCount() - 1);
    }

    public void closeEdit(View view){
        finish();
    }

    public void saveChange(View view){

    }
}
