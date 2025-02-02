package com.dam.dam_2025_recap.feature.movie.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.dam.dam_2025_recap.databinding.FragmentMovieListBinding
import com.dam.dam_2025_recap.feature.movie.domain.Movie
import com.dam.dam_2025_recap.feature.movie.presentation.MoviesViewModel
import com.dam.dam_2025_recap.feature.movie.presentation.adapter.MovieAdapter

class MoviesFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        //setUpView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
    }

    fun setUpObservers() {
        val observer = Observer<MoviesViewModel.UiState> { view ->

            if (view.error != null) {
                Log.d("@dev", "Error")
            }
            view.movies?.let {
                //bindData(it)
            }
            if (view.loading) {
                Log.d("@dev", "Loading")
            } else {
                Log.d("@dev", "Cargado")
            }
        }
    }

    fun bindData(movies: List<Movie>) {
        //Si es un recycler se llama a adapter.

    }
    fun setUpView() {
// En tu Activity/Fragment:
        val adapter = MovieAdapter()
       /* adapter. { movieId ->
            // Acción al clickear un elemento (ej: navegar a otro fragment)
            Log.d("@dev", "ID clickeado: $movieId")
            findNavController().navigate(R.id.action_to_detail)
        }

        */

        //recyclerView.adapter = adapter

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}