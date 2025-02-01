package com.dam.dam_2025_recap.feature.movie.data

import com.dam.dam_2025_recap.feature.movie.data.local.db.MovieDbLocalDataSource
import com.dam.dam_2025_recap.feature.movie.data.local.db.toModel
import com.dam.dam_2025_recap.feature.movie.data.remote.MockRemoteDataSource
import com.dam.dam_2025_recap.feature.movie.domain.Movie
import com.dam.dam_2025_recap.feature.movie.domain.MovieRepository

class MovieDataRepository(
    private val localDataSource: MovieDbLocalDataSource,
    private val remoteDataSource: MockRemoteDataSource
) : MovieRepository {
    override suspend fun getMovies(): List<Movie> {
        val localMovies = localDataSource.findMovies()
        return if (localMovies.isEmpty()) {
            val remoteMovies = remoteDataSource.getMovieList()
            localDataSource.saveMovies(remoteMovies)
            remoteMovies
        } else {
            localMovies.map { it.toModel() }
        }
    }

    override suspend fun getMovie(id: String): Movie? {
        val localMovie = localDataSource.findMovieById(id)

        return if (localMovie == null) {
            val remoteMovie = remoteDataSource.getMovieById(id)
            localDataSource.saveMovie(remoteMovie!!)
            remoteMovie
        } else {
            localMovie.toModel()
        }
    }
}