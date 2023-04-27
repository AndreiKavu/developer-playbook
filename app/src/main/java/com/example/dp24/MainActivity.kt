package com.example.dp24

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.dp24.navigation.NavGraph
import com.example.dp24.screens.AudioPlayerScreen
import com.example.dp24.ui.theme.Dp24Theme

class MainActivity : ComponentActivity() {

    private lateinit var audioPlayer: AudioPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // create an audio player
//        audioPlayer = AudioPlayer(this, this.getResources().openRawResourceFd(R.raw.rocket_bass))

        // display audio player screen
//        setContent {
//            AudioPlayerScreen(audioPlayer)
//        }
            setContent {
            Dp24Theme {
                // A surface container using the 'background' color from the theme
                MainScreen()
            }
        }
    }
}

@Composable
private fun MainScreen() {
    val navController = rememberNavController()
    NavGraph(navController)
}
//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    Dp24Theme {
//        Greeting("Android")
//    }
//}