package com.kotlinexample.moviesapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.kotlinexample.moviesapp.models.Movie
import com.kotlinexample.moviesapp.models.MoviesRoom
import kotlinx.coroutines.flow.Flow


@Dao
interface MovieDAO {

//    @Insert
//    suspend fun insertMovie(movie: MoviesRoom):Long
//
//    @Query("SELECT * FROM movies")
//    suspend fun getMovies(): List<MoviesRoom>  // Flow-based query

}