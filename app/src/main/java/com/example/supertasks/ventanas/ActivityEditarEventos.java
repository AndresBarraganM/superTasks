package com.example.supertasks.ventanas;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.supertasks.R;
import com.example.supertasks.adaptadores.PrioridadAdaptador;

import java.util.Calendar;

public class ActivityEditarEventos extends AppCompatActivity {
    EditText editarNombre, editarDescripcion;
    ImageView btnRegresar;
    TextView btnAceptar;
    Spinner editarPrioridad;
    Calendar fecha = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_evento);
        editarNombre = findViewById(R.id.txtFieldEditarNombre);
        ImageView editarFecha = findViewById(R.id.verCalendario2);
        editarDescripcion = findViewById(R.id.editarDescripcion);
        editarPrioridad = findViewById(R.id.comboEditarPrioridad);
        btnAceptar = findViewById(R.id.btnTxtAceptar);
        btnRegresar = findViewById(R.id.btnRegresar2);
        PrioridadAdaptador.configurarPrioridad(this, editarPrioridad);

        // Boton para regresar 22-05-2024
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityEditarEventos.this, ActivityListaEventos.class);
                startActivity(intent);
            }
        });

        // Obtener los datos ingresados en Crear Eventos 22-05-2024

        Intent intent = getIntent();
        String nombreEvento = intent.getStringExtra("nombreEvento");
        String fecha = intent.getStringExtra("fechaEvento");
        String descripcionEvento = intent.getStringExtra("descripcionEvento");
        String prioridadEvento = intent.getStringExtra("prioridadSeleccionada");
        editarNombre.setText(nombreEvento);
        // fecha pendiente..
        editarDescripcion.setText(descripcionEvento);
        // Poner la prioridad en el combo box
        PrioridadAdaptador.seleccionarOpcionCombo(editarPrioridad, prioridadEvento);



        // Boton para aceptar la edicion del evento 22-05-2024
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityEditarEventos.this, ActivityListaEventos.class);
                startActivity(intent);
            }
        });
    }
}
