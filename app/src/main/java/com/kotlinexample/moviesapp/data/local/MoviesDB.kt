package com.kotlinexample.moviesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kotlinexample.moviesapp.models.Movie
import com.kotlinexample.moviesapp.models.MoviesRoom

@Database(entities = [MoviesRoom::class], version = 1)
abstract class MoviesDB : RoomDatabase() {
    abstract fun movieDao(): MovieDAO
}