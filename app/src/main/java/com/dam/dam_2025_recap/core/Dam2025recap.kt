package com.dam.dam_2025_recap.core

import android.app.Application
import com.dam.dam_2025_recap.core.di.AppModule
import com.dam.dam_2025_recap.core.di.LocalModule
import com.dam.dam_2025_recap.feature.movie.di.MoviesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.ksp.generated.module

class Dam2025recap : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Dam2025recap)
            modules(
                AppModule().module,
                LocalModule().module,
                MoviesModule().module
            )
        }
    }
}