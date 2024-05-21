package com.example.supertasks.ventanas;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.supertasks.R;
import com.example.supertasks.modelos.EventosGuardados;

import org.w3c.dom.Text;

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
        // Se inicializa la clase EventosGuardados
        EventosGuardados eventosGuardados = new EventosGuardados();

        // 20/05/2024 -- funcionalidad de ver el proximo pendiente de mañana

        TextView txtContadorEventos = findViewById(R.id.contadorEventos);
        int eventosDia = eventosGuardados.cantidadEventosPendientes("dia");
        txtContadorEventos.setText(String.valueOf(eventosDia));

        // 20/05/2024 -- funcionalidad de ver los pendientes de la semana

        TextView txtContadorEventos2 = findViewById(R.id.contadorEventos2);
        int eventosSemana = eventosGuardados.cantidadEventosPendientes("semana");
        txtContadorEventos2.setText(String.valueOf(eventosSemana));

        // 20/05/2024 -- funcionalidad de ver los proximos pendientes del mes

        TextView txtContadorEventos3 = findViewById(R.id.contadorEventos3);
        int eventosMes = eventosGuardados.cantidadEventosPendientes("mes");
        txtContadorEventos3.setText(String.valueOf(eventosMes));

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

