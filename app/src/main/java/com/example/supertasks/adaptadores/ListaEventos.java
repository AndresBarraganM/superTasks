package com.example.supertasks.adaptadores;

public class ListaEventos {
    public String nombreEvento;
    public String fechaEvento;


    public ListaEventos(String nombreEvento, String fechaEvento) {
        this.nombreEvento = nombreEvento;
        this.fechaEvento = fechaEvento;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(String fechaEvento) {
        this.fechaEvento = fechaEvento;
    }
}
