package com.kotlinexample.moviesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.kotlinexample.moviesapp.R
import com.kotlinexample.moviesapp.adapters.CascadingItemDecoration
import com.kotlinexample.moviesapp.adapters.HomeTvAdapter
import com.kotlinexample.moviesapp.adapters.SeeAllAdapter
import com.kotlinexample.moviesapp.adapters.SeeAllShowsAdapter
import com.kotlinexample.moviesapp.adapters.TvAdapter
import com.kotlinexample.moviesapp.data.repository.MoviesRepository
import com.kotlinexample.moviesapp.data.repository.TvRepository
import com.kotlinexample.moviesapp.databinding.FragmentSeeAllShowsBinding


class SeeAllShowsFragment : Fragment() {
    lateinit var binding: FragmentSeeAllShowsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSeeAllShowsBinding.inflate(inflater,container,false)

        binding.back.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed() // Back button
        }

        val head = arguments?.getString("movieType")
        binding.toolbarTitle.text = head

        binding.progressBar.visibility = View.VISIBLE

        when(head){
            "Popular Now" -> {
                fillTrendRecycler()
            }
            "High Rated" -> {
                fillHighRatedRecycler()
            }
            "On Air" -> {
                fillComingSoonRecycler()
            }
        }

        bottomNavigationVisibility()


        return binding.root
    }



    private fun fillTrendRecycler() {

        val popular = TvRepository.getPopularTvShows(3, onSuccess = {
            binding.progressBar.visibility = View.GONE
            binding.SeeAllRecyclerView.adapter = SeeAllShowsAdapter(it,requireContext()) // Adapter for Trended movies
            binding.SeeAllRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }, onError = {
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
        })

    }

    private fun fillHighRatedRecycler() {

        val topRated = TvRepository.getTopRatedTvShows(1, onSuccess = {
            binding.progressBar.visibility = View.GONE

            binding.SeeAllRecyclerView.adapter = SeeAllShowsAdapter(it,requireContext())
            binding.SeeAllRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }, onError = {
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
        })

    }

    private fun fillComingSoonRecycler() {

        val onAir = TvRepository.getOnTheAirTvShows(1, onSuccess = {
            binding.progressBar.visibility = View.GONE

            binding.SeeAllRecyclerView.adapter = SeeAllShowsAdapter(it,requireContext())
            binding.SeeAllRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }, onError = {
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
        })
    }

    private fun bottomNavigationVisibility() {
        val bottomView = requireActivity().findViewById<View>(R.id.bottomNavigationView)
        bottomView.visibility = View.GONE
    }

}