package com.example.supertasks.modelos

import java.util.Date //Documentacion https://developer.android.com/reference/kotlin/java/util/Date

//Clase la cual representa eventos

class Evento {
    private var id_evento: Int = 0 //ID de la base de datos
        get()= field
        set(value) {field = value }
    private var fecha: Date = Date()//Fecha de el evento
        get() = field
        set(value) {field = value}
    private var nombre: String = "Nombre de evento" //nombre del evento
        get() = field
        set(value) {field = value}
    private var descripcion: String? = "Descripcion de evento" //Descripcion del evento
        get() = field
        set(value) {field = value}
    private var prioridad: Int? = 0 //Prioridad de el evento
        get() = field
        set(value) {field = value}
    private var color: String? = null //Color de el evento, por ver si se implementara
        get() = field
        set(value) {field = value}

    //Constructores
    //Constructor completo
    constructor(nombre: String, fecha: Date, descripcion: String, prioridad: Int, color: String){
        this.nombre = nombre
        this.fecha = fecha
        this.descripcion = descripcion
        this.prioridad = prioridad
        this.color = color
    }

    //Constructor completo con fecha hecha con numeros (hora en militar)
    constructor(nombre: String, dia: Int, mes: Int, anio: Int, hora: Int, minuto: Int,
                descripcion: String, prioridad: Int, color: String){
        this.nombre = nombre
        this.descripcion = descripcion
        this.prioridad = prioridad
        this.color = color

        //Crear fecha
        this.fecha = Date(anio, mes, dia, hora, minuto)
    }

    //Constructor con solo cosas necesarias
    constructor(nombre: String, fecha: Date){
        this.nombre = nombre
        this.fecha = fecha
    }

    //Constructor con cosas necesarias con horas hechas con numeros (hora en militar)
    constructor(nombre: String, dia: Int, mes: Int, anio: Int, hora: Int, minuto: Int){
        this.nombre = nombre
        this.descripcion = descripcion
        this.prioridad = prioridad
        this.color = color

        this.fecha = Date(anio, mes, dia, hora, minuto)
    }


    //Metodo para
    override fun toString(): String {
        return "Evento(id_evento=$id_evento, fecha=$fecha, nombre='$nombre', descripcion=$descripcion, prioridad=$prioridad, color=$color)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Evento

        if (fecha != other.fecha) return false

        return true
    }

    override fun hashCode(): Int {
        return fecha.hashCode()
    }


}