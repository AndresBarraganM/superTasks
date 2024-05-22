package com.example.supertasks.ventanas;
import android.Manifest;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
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
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import com.example.supertasks.R;
import com.example.supertasks.modelos.Evento;
import com.example.supertasks.modelos.EventosGuardados;
import com.example.supertasks.MainActivity;
import java.util.Calendar;

public class ActivityCrearEventos extends AppCompatActivity {
    private EditText nombreEvento, descripcionEvento;

    // Variables para almacenar los datos de los eventos
    private String nombre, descripcion, prioridadSeleccionada, fechaFormateada;
    private Calendar fecha = Calendar.getInstance();
    private Evento evento = new Evento();

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
        TextView btnTxtAgregar = findViewById(R.id.btnTxtAgregar);

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

        //Notificaciones
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "test")
                .setSmallIcon(R.drawable.baseline_notifications_24)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("PRUEBA DE NOTIFICACION (ME QUIERO CORTAR EL PITO)")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        NotificationManager notificationManager = getSystemService(NotificationManager.class);

        TextView postNotification = findViewById(R.id.btnTxtAgregar);

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

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && ActivityCompat.checkSelfPermission(ActivityCrearEventos.this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    activityResultLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        CharSequence name = getString(R.string.app_name);
                        String description = "Example Notification";
                        int importance = NotificationManager.IMPORTANCE_DEFAULT;
                        NotificationChannel channel = new NotificationChannel("test", name, importance);
                        channel.setDescription(description);
                        notificationManager.createNotificationChannel(channel);

                        notificationManager.notify(10, builder.build());
                    }
                }

                nombre = nombreEvento.getText().toString();
                descripcion = descripcionEvento.getText().toString();
                prioridadSeleccionada = comboPrioridad.getSelectedItem().toString();
                evento.setNombre(nombre);
                evento.setDescripcion(descripcion);
                evento.setPrioridad(convertirPrioridad(prioridadSeleccionada));
                String guardarEvento = eventoLocal.agregarEvento(evento);
                String mensaje = "Nombre: " + evento.getNombre() +
                        "\nFecha: " + evento.getFecha() +
                        "\nPrioridad: " + evento.getPrioridad();
                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();

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
