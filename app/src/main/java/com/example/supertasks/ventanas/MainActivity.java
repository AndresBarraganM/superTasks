package com.example.supertasks.ventanas;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.supertasks.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtén una referencia al botón --> 26/04/2024
        Button btnCrearTarea = findViewById(R.id.btnCrearTarea);
        btnCrearTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityCrearEventos.class);
                startActivity(intent);

            }
        });

        // 02/05/2024 -- Funcionalidad desplegar calendario en Activity Calendario //

        ImageView btnCalendario = findViewById(R.id.btnCalendario);
        btnCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 26/04/2024
                Intent intent = new Intent(MainActivity.this, ActivityCalendario.class);
                startActivity(intent);
            }
        });

        // 02/05/2024 -- Funcionalidad boton Ver Todos //

        TextView txtVerTodosEventos = findViewById(R.id.txtVerTodosEventos);
        txtVerTodosEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityListaEventos.class);
                startActivity(intent);
            }
        });

        // Funcionalidad para ver pendientes completados

        TextView txtVerTodosCompletados = findViewById(R.id.txtVerTodosCompletados);
        txtVerTodosCompletados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityListaEventos.class);
                startActivity(intent);
            }
        });
    }
}

