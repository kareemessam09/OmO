package com.kotlinexample.moviesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kotlinexample.moviesapp.R
import com.kotlinexample.moviesapp.databinding.FragmentStartBinding


class StartFragment : Fragment() {

    lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentStartBinding.inflate(inflater, container, false)
        val navController = findNavController()


        binding.getStartetBtn.setOnClickListener {
            navController.navigate(R.id.action_startFragment_to_homeFragment)
        }


        bottomNavigationVisibility() // Hide bottom navigation view


        return binding.root
    }


    // Hide bottom navigation view
    private fun bottomNavigationVisibility() {
        val bottomView = requireActivity().findViewById<View>(R.id.bottomNavigationView)
        bottomView.visibility = View.GONE
    }


}