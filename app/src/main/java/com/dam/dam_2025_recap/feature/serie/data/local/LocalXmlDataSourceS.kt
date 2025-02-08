package com.dam.dam_2025_recap.feature.serie.data.local

import android.content.Context
import com.dam.dam_2025_recap.R
import com.dam.dam_2025_recap.feature.serie.domain.Serie
import com.google.gson.Gson
import org.koin.core.annotation.Single

@Single
class LocalXmlDataSourceS(val context: Context) {
    private val sharedPref = context.getSharedPreferences(
        context.getString(R.string.local_data_series),
        Context.MODE_PRIVATE
    )
    private val gson = Gson()

    fun saveSerie(serie: Serie) {
        val editor = sharedPref.edit()
        editor.putString(serie.id, gson.toJson(serie)).apply()
    }

    fun seveSeries(series: List<Serie>) {
        val editor = sharedPref.edit()
        series.forEach { serie ->
            editor.putString(serie.id, gson.toJson(serie)).apply()
        }
    }

    fun getSerie(id: String): Serie? {
        val serie = sharedPref.getString(id, null)
        return gson.fromJson(serie, Serie::class.java)
    }

    fun getSeries(): List<Serie> {
        val series = ArrayList<Serie>()
        val seriesMap = sharedPref.all
        seriesMap.values.forEach {
            series.add(gson.fromJson(it as String, Serie::class.java))
        }
        return series
    }
}