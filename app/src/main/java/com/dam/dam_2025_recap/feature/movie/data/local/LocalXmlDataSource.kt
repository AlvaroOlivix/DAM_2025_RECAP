package com.dam.dam_2025_recap.feature.movie.data.local

import android.content.Context
import com.dam.dam_2025_recap.R
import com.dam.dam_2025_recap.feature.movie.domain.Movie
import com.google.gson.Gson
import org.koin.core.annotation.Single

@Single
class LocalXmlDataSource(private val context: Context) {

    private val sharedPref =
        context.getSharedPreferences(context.getString(R.string.local_data), Context.MODE_PRIVATE)
    private val gson = Gson()


    fun saveMovie(movie: Movie) {
        val editor = sharedPref.edit()
        editor.putString(movie.id, gson.toJson(movie))
        editor.apply()
    }

    fun saveMovies(movies: List<Movie>) {
        val editor = sharedPref.edit()
        movies.forEach { movie ->
            editor.putString(movie.id, gson.toJson(movie))
        }
        editor.apply()
    }

    fun findMovieById(id: String): Movie? {
        return gson.fromJson(sharedPref.getString(id, null), Movie::class.java)
    }

    fun findMovies(): List<Movie> {
        val movies = mutableListOf<Movie>()
        sharedPref.all.forEach { entry ->
            movies.add(gson.fromJson(entry.value as String, Movie::class.java))
        }
        return movies
    }
}