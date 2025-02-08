package com.dam.dam_2025_recap.feature.serie.data

import com.dam.dam_2025_recap.feature.serie.data.local.LocalXmlDataSourceS
import com.dam.dam_2025_recap.feature.serie.data.remote.MockRemoteDataSourceS
import com.dam.dam_2025_recap.feature.serie.domain.Serie
import com.dam.dam_2025_recap.feature.serie.domain.SerieRepository
import org.koin.core.annotation.Single


@Single
class SerieDataRepository(
    private val localData: LocalXmlDataSourceS, private val remoteData: MockRemoteDataSourceS
) : SerieRepository {
    override fun getSeries(): List<Serie> {
        val localSeries = localData.getSeries()
        return try {
            return if (localSeries.isEmpty()) {
                val remoteSeries = remoteData.getSeries()
                localData.seveSeries(remoteSeries)
                remoteSeries
            } else {
                localSeries
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override fun getSerieById(id: String): Serie? {
        val localSerie = localData.getSerie(id)
        return try {
            if (localSerie == null){
                val remoteSerie = remoteData.getSerieById(id)
                localData.saveSerie(remoteSerie!!)
                remoteSerie
            }else{
                localSerie
            }
        }catch (e: Exception){
            Serie("error","error","error")
        }
    }
}