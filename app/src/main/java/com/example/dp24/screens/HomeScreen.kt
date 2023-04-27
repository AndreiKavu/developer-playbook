package com.example.dp24.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
@Composable
fun HomeScreen(modifier: Modifier = Modifier, navigateToHome: () -> Unit) {

    Surface(
        modifier = modifier
            .fillMaxSize(),
    ){
        Button(modifier = modifier.wrapContentSize(), onClick = { navigateToHome() }) {
            Text(text = "open player screen ")
        }
    }

}