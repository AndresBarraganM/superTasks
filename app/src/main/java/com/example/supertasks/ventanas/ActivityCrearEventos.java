package com.example.supertasks.ventanas;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import com.example.supertasks.R;
import com.example.supertasks.modelos.Evento;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class ActivityCrearEventos extends AppCompatActivity {
    private EditText nombreEvento, descripcionEvento;
    private Evento evento = new Evento();

    int dia, mes, anio, hora, minuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_evento);
        ImageView verCalendario = findViewById(R.id.verCalendario);
        nombreEvento = findViewById(R.id.txtFieldNombre);
        TextView btnAgregar = findViewById(R.id.btnTxtAgregar);
        ImageView btnRegresar = findViewById(R.id.btnRegresar2);
        Spinner comboPrioridad = findViewById(R.id.comboPrioridad);
        String [] opcionesCombo = {"Alta", "Media", "Baja"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcionesCombo);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        comboPrioridad.setAdapter(adaptador);

        // Establecer fecha 16-05-2024
        // Se agrego la hora 17-05-2024
        verCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar fecha = Calendar.getInstance();
                dia = fecha.get(Calendar.DAY_OF_MONTH);
                mes = fecha.get(Calendar.MONTH);
                anio = fecha.get(Calendar.YEAR);
                hora = fecha.get(Calendar.HOUR_OF_DAY);
                minuto = fecha.get(Calendar.MINUTE);

                // Crear y mostrar el DatePickerDialog
                DatePickerDialog dpd = new DatePickerDialog(ActivityCrearEventos.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int anioSeleccionado, int mesSeleccionado, int diaSeleccionado) {
                                TimePickerDialog tpd = new TimePickerDialog(ActivityCrearEventos.this,
                                        new TimePickerDialog.OnTimeSetListener() {
                                            @Override
                                            public void onTimeSet(TimePicker view, int horaSeleccionada, int minutoSeleccionado) {
                                                // Establecer la fecha y hora seleccionadas
                                                evento.setFechaFromDatePicker(anioSeleccionado, mesSeleccionado, diaSeleccionado, horaSeleccionada, minutoSeleccionado);

                                                String diaFormateado = (diaSeleccionado < 10) ? "0" + diaSeleccionado : String.valueOf(diaSeleccionado);
                                                String mesFormateado = (mesSeleccionado < 9) ? "0" + (mesSeleccionado + 1) : String.valueOf(mesSeleccionado + 1);
                                                String horaFormateada = (horaSeleccionada < 10) ? "0" + horaSeleccionada : String.valueOf(horaSeleccionada);
                                                String minutoFormateado = (minutoSeleccionado < 10) ? "0" + minutoSeleccionado : String.valueOf(minutoSeleccionado);
                                                String fechaFormateada = (diaFormateado + "/" + mesFormateado + "/" + anioSeleccionado + "/" + horaFormateada + "/" + minutoFormateado);

                                                Log.d("ActivityCrearEventos", "Fecha y hora seleccionadas: " +  fechaFormateada);
                                            }
                                        }, hora, minuto, true);
                                tpd.show();
                            }
                        }, anio, mes, dia);
                dpd.show();
            }
        });

        // Obtener el texto ingresado en el nombre de evento 16-05-2024
        nombreEvento.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String textoIngresado = nombreEvento.getText().toString();
                evento.setNombre(textoIngresado);
                Log.d("ActivityCrearEventos", "Texto ingresado: " + textoIngresado);
            }
        });

        // Obtener el texto ingresado en descripcion 16-05-2024
        descripcionEvento.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String textoIngresado = descripcionEvento.getText().toString();
                evento.setDescripcion(textoIngresado);
                Log.d("ActivityCrearEventos", "Texto ingresado: " + textoIngresado);
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí defines la acción que deseas que ocurra al presionar btnRegresar2
                Intent intent = new Intent(ActivityCrearEventos.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
