package com.dam.dam_2025_recap.feature.movie.domain

import org.koin.core.annotation.Single


@Single
class GetMovieUseCase(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(id: String): Movie? {
        return movieRepository.getMovie(id)
    }
}