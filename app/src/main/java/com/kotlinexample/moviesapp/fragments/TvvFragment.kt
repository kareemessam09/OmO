package com.kotlinexample.moviesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.kotlinexample.moviesapp.R
import com.kotlinexample.moviesapp.databinding.FragmentTvvBinding


class tvvFragment : Fragment() {
    lateinit var binding: FragmentTvvBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvvBinding.inflate(inflater, container, false)


        binding.back.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }


        val bottom = requireActivity().findViewById<View>(R.id.bottomNavigationView)
        bottom.visibility = View.GONE

        val tv = tvvFragmentArgs.fromBundle(requireArguments())


        val movieBackdropUrl = "https://image.tmdb.org/t/p/w342${tv.movie.backdropPath}"
        Glide.with(binding.movieBackdrop.context)
            .load(movieBackdropUrl)
            .into(binding.movieBackdrop)


        binding.movieTitle.text = tv.movie.name

        binding.movieOverview.text = tv.movie.overview

        val poster = "https://image.tmdb.org/t/p/w342${tv.movie.posterPath}"
        Glide.with(binding.moviePoster.context)
            .load(poster)
            .into(binding.moviePoster)

        binding.movieRating.rating = tv.movie.voteAverage.toFloat()/2

        binding.idMovieReleaseDate.text = tv.movie.firstAirDate






        return binding.root
    }
}