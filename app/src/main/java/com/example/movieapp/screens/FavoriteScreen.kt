package com.example.movieapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movieapp.models.FavoritesViewModel
import com.example.movieapp.models.Movie
import com.example.movieapp.models.MovieCollectionViewModel
import com.example.movieapp.utils.InjectorUtils
//import com.example.movieapp.models.getMovies
import com.example.movieapp.widgets.MovieRow
import com.example.movieapp.widgets.SimpleTopAppBar
import kotlinx.coroutines.launch

@Composable
fun FavoriteScreen(navController: NavController){
    val viewModel: FavoritesViewModel = viewModel(factory = InjectorUtils.provideMovieViewModelFactory(LocalContext.current))
    val movieListState by viewModel.movieListState.collectAsState()
    val coroutineScope = rememberCoroutineScope()


    Scaffold(topBar = {
        SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }) {
            Text(text = "My Favorite Movies")
        }
    }){ padding ->
    //    val movieList: List<Movie> = getMovies()

        Column(modifier = Modifier.padding(padding)) {
            LazyColumn {
                items(items = movieListState){ movie ->
                    MovieRow(
                        movie = movie,
                        onMovieRowClick = { movieId ->
                            navController.navigate(route = Screen.DetailScreen.withId(movieId))
                        } ,
                        toggleFavoriteMovie = { movie ->
                            coroutineScope.launch {
                                viewModel.updateFavoriteMovies(movie)
                            }
                        }
                    )
                }
            }
        }
    }
}