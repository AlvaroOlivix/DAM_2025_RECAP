package com.dam.dam_2025_recap.feature.movie.data.remote

import com.dam.dam_2025_recap.feature.movie.domain.Movie

class MockRemoteDataSource {

    private val movies = listOf(
        Movie(
            "1",
            "Movie 1",
            "Description 1",
            "https://example.com/movie1.jpg"
        ),
        Movie(
            "2",
            "Movie 2",
            "Description 2",
            "https://example.com/movie2.jpg"
        ),
        Movie(
            "3",
            "Movie 3",
            "Description 3",
            "https://example.com/movie3.jpg"
        )
    )


    fun getMovieList(): List<Movie> {
        return movies
    }

    fun getMovieById(id: String): Movie? {
        return movies.find { it.id == id }
    }
}