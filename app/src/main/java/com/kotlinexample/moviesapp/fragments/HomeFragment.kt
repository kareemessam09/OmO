package com.kotlinexample.moviesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearSnapHelper
import com.kotlinexample.moviesapp.R
import com.kotlinexample.moviesapp.adapters.CascadingItemDecoration
import com.kotlinexample.moviesapp.adapters.TrendedMoviesAdapter
import com.kotlinexample.moviesapp.databinding.FragmentHomeBinding
import com.kotlinexample.moviesapp.models.TrendMovies


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        bottomnavigation() // Bottom navigation visibility


        val ll = mutableListOf(
            TrendMovies("https://image.tmdb.org/t/p/w500/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg"),
            TrendMovies("https://image.tmdb.org/t/p/w500/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg"),
            TrendMovies("https://image.tmdb.org/t/p/w500/q719jXXEzOoYaps6babgKnONONX.jpg"),
            TrendMovies("https://image.tmdb.org/t/p/w500/2CAL2433ZeIihfX1Hb2139CX0pW.jpg"),
            TrendMovies("https://image.tmdb.org/t/p/w500/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg")
        )

        binding.trendedRecyclerView.adapter = TrendedMoviesAdapter(ll,requireContext()) // Adapter for Trended movies

        binding.trendedRecyclerView.layoutManager = CascadingItemDecoration(requireContext())

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.trendedRecyclerView)

        return binding.root
    }


    // Bottom navigation visibility
    private fun bottomnavigation() {
        val bottomView = requireActivity().findViewById<View>(R.id.bottomNavigationView)
        bottomView.visibility = View.VISIBLE
    }

}