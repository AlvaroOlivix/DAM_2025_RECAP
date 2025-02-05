package com.dam.dam_2025_recap.feature.movie.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.dam.dam_2025_recap.core.extensions.loadUrl
import com.dam.dam_2025_recap.databinding.FragmentMovieDetailBinding
import com.dam.dam_2025_recap.feature.movie.domain.Movie
import com.dam.dam_2025_recap.feature.movie.presentation.MovieDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : Fragment() {

    private val viewModel: MovieDetailViewModel by viewModel()
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMovieId()?.let {
            viewModel.loadMovie(it)
        }
        setUpObserver()
    }

    private fun setUpObserver() {
        val movieDetailObserver = Observer<MovieDetailViewModel.UiState> {
            it.movie?.let { movie ->
                bindData(movie)
            }
            it.error?.let {
                //show error
            }
            if (it.loading) {
                //Loading
            } else {
                //Oculta loading
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, movieDetailObserver)
    }

    private fun bindData(movie: Movie) {
        binding.apply {
            text.text = movie.title
            text2.text = movie.description
            image.loadUrl(movie.imageUrl)
        }
    }
    private fun getMovieId(): String {
        return args.movieId
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}