package com.example.dp24.navigation

sealed class NavRoute(val path: String) {

    object Home: NavRoute("home")

    object Player: NavRoute("pleer")
}
