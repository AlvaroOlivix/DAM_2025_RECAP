package com.dam.dam_2025_recap.feature.movie.domain

import org.koin.core.annotation.Single

@Single
class GetMovieListUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(): List<Movie> {
        return movieRepository.getMovies()
    }
}