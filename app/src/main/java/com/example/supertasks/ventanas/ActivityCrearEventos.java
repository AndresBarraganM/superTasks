package com.example.supertasks.ventanas;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.supertasks.R;
import com.example.supertasks.modelos.Evento;

import java.util.Calendar;


public class ActivityCrearEventos extends AppCompatActivity {
    public ImageView verCalendario;
    private Evento evento = new Evento();

    int dia, mes, anio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_evento);
        verCalendario = findViewById(R.id.verCalendario);
        TextView btnAgregar = findViewById(R.id.btnTxtAgregar);
        ImageView btnRegresar = findViewById(R.id.btnRegresar2);

        // Establecer fecha 16-05-2024
        verCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void  onClick(View view) {
                final Calendar calendario = Calendar.getInstance();
                dia = calendario.get(Calendar.DAY_OF_MONTH);
                mes = calendario.get(Calendar.MONTH);
                anio = calendario.get(Calendar.YEAR);

                DatePickerDialog dpd = new DatePickerDialog(ActivityCrearEventos.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int anioSeleccionado, int mesSeleccionado, int diaSeleccionado) {
                        evento.setFechaFromDatePicker(anioSeleccionado, mesSeleccionado, diaSeleccionado); // Llama al método Kotlin
                        Log.d("ActivityCrearEventos", "Fecha seleccionada: " + diaSeleccionado + "/" + (mesSeleccionado + 1) + "/" + anioSeleccionado);
                    }
                }, anio, mes, dia);

                dpd.show();
            }
        });
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí defines la acción que deseas que ocurra al presionar btnRegresar2
                Intent intent = new Intent(ActivityCrearEventos.this, MainActivity.class);
                startActivity(intent);
                // finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
