package com.example.supertasks.ventanas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.supertasks.R;
import com.example.supertasks.adaptadores.EventoCalendario;

import java.util.Calendar;
import java.util.HashSet;

public class ActivityCalendario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);
        EventoCalendario calendarioView = findViewById(R.id.calendario);
        Button btnAgregar = findViewById(R.id.btnAgregar);

        // Obtener el Intent y los datos pasados
        Intent intent = getIntent();
        long fechaEventoMillis = intent.getLongExtra("fechaEvento", -1);
        HashSet<Calendar> eventoCalendario = new HashSet<>();

        if (fechaEventoMillis != -1) {
            Calendar evento = Calendar.getInstance();
            evento.setTimeInMillis(fechaEventoMillis);
            eventoCalendario.add(evento);
        }

        // Configurar los días de eventos en la vista personalizada del calendario
        calendarioView.setEventoCalendario(eventoCalendario);

        ImageView btnRegresar = findViewById(R.id.btnRegresar);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityCalendario.this, ActivityCrearEventos.class);
                startActivity(intent);
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityCalendario.this, MainActivityJava.class);
                startActivity(intent);
            }
        });

        // Puedes dejar el setOnDateChangeListener vacío o eliminarlo si no es necesario
        calendarioView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            // Implementa la lógica si necesitas manejar la selección de fecha
        });
    }
}
