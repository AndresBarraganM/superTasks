package com.example.supertasks.modelos

import com.example.supertasks.metodos_bd.DBHelper
import java.util.Calendar



class EventosGuardados {

    var eventos: MutableList<Evento> = arrayListOf()

    //Función que guarda los futuros eventos a realizar
    fun agregarEvento(evento: Evento): String {
        //Agregar a base de datos
        //DBHelper.crearEvento(evento)

        //Agregar a lista local
        eventos.add(evento)

        return "Evento: ${evento.toString()} agregado correctamente"
    }

    // Funcion que guarda los eventos que el usuario quiera eliminar
    fun eliminarEvento(evento: Evento): String {
        //Agregar a base de datos
        //DBHelper.eliminarEvento(evento.id)

        //Agregar a lista
        eventos.remove(evento)

        return "Evento: ${evento.toString()} eliminado correctamente"
    }

    // Funcion que modifica los eventos existentes del usuario
    fun modificarEvento(evento: String): String {
        //Aquí se guardará el evento modificar

        //Realizar en BD
        //

        //En forma local
        //Obtener indice
        var indice = eventos.indexOfFirst { it.equals(evento)}
        //Obtener cual era el evento anterior
        var eventoAnterior = eventos[indice]
        //sustituir
        eventos[indice] = eventoAnterior

        return "Evento $eventoAnterior modificado a $evento"
    }

    // Función que muestra la cantidad de eventos que el usuario realizará
    fun listaDeEventosFuturos(): MutableList<Evento> {
        var eventosFuturos: MutableList<Evento> = arrayListOf()


        //Obtener dia actual
        val ahora = Calendar.getInstance().time

        //Iterar sobre la lista propia
        eventos.forEach{
            if (it.fecha.compareTo(ahora) > 0){ // es despues
                eventosFuturos.add(it)
            }

        }
        return eventosFuturos
    }

    // función que muestra la cantidad de eventos que ha hecho el usuario
    fun listaDeEventosYaHechos(): MutableList<Evento> {
        var eventosPasados: MutableList<Evento> = arrayListOf()


        //Obtener dia actual
        val ahora = Calendar.getInstance().time

        //Iterar sobre la lista propia
        eventos.forEach{
            if (it.fecha.compareTo(ahora) < 0){ // es despues
                eventosPasados.add(it)
            }

        }
        return eventosPasados
    }

    // Función que muestra los eventos pendientes en los proximos dias-meses-años
    fun cantidadEventosPendientes(perido: String): String {
        // Regresará el periodo en dias-meses-años
        return "periodo" // Ejemplo de lo que regresará, no se tome como el absoluto.
    }


}