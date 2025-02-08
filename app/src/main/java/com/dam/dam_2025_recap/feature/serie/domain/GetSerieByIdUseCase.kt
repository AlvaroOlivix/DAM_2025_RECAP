package com.dam.dam_2025_recap.feature.serie.domain

import org.koin.core.annotation.Single

@Single
class GetSerieByIdUseCase(private val serieRepository: SerieRepository) {
    suspend operator fun invoke(id: String): Serie? {
        return serieRepository.getSerieById(id)
    }
}