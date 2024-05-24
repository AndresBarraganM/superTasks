package com.example.supertasks.ventanas;
import android.Manifest;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.example.supertasks.R;
import com.example.supertasks.adaptadores.PrioridadAdaptador;
import com.example.supertasks.modelos.Evento;
import com.example.supertasks.modelos.EventosGuardados;
import com.example.supertasks.MainActivity;
import java.util.Calendar;

public class ActivityCrearEventos extends AppCompatActivity {
    private EditText nombreEvento, descripcionEvento;
    private String nombre, descripcion, prioridadSeleccionada, fechaFormateada;
    private Calendar fecha = Calendar.getInstance();
    private Evento evento = new Evento();
    private Spinner comboPrioridad;

    private void scheduleNotification(String nombre, String descripcion, long triggerAtMillis) {
        Intent intent = new Intent(this, NotificationReceiver.class);
        intent.putExtra("nombre", nombre);
        intent.putExtra("descripcion", descripcion);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent);
    }

    private final ActivityResultLauncher<String> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
        @Override
        public void onActivityResult(Boolean o) {
            if (o) {
                Toast.makeText(ActivityCrearEventos.this, "Post notification permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ActivityCrearEventos.this, "Post notification permission not granted", Toast.LENGTH_SHORT).show();
            }
        }
    });
    int dia, mes, anio, hora, minuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_evento);
        EventosGuardados eventoLocal = MainActivity.eventosLocales;
        if (eventoLocal != null) {
            // Intenta agregar el evento
            String guardarEvento = eventoLocal.agregarEvento(evento);
            Log.d("ActivityCrearEventos", "Evento almacenado: " + guardarEvento);
            // Resto del código para manejar el evento agregado
        } else {
            Log.e("ActivityCrearEventos", "eventosLocales es nulo");
            // Manejar el caso donde eventosLocales es nulo, por ejemplo, mostrar un mensaje de error al usuario.
        }
        TextView btnTxtAgregar = findViewById(R.id.btnTxtAgregar);
        comboPrioridad = findViewById(R.id.comboPrioridad);
        ImageView verCalendario = findViewById(R.id.verCalendario);
        nombreEvento = findViewById(R.id.txtFieldNombre);
        descripcionEvento = findViewById(R.id.editTextTextMultiLine);
        ImageView btnRegresar = findViewById(R.id.btnRegresar2);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        TextView postNotification = findViewById(R.id.btnTxtAgregar);

        // Configurar el comboBox
        PrioridadAdaptador.configurarPrioridad(this, comboPrioridad);

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
                // Notificacion
                Log.d("ActivityCrearEventos", "btnTxtAgregar clicked");

                // Notificacion
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && ActivityCompat.checkSelfPermission(ActivityCrearEventos.this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    Log.d("ActivityCrearEventos", "Requesting notification permission");
                    activityResultLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
                } else {
                    Log.d("ActivityCrearEventos", "Notification permission granted or not required");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        CharSequence name = getString(R.string.app_name);
                        String description = "Example Notification";
                        int importance = NotificationManager.IMPORTANCE_DEFAULT;
                        NotificationChannel channel = new NotificationChannel("test", name, importance);
                        channel.setDescription(description);
                        NotificationManager notificationManager = getSystemService(NotificationManager.class);
                        if (notificationManager != null) {
                            notificationManager.createNotificationChannel(channel);
                            Log.d("ActivityCrearEventos", "Notification channel created");
                        } else {
                            Log.e("Notification", "NotificationManager is null");
                            return;
                        }
                    }
                }

                // Crear Evento y Almacenarlo
                nombre = nombreEvento.getText().toString();
                descripcion = descripcionEvento.getText().toString();
                prioridadSeleccionada = comboPrioridad.getSelectedItem().toString();
                evento.setNombre(nombre);
                evento.setDescripcion(descripcion);
                evento.setPrioridad(convertirPrioridad(prioridadSeleccionada));

                // Migrar datos 24-05-2024
                Bundle bundle = new Bundle();
                bundle.putString("nombre", evento.getNombre());
                bundle.putString("descripcion", evento.getDescripcion());
                bundle.putLong("fecha", evento.getFecha().getTime());
                bundle.putInt("prioridad", evento.getPrioridad());

                String mensaje = "Nombre: " + evento.getNombre() +
                        "\nFecha: " + evento.getFecha() +
                        "\nPrioridad: " + evento.getPrioridad();
                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();

                // Validacion
                if (eventoLocal != null) {
                    String guardarEvento = eventoLocal.agregarEvento(evento);
                    Log.d("ActivityCrearEventos", "Evento almacenado: " + guardarEvento);
                    // Programar la notificación
                    long triggerAtMillis = evento.getFecha().getTime();
                    scheduleNotification(nombre, descripcion, triggerAtMillis);
                    Log.d("ActivityCrearEventos", "Starting ActivityEditarEventos");
                } else {
                    Log.e("ActivityCrearEventos", "eventosLocales es nulo");
                    Toast.makeText(ActivityCrearEventos.this, "Error al guardar el evento", Toast.LENGTH_SHORT).show();
                }

                // Migrar evento guardado a la lista de eventos 24-05-2024
                Intent resultIntent = new Intent();
                resultIntent.putExtra("nombre", evento.getNombre());
                resultIntent.putExtra("fecha", evento.getFecha().getTime());
                resultIntent.putExtra("prioridad", evento.getPrioridad());
                setResult(RESULT_OK, resultIntent);
                finish();
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
