package com.kotlinexample.moviesapp.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kotlinexample.moviesapp.adapters.TrendedMoviesAdapter
import com.kotlinexample.moviesapp.data.repository.MoviesRepository
import com.kotlinexample.moviesapp.models.Movie

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies


    private var isDataLoaded = false

    fun getMovies() {
        if (isDataLoaded) {
            return // Do not fetch movies if data has already been loaded
        }

        // Fetch movies from repository
        MoviesRepository.getTopRatedMovies(1, onSuccess = {
            _movies.value = it
            isDataLoaded = true // Mark data as loaded
        }, onError = {
            Toast.makeText(getApplication(), "Error", Toast.LENGTH_SHORT).show()
        })
    }


}