package com.example.supertasks

import androidx.test.platform.app.InstrumentationRegistry
import com.example.supertasks.metodos_bd.DBHelper
import com.example.supertasks.modelos.Evento
import com.example.supertasks.modelos.EventosGuardados
import org.junit.Test
import java.util.Calendar

import java.util.Date

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    //NO FUNCIONA DEVIDO A QUE NO PUEDE USAR CONTEXTO
    //UTILIZA EL QUE SE ENCUENTRA EN ExampleInstrumentedTest
    @Test
    fun funciona_eventos_guardados() {
        val listaEventos = EventosGuardados()

        var db = DBHelper(InstrumentationRegistry.getInstrumentation().getTargetContext())


        val ahora = Calendar.getInstance().time
        println("Tiempo de ahora: "+ ahora)

        //Probar agregar objetos
        var dat = Date(124,3,9,18,53)
        var ev = Evento("nombre1", dat, "Es el primer evento", 2, "rojo")
        listaEventos.agregarEvento(ev)

        dat = Date(124,3,20,17,0)
        ev = Evento("nombre2", dat, "Es el segundo evento", 2, "azul")
        listaEventos.agregarEvento(ev)

        dat = Date(124,4,10,20,30)
        ev = Evento("nombre3", dat, "Es el tercer evento", 2, "amarillo")
        listaEventos.agregarEvento(ev)

        dat = Date(124,3,18,10,30)
        ev = Evento("nombre4", dat, "Es el cuarto evento", 2, "amarillo")
        listaEventos.agregarEvento(ev)

        dat = Date(124,4,15,18,27)
        ev = Evento("nombre5", dat, "Es el quinto evento", 2, "amarillo")
        listaEventos.agregarEvento(ev)

        dat = Date(124,4,26,18,27)
        ev = Evento("nombre6", dat, "Es el sexto evento", 2, "amarillo")
        listaEventos.agregarEvento(ev)

        dat = Date(0,0,0,18,27)
        ev = Evento("Evento a borrar", dat, "Es el quinto evento", 2, "amarillo")
        listaEventos.agregarEvento(ev)
        //Ver datos
        println(listaEventos.listaDeEventosFuturos())
        println(listaEventos.listaDeEventosYaHechos())

        //Eliminar datos
        listaEventos.eliminarEvento(ev)
        println(listaEventos.listaDeEventosFuturos())

        //Probar modificar evento
        //TODO Falta hacer que agregar evento devuelva el evento con su id

        //Ver metodo para cantidad de eventos
        println("eventos por dia " +
        listaEventos.cantidadEventosPendientes("dia"))
        println("eventos por semana " +
                listaEventos.cantidadEventosPendientes("semana"))
        println("eventos por mes " +
                listaEventos.cantidadEventosPendientes("mes"))
    }
}