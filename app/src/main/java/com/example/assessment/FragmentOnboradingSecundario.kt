package com.example.assessment

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.assessment.databinding.FragmentOnboradingSecundarioBinding

class FragmentOnboradingSecundario : Fragment() {
    private lateinit var binding: FragmentOnboradingSecundarioBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboradingSecundarioBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = "<font color=#4D5A67>Encontre todo o conteúdo daquele</font><br><font color=#3FB799> Filme ou Série </font> <br><font color=#4D5A67>que você adora agora mesmo.</font>"
        binding.titleSecOn.text = Html.fromHtml(title)
    }
}