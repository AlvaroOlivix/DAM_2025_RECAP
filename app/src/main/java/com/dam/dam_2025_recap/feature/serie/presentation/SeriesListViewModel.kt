package com.dam.dam_2025_recap.feature.serie.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dam.dam_2025_recap.feature.serie.domain.GetSeriesUseCase
import com.dam.dam_2025_recap.feature.serie.domain.Serie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SeriesListViewModel(private val getSeriesUseCase: GetSeriesUseCase) : ViewModel() {

    private val _uiState = MutableLiveData(UiState())
    val uiState: LiveData<UiState> = _uiState

    fun getSeries() {
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.postValue(UiState(series = getSeriesUseCase()))
        }
    }

    data class UiState(
        val series: List<Serie> = emptyList(),
        val isLoading: Boolean = false,
        val error: String? = null
    )
}
