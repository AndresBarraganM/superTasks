package com.example.supertasks.ventanas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.supertasks.R;

public class ActivityCalendario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);
        CalendarView calendarioView = findViewById(R.id.calendario);

        /*
            Calendar calendarioActual = Calendar.getInstance();
            int anio = calendarioActual.get(Calendar.YEAR);
            int mes = calendarioActual.get(Calendar.MONTH);
          int dia = calendarioActual.get(Calendar.DAY_OF_MONTH);
         */
        calendarioView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
        });

        ImageView btnRegresar = findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 26/04/2024
                Intent intent = new Intent(ActivityCalendario.this, MainActivityJava.class);
                startActivity(intent);
            }
        });
    }
}
