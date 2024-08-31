package com.kotlinexample.moviesapp.adapters

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kotlinexample.moviesapp.databinding.MovieHomeItemBinding
import com.kotlinexample.moviesapp.models.TrendMovies

class HomeMoviesAdapter (private val moviePosters: List<TrendMovies>, private val context: Context) : RecyclerView.Adapter<HomeMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMovieViewHolder {
        val v = MovieHomeItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return HomeMovieViewHolder(v)
    }

    override fun onBindViewHolder(holder: HomeMovieViewHolder, position: Int) {



        Glide.with(holder.itemView.context)
            .load(moviePosters[position].poster)
            .into(holder.moviePoster)

    }

    override fun getItemCount(): Int = moviePosters.size


}

class HomeMovieViewHolder(movieHome: MovieHomeItemBinding) : RecyclerView.ViewHolder(movieHome.root) {
    val moviePoster = movieHome.posterImage

}

