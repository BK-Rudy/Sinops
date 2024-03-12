package com.example.assessment

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.assessment.databinding.FragmentOnBoardingBinding

class FragmentOnBoarding : Fragment() {
    private lateinit var binding: FragmentOnBoardingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = "<font color=#4D5A67>Encontre</font> <font color=#3FB799>Seu Filme <br> Favorito</font> <font color=#4D5A67> Aqui"
        binding.titleVp.text = Html.fromHtml(title)
    }


}