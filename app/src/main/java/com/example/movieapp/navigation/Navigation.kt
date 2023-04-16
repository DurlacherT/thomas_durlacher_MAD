package com.example.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.MovieCollectionViewModelFactory
import com.example.movieapp.data.MovieDatabase
import com.example.movieapp.models.MovieCollectionViewModel
import com.example.movieapp.repositories.MovieRepository
import com.example.movieapp.screens.*

@Composable
fun Navigation() {
    val navController = rememberNavController()

    val db = MovieDatabase.getDatabase(LocalContext.current)
    val repository = MovieRepository(movieDao = db.movieDao())
    val factory = MovieCollectionViewModelFactory(repository)

    val coroutineScope = rememberCoroutineScope()

    val viewModel: MovieCollectionViewModel = viewModel(factory = factory)

    val movies = repository.getAllMovies()

    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {

        composable(route = Screen.MainScreen.route){
            HomeScreen(navController = navController, viewModel = viewModel, data = repository)
        }

        composable(Screen.FavoriteScreen.route) {
            FavoriteScreen(navController = navController, viewModel = viewModel)
        }

        composable(Screen.AddMovieScreen.route) {
            AddMovieScreen(navController = navController, viewModel = viewModel)
        }

        // build a route like: root/detail-screen/id=34
        composable(
            Screen.DetailScreen.route,
            arguments = listOf(navArgument(name = DETAIL_ARGUMENT_KEY) {type = NavType.StringType})
        ) { backStackEntry ->    // backstack contains all information from navhost
            DetailScreen(navController = navController, viewModel = viewModel, backStackEntry.arguments?.getString(
                DETAIL_ARGUMENT_KEY))   // get the argument from navhost that will be passed
        }
    }
}