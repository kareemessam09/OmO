package com.kotlinexample.moviesapp.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.compose.ui.graphics.ShaderBrush
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import com.kotlinexample.moviesapp.R
import com.kotlinexample.moviesapp.data.local.SharedPrefrenc
import com.kotlinexample.moviesapp.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val navController = findNavController()

        if (SharedPrefrenc.getLoginState()) {
            navController.navigate(R.id.action_loginFragment_to_homeFragment)
        }

        bottomNavigationVisibility() // Hide bottom navigation view



        val signInLauncher = registerForActivityResult(
            FirebaseAuthUIActivityResultContract(),
        ) { res ->
            this.onSignInResult(res)
        }

        binding.googleIcon.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            val providers = arrayListOf(
                AuthUI.IdpConfig.GoogleBuilder().build(),
            )

            val signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build()

            signInLauncher.launch(signInIntent)

        }


        binding.signInB.setOnClickListener {
            SharedPrefrenc.setLoginState(true)
            SharedPrefrenc.setUserName(binding.emailET.text.toString())
            navController.navigate(R.id.action_loginFragment_to_homeFragment)
            val bottom = requireActivity().findViewById<ChipNavigationBar>(R.id.bottomNavigationView)
            bottom.setItemSelected(R.id.homeFragment)
        }
        binding.signuptrans.setOnClickListener{
            navController.navigate(R.id.action_loginFragment_to_signUpFragment)
        }



        return binding.root
    }



    // Hide bottom navigation view
    private fun bottomNavigationVisibility() {
        val bottomView = requireActivity().findViewById<View>(R.id.bottomNavigationView)
        bottomView.visibility = View.GONE
    }





    @SuppressLint("RestrictedApi")
    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        binding.progressBar.visibility = View.GONE
        val response = result.idpResponse
        val photoUri = response?.user?.photoUri.toString()
        val name = response?.user?.name.toString()

        if (result.resultCode == Activity.RESULT_OK) {
            val user = FirebaseAuth.getInstance().currentUser
            SharedPrefrenc.setUserName(name)



            SharedPrefrenc.setLoginState(true)
            val navController = findNavController()
            navController.navigate(R.id.action_loginFragment_to_homeFragment)
        } else {
            Toast.makeText(requireContext(), "Sign in failed", Toast.LENGTH_SHORT).show()
        }
    }



}