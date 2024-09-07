package com.kotlinexample.moviesapp.data.local

import android.content.Context
import androidx.room.Room

object DbBuilder{

    @Volatile
    private var INSTANCE: MoviesDB? = null

    fun getInstance(context: Context): MoviesDB {
        if (INSTANCE == null) {
            synchronized(MoviesDB::class) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    MoviesDB::class.java,
                    "movie_db"
                ).build()
            }
        }
        return INSTANCE!!
    }
}
