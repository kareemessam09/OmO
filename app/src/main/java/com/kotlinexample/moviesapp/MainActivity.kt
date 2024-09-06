package com.kotlinexample.moviesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import com.kotlinexample.moviesapp.data.local.SharedPrefrenc
import com.kotlinexample.moviesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Thread.sleep(2000)
        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initViews()


    }

    private fun initViews() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val navController = fragment.navController
        binding.bottomNavigationView.setItemSelected(R.id.homeFragment)
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item) {
                R.id.homeFragment -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }

                R.id.categoryFragment -> {
                    navController.navigate(R.id.categoryFragment)
                    true
                }

                R.id.meFragment -> {
                    navController.navigate(R.id.meFragment)
                    true
                }

                else -> false

            }




            SharedPrefrenc.initalize(applicationContext)
        }


    }
}