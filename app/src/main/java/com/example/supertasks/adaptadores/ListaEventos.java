package com.example.supertasks.adaptadores;

import java.util.Date;

public class ListaEventos {
    public String nombreEvento, descripcionEvento;
    public String fechaEvento;
    public int prioridadEvento;

    public int id_evento;

    public ListaEventos(String nombreEvento, Date fechaEvento, String descripcionEvento, int prioridadEvento, int id_evento) {
        this.nombreEvento = nombreEvento;
        this.fechaEvento = String.valueOf(fechaEvento);
        this.descripcionEvento = descripcionEvento;
        this.prioridadEvento = prioridadEvento;
        this.id_evento = id_evento;
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

    public int getId_evento() {return id_evento;}

    public void setId_evento(int id_evento) {this.id_evento = id_evento;}
}
