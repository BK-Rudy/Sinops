package com.example.assessment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assessment.databinding.FragmentHomeBinding
import com.example.assessment.services.MovieService
import com.example.assessment.viewmodel.UserViewModel
import com.google.android.material.chip.Chip

class FragmentHome : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
    private val model: UserViewModel by activityViewModels()
    private lateinit var movieService: MovieService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        movieService = MovieService()
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        model.nome.observe(viewLifecycleOwner, { nome ->
            binding.txtUsuario.text = nome
        })

        binding.chipGroup.setOnCheckedChangeListener { chipGroup, i ->

            var chip = chipGroup.findViewById<Chip>(i)

            if(chip != null) {
                var movies = movieService.getMoviesByGenre(chip.text.toString())
                adapter = RecyclerAdapter(movies)
                binding.recyclerView.adapter = adapter
            } else {
                var movies = movieService.getAllMovies()
                adapter = RecyclerAdapter(movies)
                binding.recyclerView.adapter = adapter
            }


        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val mutableList = mutableListOf<Movie>()

        var movieService = MovieService()
        val mutableList = movieService.getAllMovies()

        layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.layoutManager = layoutManager
        adapter = RecyclerAdapter(mutableList)

        binding.recyclerView.adapter = adapter
    }

}