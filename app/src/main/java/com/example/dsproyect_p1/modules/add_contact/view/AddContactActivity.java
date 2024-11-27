package com.example.dsproyect_p1.modules.add_contact.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
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

import com.example.dsproyect_p1.data.repository.ContactRepository;
import com.example.dsproyect_p1.modules.contacts_overview.view.ContactsOverviewActivity;
import com.example.dsproyect_p1.R;
import com.example.dsproyect_p1.data.api.ContactApi;
import com.example.dsproyect_p1.data.model.*;
import com.example.dsproyect_p1.data.structures.CustomArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class AddContactActivity extends AppCompatActivity {
  private LinearLayout contenerdorTelephone,
      contenedorAdress,
      contenedorEmail,
      contenedorDate,
      contenedorSocialMedia,
      contenedorAsociados;
  EditText name, residenciaCC;
  Button cancelar, guardar;
  ContactRepository contactRepository;
  ContactType contactType;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_add_contacts);
    ViewCompat.setOnApplyWindowInsetsListener(
        findViewById(R.id.main),
        (v, insets) -> {
          Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
          v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
          return insets;
        });



    name = findViewById(R.id.nombreID);
    residenciaCC = findViewById(R.id.residenciaID);

    cancelar = findViewById(R.id.cancelarC);
    guardar = findViewById(R.id.guardarC);

    guardar.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            registarContacto();
            Intent i = new Intent(getApplicationContext(), ContactsOverviewActivity.class);
            startActivity(i);
          }
        });
    cancelar.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent i = new Intent(getApplicationContext(), ContactsOverviewActivity.class);
            startActivity(i);
          }
        });
    // ------------------------------Telefono-----------------------------------------------------------
    contenerdorTelephone = findViewById(R.id.contenedorTelefonos);
    Button buttonAgregarTelefono = findViewById(R.id.buttonAgregarTelefono);

    buttonAgregarTelefono.setOnClickListener(
        v -> {
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
              this, R.array.etiquetas_telefonoC, android.R.layout.simple_spinner_item);
          adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          spinnerEtiqueta.setAdapter(adapter);

          EditText editTextTelefono = new EditText(this);
          editTextTelefono.setLayoutParams(
              new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
          editTextTelefono.setHint("Teléfono");
          editTextTelefono.setInputType(android.text.InputType.TYPE_CLASS_PHONE);

          Button botonEliminar = new Button(this);
          botonEliminar.setLayoutParams(
              new LinearLayout.LayoutParams(75, 75)); // Ancho y alto en píxeles
          botonEliminar.setText("x");
          botonEliminar.setTextSize(8);
          botonEliminar.setBackgroundColor(Color.RED);
          botonEliminar.setTextColor(Color.WHITE);

          botonEliminar.setOnClickListener(b -> contenerdorTelephone.removeView(nuevoTelefono));
          nuevoTelefono.addView(editTextTelefono);
          nuevoTelefono.addView(spinnerEtiqueta);
          nuevoTelefono.addView(botonEliminar);

          contenerdorTelephone.addView(nuevoTelefono, contenerdorTelephone.getChildCount() - 1);
        });

    // ------------------------------Contactos
    // asociados----------------------------------------------------------
    contenedorAsociados = findViewById(R.id.contenedorAsociados);
    Button buttonAgregarAsociados = findViewById(R.id.buttonAgregarAsociados);

    buttonAgregarAsociados.setOnClickListener(
        v -> {
          LinearLayout nuevoAsociado = new LinearLayout(this);
          nuevoAsociado.setOrientation(LinearLayout.HORIZONTAL);
          nuevoAsociado.setLayoutParams(
              new LinearLayout.LayoutParams(
                  LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
          nuevoAsociado.setPadding(0, 16, 0, 16);

          EditText editTextNameAsociado = new EditText(this);
          editTextNameAsociado.setLayoutParams(
              new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
          editTextNameAsociado.setHint("Nombre");
          editTextNameAsociado.setInputType(InputType.TYPE_CLASS_TEXT);

          EditText editTextNumAsociado = new EditText(this);
          editTextNumAsociado.setLayoutParams(
              new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
          editTextNumAsociado.setHint("Teléfono");
          editTextNumAsociado.setInputType(android.text.InputType.TYPE_CLASS_PHONE);

          Button botonEliminar = new Button(this);
          botonEliminar.setLayoutParams(
              new LinearLayout.LayoutParams(75, 75)); // Ancho y alto en píxeles
          botonEliminar.setText("x");
          botonEliminar.setTextSize(8);
          botonEliminar.setBackgroundColor(Color.RED);
          botonEliminar.setTextColor(Color.WHITE);

          botonEliminar.setOnClickListener(b -> contenedorAsociados.removeView(nuevoAsociado));

          nuevoAsociado.addView(editTextNameAsociado);
          nuevoAsociado.addView(editTextNumAsociado);
          nuevoAsociado.addView(botonEliminar);

          contenedorAsociados.addView(nuevoAsociado, contenedorAsociados.getChildCount() - 1);
        });

    // ----------------------------E-mail----------------------------------------------------------------
    contenedorEmail = findViewById(R.id.contenedorCorreos);
    Button buttonAgregarEmail = findViewById(R.id.buttonAgregarEmail);

    buttonAgregarEmail.setOnClickListener(
        v -> {
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
          editTextEmail.setInputType(android.text.InputType.TYPE_CLASS_TEXT);

          Button botonEliminar = new Button(this);
          botonEliminar.setLayoutParams(
              new LinearLayout.LayoutParams(75, 75)); // Ancho y alto en píxeles
          botonEliminar.setText("x");
          botonEliminar.setTextSize(8);
          botonEliminar.setBackgroundColor(Color.RED);
          botonEliminar.setTextColor(Color.WHITE);

          botonEliminar.setOnClickListener(b -> contenedorEmail.removeView(nuevoEmail));

          nuevoEmail.addView(editTextEmail);
          nuevoEmail.addView(spinnerEtiqueta);
          nuevoEmail.addView(botonEliminar);

          contenedorEmail.addView(nuevoEmail, contenedorEmail.getChildCount() - 1);
        });

    // ----------------------------Direccion----------------------------------------------------------------
    contenedorAdress = findViewById(R.id.contenedorDireccion);
    Button buttonAgreganDireccion = findViewById(R.id.buttonAgregarDireccion);

    buttonAgreganDireccion.setOnClickListener(
        v -> {
          LinearLayout nuevaDireccion = new LinearLayout(this);
          nuevaDireccion.setOrientation(LinearLayout.HORIZONTAL);
          nuevaDireccion.setLayoutParams(
              new LinearLayout.LayoutParams(
                  LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
          nuevaDireccion.setPadding(0, 16, 0, 16);

          Spinner spinnerEtiqueta = new Spinner(this);
          spinnerEtiqueta.setLayoutParams(
              new LinearLayout.LayoutParams(
                  0, LinearLayout.LayoutParams.WRAP_CONTENT, 1)); // Peso 1

          ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
              this, R.array.etiquetas_direccionC, android.R.layout.simple_spinner_item);
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

          botonEliminar.setOnClickListener(b -> contenedorAdress.removeView(nuevaDireccion));

          nuevaDireccion.addView(editTextDireccion);
          nuevaDireccion.addView(spinnerEtiqueta);
          nuevaDireccion.addView(botonEliminar);

          contenedorAdress.addView(nuevaDireccion, contenedorAdress.getChildCount() - 1);
        });

    // ----------------------------Red
    // Social----------------------------------------------------------------
    contenedorSocialMedia = findViewById(R.id.contenedorRedSocial);
    Button buttonAgregarSocial = findViewById(R.id.buttonAgregarRedSocial);

    buttonAgregarSocial.setOnClickListener(
        v -> {
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
          editTextSocial.setInputType(android.text.InputType.TYPE_CLASS_TEXT);

          Button botonEliminar = new Button(this);
          botonEliminar.setLayoutParams(
              new LinearLayout.LayoutParams(75, 75)); // Ancho y alto en píxeles
          botonEliminar.setText("x");
          botonEliminar.setTextSize(8);
          botonEliminar.setBackgroundColor(Color.RED);
          botonEliminar.setTextColor(Color.WHITE);

          botonEliminar.setOnClickListener(b -> contenedorSocialMedia.removeView(nuevoSocial));

          nuevoSocial.addView(editTextSocial);
          nuevoSocial.addView(spinnerEtiqueta);
          nuevoSocial.addView(botonEliminar);

          contenedorSocialMedia.addView(nuevoSocial, contenedorSocialMedia.getChildCount() - 1);
        });

    // -------------------------------------------Fecha----------------------------------------------------
    contenedorDate = findViewById(R.id.contenedorFecha);
    Button buttonAgregarFecha = findViewById(R.id.buttonAgregarFecha);

    buttonAgregarFecha.setOnClickListener(
        v -> {
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
              this, R.array.etiquetas_fechaC, android.R.layout.simple_spinner_item);
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

          botonEliminar.setOnClickListener(b -> contenedorDate.removeView(nuevaFecha));

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

          contenedorDate.addView(nuevaFecha, contenedorDate.getChildCount() - 1);
        });
  }

  public CustomArrayList<Telephone> obtenerTelefonos() {
    CustomArrayList<Telephone> listaTelefonos = new CustomArrayList<>();
    for (int i = 0; i < contenerdorTelephone.getChildCount(); i++) {
      View vista = contenerdorTelephone.getChildAt(i);
      if (vista instanceof LinearLayout) {
        LinearLayout telefono = (LinearLayout) vista;
        EditText editTextTelefono = (EditText) telefono.getChildAt(0);
        String numero = editTextTelefono.getText().toString();
        Spinner spinnerLabel = (Spinner) telefono.getChildAt(1);
        String label = spinnerLabel.getSelectedItem().toString();
        if (!numero.isEmpty()) {
          listaTelefonos.add(new Telephone(label, numero));
        }
      }
    }
    return listaTelefonos;
  }

  public CustomArrayList<Email> obtenerEmail() {
    CustomArrayList<Email> listaEmail = new CustomArrayList<>();
    for (int i = 0; i < contenedorEmail.getChildCount(); i++) {
      View vista = contenedorEmail.getChildAt(i);
      if (vista instanceof LinearLayout) {
        LinearLayout llEmail = (LinearLayout) vista;
        EditText editTextEmail = (EditText) llEmail.getChildAt(0);
        String email = editTextEmail.getText().toString();
        Spinner spinnerLabel = (Spinner) llEmail.getChildAt(1);
        String label = spinnerLabel.getSelectedItem().toString();
        if (!email.isEmpty()) {
          listaEmail.add(new Email(label, email));
        }
      }
    }
    return listaEmail;
  }

  public CustomArrayList<Address> obtenerDirecciones() {
    CustomArrayList<Address> listaAdress = new CustomArrayList<>();
    for (int i = 0; i < contenedorAdress.getChildCount(); i++) {
      View vista = contenedorAdress.getChildAt(i);
      if (vista instanceof LinearLayout) {
        LinearLayout llDireccion = (LinearLayout) vista;
        EditText editTextDireccion = (EditText) llDireccion.getChildAt(0);
        String direccion = editTextDireccion.getText().toString();
        Spinner spinnerLabel = (Spinner) llDireccion.getChildAt(1);
        String label = spinnerLabel.getSelectedItem().toString();
        if (!direccion.isEmpty()) {
          listaAdress.add(new Address(label, direccion));
        }
      }
    }
    return listaAdress;
  }

  public CustomArrayList<EventDate> obtenerFecha() {
    CustomArrayList<EventDate> listaDate = new CustomArrayList<>();
    for (int i = 0; i < contenedorDate.getChildCount(); i++) {
      View vista = contenedorDate.getChildAt(i);
      if (vista instanceof LinearLayout) {
        LinearLayout llFecha = (LinearLayout) vista;
        EditText editTextFecha = (EditText) llFecha.getChildAt(0);
        String fecha = editTextFecha.getText().toString();

        DateTimeFormatter formate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(fecha, formate);

        Spinner spinnerLabel = (Spinner) llFecha.getChildAt(1);
        String label = spinnerLabel.getSelectedItem().toString();
        if (!fecha.isEmpty()) {
          listaDate.add(new EventDate(label, date));
        }
      }
    }
    return listaDate;
  }

  public CustomArrayList<SocialMediaAccount> obtenerSocialMedia() {
    CustomArrayList<SocialMediaAccount> listaRedSocial = new CustomArrayList<>();
    for (int i = 0; i < contenedorSocialMedia.getChildCount(); i++) {
      View vista = contenedorSocialMedia.getChildAt(i);
      if (vista instanceof LinearLayout) {
        LinearLayout llSocial = (LinearLayout) vista;
        EditText editTextuser = (EditText) llSocial.getChildAt(0);
        String user = editTextuser.getText().toString();
        Spinner spinnerLabel = (Spinner) llSocial.getChildAt(1);
        String seleccion = spinnerLabel.getSelectedItem().toString();

        SocialMedia label = null;

        switch (seleccion) {
          case "Facebook":
            label = SocialMedia.FACEBOOK;
            break;
          case "Instagram":
            label = SocialMedia.INSTAGRAM;
            break;
          case "Twitter":
            label = SocialMedia.TWITTER;
            break;
          case "YouTube":
            label = SocialMedia.YOUTUBE;
            break;
        }
        if (!user.isEmpty()) {
          listaRedSocial.add(new SocialMediaAccount(label, user));
        }
      }
    }
    return listaRedSocial;
  }

  public CustomArrayList<AssociateContact> obtenerAsociados() {
    CustomArrayList<AssociateContact> listaAsociados = new CustomArrayList<>();
    for (int i = 0; i < contenedorAsociados.getChildCount(); i++) {
      View vista = contenedorAsociados.getChildAt(i);
      if (vista instanceof LinearLayout) {
        LinearLayout llAsociado = (LinearLayout) vista;
        EditText editTextnombre = (EditText) llAsociado.getChildAt(0);
        String nombre = editTextnombre.getText().toString();
        EditText editTextnumero = (EditText) llAsociado.getChildAt(0);
        String numero = editTextnumero.getText().toString();
        if (!nombre.isEmpty()) {
          listaAsociados.add(new AssociateContact(nombre, numero));
        }
      }
    }
    return listaAsociados;
  }

  public void registarContacto() {
    CustomArrayList<Telephone> telefonos = obtenerTelefonos();
    CustomArrayList<Email> email = obtenerEmail();
    CustomArrayList<Address> direccion = obtenerDirecciones();
    CustomArrayList<EventDate> fechas = obtenerFecha();
    CustomArrayList<SocialMediaAccount> redes = obtenerSocialMedia();
    CustomArrayList<AssociateContact> asociados = obtenerAsociados();
    String nombre = name.getText().toString();
    String resdencia = residenciaCC.getText().toString();

    Contact contact = new Contact(
        null,
        contactType,
        nombre,
        resdencia,
        telefonos,
        direccion,
        email,
        fechas,
        asociados,
        redes);

      contactRepository.saveContact(contact).thenRun(()-> {
          runOnUiThread(()->{
              Toast.makeText(this, "Contact saved successfully", Toast.LENGTH_SHORT).show();
              finish();
          });
      });
  }
}
