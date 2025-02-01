package com.dam.dam_2025_recap.feature.movie.data.local.db

import com.dam.dam_2025_recap.feature.movie.domain.Movie


fun Movie.toEntity(date: Long): MovieEntity {
    return MovieEntity(
        this.id,
        this.title,
        this.description,
        this.imageUrl,
        date
    )
}

fun MovieEntity.toModel(): Movie =
    Movie(
        this.id,
        this.title,
        this.description,
        this.imageUrl
    )