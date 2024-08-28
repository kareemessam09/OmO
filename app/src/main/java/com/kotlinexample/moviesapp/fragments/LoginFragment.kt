package com.kotlinexample.moviesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kotlinexample.moviesapp.R
import com.kotlinexample.moviesapp.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val navController = findNavController()

        bottomNavgationVisibility() // Hide bottom navigation view


        binding.login.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_homeFragment)
        }


        return binding.root
    }



    // Hide bottom navigation view
    private fun bottomNavgationVisibility() {
        val bottomView = requireActivity().findViewById<View>(R.id.bottomNavigationView)
        bottomView.visibility = View.GONE
    }






}