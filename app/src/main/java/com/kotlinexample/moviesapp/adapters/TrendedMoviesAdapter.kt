package com.kotlinexample.moviesapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kotlinexample.moviesapp.R
import com.kotlinexample.moviesapp.databinding.MovieTrendItemBinding
import com.kotlinexample.moviesapp.models.TrendMovies

class TrendedMoviesAdapter(private val moviePosters: List<TrendMovies>,private val context: Context) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val v = MovieTrendItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return MovieViewHolder(v)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {


        Glide.with(holder.itemView.context)
            .load(moviePosters[position].poster)
            .into(holder.moviePoster)

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
