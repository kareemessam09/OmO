package com.kotlinexample.moviesapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kotlinexample.moviesapp.databinding.MovieVerticalItemBinding
import com.kotlinexample.moviesapp.fragments.SeeAllFragment
import com.kotlinexample.moviesapp.fragments.SeeAllFragmentDirections
import com.kotlinexample.moviesapp.models.Movie


class SeeAllAdapter (val movies: List<Movie>, val context: Context) : RecyclerView.Adapter<SeeAllViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeeAllViewHolder {
        val v = MovieVerticalItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return SeeAllViewHolder(v)
    }

    override fun onBindViewHolder(holder: SeeAllViewHolder, position: Int) {

        holder.movieTitle.text = movies[position].title
        holder.movieRating.rating = movies[position].rating.toFloat() / 2
        holder.movieRelease.text = movies[position].releaseDate

        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w342${movies[position].posterPath}")
            .into(holder.moviePoster)


        holder.itemView.setOnClickListener {
            val action = SeeAllFragmentDirections.actionSeeAllFragmentToMovieFragment(movies[position])
            it.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int = movies.size
}

class SeeAllViewHolder(movieSeeAll: MovieVerticalItemBinding) : RecyclerView.ViewHolder(movieSeeAll.root) {
    val moviePoster = movieSeeAll.posterImage
    val movieTitle = movieSeeAll.title
    val movieRating = movieSeeAll.movieRating
    val movieRelease = movieSeeAll.release

}