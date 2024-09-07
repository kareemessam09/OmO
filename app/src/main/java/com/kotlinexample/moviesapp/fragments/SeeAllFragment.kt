package com.kotlinexample.moviesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlinexample.moviesapp.R
import com.kotlinexample.moviesapp.adapters.SeeAllAdapter
import com.kotlinexample.moviesapp.data.repository.MoviesRepository
import com.kotlinexample.moviesapp.databinding.FragmentSeeAllTrendedBinding

class SeeAllFragment : Fragment() {

    private lateinit var binding: FragmentSeeAllTrendedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSeeAllTrendedBinding.inflate(inflater, container, false)

        binding.back.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed() // Back button
        }

        val head = arguments?.getString("movieType")
        binding.toolbarTitle.text = head

        when(head){
            "trended" -> {
                fillTrendRecycler()
            }
            "highRated" -> {
               fillHighRatedRecycler()
            }
            "comingSoon" -> {
                fillComingSoonRecycler()
            }
        }

        bottomNavigationVisibility() // Hide bottom navigation view

        return binding.root
    }


    private fun bottomNavigationVisibility() {
        val bottomView = requireActivity().findViewById<View>(R.id.bottomNavigationView)
        bottomView.visibility = View.GONE
    }

    private fun fillTrendRecycler() {

        val trended = MoviesRepository.getPopularMovies(1, onSuccess = {
            binding.SeeAllRecyclerView.adapter = SeeAllAdapter(it,requireContext()) // Adapter for Trended movies
            binding.SeeAllRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }, onError = {
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
        })

    }

    private fun fillHighRatedRecycler() {

        val trended = MoviesRepository.getTopRatedMovies(1, onSuccess = {
            binding.SeeAllRecyclerView.adapter = SeeAllAdapter(it,requireContext()) // Adapter for Trended movies
            binding.SeeAllRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }, onError = {
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
        })

    }

    private fun fillComingSoonRecycler() {

        val trended = MoviesRepository.getUpcomingMovies(1, onSuccess = {
            binding.SeeAllRecyclerView.adapter = SeeAllAdapter(it,requireContext()) // Adapter for Trended movies
            binding.SeeAllRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }, onError = {
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
        })

    }

}