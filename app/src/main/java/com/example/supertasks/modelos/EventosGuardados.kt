package com.example.supertasks.modelos

import com.example.supertasks.metodos_bd.DBHelper
import java.util.Calendar
import java.util.Date


class EventosGuardados constructor(private var db: DBHelper) {

    var eventos: MutableList<Evento> = arrayListOf()

    //Función que guarda los futuros eventos a realizar
    fun agregarEvento(evento: Evento): String {
        //Agregar a base de datos
        val eventoNuevo = db.crearEvento(evento)

        //Agregar a lista local
        eventos.add(eventoNuevo)

        return "Evento: $evento agregado correctamente"
    }

    // Funcion que guarda los eventos que el usuario quiera eliminar
    fun eliminarEvento(evento: Evento): String {
        //Agregar a base de datos
        evento.id_evento?.let { db.borrarEvento(it) }

        //Agregar a lista
        eventos.remove(evento)

        return "Evento: ${evento} eliminado correctamente"
    }

    // Funcion que modifica los eventos existentes del usuario
    fun modificarEvento( evento: Evento): String {
        //Aquí se guardará el evento modificar

        //Realizar en BD
        db.modificarEvento(evento)

        //En forma local
        //Obtener indice
        val indice = eventos.indexOfFirst { it.id_evento == (evento.id_evento)}
        //Obtener cual era el evento anterior
        val eventoAnterior = eventos[indice]
        //sustituir
        eventos[indice] = evento

        return "Evento $eventoAnterior modificado a $evento"
    }

    // Función que muestra la cantidad de eventos que el usuario realizará
    fun listaDeEventosFuturos(): MutableList<Evento> {
        val eventosFuturos: MutableList<Evento> = arrayListOf()

        //Obtener dia actual
        val ahora = Calendar.getInstance().time

        //Iterar sobre la lista propia
        eventos.forEach{
            if (it.fecha.after(ahora) ){ // es despues
                eventosFuturos.add(it)
            }

        }
        return eventosFuturos
    }

    // función que muestra la cantidad de eventos que ha hecho el usuario
    fun listaDeEventosYaHechos(): MutableList<Evento> {
        val eventosPasados: MutableList<Evento> = arrayListOf()


        //Obtener dia actual
        val ahora = Calendar.getInstance().time

        //Iterar sobre la lista propia
        eventos.forEach{
            if (it.fecha.before(ahora) ){ // es antes
                eventosPasados.add(it)
            }

        }
        return eventosPasados
    }

    // Función que muestra los eventos pendientes en los proximos dias-meses-años
    fun cantidadEventosPendientes(periodo: String): Int {
        var cantidadEventos = 0

        //Crear lista solo con las fechas
        val listaFechas: MutableList<Date> = arrayListOf()

        //Obtener dia actual
        val ahora: Calendar = Calendar.getInstance()
        val limiteSup: Calendar = Calendar.getInstance()

        eventos?.forEach {
            listaFechas.add(it.fecha)
        }

        //Variar limite superior
        when(periodo){
            ("dia") -> {
                //Agregar dia segun ahora
                limiteSup.add(Calendar.DAY_OF_MONTH,1)
            }
            ("semana") -> {
                //Agregar semana segun ahora
                limiteSup.add(Calendar.WEEK_OF_MONTH,1)
            }
            ("mes") -> {
                //Agregar dia segun ahora
                limiteSup.add(Calendar.MONTH,1)
            }
            else -> {
                println("Error en metodo cantidad de eventos pendientes," +
                        " se indico periodo no existente")
                return -1
            }
        }

        //Pasar a Date
        val ahoraDate = ahora.time
        val limSupDate = limiteSup.time

        listaFechas?.forEach{
            if ((it.after(ahoraDate)) && (it.before(limSupDate))){
                cantidadEventos += 1
            }
        }

        return cantidadEventos
    }

    //Este metodo devuelve una lista con los eventos proximos mas cercanos, la catnidad es indicada
    fun eventosCercanos(cantidad: Int): MutableList<Evento>{
        //Lista que saldra
        val listaLocal = listaDeEventosFuturos()
        val listaSalida: MutableList<Evento> = arrayListOf()
        // Si no encontro ninguno
        if (listaLocal.size == 0) { return listaSalida }
        listaLocal.sortBy{it.fecha}
        //Recortar
        for (i in 0 until  cantidad){
            listaSalida.add(listaLocal[i])
        }

        return listaSalida
    }
}