package com.dam.dam_2025_recap.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dam.dam_2025_recap.feature.movie.data.local.db.MovieDAO
import com.dam.dam_2025_recap.feature.movie.data.local.db.MovieEntity


@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class RecapDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDAO
}