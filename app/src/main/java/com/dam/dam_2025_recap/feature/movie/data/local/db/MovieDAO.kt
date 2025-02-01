package com.dam.dam_2025_recap.feature.movie.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDAO {

    @Query("SELECT * FROM $MOVIES_TABLE")
    suspend fun getAll(): List<MovieEntity>

    @Query("SELECT * FROM $MOVIES_TABLE WHERE $MOVIE_ID = :id")
    suspend fun getById(id: String): MovieEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(vararg movies: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movie: MovieEntity)


}