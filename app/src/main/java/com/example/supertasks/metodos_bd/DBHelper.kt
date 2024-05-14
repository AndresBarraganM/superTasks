package com.example.supertasks.metodos_bd

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.supertasks.modelos.Evento
import java.util.Date

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        private const val DATABASE_NAME = "SuperTaskDB"
        private const val DATABASE_VERSION = 1

        private const val TABLE_NAME_EVENTO = "Evento"
        private const val ID_PRIMARIA_EVENTO = "id_evento"

        private const val TABLE_NAME_CATEGORIA = "Categoria"
        private const val ID_PRIMARIA_CATEGORIA = "id_categoria"

        private const val  TABLE_NAME_RELACION_EVENT_CAT = "Evento-Categoria"
    }

    //Comando con el que creamos la base de datos,
    val comando_crear_tabla_evento: String = """
        CREATE TABLE $TABLE_NAME_EVENTO (
          $ID_PRIMARIA_EVENTO INT NOT NULL,
          "fecha" TEXT NOT NULL DEFAULT '0000-00-00 00:00',
          "nombre" VARCHAR(20) NOT NULL DEFAULT 'Nombre de evento',
          "descripcion" VARCHAR(100)  DEFAULT 'No cuenta con descripcion',
          "prioridad" INT DEFAULT 1,
          "color" VARCHAR(20),
          PRIMARY KEY ($ID_PRIMARIA_EVENTO, AUTO_INCREMENT)
          );
    """

    val comando_crear_tabla_categoria: String = """CREATE TABLE $TABLE_NAME_CATEGORIA (
        $ID_PRIMARIA_CATEGORIA	INTEGER NOT NULL,
        "nombre"	TEXT DEFAULT 'Nombre de la etiqueta',
        "descripcion"	TEXT DEFAULT 'No cuenta con descripcion',
        "color"	TEXT,
        PRIMARY KEY($ID_PRIMARIA_CATEGORIA, AUTO_INCREMENT)
    )"""

    val comando_crear_tabla_relacion_event_cat: String = """
        CREATE TABLE $TABLE_NAME_RELACION_EVENT_CAT\ (
        $ID_PRIMARIA_EVENTO	INTEGER NOT NULL,
        $ID_PRIMARIA_CATEGORIA	INTEGER NOT NULL
)"""

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(comando_crear_tabla_evento)
        db.execSQL(comando_crear_tabla_categoria)
        db.execSQL(comando_crear_tabla_relacion_event_cat)
    }

    //Para actualizar la base de datos a una nueva version
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_EVENTO")
        onCreate(db)
    }

    /*
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
     */
    //Metodos para crear
    fun crearEvento(evento: Evento): Evento{
        val nombre = evento.nombre
        val descripcion = evento.descripcion
        val prioridad = evento.prioridad
        val color = evento.color

        //Obtener fecha en el formato deseado
        val dia = (evento.fecha.getDay()).toString()
        val mes = (evento.fecha.getMonth()).toString()
        val anio = (evento.fecha.getYear()).toString()

        val hora = (evento.fecha.getHours()).toString()
        val minutos = (evento.fecha.getMinutes()).toString()

        //Saltamos segundos
        val fecha = "$anio-$mes-$dia $hora:$minutos"

        val db = writableDatabase

        val values = ContentValues().apply {
            put("fecha", fecha)
            put("nombre", nombre)
            put("descripcion", descripcion)
            put("prioridad", prioridad)
            put("color", color)
        }

        val id = db.insert(TABLE_NAME_CATEGORIA, null, values)
        db.close()

        evento.id_evento = id.toInt()

        return evento
    }

    fun obtenerTodosEventos(): List<Evento>{
        val listaEventos = mutableListOf<Evento>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME_EVENTO"
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(ID_PRIMARIA_EVENTO))
            val nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
            val descripcion = cursor.getString(cursor.getColumnIndexOrThrow("desripcion"))
            val prioridad = cursor.getInt(cursor.getColumnIndexOrThrow("prioridad"))
            val color = cursor.getString(cursor.getColumnIndexOrThrow("color"))

            //crear Date, fecha de entrada en 0000-00-00 00:00
            val fechaTexto = cursor.getString(cursor.getColumnIndexOrThrow("fecha"))
                //Separar para dia, mes, anio y hora
            val dia = Integer.parseInt(fechaTexto.split("-")[0])
            val mes = Integer.parseInt(fechaTexto.split("-")[1])
            val anio = Integer.parseInt((fechaTexto.split("-")[2]).split(" ")[0])
            val hora = Integer.parseInt((fechaTexto.split(" ")[1]).split(":")[0])
            val minutos = Integer.parseInt(fechaTexto.split(":")[1])
            val fecha = Date(anio,mes,dia,hora,minutos)

            val evento = Evento(id, nombre,fecha, descripcion, prioridad, color)
            listaEventos.add(evento)
        }
        cursor.close()
        db.close()
        return listaEventos
    }

    fun obtenerEvento(id_entrada: Int): Evento {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME_EVENTO WHERE 'id_evento'= $id_entrada"
        val cursor = db.rawQuery(query, null)

        cursor.moveToNext()
        val id = cursor.getInt(cursor.getColumnIndexOrThrow(ID_PRIMARIA_EVENTO))
        val nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
        val descripcion = cursor.getString(cursor.getColumnIndexOrThrow("desripcion"))
        val prioridad = cursor.getInt(cursor.getColumnIndexOrThrow("prioridad"))
        val color = cursor.getString(cursor.getColumnIndexOrThrow("color"))

        //crear Date, fecha de entrada en 0000-00-00 00:00
        val fechaTexto = cursor.getString(cursor.getColumnIndexOrThrow("fecha"))
        //Separar para dia, mes, anio y hora
        val dia = Integer.parseInt(fechaTexto.split("-")[0])
        val mes = Integer.parseInt(fechaTexto.split("-")[1])
        val anio = Integer.parseInt((fechaTexto.split("-")[2]).split(" ")[0])
        val hora = Integer.parseInt((fechaTexto.split(" ")[1]).split(":")[0])
        val minutos = Integer.parseInt(fechaTexto.split(":")[1])
        val fecha = Date(anio,mes,dia,hora,minutos)

        val evento = Evento(id, nombre,fecha, descripcion, prioridad, color)

        cursor.close()
        db.close()

        return evento
    }

    fun borrarEvento(id_entrada: Int){
        val db = writableDatabase
        val clausulaWhere  =  ""
        val argumentos = arrayOf(id_entrada.toString())
        db.delete(TABLE_NAME_EVENTO,clausulaWhere, argumentos)
        db.close()
    }
}

