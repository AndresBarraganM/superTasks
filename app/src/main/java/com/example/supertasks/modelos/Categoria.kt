package com.example.supertasks.modelos

import java.util.Date

//Clase que representa categorias

class Categoria {
    var id_categoria: Int = 0 //ID de la bd
        get()= field
        set(value) {field = value }

    var nombre: String = "Nombre de evento" //nombre del evento
        get() = field
        set(value) {field = value}

    var descripcion: String? = "Descripcion de evento" //Descripcion del evento
        get() = field
        set(value) {field = value}

    var color: String? = null //Color de el evento, por ver si se implementara
        get() = field
        set(value) {field = value}

    //Constructor
        //Constructor completo
    constructor(id_categoria: Int, nombre: String, descripcion: String?, color: String?) {
        this.id_categoria = id_categoria
        this.nombre = nombre
        this.descripcion = descripcion
        this.color = color
    }


        //Constructor con cosas necesarisas
    constructor(id_categoria: Int, nombre: String) {
        this.id_categoria = id_categoria
        this.nombre = nombre
    }

    //to_string
    override fun toString(): String {
        return "Categoria(id_categoria=$id_categoria, nombre='$nombre', descripcion=$descripcion, color=$color)"
    }

    //compare
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Categoria

        if (nombre != other.nombre) return false
        if (descripcion != other.descripcion) return false

        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }


}