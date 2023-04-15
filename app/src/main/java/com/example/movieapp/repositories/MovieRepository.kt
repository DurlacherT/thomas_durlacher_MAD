package com.example.movieapp.repositories

import com.example.movieapp.data.MovieDao
import com.example.movieapp.models.Movie

class MovieRepository (private val movieDao: MovieDao) {

    fun add(movie: Movie) = movieDao.add(movie)
    fun delete(movie: Movie) = movieDao.delete(movie)
    fun update(movie: Movie) = movieDao.update(movie)
    fun getAllMovies() = movieDao.readAll()
    fun getAllMoviesisFavorite() = movieDao.readAllisFavorite()



}