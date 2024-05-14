package com.example.supertasks.ventanas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
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

        TextView btnAgregar = findViewById(R.id.btnTxtAgregar);
        ImageView btnRegresar = findViewById(R.id.btnRegresar2);

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí defines la acción que deseas que ocurra al presionar btnRegresar2
                Intent intent = new Intent(ActivityCrearEventos.this, MainActivity.class);
                startActivity(intent);
                // finish();
            }
        });

        verCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cambia la visibilidad al hacer clic
                isVisible = !isVisible;
                verCalendario.setVisibility(isVisible ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
