package com.dam.dam_2025_recap.feature.movie.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


const val MOVIES_TABLE = "movies"
const val MOVIE_ID = "id"

@Entity(tableName = MOVIES_TABLE)
data class MovieEntity (
    @PrimaryKey @ColumnInfo(name = MOVIE_ID) val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
    @ColumnInfo(name = "date") val date: Long
)