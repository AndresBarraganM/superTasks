package com.example.supertasks.adaptadores;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.CalendarView;

import java.util.Calendar;
import java.util.HashSet;

public class EventoCalendario extends CalendarView {

    private HashSet<Calendar> eventoCalendario = new HashSet<>();

    public EventoCalendario(Context context) {
        super(context);
    }

    public EventoCalendario(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EventoCalendario(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setEventoCalendario(HashSet<Calendar> eventoCalendario) {
        this.eventoCalendario = eventoCalendario;
        invalidate();
    }

}
