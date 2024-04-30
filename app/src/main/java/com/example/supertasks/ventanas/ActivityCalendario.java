package com.example.supertasks.ventanas;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.supertasks.R;
import java.util.Calendar;

public class ActivityCalendario extends AppCompatActivity{
    TextView tv;

    // 26-04-2024
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);
        tv = findViewById(R.id.calendario);
        initCalendario(tv);
    }

    public void initCalendario(View view) {
        Calendar calendario = Calendar.getInstance();
        int anio = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(ActivityCalendario.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String fecha = dayOfMonth + "/" + month + "/" + year;
                tv.setText(fecha);
            }
        }, anio, mes, dia);
        dpd.show();
    }
}

