package com.dam.dam_2025_recap.feature.movie.data.remote

import com.dam.dam_2025_recap.feature.movie.domain.Movie
import org.koin.core.annotation.Single

@Single
class MockRemoteDataSource {

    private val movies = listOf(
        Movie(
            "1",
            "Movie 1",
            "Description 1",
            "https://th.bing.com/th/id/OIP.iT2uYV3AZe3smX-w7QlDfwHaLH?w=135&h=202&c=7&r=0&o=5&pid=1.7"
        ),
        Movie(
            "2",
            "Movie 2",
            "Description 2",
            "https://th.bing.com/th/id/OIP.QHhITJ4EKej-rqyXtzGo7AHaLQ?w=202&h=308&c=7&r=0&o=5&pid=1.7"
        ),
        Movie(
            "3",
            "Movie 3",
            "Description 3",
            "https://th.bing.com/th/id/OIP.F_LGro-Fc91r40PAd0Q5hQHaKs?w=204&h=296&c=7&r=0&o=5&pid=1.7"
        ),
        Movie(
            "4",
            "Movie 4",
            "Description 4",
            "https://th.bing.com/th/id/OIP.iT2uYV3AZe3smX-w7QlDfwHaLH?w=135&h=202&c=7&r=0&o=5&pid=1.7"
        ),
        Movie(
            "5",
            "Movie 5",
            "Description 5",
            "https://th.bing.com/th/id/OIP.QHhITJ4EKej-rqyXtzGo7AHaLQ?w=202&h=308&c=7&r=0&o=5&pid=1.7"
        ),
        Movie(
            "6",
            "Movie 6",
            "Description 6",
            "https://th.bing.com/th/id/OIP.F_LGro-Fc91r40PAd0Q5hQHaKs?w=204&h=296&c=7&r=0&o=5&pid=1.7"
        )
    )


    fun getMovieList(): List<Movie> {
        return movies
    }

    fun getMovieById(id: String): Movie? {
        return movies.find { it.id == id }
    }
}