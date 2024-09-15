package com.kotlinexample.moviesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.kotlinexample.moviesapp.R
import com.kotlinexample.moviesapp.adapters.CascadingItemDecoration
import com.kotlinexample.moviesapp.adapters.HomeMoviesAdapter
import com.kotlinexample.moviesapp.adapters.HomeTvAdapter
import com.kotlinexample.moviesapp.adapters.TrendedMoviesAdapter
import com.kotlinexample.moviesapp.adapters.TvAdapter
import com.kotlinexample.moviesapp.data.remote.GetTvResponse
import com.kotlinexample.moviesapp.data.repository.MoviesRepository
import com.kotlinexample.moviesapp.data.repository.TvRepository
import com.kotlinexample.moviesapp.databinding.FragmentTvBinding
import kotlinx.coroutines.launch


class TvFragment : Fragment() {

    private lateinit var binding: FragmentTvBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvBinding.inflate(inflater, container, false)



        bottomnavigation() // Bottom navigation visibility

        binding.progressBar.visibility = View.VISIBLE

        lifecycleScope.launch {
            fillHomeRecycler()
            fillHighRatedRecycler()
            fillPopularRecycler()
            fillOnAirRecycler()
        }

        binding.seeAll.setOnClickListener {
            val action = TvFragmentDirections.actionTvToSeeAll("Popular Now")
            requireView().findNavController().navigate(action)
        }

        binding.seeAll2.setOnClickListener {
            val action = TvFragmentDirections.actionTvToSeeAll("High Rated")
            requireView().findNavController().navigate(action)
        }

        binding.seeAll3.setOnClickListener {
            val action = TvFragmentDirections.actionTvToSeeAll("On Air")
            requireView().findNavController().navigate(action)
        }




        return binding.root
    }

    private fun fillHomeRecycler() {
        val home = TvRepository.getAiringTodayTvShows(3, onSuccess = {
            binding.progressBar.visibility = View.GONE
            binding.homerecycler.adapter = HomeTvAdapter(it,requireContext()) // Adapter for Trended movies
        }, onError = {
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
        })

        binding.homerecycler.layoutManager = CascadingItemDecoration(requireContext())

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.homerecycler)
    }


    private fun fillHighRatedRecycler() {

        val topRated = TvRepository.getTopRatedTvShows(1, onSuccess = {
            binding.progressBar.visibility = View.GONE

            binding.highRatedRecyclerView.adapter = TvAdapter(it,requireContext()) // Adapter for Trended movies
        }, onError = {
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
        })

        binding.highRatedRecyclerView.layoutManager = CascadingItemDecoration(requireContext())

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.highRatedRecyclerView)
    }


    private fun fillPopularRecycler() {

        val popular = TvRepository.getPopularTvShows(3, onSuccess = {
            binding.progressBar.visibility = View.GONE

            binding.popularRecyclerView.adapter = TvAdapter(it,requireContext()) // Adapter for Trended movies
        }, onError = {
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
        })

        binding.popularRecyclerView.layoutManager = CascadingItemDecoration(requireContext())

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.popularRecyclerView)
    }


    private fun fillOnAirRecycler() {

        val onAir = TvRepository.getOnTheAirTvShows(1, onSuccess = {
            binding.progressBar.visibility = View.GONE

            binding.OnAirRecyclerView.adapter = TvAdapter(it,requireContext()) // Adapter for Trended movies
        }, onError = {
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
        })

       binding.OnAirRecyclerView.layoutManager = CascadingItemDecoration(requireContext())

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.OnAirRecyclerView)
    }



    // Bottom navigation visibility
    private fun bottomnavigation() {
        val bottomView = requireActivity().findViewById<View>(R.id.bottomNavigationView)
        bottomView.visibility = View.VISIBLE
    }



}
