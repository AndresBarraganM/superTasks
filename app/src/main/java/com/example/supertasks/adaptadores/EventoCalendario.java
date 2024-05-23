package com.example.supertasks.adaptadores;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.CalendarView;

import java.util.Calendar;
import java.util.HashSet;

public class EventoCalendario extends CalendarView {
    private HashSet<Calendar> eventoCalendario = new HashSet<>();
    private Paint paint;

    public EventoCalendario(Context context) {
        super(context);
        init();
    }

    public EventoCalendario(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EventoCalendario(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED); // Color del punto
        paint.setStyle(Paint.Style.FILL);
    }

    public void setEventoCalendario(HashSet<Calendar> eventoCalendario) {
        this.eventoCalendario = eventoCalendario;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Calcula la posición de los puntos y dibuja en el canvas
        for (Calendar eventDay : eventoCalendario) {
            // Convierte la fecha del evento a la posición en el CalendarView
            // Esto es una aproximación. Necesitas ajustar según tu diseño.
            int dayOfMonth = eventDay.get(Calendar.DAY_OF_MONTH);
            int month = eventDay.get(Calendar.MONTH);
            int year = eventDay.get(Calendar.YEAR);

            // Calcula las posiciones X e Y (esto es una aproximación)
            float x = getXPositionForDate(dayOfMonth, month, year);
            float y = getYPositionForDate(dayOfMonth, month, year);

            // Dibuja un punto en la fecha del evento
            if (x >= 0 && y >= 0) {
                canvas.drawCircle(x, y, 10, paint); // Radio del punto: 10px
            }
        }
    }

    private float getXPositionForDate(int day, int month, int year) {
        // Lógica para calcular la posición X (esto es solo una aproximación)
        // Debes ajustar esto para que funcione correctamente según tu diseño
        return day * 20; // Ejemplo simplificado, debes ajustar para tu caso
    }

    private float getYPositionForDate(int day, int month, int year) {
        // Lógica para calcular la posición Y (esto es solo una aproximación)
        // Debes ajustar esto para que funcione correctamente según tu diseño
        return month * 40; // Ejemplo simplificado, debes ajustar para tu caso
    }
}
