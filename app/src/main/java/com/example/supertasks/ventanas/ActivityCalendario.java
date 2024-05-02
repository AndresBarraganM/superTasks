package com.example.supertasks.ventanas;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.supertasks.R;
import java.util.Calendar;

public class ActivityCalendario extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        tv = findViewById(R.id.calendario);
        CalendarView calendarioView = findViewById(R.id.calendario);

        // Obtener la fecha actual y mostrarla en el TextView
        Calendar calendarioActual = Calendar.getInstance();
        int anio = calendarioActual.get(Calendar.YEAR);
        int mes = calendarioActual.get(Calendar.MONTH);
        int dia = calendarioActual.get(Calendar.DAY_OF_MONTH);
        tv.setText(dia + "/" + (mes + 1) + "/" + anio); // El mes se indexa desde 0, así que sumamos 1

        // Establecer un Listener para el cambio de fecha en el CalendarView
        calendarioView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Actualizar el TextView con la fecha seleccionada
                tv.setText(dayOfMonth + "/" + (month + 1) + "/" + year); // El mes se indexa desde 0, así que sumamos 1
            }
        });
    }
}
