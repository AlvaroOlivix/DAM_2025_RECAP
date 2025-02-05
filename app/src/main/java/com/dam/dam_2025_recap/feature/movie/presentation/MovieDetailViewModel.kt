package com.dam.dam_2025_recap.feature.movie.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dam.dam_2025_recap.feature.movie.domain.GetMovieUseCase
import com.dam.dam_2025_recap.feature.movie.domain.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MovieDetailViewModel(
    private val getMovieUseCase: GetMovieUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadMovie(movieId: String) {
        _uiState.value = UiState(loading = true)
        viewModelScope.launch(Dispatchers.IO) {
                val movie = getMovieUseCase(movieId)
                _uiState.postValue(UiState(movie = movie))
            }
    }
    data class UiState(
        val movie: Movie? = null,
        val error: String? = null,
        val loading: Boolean = false
    )
}