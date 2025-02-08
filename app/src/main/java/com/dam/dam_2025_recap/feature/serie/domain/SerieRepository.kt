package com.dam.dam_2025_recap.feature.serie.domain

interface SerieRepository {
    fun getSeries():List<Serie>
    fun getSerieById(id: String): Serie?
}