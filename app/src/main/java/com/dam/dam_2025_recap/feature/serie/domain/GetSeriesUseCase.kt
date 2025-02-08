package com.dam.dam_2025_recap.feature.serie.domain

import org.koin.core.annotation.Single


@Single
class GetSeriesUseCase(private val serieRepository: SerieRepository){

    suspend operator fun invoke(): List<Serie>{
        return serieRepository.getSeries()
    }
}