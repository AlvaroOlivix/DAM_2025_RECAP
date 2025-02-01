package com.dam.dam_2025_recap.feature.movie.domain

interface MovieRepository {
    fun getMovies(): List<Movie>
    fun getMovie(id: String): Movie?
}