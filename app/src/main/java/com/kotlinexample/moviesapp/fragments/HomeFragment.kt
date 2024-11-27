package com.kotlinexample.moviesapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearSnapHelper
import com.kotlinexample.moviesapp.R
import com.kotlinexample.moviesapp.adapters.CascadingItemDecoration
import com.kotlinexample.moviesapp.adapters.HomeMoviesAdapter
import com.kotlinexample.moviesapp.adapters.TrendedMoviesAdapter
import com.kotlinexample.moviesapp.data.local.DbBuilder
import com.kotlinexample.moviesapp.data.repository.MoviesRepository
import com.kotlinexample.moviesapp.databinding.FragmentHomeBinding
import com.kotlinexample.moviesapp.models.Movie
import com.kotlinexample.moviesapp.models.MoviesRoom
import com.kotlinexample.moviesapp.models.TrendMovies
import com.kotlinexample.moviesapp.viewmodels.MovieViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import retrofit2.Response
import kotlin.math.log


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: MovieViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        bottomnavigation() // Bottom navigation visibility
        binding.highRatedRecyclerView.adapter = TrendedMoviesAdapter(emptyList(), requireContext())

        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]

        binding.progressBar.visibility = View.VISIBLE




        lifecycleScope.launch {
            fillHomeRecycler()
           fillTrendRecycler()
           fillHighRatedRecycler()
           fillComingSoonRecycler()
       }





        binding.seeAll.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSeeAllFragment("Trended")
            requireView().findNavController().navigate(action)
        }
        binding.seeAll2.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSeeAllFragment("HighRated")
            requireView().findNavController().navigate(action)
        }

        binding.seeAll3.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSeeAllFragment("ComingSoon")
            requireView().findNavController().navigate(action)
        }

        return binding.root
    }







    private suspend fun fillHighRatedRecycler() {

        // Set up RecyclerView and observe LiveData
        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            if (movies != null) {
                binding.highRatedRecyclerView.adapter = TrendedMoviesAdapter(movies, requireContext())

                if (binding.highRatedRecyclerView.adapter == null) {
                    binding.highRatedRecyclerView.adapter = TrendedMoviesAdapter(movies, requireContext())
                }
            }
        }

        if (viewModel.movies.value.isNullOrEmpty()) {
            viewModel.getMovies()
        }

        // Set up RecyclerView
        binding.highRatedRecyclerView.layoutManager = CascadingItemDecoration(requireContext())
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.highRatedRecyclerView)
    }




    private suspend fun fillHomeRecycler() {


        MoviesRepository.getNowPlayingMovies(3, onSuccess = {
            binding.progressBar.visibility = View.GONE
            binding.homerecycler.adapter = HomeMoviesAdapter(it,requireContext()) // Adapter for Trended movies
        }, onError = {
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
        })

        binding.homerecycler.layoutManager = CascadingItemDecoration(requireContext())

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.trendedRecyclerView)


    }



    private suspend fun fillTrendRecycler() {


        MoviesRepository.getPopularMovies(1, onSuccess = {
               binding.trendedRecyclerView.adapter = TrendedMoviesAdapter(it,requireContext()) // Adapter for Trended movies
           }, onError = {
               Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
           })

        binding.trendedRecyclerView.layoutManager = CascadingItemDecoration(requireContext())

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.homerecycler)
    }


    private suspend fun fillComingSoonRecycler() {

        lifecycleScope.launch {
            MoviesRepository.getUpcomingMovies(2, onSuccess = {
                binding.comingRecyclerView.adapter =
                    TrendedMoviesAdapter(it, requireContext()) // Adapter for Trended movies
            }, onError = {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            })
        }
            binding.comingRecyclerView.layoutManager = CascadingItemDecoration(requireContext())

            val snapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(binding.comingRecyclerView)
        }




    // Bottom navigation visibility
    private fun bottomnavigation() {
        val bottomView = requireActivity().findViewById<View>(R.id.bottomNavigationView)
        bottomView.visibility = View.VISIBLE
    }

}