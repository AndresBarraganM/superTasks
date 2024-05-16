package com.example.supertasks

import androidx.test.platform.app.InstrumentationRegistry
import com.example.supertasks.metodos_bd.DBHelper
import com.example.supertasks.modelos.Evento
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Date

class BDTest {
    @Test
    fun testDeBaseDatos(){
        //Eventos iniciales
        var eventos: MutableList<Evento> = crearListaEventos()
        //Creo la bd
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        var db = DBHelper(appContext)

        //PRUEBA, CREAR EVENTO
        println("PRUEBA CREAR UN EVENTO")
        for (ev in  eventos){
            db.crearEvento(ev)
            println("Evento agregado:"+ ev)
        }

        //PRUEBA, OBTENER EVENTOS
        println("PRUEBA RECUPERAR EVENTOS")
        var eventosRecuperados = db.obtenerTodosEventos()
        assert(eventosRecuperados.equals(eventos))

        //PRUEBA, LEER UN EVENTO
        println("PRUEBA LEER UN EVENTO")
        var id_prueba = eventos[1].id_evento

        println("id a recuperar:" + id_prueba)
        val eventoDeBD = id_prueba?.let { db.obtenerEvento(it) }
        if (eventoDeBD != null) {
            assert(eventoDeBD.equals(eventos[1]))
        } else{
            throw Error("No Evento encontrado con esta id")
        }

        //PRUEBA, BORRAR EVENTO
        println("PRUEBA BORRAR EVENTO")
        id_prueba =  eventos[1].id_evento
        if (id_prueba != null) {
            db.borrarEvento(id_prueba)
        }
        if (eventoDeBD != null) {
            println("Evento 1 no fue encontrado")
        } else{
            throw Error("Se encontro un evento que deve haber sido borrado")
        }


    }

    fun crearListaEventos(): MutableList<Evento> {
        var listaEventos: MutableList<Evento> = arrayListOf()

        var dat = Date(124, 3, 9, 18, 53)
        var ev = Evento("nombre1", dat, "Es el primer evento", 2, "rojo")
        listaEventos.add(ev)

        dat = Date(124, 3, 20, 17, 0)
        ev = Evento("nombre2", dat, "Es el segundo evento", 2, "azul")
        listaEventos.add(ev)

        dat = Date(124, 4, 10, 20, 30)
        ev = Evento("nombre3", dat, "Es el tercer evento", 2, "amarillo")
        listaEventos.add(ev)

        dat = Date(124, 3, 18, 10, 30)
        ev = Evento("nombre4", dat, "Es el cuarto evento", 2, "amarillo")
        listaEventos.add(ev)

        dat = Date(124, 4, 15, 18, 27)
        ev = Evento("nombre5", dat, "Es el quinto evento", 2, "amarillo")
        listaEventos.add(ev)

        return listaEventos
    }
}