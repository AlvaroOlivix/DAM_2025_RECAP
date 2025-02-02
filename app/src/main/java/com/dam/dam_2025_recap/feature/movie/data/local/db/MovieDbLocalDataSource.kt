package com.dam.dam_2025_recap.feature.movie.data.local.db

import com.dam.dam_2025_recap.feature.movie.domain.Movie
import org.koin.core.annotation.Single
import java.util.Date

@Single
class MovieDbLocalDataSource(private val movieDAO: MovieDAO) {

    private val cache = 60000

    suspend fun findMovies(): List<MovieEntity> {

        //Almacena todas las peliculas
        val movies = movieDAO.getAll()
        //Si el almacenamiento esta vacio o ha pasado más tiempo del permitido.
        return if (movies.isEmpty() || getTime()) {
            //Devuelve un empty
            emptyList()
        } else {
            //Si no, devuelve las peliculas ordenadas por titulo.
            movies.sortedBy { it.title }
        }
    }

    suspend fun saveMovies(movies: List<Movie>) {
        val dateToday = getDate().time
        movieDAO.saveMovies(*movies.map { it.toEntity(dateToday) }.toTypedArray())
    }

    suspend fun findMovieById(id: String): MovieEntity? {
        val movie = movieDAO.getById(id)
        return if (movie == null || getTime()) {
            null
        } else {
            movie
        }
    }

    suspend fun saveMovie(movie: Movie) {
        val dateToday = getDate().time
        movieDAO.saveMovie(movie.toEntity(dateToday))
    }

    //Función para recoger tiempo
    private fun getDate(): Date {
        return Date()
    }

    private suspend fun getTime(): Boolean {
        val movies = movieDAO.getAll()
        return movies.first().date + cache < getDate().time
    }
}