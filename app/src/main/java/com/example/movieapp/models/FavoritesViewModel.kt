package com.example.movieapp.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.repositories.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel (private val repository: MovieRepository): ViewModel(){
    private val _movieListState = MutableStateFlow(listOf<Movie>())
    val movieListState: StateFlow<List<Movie>> = _movieListState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllMoviesisFavorite()
                .collect{listOfMovies ->
                        _movieListState.value = listOfMovies
                }
        }
    }

    suspend fun updateFavoriteMovies(movie: Movie) {
        movie.isFavorite = ! movie.isFavorite
        repository.update(movie)
    }

}
