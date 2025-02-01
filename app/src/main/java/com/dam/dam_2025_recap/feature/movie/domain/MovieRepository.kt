package com.dam.dam_2025_recap.feature.movie.domain

interface MovieRepository {
    suspend fun getMovies(): List<Movie>
    suspend fun getMovie(id: String): Movie?
}