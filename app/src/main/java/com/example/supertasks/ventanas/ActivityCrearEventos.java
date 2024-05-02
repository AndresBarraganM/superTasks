package com.example.supertasks.ventanas;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.supertasks.R;

public class ActivityCrearEventos extends AppCompatActivity {
    private ImageView verCalendario;
    private boolean isVisible = false; // Variable para rastrear la visibilidad

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_evento);

        verCalendario = findViewById(R.id.verCalendario);
        verCalendario.setVisibility(View.GONE);

        verCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cambia la visibilidad al hacer clic
                isVisible = !isVisible;
                verCalendario.setVisibility(isVisible ? View.VISIBLE : View.GONE);
            }
        });
    }
}