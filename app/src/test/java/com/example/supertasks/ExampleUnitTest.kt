package com.example.supertasks

import com.example.supertasks.modelos.Evento
import com.example.supertasks.modelos.EventosGuardados
import org.junit.Test

import java.util.Date

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun funciona_eventos_guardados() {
        val listaEventos = EventosGuardados()

        //Probar agregar objetos
        var dat = Date(2024,5,18,15,30)
        var ev = Evento("nombre1",fecha = dat, "Es el primer evento", 2, "rojo")
        listaEventos.agregarEvento(ev)

        dat = Date(2024,4,27,17,0,0)
        ev = Evento("nombre2",fecha = dat, "Es el segundo evento", 2, "azul")
        listaEventos.agregarEvento(ev)

        dat = Date(2024,5,18,15,30)
        ev = Evento("nombre3",fecha = dat, "Es el tercer evento", 2, "amarillo")
        listaEventos.agregarEvento(ev)

        dat = Date(2024,3,18,10,30)
        ev = Evento("nombre4",fecha = dat, "Es el cuarto evento", 2, "amarillo")
        listaEventos.agregarEvento(ev)

        dat = Date(2024,5,10,18,27)
        ev = Evento("nombre5",fecha = dat, "Es el cuarto evento", 2, "amarillo")
        listaEventos.agregarEvento(ev)

        //Ver datos
        println(listaEventos.listaDeEventosFuturos())
        println(listaEventos.listaDeEventosYaHechos())

        //Eliminar datos
    }
}