package com.example.supertasks.ventanas;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.supertasks.MainActivity;
import androidx.appcompat.app.AppCompatActivity;

import com.example.supertasks.R;
import com.example.supertasks.modelos.EventosGuardados;

public class MainActivityJava extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int eventosDia = 0, eventosSemana = 0, eventosMes = 0;

        // Inicializar
        EventosGuardados eventoLocal = MainActivity.eventosLocales;
        TextView txtContadorEventos = findViewById(R.id.contadorEventos);
        TextView txtContadorEventos2 = findViewById(R.id.contadorEventos2);
        TextView txtContadorEventos3 = findViewById(R.id.contadorEventos3);
        ImageView btnCalendario = findViewById(R.id.btnCalendario);
        TextView txtVerTodosEventos = findViewById(R.id.txtVerTodosEventos);
        TextView txtVerTodosCompletados = findViewById(R.id.txtVerTodosCompletados);

        // Obtén una referencia al botón --> 26/04/2024
        Button btnCrearTarea = findViewById(R.id.btnCrearTarea);
        btnCrearTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityJava.this, ActivityCrearEventos.class);
                startActivity(intent);

            }
        });

        // 02/05/2024 -- Funcionalidad desplegar calendario en Activity Calendario //
        btnCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 26/04/2024
                Intent intent = new Intent(MainActivityJava.this, ActivityCalendario.class);
                startActivity(intent);
            }
        });
        // Se inicializa la clase EventosGuardados

        // 20/05/2024 -- funcionalidad de ver el proximo pendiente de mañana
        if (eventoLocal != null) {
            Log.d("EVENTO PENDIENTE",  "----------------------------------"+eventoLocal.toString());
            eventosDia = eventoLocal.cantidadEventosPendientes("dia");
            txtContadorEventos.setText(String.valueOf(eventosDia));
            Log.d("EVENTO PENDIENTE", "CANTTIDAD DE EVENTOS: " + txtContadorEventos);

            eventosSemana = eventoLocal.cantidadEventosPendientes("semana");
            txtContadorEventos2.setText(String.valueOf(eventosSemana));
            Log.d("EVENTO PENDIENTE", "CANTTIDAD DE EVENTOS: " + txtContadorEventos2);

            eventosMes = eventoLocal.cantidadEventosPendientes("mes");
            txtContadorEventos3.setText(String.valueOf(eventosMes));
            Log.d("EVENTO PENDIENTE", "CANTTIDAD DE EVENTOS: " + txtContadorEventos3);
        } else {
            Log.d("EVENTO PENDIENTE", "eventoLocal es null");
        }

        // 02/05/2024 -- Funcionalidad boton Ver Todos //
        txtVerTodosEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityJava.this, ActivityListaEventos.class);
                startActivity(intent);
            }
        });

        // Funcionalidad para ver pendientes completados
        txtVerTodosCompletados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityJava.this, ActivityListaEventos.class);
                startActivity(intent);
            }
        });
    }
}

