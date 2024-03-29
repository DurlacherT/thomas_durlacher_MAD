package com.example.movieapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieapp.models.Movie
import com.example.movieapp.models.MovieCollectionViewModel
import com.example.movieapp.models.getMovies
import com.example.movieapp.widgets.MovieRow
import com.example.movieapp.widgets.SimpleTopAppBar

@Composable
fun FavoriteScreen(navController: NavController, viewModel : MovieCollectionViewModel){
    Scaffold(topBar = {
        SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }) {
            Text(text = "My Favorite Movies")
        }
    }){ padding ->
        val movieList: List<Movie> = getMovies()

        Column(modifier = Modifier.padding(padding)) {
            LazyColumn {
                items(viewModel.getFavorites()){ movie ->
                    MovieRow(
                        movie = movie,
                        viewModel = viewModel,
                        onItemClick = { movieId ->
                            navController.navigate(route = Screen.DetailScreen.withId(movieId))
                        }
                    )
                }
            }
        }
    }
}