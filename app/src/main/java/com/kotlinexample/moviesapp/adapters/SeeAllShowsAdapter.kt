package com.kotlinexample.moviesapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kotlinexample.moviesapp.databinding.MovieVerticalItemBinding
import com.kotlinexample.moviesapp.fragments.SeeAllFragmentDirections
import com.kotlinexample.moviesapp.fragments.SeeAllShowsFragment
import com.kotlinexample.moviesapp.fragments.SeeAllShowsFragmentDirections
import com.kotlinexample.moviesapp.models.Movie
import com.kotlinexample.moviesapp.models.Tv

class SeeAllShowsAdapter(val tvs: List<Tv>, val context: Context) : RecyclerView.Adapter<SeeAllShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeeAllShowViewHolder {
        val v = MovieVerticalItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return SeeAllShowViewHolder(v)
    }

    override fun onBindViewHolder(holder: SeeAllShowViewHolder, position: Int) {

        holder.movieTitle.text = tvs[position].name
        holder.movieRating.rating = tvs[position].voteAverage.toFloat() / 2
        holder.movieRelease.text = tvs[position].firstAirDate

        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w342${tvs[position].posterPath}")
            .into(holder.moviePoster)


        holder.itemView.setOnClickListener {
            val action =  SeeAllShowsFragmentDirections.actionSeeAllShowsFragmentToTvvFragment(tvs[position])
            it.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int = tvs.size
}

class SeeAllShowViewHolder(movieSeeAll: MovieVerticalItemBinding) : RecyclerView.ViewHolder(movieSeeAll.root) {
    val moviePoster = movieSeeAll.posterImage
    val movieTitle = movieSeeAll.title
    val movieRating = movieSeeAll.movieRating
    val movieRelease = movieSeeAll.release

}
