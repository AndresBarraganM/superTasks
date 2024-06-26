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
import com.example.supertasks.modelos.Evento;
import com.example.supertasks.modelos.EventosGuardados;

import org.w3c.dom.Text;

import java.util.List;
import com.example.supertasks.MainActivity;

public class MainActivityJava extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int eventosDia = 0, eventosSemana = 0, eventosMes = 0;
        List<Evento> porCompletar, yaCompletado;

        // Inicializar
        EventosGuardados eventoLocal = MainActivity.Companion.getEventosLocales();
        TextView txtContadorEventos = findViewById(R.id.contadorEventos);
        TextView txtContadorEventos2 = findViewById(R.id.contadorEventos2);
        TextView txtContadorEventos3 = findViewById(R.id.contadorEventos3);
        ImageView btnCalendario = findViewById(R.id.btnCalendario);
        TextView txtVerTodosEventos = findViewById(R.id.txtVerTodosEventos);
        TextView txtVerTodosCompletados = findViewById(R.id.txtVerTodosCompletados);
        TextView labelUltimoPendiente = findViewById(R.id.labelUltimoPendiente);
        TextView labelUltimoPendiente2 = findViewById(R.id.labelUltimoPendiente2);
        TextView labelPendienteCompletado = findViewById(R.id.labelPendienteCompletados);

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

        // 22/05/2024 -- Funcionalidad de mostrar los dos ultimos eventos pendientes
        if(eventoLocal != null || eventoLocal.getEventos().size() >2){
            porCompletar = eventoLocal.eventosCercanos(2);
            if (porCompletar.size() >= 2) {
                Evento ultimoEvento1 = porCompletar.get(0);
                Evento ultimoEvento2 = porCompletar.get(1);
                labelUltimoPendiente.setText(ultimoEvento1.getNombre());
                labelUltimoPendiente2.setText(ultimoEvento2.getNombre());
            } else {
                Log.d("MainActivityJava", "No hay suficientes eventos por completar");
            }
        } else {
            Log.d("MainActivityJava", "eventoLocal es null");
        }

        // 22/05/2024 -- Funcionalidad de mostrar el ultimo evento completado
        if(eventoLocal != null){
            yaCompletado = eventoLocal.eventosCompletadosOrdenados(1);
            if (!yaCompletado.isEmpty()) {
                Evento completado = yaCompletado.get(0);
                labelPendienteCompletado.setText(completado.getNombre());
            } else {
                Log.d("MainActivityJava", "No hay eventos completados");
            }
        } else {
            Log.d("MainActivityJava", "eventoLocal es null");
        }


        // 20/05/2024 -- funcionalidad de ver el proximo pendiente de mañana
        if (eventoLocal != null) {
            eventosDia = eventoLocal.cantidadEventosPendientes("dia");
            txtContadorEventos.setText(String.valueOf(eventosDia));
            Log.d("MainActivityJava", "Cantidad de eventos del día: " + eventosDia);

            eventosSemana = eventoLocal.cantidadEventosPendientes("semana");
            txtContadorEventos2.setText(String.valueOf(eventosSemana));
            Log.d("MainActivityJava", "Cantidad de eventos de la semana: " + eventosSemana);

            eventosMes = eventoLocal.cantidadEventosPendientes("mes");
            txtContadorEventos3.setText(String.valueOf(eventosMes));
            Log.d("MainActivityJava", "Cantidad de eventos del mes: " + eventosMes);
        } else {
            Log.d("MainActivityJava", "eventoLocal es null");
        }

        // 02/05/2024 -- Funcionalidad boton Ver Todos //
        // boundle envia la informacion de esta ventana a la otra

        Bundle b = new Bundle();
        txtVerTodosEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityJava.this, ActivityListaEventos.class);
                b.putString("filtro", "futuros"); //Entrara como evento futuro
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
            }
        });

        // Funcionalidad para ver pendientes completados
        txtVerTodosCompletados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityJava.this, ActivityListaEventos.class);
                b.putString("filtro", "pasados"); //Entrara como evento futuro
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
            }
        });
    }
}

