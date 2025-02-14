package com.heckteck.dogslibrary.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.heckteck.dogslibrary.ui.home.HomeScreen

@Composable
fun DogsNavGraph(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.Home
    ) {
        composable<Screens.Home> {
            HomeScreen(
                onClickGenerate = {
                    navController.navigate(Screens.GenerateDogs)
                },
                onClickLibrary = {
                    navController.navigate(Screens.Library)
                }
            )
        }
    }
}