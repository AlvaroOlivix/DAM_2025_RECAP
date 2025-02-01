package com.dam.dam_2025_recap.feature.movie.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.dam.dam_2025_recap.R
import com.dam.dam_2025_recap.core.data.local.RecapDataBase
import com.dam.dam_2025_recap.feature.movie.data.local.db.toEntity
import com.dam.dam_2025_recap.feature.movie.data.remote.MockRemoteDataSource
import com.dam.dam_2025_recap.feature.movie.domain.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
            applicationContext,
            RecapDataBase::class.java, "database-name"
        ).build()

        val movieDAO = db.movieDao()

        CoroutineScope(Dispatchers.IO).launch {

            val newMovie = Movie("1", "titulo", "dd", "uu")
            movieDAO.saveMovie(newMovie.toEntity(System.currentTimeMillis()))

            val movies = movieDAO.getAll()
            movies.forEach { movie ->
                Log.d(
                    "@dev",
                    "Pelicula: ${movie.id} ${movie.title} ${movie.description} ${movie.imageUrl}"
                )
            }
        }
        db.close()
    }
}