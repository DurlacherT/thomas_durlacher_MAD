package com.example.movieapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieapp.models.Movie

@Database(
    entities = [Movie::class],
    version = 1
)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object{
        @Volatile
        private var Instance: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase {
            return Instance?: synchronized(this){
                Room.databaseBuilder(context, MovieDatabase::class.java, "task_db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        Instance = it
                    }
            }
        }
    }
}