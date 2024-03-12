package com.example.assessment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.assessment.databinding.FragmentOnBoardingPrincipalBinding

class FragmentOnBoardingPrincipal : Fragment() {

    private lateinit var binding: FragmentOnBoardingPrincipalBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingPrincipalBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

}
