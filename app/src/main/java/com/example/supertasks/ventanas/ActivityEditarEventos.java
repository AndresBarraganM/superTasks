package com.example.supertasks.ventanas;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.supertasks.MainActivity;
import com.example.supertasks.R;
import com.example.supertasks.adaptadores.PrioridadAdaptador;
import com.example.supertasks.modelos.Evento;
import com.example.supertasks.modelos.EventosGuardados;

import java.util.Calendar;

public class ActivityEditarEventos extends AppCompatActivity {
    EditText editarNombre, editarDescripcion;
    ImageView btnRegresar;
    TextView btnAceptar;
    Spinner editarPrioridad;
    private Evento evento = new Evento();
    Calendar fecha = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_evento);
        EventosGuardados eventoLocal = MainActivity.eventosLocales;
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
                String nombre = editarNombre.getText().toString();
                String descripcion = editarDescripcion.getText().toString();
                String prioridadSeleccionada = editarPrioridad.getSelectedItem().toString();
                evento.setNombre(nombre);
                evento.setDescripcion(descripcion);
                evento.setPrioridad(convertirPrioridad(prioridadSeleccionada));
                String guardarEvento = eventoLocal.agregarEvento(evento);

                String mensaje = "Nombre: " + nombre +
                        "\nDescripción: " + descripcion +
                        "\nPrioridad: " + prioridadSeleccionada;
                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(ActivityEditarEventos.this, ActivityListaEventos.class);
                startActivity(intent);
            }
        });
    }
    private int convertirPrioridad(String prioridadSeleccionada) {
        if ("Alta".equals(prioridadSeleccionada)) {
            return 1;
        } else if ("Media".equals(prioridadSeleccionada)) {
            return 2;
        } else if ("Baja".equals(prioridadSeleccionada)) {
            return 3;
        } else {
            return 0;
        }
    }

}
