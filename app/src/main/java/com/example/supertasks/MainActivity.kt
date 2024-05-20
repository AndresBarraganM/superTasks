import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.supertasks.metodos_bd.DBHelper
import com.example.supertasks.modelos.EventosGuardados
import com.example.supertasks.ui.theme.SuperTasksTheme

class MainActivity : ComponentActivity() {
    val contexto = this
    companion object {
        private var instance: MainActivity? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
        var  eventosLocales =EventosGuardados()
        var db = DBHelper(applicationContext())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                            val intent = Intent(this@MainActivity, MainActivity::class.java)
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}

// Otras funciones y composables aquí...

