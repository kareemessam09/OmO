package com.kotlinexample.moviesapp.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.kotlinexample.moviesapp.R
import com.kotlinexample.moviesapp.databinding.FragmentMovieBinding


class MovieFragment : Fragment() {

    lateinit var binding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)


        binding.back.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }


        val bottom = requireActivity().findViewById<View>(R.id.bottomNavigationView)
        bottom.visibility = View.GONE

        val movie = MovieFragmentArgs.fromBundle(requireArguments())

        val movieBackdropUrl = "https://image.tmdb.org/t/p/w342${movie.movie.backdropPath}"
        Glide.with(binding.movieBackdrop.context)
            .load(movieBackdropUrl)
            .into(binding.movieBackdrop)


        binding.movieTitle.text = movie.movie.title

        binding.movieOverview.text = movie.movie.overview

        val poster = "https://image.tmdb.org/t/p/w342${movie.movie.posterPath}"
        Glide.with(binding.moviePoster.context)
            .load(poster)
            .into(binding.moviePoster)

        binding.movieRating.rating = movie.movie.rating.toFloat()/2

        binding.idMovieReleaseDate.text = movie.movie.releaseDate






        return binding.root
    }

}