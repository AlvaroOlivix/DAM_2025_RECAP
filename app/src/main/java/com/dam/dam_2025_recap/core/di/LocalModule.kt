package com.dam.dam_2025_recap.core.di

import android.content.Context
import androidx.room.Room
import com.dam.dam_2025_recap.core.data.local.RecapDataBase
import com.dam.dam_2025_recap.feature.movie.data.local.db.MovieDAO
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single


@Module
@ComponentScan
class LocalModule {
    @Single
    fun provideDatabase(context: Context): RecapDataBase {
        val db = Room.databaseBuilder(
            context,
            RecapDataBase::class.java,
            "recap_db"
        ).build()
        return db
    }

    @Single
    fun provideMovieDao(db: RecapDataBase): MovieDAO {
        return db.movieDao()
    }
}