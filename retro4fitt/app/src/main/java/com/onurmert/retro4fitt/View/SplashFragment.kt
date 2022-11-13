package com.onurmert.retro4fitt.View

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.onurmert.retro4fitt.Database.FilmsDatabaseHelper
import com.onurmert.retro4fitt.Retrofit1.ApiClient
import com.onurmert.retro4fitt.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ApiClient().getMovie(requireContext())

        val action = SplashFragmentDirections.actionBlankFragmentToBlankFragment2()
        Handler().postDelayed({
                Navigation.findNavController(view).navigate(action)
            },1000
        )
    }
}