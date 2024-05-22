package com.example.supertasks.ventanas;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.supertasks.MainActivity;
import com.example.supertasks.R;
import com.example.supertasks.adaptadores.ListaAdaptador;
import com.example.supertasks.adaptadores.ListaEventos;
import com.example.supertasks.modelos.Evento;

import java.util.ArrayList;
import java.util.List;


public class ActivityListaEventos extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    List<ListaEventos> elementos;
    RecyclerView recyclerView;
    ListaAdaptador listaAdaptador;

    String[] palabrasCmb = {"Eventos ya hechos", "Eventos por hacer"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_eventos);

        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 13-05-2024
        ImageView btnRegresar = findViewById(R.id.btnBack);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityListaEventos.this, MainActivityJava.class);
                startActivity(intent);

            }
        });

        ImageView btnAgregar = findViewById(R.id.btnAdd);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityListaEventos.this, ActivityCrearEventos.class);
                startActivity(intent);
            }
        });

            //Para el spiner o Combobox
        // Tomar spinner
        // aplicar item del listener seleccionado
        // indica el seleccionado
        Spinner cmbEventos = findViewById(R.id.ComboCompletados);
        cmbEventos.setOnItemSelectedListener(this);
        // lista de cosas de la combobox
        ArrayAdapter ad
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                palabrasCmb);

        // layout de el cmb
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        cmbEventos.setAdapter(ad);


        init();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void init() {
        setearRecyclerView();
    }

    //Metodo para setear el recyclerView
    private void setearRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerVwListaEventos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(crearAdaptador());
    }

    //Metodo que devuelve los objetos para el recycler
    private ListaAdaptador crearAdaptador() {
        elementos = new ArrayList<>();
        Log.d("ACTIVITYLISTAEVENTOS", "Obteniendo para el recicler");
        //Obtener lista de los eventos que guardamos TODO Hacer que cambie segun el cmb y la entrada desde main
        List<Evento> eventos = MainActivity.eventosLocales.listaDeEventosFuturos();

        //Poner los eventos
        elementos = new ArrayList<>();
        Evento ev;
        for (int i= 0; i <= eventos.size() -1; i++){
            ev = eventos.get(i);
            elementos.add(new ListaEventos(ev.getNombre(), ev.getFecha().toString()));
        }

        ListaAdaptador listaAdaptador = new ListaAdaptador(elementos, this);


        return listaAdaptador;
    }




    // Listeners del combobox
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
