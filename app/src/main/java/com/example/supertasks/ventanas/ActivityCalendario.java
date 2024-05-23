package com.example.supertasks.ventanas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
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
        // Migrar datos 22-05-2024
        Intent intent = getIntent();
        long fechaEventoMillis = intent.getLongExtra("fechaevento", -1);
        HashSet<Calendar> eventoCalendario = new HashSet<>();

        // Establecer fecha 22-05-2024
        if (fechaEventoMillis != -1) {
            Calendar evento = Calendar.getInstance();
            evento.setTimeInMillis(fechaEventoMillis);
            eventoCalendario.add(evento);
        }

        calendarioView.setEventoCalendario(eventoCalendario);

        ImageView btnRegresar = findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 26/04/2024
                Intent intent = new Intent(ActivityCalendario.this, MainActivityJava.class);
                startActivity(intent);
            }
        });
    }
}
