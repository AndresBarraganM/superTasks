package com.example.supertasks.modelos

import com.example.supertasks.metodos_bd.DBHelper
import java.util.Calendar
import java.util.Date


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
    fun modificarEvento(id_evento_a_cambiar: Int, evento: Evento): String {
        //Aquí se guardará el evento modificar

        //Realizar en BD
        //

        //En forma local
        //Obtener indice
        var indice = eventos.indexOfFirst { it.id_evento == (evento.id_evento)}
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
    fun cantidadEventosPendientes(periodo: String): Int {
        var cantidadEventos: Int = 0

        //Crear lista solo con las fechas
        var listaFechas: MutableList<Date> = arrayListOf()

        //Obtener dia actual
        val ahora: Calendar = Calendar.getInstance()
        var limiteSup: Calendar = Calendar.getInstance()

        eventos.forEach{
            listaFechas.add(
                it.fecha)
        }

        //Variar limite superior
        when(periodo){
            ("dia") -> {
                //Agregar dia segun ahora
                limiteSup.add(Calendar.DAY_OF_MONTH,1);
            }
            ("semana") -> {
                //Agregar semana segun ahora
                limiteSup.add(Calendar.WEEK_OF_MONTH,1);
            }
            ("mes") -> {
                //Agregar dia segun ahora
                limiteSup.add(Calendar.MONTH,1);
            }
            else -> return -1
        }

        //Pasar a Date
        val ahoraDate = ahora.time
        val limSupDate = limiteSup.time

        listaFechas.forEach{
            if ((it.compareTo(ahoraDate) < 0) and (it.compareTo(limSupDate) > 0)){
                cantidadEventos += 1
            }
        }

        return cantidadEventos
    }


}