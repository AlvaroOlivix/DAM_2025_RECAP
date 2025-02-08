package com.dam.dam_2025_recap.feature.serie.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dam.dam_2025_recap.feature.serie.domain.GetSerieByIdUseCase
import com.dam.dam_2025_recap.feature.serie.domain.Serie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SerieDetailViewModel(private val getSerieByIdUseCase: GetSerieByIdUseCase):ViewModel() {

    private val _uiState = MutableLiveData(UiState())
    val uiState: LiveData<UiState> get() = _uiState


    fun getSerieById(id: String) {
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.postValue(UiState(serie = getSerieByIdUseCase(id)))
        }
    }
    data class UiState(
        val serie: Serie? = null,
        val isLoading: Boolean = false,
        val error: String? = null
    )
}