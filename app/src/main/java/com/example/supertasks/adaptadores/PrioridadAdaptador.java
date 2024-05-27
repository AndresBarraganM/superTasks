package com.example.supertasks.adaptadores;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class PrioridadAdaptador {
    public static void configurarPrioridad (Context context, Spinner comboPrioridad) {
        String[] opcionesCombo = {"Baja", "Media", "Alta"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, opcionesCombo);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        comboPrioridad.setAdapter(adaptador);
    }

    public static void seleccionarOpcionCombo(Spinner prioridad, String opcion) {
        ArrayAdapter<String> adaptador = (ArrayAdapter<String>) prioridad.getAdapter();
        if (adaptador != null) {
            int spinnerPosition = adaptador.getPosition(opcion);
            if (spinnerPosition >= 0) {
                prioridad.setSelection(spinnerPosition);
            }
        }
    }
}
