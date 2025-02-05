package com.dam.dam_2025_recap.feature.movie.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dam.dam_2025_recap.databinding.FragmentMovieListBinding
import com.dam.dam_2025_recap.feature.movie.domain.Movie
import com.dam.dam_2025_recap.feature.movie.presentation.MoviesViewModel
import com.dam.dam_2025_recap.feature.movie.presentation.adapter.MovieAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MoviesViewModel by viewModel()
    private val movieAdapter = MovieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        setUpView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        viewModel.loadMovies()
    }

    private fun setUpObservers() {
        val observer = Observer<MoviesViewModel.UiState> { view ->

            if (view.error != null) {
                Log.d("@dev", "Error")
            }
            view.movies?.let {
                bindData(it)
            }
            if (view.loading) {
                Log.d("@dev", "Loading")
            } else {
                Log.d("@dev", "Cargado")
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    private fun setUpView() {
        //Se crea despues de definir el adapter y sus clases
        binding.apply {
            listRecycler.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL, false
            )
            movieAdapter.setOnClick {
                navigateToDetail(it)
            }
            listRecycler.adapter = movieAdapter
        }
    }

    private fun bindData(movies: List<Movie>) {
        //Si es un recycler se llama a adapter y si es un ListAdapter se usa submitList.
        movieAdapter.submitList(movies)
    }

    private fun navigateToDetail(movieId: String) {
        findNavController().navigate(MoviesFragmentDirections.actionMovieListToMovieDetail(movieId))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}





