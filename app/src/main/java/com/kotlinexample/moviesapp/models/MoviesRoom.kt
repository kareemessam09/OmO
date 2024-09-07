package com.kotlinexample.moviesapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class MoviesRoom(
    @PrimaryKey val id: Long,  // Room will generate a new id for each movie
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "poster_path") val posterPath: String,  // Use snake_case if necessary
    @ColumnInfo(name = "backdrop_path") val backdropPath: String,
    @ColumnInfo(name = "vote_average") val rating: Float,
    @ColumnInfo(name = "release_date") val releaseDate: String
)