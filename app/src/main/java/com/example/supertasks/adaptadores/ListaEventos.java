package com.example.supertasks.adaptadores;

import java.util.Date;

public class ListaEventos {
    public String nombreEvento, descripcionEvento;
    public String fechaEvento;
    public int prioridadEvento;


    public ListaEventos(String nombreEvento, Date fechaEvento, String descripcionEvento, int prioridadEvento) {
        this.nombreEvento = nombreEvento;
        this.fechaEvento = String.valueOf(fechaEvento);
        this.descripcionEvento = descripcionEvento;
        this.prioridadEvento = prioridadEvento;
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

    public String getDescripcion() {return descripcionEvento;}

    public void setDescripcionEvento(String descripcionEvento) {this.descripcionEvento = descripcionEvento;}

    public int getPrioridad () {
        return prioridadEvento;
    }

    public void setPrioridadEvento(int prioridadEvento) {this.prioridadEvento = prioridadEvento;}


}
