package com.example.assessment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.assessment.databinding.FragmentFormBinding
import com.example.assessment.viewmodel.UserViewModel
import androidx.fragment.app.activityViewModels

class FragmentForm : Fragment() {

    private var _binding: FragmentFormBinding? = null
    private val binding get() = _binding!!
    private val model: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =  FragmentFormBinding.inflate(layoutInflater)
        val view: View = binding.root
        // Inflate the layout for this fragment
        nextPage(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = model
            fragmentForm = this@FragmentForm
        }
    }

    private fun nextPage(view: View) {

        binding.edInputNome.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(char: CharSequence?, p1: Int, p2: Int, p3: Int) {
                model.setNome(char.toString())
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
    }
}