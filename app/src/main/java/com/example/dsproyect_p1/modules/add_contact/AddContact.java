package com.example.dsproyect_p1.modules.add_contact;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.EditText;

import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dsproyect_p1.R;

public class AddContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_contact);





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //------------------------------Telefono-----------------------------------------------------------
        LinearLayout contenedorTelefonos = findViewById(R.id.contenedorTelefonos);
        Button buttonAgregarTelefono = findViewById(R.id.buttonAgregarTelefono);

        buttonAgregarTelefono.setOnClickListener(v -> {

            LinearLayout nuevoTelefono = new LinearLayout(this);
            nuevoTelefono.setOrientation(LinearLayout.HORIZONTAL);
            nuevoTelefono.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            nuevoTelefono.setPadding(0, 16, 0, 16);


            Spinner spinnerEtiqueta = new Spinner(this);
            spinnerEtiqueta.setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1)); // Peso 1


            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.etiquetas_telefono, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerEtiqueta.setAdapter(adapter);


            EditText editTextTelefono = new EditText(this);
            editTextTelefono.setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1)); // Peso 2
            editTextTelefono.setHint("Teléfono");
            editTextTelefono.setInputType(android.text.InputType.TYPE_CLASS_PHONE);


            Button botonEliminar = new Button(this);
            botonEliminar.setLayoutParams(new LinearLayout.LayoutParams(75, 75)); // Ancho y alto en píxeles
            botonEliminar.setText("x");
            botonEliminar.setTextSize(8);
            botonEliminar.setBackgroundColor(Color.RED);
            botonEliminar.setTextColor(Color.WHITE);



            botonEliminar.setOnClickListener(b -> contenedorTelefonos.removeView(nuevoTelefono));



            nuevoTelefono.addView(editTextTelefono);
            nuevoTelefono.addView(spinnerEtiqueta);
            nuevoTelefono.addView(botonEliminar);


            contenedorTelefonos.addView(nuevoTelefono, contenedorTelefonos.getChildCount() - 1);
        });

        //----------------------------E-mail----------------------------------------------------------------
        LinearLayout contenedorEmail = findViewById(R.id.contenedorCorreos);
        Button buttonAgregarEmail = findViewById(R.id.buttonAgregarEmail);

        buttonAgregarEmail.setOnClickListener(v -> {

            LinearLayout nuevoEmail = new LinearLayout(this);
            nuevoEmail.setOrientation(LinearLayout.HORIZONTAL);
            nuevoEmail.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            nuevoEmail.setPadding(0, 16, 0, 16);


            Spinner spinnerEtiqueta = new Spinner(this);
            spinnerEtiqueta.setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1)); // Peso 1


            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.etiquetas_email, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerEtiqueta.setAdapter(adapter);


            EditText editTextEmail = new EditText(this);
            editTextEmail.setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1)); // Peso 2
            editTextEmail.setHint("E-mail");
            editTextEmail.setInputType(android.text.InputType.TYPE_CLASS_TEXT);

            Button botonEliminar = new Button(this);
            botonEliminar.setLayoutParams(new LinearLayout.LayoutParams(75, 75)); // Ancho y alto en píxeles
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

        //----------------------------Direccion----------------------------------------------------------------
        LinearLayout contenedorDireccion = findViewById(R.id.contenedorDireccion);
        Button buttonAgreganDireccion = findViewById(R.id.buttonAgregarDireccion);

        buttonAgreganDireccion.setOnClickListener(v -> {
            LinearLayout nuevaDireccion = new LinearLayout(this);
            nuevaDireccion.setOrientation(LinearLayout.HORIZONTAL);
            nuevaDireccion.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            nuevaDireccion.setPadding(0, 16, 0, 16);

            Spinner spinnerEtiqueta = new Spinner(this);
            spinnerEtiqueta.setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1)); // Peso 1

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.etiquetas_direccion, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerEtiqueta.setAdapter(adapter);

            EditText editTextDireccion = new EditText(this);
            editTextDireccion.setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1)); // Peso 2
            editTextDireccion.setHint("Dirección");
            editTextDireccion.setInputType(InputType.TYPE_CLASS_TEXT);

            Button botonEliminar = new Button(this);
            botonEliminar.setLayoutParams(new LinearLayout.LayoutParams(75, 75));
            botonEliminar.setText("x");
            botonEliminar.setTextSize(8);
            botonEliminar.setBackgroundColor(Color.RED);
            botonEliminar.setTextColor(Color.WHITE);

            botonEliminar.setOnClickListener(b -> contenedorDireccion.removeView(nuevaDireccion));

            nuevaDireccion.addView(editTextDireccion);
            nuevaDireccion.addView(spinnerEtiqueta);
            nuevaDireccion.addView(botonEliminar);

            contenedorDireccion.addView(nuevaDireccion, contenedorDireccion.getChildCount() - 1);
        });

        //----------------------------Red Social----------------------------------------------------------------
        LinearLayout contenedorSocial = findViewById(R.id.contenedorRedSocial);
        Button buttonAgregarSocial = findViewById(R.id.buttonAgregarRedSocial);

        buttonAgregarSocial.setOnClickListener(v -> {
            LinearLayout nuevoSocial = new LinearLayout(this);
            nuevoSocial.setOrientation(LinearLayout.HORIZONTAL);
            nuevoSocial.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            nuevoSocial.setPadding(0, 16, 0, 16);

            Spinner spinnerEtiqueta = new Spinner(this);
            spinnerEtiqueta.setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1));

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.etiquetas_RedSocial, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerEtiqueta.setAdapter(adapter);

            EditText editTextSocial = new EditText(this);
            editTextSocial.setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1));
            editTextSocial.setHint("Red Social");
            editTextSocial.setInputType(android.text.InputType.TYPE_CLASS_TEXT);

            Button botonEliminar = new Button(this);
            botonEliminar.setLayoutParams(new LinearLayout.LayoutParams(75, 75)); // Ancho y alto en píxeles
            botonEliminar.setText("x");
            botonEliminar.setTextSize(8);
            botonEliminar.setBackgroundColor(Color.RED);
            botonEliminar.setTextColor(Color.WHITE);

            botonEliminar.setOnClickListener(b -> contenedorSocial.removeView(nuevoSocial));

            nuevoSocial.addView(editTextSocial);
            nuevoSocial.addView(spinnerEtiqueta);
            nuevoSocial.addView(botonEliminar);

            contenedorSocial.addView(nuevoSocial, contenedorSocial.getChildCount() - 1);
        });

        //-------------------------------------------Fecha----------------------------------------------------
        LinearLayout contenedorFechas = findViewById(R.id.contenedorFecha);
        Button buttonAgregarFecha = findViewById(R.id.buttonAgregarFecha);

        buttonAgregarFecha.setOnClickListener(v -> {
            LinearLayout nuevaFecha = new LinearLayout(this);
            nuevaFecha.setOrientation(LinearLayout.HORIZONTAL);
            nuevaFecha.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            nuevaFecha.setPadding(0, 16, 0, 16);

            Spinner spinnerEtiqueta = new Spinner(this);
            spinnerEtiqueta.setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1));

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.etiquetas_fecha, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerEtiqueta.setAdapter(adapter);

            TextView textViewFecha = new TextView(this);
            textViewFecha.setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1));
            textViewFecha.setHint("Seleccionar fecha");
            textViewFecha.setPadding(16, 16, 16, 16);
            textViewFecha.setBackgroundResource(android.R.drawable.edit_text);

            Button botonEliminar = new Button(this);
            botonEliminar.setLayoutParams(new LinearLayout.LayoutParams(75, 75)); // Ancho y alto en píxeles
            botonEliminar.setText("x");
            botonEliminar.setTextSize(8);
            botonEliminar.setBackgroundColor(Color.RED);
            botonEliminar.setTextColor(Color.WHITE);

            botonEliminar.setOnClickListener(b -> contenedorFechas.removeView(nuevaFecha));

            textViewFecha.setOnClickListener(tv -> {
                Calendar calendario = Calendar.getInstance();
                int anio = calendario.get(Calendar.YEAR);
                int mes = calendario.get(Calendar.MONTH);
                int dia = calendario.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        (view, year, monthOfYear, dayOfMonth) -> {
                            String fechaSeleccionada = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                            textViewFecha.setText(fechaSeleccionada);
                            textViewFecha.setTextColor(Color.BLACK);
                        }, anio, mes, dia);
                datePickerDialog.show();
            });

            nuevaFecha.addView(textViewFecha);
            nuevaFecha.addView(spinnerEtiqueta);

            nuevaFecha.addView(botonEliminar);

            contenedorFechas.addView(nuevaFecha, contenedorFechas.getChildCount() - 1);
        });


    }


}