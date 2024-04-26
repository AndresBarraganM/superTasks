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

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(comando_crear_tabla_evento)
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

