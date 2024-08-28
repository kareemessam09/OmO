package com.kotlinexample.moviesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.kotlinexample.moviesapp.databinding.ActivityMainBinding
import com.kotlinexample.moviesapp.databinding.FragmentLoginBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val fragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val navController = fragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)

        // Hide the bottom navigation bar when the user is on the login screen

    }


}