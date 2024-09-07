package com.kotlinexample.moviesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        bottomnavigation() // Bottom navigation visibility


        fillTrendRecycler() //fill trend recycler view

        fillHomeRecycler() //fill home recycler view

        fillHighRatedRecycler()//fill high rated recycler view

        fillComingSoonRecycler() //fill coming soon recycler view

        binding.seeAll.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSeeAllFragment("trended")
            requireView().findNavController().navigate(action)
        }
        binding.seeAll2.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSeeAllFragment("highRated")
            requireView().findNavController().navigate(action)
        }

        binding.seeAll3.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSeeAllFragment("comingSoon")
            requireView().findNavController().navigate(action)
        }

        return binding.root
    }




    private fun fillHighRatedRecycler() {

    val topRated = MoviesRepository.getTopRatedMovies(1, onSuccess = {
            binding.highRatedRecyclerView.adapter = TrendedMoviesAdapter(it,requireContext()) // Adapter for Trended movies
        }, onError = {
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
        })

        binding.highRatedRecyclerView.layoutManager = CascadingItemDecoration(requireContext())

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.highRatedRecyclerView)
    }





    private fun fillHomeRecycler() {

        val home = MoviesRepository.getNowPlayingMovies(1, onSuccess = {
            binding.homerecycler.adapter = HomeMoviesAdapter(it,requireContext()) // Adapter for Trended movies
        }, onError = {
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
        })

        binding.homerecycler.layoutManager = CascadingItemDecoration(requireContext())

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.trendedRecyclerView)


    }



    private fun fillTrendRecycler() {

        val popular = MoviesRepository.getPopularMovies(1, onSuccess = {
            binding.trendedRecyclerView.adapter = TrendedMoviesAdapter(it,requireContext()) // Adapter for Trended movies
        }, onError = {
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
        })

        binding.trendedRecyclerView.layoutManager = CascadingItemDecoration(requireContext())

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.homerecycler)
    }


    private fun fillComingSoonRecycler() {

        val room = DbBuilder.getInstance(requireContext()).movieDao()


//        GlobalScope.launch {
//            val moviesList = room.getMovies().map { it.toMovie() }
//            binding.comingRecyclerView.adapter =
//                TrendedMoviesAdapter(moviesList, requireContext()) // Adapter for Trended movies
                val comingSoon = MoviesRepository.getUpcomingMovies(1, onSuccess = {
                    binding.comingRecyclerView.adapter = TrendedMoviesAdapter(it,requireContext()) // Adapter for Trended movies
                }, onError = {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                })

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