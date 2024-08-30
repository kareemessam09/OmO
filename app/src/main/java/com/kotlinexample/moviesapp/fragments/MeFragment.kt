package com.kotlinexample.moviesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlinexample.moviesapp.R
import com.kotlinexample.moviesapp.databinding.FragmentMeBinding


class MeFragment : Fragment() {

    private lateinit var binding: FragmentMeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMeBinding.inflate(inflater, container, false)









        return binding.root
    }

}