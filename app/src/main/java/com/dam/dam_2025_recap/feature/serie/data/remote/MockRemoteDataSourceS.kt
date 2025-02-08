package com.dam.dam_2025_recap.feature.serie.data.remote

import com.dam.dam_2025_recap.feature.serie.domain.Serie
import org.koin.core.annotation.Single

@Single
class MockRemoteDataSourceS {

    private val series = listOf(
        Serie(
            "1",
            "The Bear",
            "https://th.bing.com/th/id/OIP.o3xOzRyT9mQouiX-Tyjc8gAAAA?rs=1&pid=ImgDetMain"
        ),
        Serie(
            "2",
            "The Punisher",
            "https://pics.filmaffinity.com/The_Punisher_Serie_de_TV-888303566-large.jpg"
        ),
        Serie(
            "3",
            "The Walking Dead",
            "https://posterspy.com/wp-content/uploads/2022/12/POSTERSPY-3.jpg"
        )
    )

    fun getSeries(): List<Serie> {
        return series
    }

    fun getSerieById(id: String): Serie? {
        return series.find { it.id == id }
    }
}