package com.example.movieapp.data

import androidx.room.*
import com.example.movieapp.models.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert
    fun add(movie: Movie)

    @Update
    fun update(movie: Movie)

    @Delete
    fun delete(movie: Movie)

    @Query  ("SELECT * FROM MOVIE")
    fun readAll(): Flow<List<Movie>>

    @Query  ("SELECT * FROM MOVIE where isFavorite = 1")
    fun readAllisFavorite():  Flow<List<Movie>>
}