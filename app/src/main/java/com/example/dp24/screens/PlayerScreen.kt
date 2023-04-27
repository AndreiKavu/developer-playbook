package com.example.dp24.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.dp24.AudioPlayer
import com.example.dp24.R


@Composable
fun PlayerScreen(modifier: Modifier = Modifier) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val  height = ((screenWidth - 36.dp) / 3)
    Surface(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp),
    ) {

        LazyVerticalGrid(
            modifier = modifier.padding(horizontal = 16.dp),
            columns = GridCells.Fixed(
                3
            ),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            list.forEach {
                item {
                    Button(
                        onClick = { /*TODO*/ },
                        modifier.heightIn(height), shape = RoundedCornerShape(0),
                        colors = ButtonDefaults.buttonColors(

                            containerColor = colorResource(id = it.color)
                        )

                    ) {

                    }
                }
            }
        }
    }
}

data class MyModel(
    val id: String,
    val color: Int
)

val list: List<MyModel> = listOf(
    MyModel("1", R.color.purple_200),
    MyModel("2", R.color.black),
    MyModel("3", R.color.teal_200),
    MyModel("4", R.color.purple_500),
    MyModel("5", R.color.black),
    MyModel("6", R.color.purple_200),
    MyModel("7", R.color.teal_200),
    MyModel("8", R.color.purple_200),
    MyModel("9", R.color.black),
    MyModel("10", R.color.purple_500),
    MyModel("11", R.color.teal_200),
    MyModel("12", R.color.purple_200),

    )


@Composable
fun AudioPlayerScreen(audioPlayer: AudioPlayer) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // display a play button
        IconButton(onClick = { }) {
            Icon(Icons.Default.PlayArrow, contentDescription = "")
        }
    }

    // release resources when the component is destroyed
    DisposableEffect(audioPlayer) {
        onDispose {
            audioPlayer.release()
        }
    }
}
