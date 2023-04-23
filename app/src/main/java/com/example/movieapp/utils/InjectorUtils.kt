package com.example.movieapp.utils


import android.content.Context
import com.example.movieapp.MovieCollectionViewModelFactory
import com.example.movieapp.data.MovieDatabase
import com.example.movieapp.repositories.MovieRepository

object InjectorUtils {
    private fun getTaskRepository(context: Context): MovieRepository{
        return MovieRepository(MovieDatabase.getDatabase(context).movieDao())
    }

    fun provideMovieViewModelFactory(context: Context): MovieCollectionViewModelFactory {
        val repository = getTaskRepository(context)
        return MovieCollectionViewModelFactory(repository)
    }
}