package com.example.supertasks

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.example.supertasks.metodos_bd.DBHelper
import com.example.supertasks.modelos.EventosGuardados
import com.example.supertasks.ui.theme.SuperTasksTheme
import com.example.supertasks.ventanas.MainActivityJava

class MainActivity : ComponentActivity() {
    init {
        instance = this
    }

    val contexto = this
    companion object {
        private var instance: MainActivity? = null

        fun getInstance(): MainActivity? {
            return instance
        }

        fun applicationContext(): Context {
            return instance?.applicationContext ?: throw IllegalStateException("Application context is not available")
        }

        lateinit var db: DBHelper

        lateinit var eventosLocales: EventosGuardados
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
        Companion.db = DBHelper(applicationContext())
        Log.d("MainActivity", "DB instance: ${Companion.db.toString()}") // Imprime en el logcat
        eventosLocales = EventosGuardados(Companion.db)
        Log.d("MainActivity", "Eventos locales instance: ${Companion.eventosLocales.toString()}")
        val intent = Intent(this@MainActivity, MainActivityJava::class.java)
        startActivity(intent)

        setContent {
            SuperTasksTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Botón para abrir la nueva actividad
                    Text(
                        text = "Ola",
                        modifier = Modifier.clickable {

                        }
                    )
                }
            }
        }
    }
}

// Otras funciones y composables aquí...

