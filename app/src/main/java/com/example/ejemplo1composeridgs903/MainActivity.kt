package com.example.ejemplo1composeridgs903

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private val tarjetas: List<PersonajeTarjeta> = listOf(
    PersonajeTarjeta("Bulma", "Bulma es la protagonista femenina de la serie manga Dragon Ball y sus adaptaciones al anime Dragon Ball, Dragon Ball Z, Dragon Ball Super y Dragon Ball GT."),
    PersonajeTarjeta("Launch", "Personaje que sufre cambios de personalidad al estornudar. Es uno de los personajes principales del manga Dragon Ball y su anime homónimo"),
    PersonajeTarjeta("Android 18", "Es la hermana melliza del Androide Número 17 y una androide creada a partir de una base humana por el Dr. Gero con el único fin de destruir a Goku."),
    PersonajeTarjeta("Marcarita ", "Marcarita es el ángel guía del Universo 11, sirviente y maestra de artes marciales del Dios de la Destrucción Vermoud."),
    PersonajeTarjeta("Majin Buu", "También conocido como Boo original, es la forma original y pura de Majin-Boo, y la última forma de Boo que aparece en Dragon Ball Z."),
    PersonajeTarjeta("Kaio del norte (Kaito)", "Se trata del dios encargado de administrar las Galaxias del Norte, el cuadrante boreal del Universo 7 y supervisar a los dioses de los planetas de dicho sector (como es el caso de Kami en la Tierra)"),
    PersonajeTarjeta("Freezer", "Freezer es el tirano espacial y el principal antagonista de la saga de Freezer.")
)

data class PersonajeTarjeta(val title: String, val body: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            Ejemplo1ComposerIDGS903Theme {
                Tarjeta(tarjetas)
            }
        }
    }

    @Composable
    fun Tarjeta(personajes: List<PersonajeTarjeta>){
        LazyColumn {
            items(personajes){ personaje ->
                MyPersonajes(personaje)
            }
        }
    }

    @Composable
    fun MyPersonajes(personaje: PersonajeTarjeta) {
        Card (
            modifier = Modifier.padding(16.dp)
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ){

        }
        Row (
            modifier = Modifier.padding(8.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {
            ImagenHeroe(personaje.title)
            Personajes(personaje)
        }
    }


    @Composable
    fun Personaje(name: String, color: Color, style: TextStyle, lines:Int=Int.MAX_VALUE){
        Column {
            Text(text = name, color = color, style = style, maxLines = lines)
        }
    }

    @Composable
    fun Personajes(personaje: PersonajeTarjeta){
        var expanded by remember { mutableStateOf(false) }
        Column (
            modifier = Modifier.padding(start = 8.dp).clickable {
                expanded = !expanded
            }
        ){
            Personaje(personaje.title,
                MaterialTheme.colorScheme.primary,
                MaterialTheme.typography.headlineMedium)

            Personaje(personaje.body,
                MaterialTheme.colorScheme.onBackground,
                MaterialTheme.typography.bodyLarge,
                if (expanded) Int.MAX_VALUE else 1)
        }
    }

    @Composable
    fun ImagenHeroe(imageName: String){
        val context = LocalContext.current
        val ImageResId = remember (imageName) {
            context.resources.getIdentifier(imageName.lowercase(),"drawable", context.packageName)
        }
        Image(
            painter = painterResource(id = ImageResId),
            contentDescription = "Chi Chi",
            modifier = Modifier
                .padding(16.dp)
                .size(100.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)

        )
    }

    @Preview
    @Composable
    fun GreetingPreview(){
        Tarjeta(tarjetas)
    }
}