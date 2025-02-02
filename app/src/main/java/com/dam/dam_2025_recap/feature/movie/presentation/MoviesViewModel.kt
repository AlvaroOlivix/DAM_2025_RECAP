package com.dam.dam_2025_recap.feature.movie.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dam.dam_2025_recap.feature.movie.domain.GetMovieListUseCase
import com.dam.dam_2025_recap.feature.movie.domain.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MoviesViewModel(private val getMovieListUseCase: GetMovieListUseCase) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadMovies() {
        _uiState.value = UiState(loading = true)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val movies = getMovieListUseCase()
                _uiState.postValue(UiState(movies = movies))
            } catch (e: Exception) {
                _uiState.postValue(UiState(error = e.message))
            }
        }
    }
    data class UiState(
        val movies: List<Movie> = emptyList(),
        val error: String? = null,
        val loading: Boolean = false
    )
}
