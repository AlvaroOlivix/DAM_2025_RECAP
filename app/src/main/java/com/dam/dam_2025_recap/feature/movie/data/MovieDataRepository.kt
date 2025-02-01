package com.dam.dam_2025_recap.feature.movie.data

import androidx.collection.emptyLongSet
import com.dam.dam_2025_recap.feature.movie.data.local.LocalXmlDataSource
import com.dam.dam_2025_recap.feature.movie.data.remote.MockRemoteDataSource
import com.dam.dam_2025_recap.feature.movie.domain.Movie
import com.dam.dam_2025_recap.feature.movie.domain.MovieRepository

class MovieDataRepository(
    private val localDataSource: LocalXmlDataSource,
    private val remoteDataSource: MockRemoteDataSource
) : MovieRepository {
    override fun getMovies(): List<Movie> {
        val localData = localDataSource.findMovies()
        return try {
            if (localData.isEmpty()) {
                val remoteData = remoteDataSource.getMovieList()
                localDataSource.saveMovies(remoteData)
                remoteData
            } else {
                localData
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override fun getMovie(id: String): Movie? {
        val localMovie = localDataSource.findMovieById(id)
        return try {
            if (localMovie == null) {
                val remoteMovie = remoteDataSource.getMovieById(id)
                localDataSource.saveMovie(remoteMovie!!)
                remoteMovie
            } else {
                localMovie
            }
        } catch (e: Exception) {
            Movie("error", "error", "error", "error")
        }
    }
}