package com.dam.dam_2025_recap.feature.serie.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.dam.dam_2025_recap.databinding.FragmentSeriesListBinding
import com.dam.dam_2025_recap.feature.serie.presentation.SeriesListViewModel

class SeriesListFragment : Fragment() {

    private var _binding: FragmentSeriesListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SeriesListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //setUpView()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSeries()
        //setUpObservers()
    }

    private fun setUpObservers() {
        val observer = Observer<SeriesListViewModel.UiState>() { uiState ->
            uiState.series?.let {
                //bindData(it) seriesAdapter.submitList(it)
                Log.d("@dev", "$it")
            }
            uiState.error?.let {
                Log.d("@dev", "error")
            }
            if (uiState.isLoading) {
                //showProgressBar()
                Log.d("@dev", "loading")
            } else {
                //hideProgressBar()
                Log.d("@dev", "not loading")
            }

        }

    }

    private fun setUpView() {

    }


}