package com.kotlinexample.moviesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import com.kotlinexample.moviesapp.R
import com.kotlinexample.moviesapp.data.local.SharedPrefrenc
import com.kotlinexample.moviesapp.databinding.FragmentMeBinding


class MeFragment : Fragment() {

    private lateinit var binding: FragmentMeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMeBinding.inflate(inflater, container, false)

        if (SharedPrefrenc.getUserName() != "null") {
            binding.profileName.text = SharedPrefrenc.getUserName()
        }else
            binding.profileName.text = "User"


        binding.logoutButton.setOnClickListener {



            SharedPrefrenc.setLoginState(false)
            SharedPrefrenc.setUserName("null")
            val navController = findNavController()

            navController.navigate(R.id.action_meFragment_to_loginFragment)
        }







        return binding.root
    }

}