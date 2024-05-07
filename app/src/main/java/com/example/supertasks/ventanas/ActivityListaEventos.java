package com.example.supertasks.ventanas;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.supertasks.R;
import com.example.supertasks.modelos.Evento;

import java.time.LocalDate;
import java.util.List;

// Todo este codigo fue hecho el 07.05.2024 //

public class ActivityListaEventos extends AppCompatActivity {

    private TextView txtEventosDiario;
    private TextView txtEventosSemanales;
    private TextView txtEventosMensuales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar vistas
        txtEventosDiario = findViewById(R.id.contadorEventos);
        txtEventosSemanales = findViewById(R.id.contadorEventos2);
        txtEventosMensuales = findViewById(R.id.contadorEventos3);

        // Supongamos que tienes una lista de eventos
        List<Evento> listaEventos = obtenerEventos();

        actualizarContadores(listaEventos);
    }

    private List<Evento> obtenerEventos() {
        // Obtener la lista de eventos desde tu fuente de datos (base de datos, API, etc.)
        return null;
    }

    private void actualizarContadores(List<Evento> listaEventos) {
        LocalDate fechaDiaria = LocalDate.now().plusDays(1);
        LocalDate fechaSemana = LocalDate.now().plusDays(7);
        LocalDate fechaMes = LocalDate.now().plusMonths(1);

        int eventosDiarios = contarEventosPorFecha(listaEventos, fechaDiaria);
        int eventosSemana = contarEventosPorFecha(listaEventos, fechaSemana);
        int eventosMes = contarEventosPorFecha(listaEventos, fechaMes);

        // Actualizar los TextView con los contadores
        txtEventosDiario.setText(String.valueOf(eventosDiarios));
        txtEventosSemanales.setText(String.valueOf(eventosSemana));
        txtEventosMensuales.setText(String.valueOf(eventosMes));
    }

    private int contarEventosPorFecha(List<Evento> lista, LocalDate fecha) {
        int contador = 0;
        for (Evento evento : lista) {
            if (evento.getFecha().equals(fecha)) {
                contador++;
            }
        }
        return contador;
    }
}
