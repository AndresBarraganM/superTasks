package com.example.supertasks.modelos

class EventosGuardados {

    lateinit var eventos: MutableList<Evento>

    //Función que guarda los futuros eventos a realizar
    fun agregarEvento(evento: String): String {
        //Aqui se guardará el evento Agregar
        return "Evento agregado correctamente"
    }

    // Funcion que guarda los eventos que el usuario quiera eliminar
    fun eliminarEvento(evento: String): String {
        // Aquí se guardará el evento Eliminar
        return "Evento eliminado correctamente"
    }

    // Funcion que modifica los eventos existentes del usuario
    fun modificarEvento(evento: String): String {
        //Aquí se guardará el evento modificar
        return "Evento modificado correctamente"
    }

    // Función que muestra la cantidad de eventos que el usuario realizará
    fun listaDeEventosFuturos(): Int {
        val cantidad: Int = 5 // ejemplo de lo que regresará, no se tome como absoluto
        return cantidad
    }

    // función que muestra la cantidad de eventos que ha hecho el usuario
    fun listaDeEventosYaHechos(): Int {
        val cantidad = 5 // Ejemplo de lo que regresará, no se tome como absoluto
        return cantidad
    }

    // Función que muestra los eventos pendientes en los proximos dias-meses-años
    fun cantidadEventosPendientes(perido: String): String {
        // Regresará el periodo en dias-meses-años
        return "periodo" // Ejemplo de lo que regresará, no se tome como el absoluto.
    }


}