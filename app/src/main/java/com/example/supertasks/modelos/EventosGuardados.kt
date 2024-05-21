package com.example.supertasks.modelos

import com.example.supertasks.metodos_bd.DBHelper
import java.util.Calendar
import java.util.Date


class EventosGuardados constructor(private var db: DBHelper) {

    var eventos: MutableList<Evento> = arrayListOf()

    init
    {
        //Eventos creados desde un inicio
        /*Crear 6 eventos futuros, 3 eventos pasados
        futuro
          1 para 5 min
          2 para 24 horas despues
          1 para 1 semana
          1 para 2 semanas
          1 para 1 mes

        pasado
          1 hace 1 dia
          2 hace 1 semana
        */

        var fecha: Calendar = Calendar.getInstance()
        var ev: Evento
                //FUTURO
        //Para 5 min
        fecha.add(Calendar.MINUTE,5)
        ev = Evento("Presentar proyecto", fecha.time, "Presentar el trabajo de topicos",
            1, "amarillo")
        this.agregarEvento(ev)

        fecha = Calendar.getInstance()
        //Para 1 dia
        fecha.add(Calendar.DAY_OF_MONTH,1)
        ev = Evento("Estudiar para metodos numericos", fecha.time, "Repasar para el examen de esta unidad",
            3, "rojo")
        this.agregarEvento(ev)

        fecha.add(Calendar.DAY_OF_MONTH,1)
        ev = Evento("Entregar examen", fecha.time, "Entregar el examende simulacion",
            1, "amarillo")
        this.agregarEvento(ev)

        fecha = Calendar.getInstance()
        //Para 1 semana
        fecha.add(Calendar.WEEK_OF_MONTH,1)
        ev = Evento("Paquete amazon", fecha.time, "Estar al pendiente de un paquete de amazon",
            2, "amarillo")
        this.agregarEvento(ev)

        fecha = Calendar.getInstance()
        //Para 2 semanas
        fecha.add(Calendar.WEEK_OF_MONTH,2)
        ev = Evento("Pagar agua", fecha.time, "Pagar recibo de agua",
            3, "azul")
        this.agregarEvento(ev)

        fecha = Calendar.getInstance()
        //Para 1 mes
        fecha.add(Calendar.MONTH,1)
        ev = Evento("Comprar boletos para avion", fecha.time, "Comprar ticquet de vuelo de avion",
            2, "azul")
        this.agregarEvento(ev)
        fecha = Calendar.getInstance()

                //PASADO
        //Para 1 dia
        fecha.add(Calendar.DAY_OF_MONTH,-1)
        ev = Evento("Preparar presentacion de topcos", fecha.time, "Prepararse para el examen de topicos",
            2, "rojo")
        this.agregarEvento(ev)

        fecha = Calendar.getInstance()
        //Para 1 semana
        fecha.add(Calendar.WEEK_OF_MONTH,-1)
        ev = Evento("Realizar pruebas de programa", fecha.time, "Probar el programa de topicos y documentar",
            3, "amarillo")
        this.agregarEvento(ev)

        ev = Evento("Limpiar hogar", fecha.time, "Tener la casa limpia para las visitas",
            2, "azul")
        this.agregarEvento(ev)
    }

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