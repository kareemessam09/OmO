package com.kotlinexample.moviesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearSnapHelper
import com.kotlinexample.moviesapp.R
import com.kotlinexample.moviesapp.adapters.CascadingItemDecoration
import com.kotlinexample.moviesapp.adapters.HomeMoviesAdapter
import com.kotlinexample.moviesapp.adapters.TrendedMoviesAdapter
import com.kotlinexample.moviesapp.adapters.TvAdapter
import com.kotlinexample.moviesapp.data.remote.GetTvResponse
import com.kotlinexample.moviesapp.data.repository.MoviesRepository
import com.kotlinexample.moviesapp.data.repository.TvRepository
import com.kotlinexample.moviesapp.databinding.FragmentTvBinding


class TvFragment : Fragment() {

    private lateinit var binding: FragmentTvBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvBinding.inflate(inflater, container, false)



        fillHighRatedRecycler()

        return binding.root
    }





    private fun fillHighRatedRecycler() {

        val topRated = TvRepository.getOnTheAirTvShows(1, onSuccess = {
            binding.highRatedRecyclerView.adapter = TvAdapter(it,requireContext()) // Adapter for Trended movies
        }, onError = {
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
        })

        binding.highRatedRecyclerView.layoutManager = CascadingItemDecoration(requireContext())

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.highRatedRecyclerView)
    }



}
