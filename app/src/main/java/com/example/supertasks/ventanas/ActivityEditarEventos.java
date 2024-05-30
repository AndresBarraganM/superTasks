package com.example.supertasks.ventanas;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.supertasks.MainActivity;
import com.example.supertasks.R;
import com.example.supertasks.adaptadores.PrioridadAdaptador;
import com.example.supertasks.modelos.Evento;
import com.example.supertasks.modelos.EventosGuardados;

import java.util.Calendar;
import java.util.Date;

public class ActivityEditarEventos extends AppCompatActivity {
    EditText editarNombre, editarDescripcion;
    ImageView btnRegresar, editarFecha;
    TextView btnAceptar;
    Spinner editarPrioridad;
    private Evento evento = new Evento();
    private String fechaFormateada;
    Calendar fecha = Calendar.getInstance();


    int dia, mes, anio, hora, minuto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_evento);
        EventosGuardados eventoLocal = MainActivity.eventosLocales;

        editarNombre = findViewById(R.id.txtFieldEditarNombre);
        editarDescripcion = findViewById(R.id.editarDescripcion);
        editarPrioridad = findViewById(R.id.comboEditarPrioridad);
        editarFecha = findViewById(R.id.verCalendario2);
        btnAceptar = findViewById(R.id.btnTxtAceptar);
        btnRegresar = findViewById(R.id.btnRegresar2);

        PrioridadAdaptador.configurarPrioridad(this, editarPrioridad);

        // Obtener los datos ingresados en Crear Eventos 22-05-2024
        Intent intent = getIntent();
        if (intent != null) {
            String nombre = intent.getStringExtra("nombreEvento");
            String descripcion = intent.getStringExtra("descripcionEvento");
            // Validacion temporal 27-05-2024
            if (descripcion == null) {
                descripcion = "Descripción no proporcionada";
            }
            editarDescripcion.setText(descripcion);
            Log.w("ACTIVITI EVENTOS", "INTENT RASTREO" + intent);
            long fechaMillis = intent.getLongExtra("fechaEvento", -1);
            int prioridad = intent.getIntExtra("prioridadEvento", -1);

            evento = new Evento();
            evento.setNombre(nombre);
            evento.setDescripcion(descripcion);
            if (fechaMillis != -1) {
                evento.setFecha(new Date(fechaMillis));
            }
            evento.setPrioridad(prioridad);

            // Logging para diagnóstico
            Log.d("ActivityEditarEventos", "Nombre: " + nombre);
            Log.d("ActivityEditarEventos", "Descripción: " + descripcion);
            Log.d("ActivityEditarEventos", "Fecha (millis): " + fechaMillis);
            Log.d("ActivityEditarEventos", "Prioridad: " + prioridad);

            editarNombre.setText(nombre);
            editarDescripcion.setText(descripcion);
            // 27-05-2024 correccion prioridades mezcladas (test)
            String prioridadTexto = PrioridadAdaptador.convertirPrioridadATexto(prioridad);
            PrioridadAdaptador.seleccionarOpcionCombo(editarPrioridad, prioridadTexto);
            //editarPrioridad.setSelection(prioridad);
        } else {
            Log.e("ActivityEditarEventos", "No se encontraron datos en el intent");
            Toast.makeText(this, "No se encontraron datos del evento", Toast.LENGTH_SHORT).show();
        }

        // Funcionalidad calendario 28-05-2024

        editarFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dia = fecha.get(Calendar.DAY_OF_MONTH);
                mes = fecha.get(Calendar.MONTH);
                anio = fecha.get(Calendar.YEAR);
                hora = fecha.get(Calendar.HOUR_OF_DAY);
                minuto = fecha.get(Calendar.MINUTE);

                DatePickerDialog dpd = new DatePickerDialog(ActivityEditarEventos.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int anioSeleccionado, int mesSeleccionado, int diaSeleccionado) {
                                TimePickerDialog tpd = new TimePickerDialog(ActivityEditarEventos.this,
                                        new TimePickerDialog.OnTimeSetListener() {
                                            @Override
                                            public void onTimeSet(TimePicker view, int horaSeleccionada, int minutoSeleccionado) {
                                                evento.setFechaFromDatePicker(anioSeleccionado, mesSeleccionado, diaSeleccionado, horaSeleccionada, minutoSeleccionado);

                                                String diaFormateado = (diaSeleccionado < 10) ? "0" + diaSeleccionado : String.valueOf(diaSeleccionado);
                                                String mesFormateado = (mesSeleccionado < 9) ? "0" + (mesSeleccionado + 1) : String.valueOf(mesSeleccionado + 1);
                                                String horaFormateada = (horaSeleccionada < 10) ? "0" + horaSeleccionada : String.valueOf(horaSeleccionada);
                                                String minutoFormateado = (minutoSeleccionado < 10) ? "0" + minutoSeleccionado : String.valueOf(minutoSeleccionado);
                                                fechaFormateada = anioSeleccionado + "/" + mesFormateado + "/" + diaFormateado + " " + horaFormateada + ":" + minutoFormateado;

                                                Log.d("ActivityCrearEventos", "Fecha y hora seleccionadas: " + fechaFormateada);
                                            }
                                        }, hora, minuto, true);
                                tpd.show();
                            }
                        }, anio, mes, dia);
                dpd.show();
            }
        });

        // Botón para regresar 22-05-2024
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la actividad actual y vuelve a la anterior
            }
        });

        // Botón para aceptar la edición del evento 22-05-2024
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Verificar que los EditText y Spinner no sean nulos
                    if (editarNombre != null && editarDescripcion != null && editarPrioridad != null) {
                        String nombre = editarNombre.getText().toString().trim();
                        String descripcion = editarDescripcion.getText().toString().trim();
                        String prioridadSeleccionada = editarPrioridad.getSelectedItem().toString();

                        // Los campos no deben estar vacíos
                        if (!nombre.isEmpty() && !descripcion.isEmpty() && !prioridadSeleccionada.isEmpty()) {
                            evento.setNombre(nombre);
                            evento.setDescripcion(descripcion);
                            evento.setPrioridad(convertirPrioridad(prioridadSeleccionada));
                            Log.d("PRIORIDAD QQQQ", "EVENTO PRIORIDAD" + prioridadSeleccionada);
                            String guardarEvento = eventoLocal.modificarEvento(evento);
                            String mensaje = "Evento modificado";
                            Toast.makeText(v.getContext(), mensaje, Toast.LENGTH_LONG).show();

                            // Regresar a la actividad principal
                            Intent intent = new Intent(ActivityEditarEventos.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(v.getContext(), "Por favor, complete todos los campos.", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(v.getContext(), "Error: elementos de la interfaz no encontrados.", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Log.e("ActivityEditarEventos", "Error al aceptar el evento", e);
                    Toast.makeText(v.getContext(), "Error al aceptar el evento", Toast.LENGTH_LONG).show();
                }
            }
        });
        init();
    }
    public void init(){
        Intent intent = getIntent();
        int prioridad = intent.getIntExtra("prioridadEvento", -1);
        editarPrioridad.setSelection(prioridad);

    }

    private int convertirPrioridad(String prioridadSeleccionada) {
        switch (prioridadSeleccionada) {
            case "Baja":
                return 0;
            case "Media":
                return 1;
            case "Alta":
                return 2;
            default:
                return 0;
        }
    }
}
