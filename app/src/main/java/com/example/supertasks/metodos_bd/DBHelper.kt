package com.example.supertasks.metodos_bd

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
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
          "fecha" TEXT NOT NULL DEFAULT '0000-00-00 00:00:00',
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
}

