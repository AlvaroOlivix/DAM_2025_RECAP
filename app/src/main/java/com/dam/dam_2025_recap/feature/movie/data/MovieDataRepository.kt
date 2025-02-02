package com.dam.dam_2025_recap.feature.movie.data

import com.dam.dam_2025_recap.feature.movie.data.local.db.MovieDbLocalDataSource
import com.dam.dam_2025_recap.feature.movie.data.local.db.toModel
import com.dam.dam_2025_recap.feature.movie.data.remote.MockRemoteDataSource
import com.dam.dam_2025_recap.feature.movie.domain.Movie
import com.dam.dam_2025_recap.feature.movie.domain.MovieRepository
import org.koin.core.annotation.Single


@Single
class MovieDataRepository(
    private val localDataSource: MovieDbLocalDataSource,
    private val remoteDataSource: MockRemoteDataSource
) : MovieRepository {
    override suspend fun getMovies(): List<Movie> {
        val localMovies = localDataSource.findMovies()
        return try {
            if (localMovies.isEmpty()) {
                val remoteMovies = remoteDataSource.getMovieList()
                localDataSource.saveMovies(remoteMovies)
                remoteMovies
            } else {
                localMovies.map { it.toModel() }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getMovie(id: String): Movie? {
        val localMovie = localDataSource.findMovieById(id)
        return try {
            if (localMovie == null) {
                val remoteMovie = remoteDataSource.getMovieById(id)
                localDataSource.saveMovie(remoteMovie!!)
                remoteMovie
            } else {
                localMovie.toModel()
            }
        } catch (e: Exception) {
            Movie("error", "error", "error", "error")
        }
    }
}