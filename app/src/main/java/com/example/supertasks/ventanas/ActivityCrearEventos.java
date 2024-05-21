package com.example.supertasks.ventanas;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.supertasks.R;
import com.example.supertasks.modelos.Evento;
import com.example.supertasks.modelos.EventosGuardados;
import com.example.supertasks.MainActivity;

import java.util.Calendar;

public class ActivityCrearEventos extends AppCompatActivity {
    private static final String CHANNEL_ID = "NEW";
    private PendingIntent pendingIntent;
    private EditText nombreEvento, descripcionEvento;

    // Variables para almacenar los datos de los eventos
    private String nombre, descripcion, prioridadSeleccionada, fechaFormateada;
    private Calendar fecha = Calendar.getInstance();
    private Evento evento = new Evento();

    int dia, mes, anio, hora, minuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_evento);
        EventosGuardados eventoLocal = MainActivity.eventosLocales;
        TextView btnTxtAgregar = findViewById(R.id.btnTxtAgregar);

        btnTxtAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    createNotificationChannel();
                }
                showNewNotification();
            }
        });

        ImageView verCalendario = findViewById(R.id.verCalendario);
        nombreEvento = findViewById(R.id.txtFieldNombre);
        descripcionEvento = findViewById(R.id.editTextTextMultiLine);
        ImageView btnRegresar = findViewById(R.id.btnRegresar2);

        // ComboBox nivel prioridad
        Spinner comboPrioridad = findViewById(R.id.comboPrioridad);
        String[] opcionesCombo = {"Alta", "Media", "Baja"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcionesCombo);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        comboPrioridad.setAdapter(adaptador);

        // Establecer fecha y hora
        verCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dia = fecha.get(Calendar.DAY_OF_MONTH);
                mes = fecha.get(Calendar.MONTH);
                anio = fecha.get(Calendar.YEAR);
                hora = fecha.get(Calendar.HOUR_OF_DAY);
                minuto = fecha.get(Calendar.MINUTE);

                DatePickerDialog dpd = new DatePickerDialog(ActivityCrearEventos.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int anioSeleccionado, int mesSeleccionado, int diaSeleccionado) {
                                TimePickerDialog tpd = new TimePickerDialog(ActivityCrearEventos.this,
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

        // Obtener el texto ingresado en el nombre de evento
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

        // Obtener el texto ingresado en descripcion
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
                Intent intent = new Intent(ActivityCrearEventos.this, MainActivityJava.class);
                startActivity(intent);
            }
        });

        btnTxtAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = nombreEvento.getText().toString();
                descripcion = descripcionEvento.getText().toString();
                prioridadSeleccionada = comboPrioridad.getSelectedItem().toString();
                evento.setNombre(nombre);
                evento.setDescripcion(descripcion);
                evento.setPrioridad(convertirPrioridad(prioridadSeleccionada));
                String mensaje = eventoLocal.agregarEvento(evento);
                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "NEW", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void showNewNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1);
                return;
            }
        }

        setPendingIntent(ActivityCrearEventos.class);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setContentTitle("Mi Primera Notificación")
                .setContentText("Esta es una prueba de notificación")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(1, builder.build());
    }

    private void setPendingIntent(Class<?> clsActivity) {
        Intent intent = new Intent(this, clsActivity);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(clsActivity);
        stackBuilder.addNextIntent(intent);
        pendingIntent = stackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showNewNotification();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
