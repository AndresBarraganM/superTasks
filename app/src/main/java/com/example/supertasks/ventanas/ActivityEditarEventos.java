package com.example.supertasks.ventanas;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import com.example.supertasks.R;

public class ActivityEditarEventos extends AppCompatActivity {
    EditText editarNombre, editarDescripcion;
    Spinner editarPrioridad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_evento);
        editarNombre = findViewById(R.id.txtFieldEditarNombre);
        editarDescripcion = findViewById(R.id.editarDescripcion);
        editarPrioridad = findViewById(R.id.comboEditarPrioridad);



    }
}
