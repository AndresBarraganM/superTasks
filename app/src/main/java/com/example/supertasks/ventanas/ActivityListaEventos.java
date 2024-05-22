package com.example.supertasks.ventanas;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.supertasks.R;
import com.example.supertasks.adaptadores.ListaAdaptador;
import com.example.supertasks.adaptadores.ListaEventos;
import java.util.ArrayList;
import java.util.List;


public class ActivityListaEventos extends AppCompatActivity {

    List<ListaEventos> elementos;

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


        init();
    }

    public void init() {
        elementos = new ArrayList<>();
        elementos.add(new ListaEventos("Limpiar casa", "20/02/2024"));
        elementos.add(new ListaEventos("Comer", "25/02/2024"));
        elementos.add(new ListaEventos("Estudiar", "26/02/24"));
        elementos.add(new ListaEventos("Ejercicio", "30/02/24"));

        ListaAdaptador listaAdaptador = new ListaAdaptador(elementos, this);

        RecyclerView recyclerView = findViewById(R.id.recyclerVwListaEventos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listaAdaptador);
    }
}
