package com.kotlinexample.moviesapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kotlinexample.moviesapp.data.local.DbBuilder
import com.kotlinexample.moviesapp.databinding.MovieTrendItemBinding
import com.kotlinexample.moviesapp.fragments.HomeFragmentDirections
import com.kotlinexample.moviesapp.models.Movie
import com.kotlinexample.moviesapp.models.MoviesRoom
import com.kotlinexample.moviesapp.models.TrendMovies
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TrendedMoviesAdapter(private var moviePosters: List<Movie>, private val context: Context) : RecyclerView.Adapter<MovieViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val v = MovieTrendItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return MovieViewHolder(v)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {


        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w342${moviePosters[position].posterPath}")
            .into(holder.moviePoster)


        val room = DbBuilder.getInstance(context).movieDao()

//
//       GlobalScope.launch {
//            val movie = moviePosters[position].toMovieRoom()
//            room.insertMovie(movie)
//        }

        holder.itemView.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToMovieFragment(moviePosters[position])
            it.findNavController().navigate(action)
        }

        val layoutParams = holder.itemView.layoutParams as RecyclerView.LayoutParams
        layoutParams.marginEnd = 8
        layoutParams.marginStart = 8
        holder.itemView.layoutParams = layoutParams

    }

    override fun getItemCount(): Int = moviePosters.size




}

class MovieViewHolder(movieTrend: MovieTrendItemBinding) : RecyclerView.ViewHolder(movieTrend.root) {
    val moviePoster = movieTrend.posterImage

}

