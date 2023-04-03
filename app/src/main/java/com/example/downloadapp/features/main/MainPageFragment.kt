package com.example.downloadapp.features.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.downloadapp.R
import com.example.downloadapp.databinding.FragmentMainPageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainPageFragment: Fragment() {
    private var _binding: FragmentMainPageBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<MainPageViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainPageBinding.inflate(inflater,container,false)


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.flashlights.setOnClickListener{
            findNavController().navigate(MainPageFragmentDirections.actionMainPageFragmentToApplicationFragment("flashlight"))
        }

        binding.coloredLights.setOnClickListener{
            findNavController().navigate(MainPageFragmentDirections.actionMainPageFragmentToApplicationFragment("coloredLight"))
        }

        binding.sosAlerts.setOnClickListener{
            findNavController().navigate(MainPageFragmentDirections.actionMainPageFragmentToApplicationFragment("sosAlert"))
        }


    }

}