package com.example.dp24.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.dp24.screens.HomeScreen
import com.example.dp24.screens.PlayerScreen

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = NavRoute.Home.path
    ) {
        addHomeScreen(navController, this)

        addPlayerScreen(navController, this)
    }
}

private fun addHomeScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = NavRoute.Home.path) {
        HomeScreen(
            navigateToHome = {
                navController.navigate(NavRoute.Player.path)
            }
        )
    }
}

private fun addPlayerScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = NavRoute.Player.path) {

        PlayerScreen()
    }
}

private fun popUpToLogin(navController: NavHostController) {
    navController.popBackStack(NavRoute.Home.path, inclusive = false)
}
