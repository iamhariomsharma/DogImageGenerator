package com.heckteck.dogslibrary.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.heckteck.dogslibrary.ui.dog_generator.DogGeneratorScreenRoot
import com.heckteck.dogslibrary.ui.dogs_library.DogLibraryScreenRoot
import com.heckteck.dogslibrary.ui.home.HomeScreen

@Composable
fun DogsNavGraph(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screens.Home
    ) {
        composable<Screens.Home>(
            enterTransition = {
                return@composable fadeIn(tween(300))
            },
            exitTransition = {
                return@composable fadeOut(tween(300))
            }, popEnterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                )
            }
        ) {
            HomeScreen(
                onClickGenerate = {
                    navController.navigate(Screens.GenerateDogs)
                },
                onClickLibrary = {
                    navController.navigate(Screens.Library)
                }
            )
        }

        composable<Screens.GenerateDogs>(
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(300)
                )
            },
            popExitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(300)
                )
            },
        ) {
            DogGeneratorScreenRoot(
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }

        composable<Screens.Library>(
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(300)
                )
            },
            popExitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(300)
                )
            },
        ) {
            DogLibraryScreenRoot(
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}