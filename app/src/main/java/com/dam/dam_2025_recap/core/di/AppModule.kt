package com.dam.dam_2025_recap.core.di

import com.google.gson.Gson
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single


@Module
@ComponentScan("com.dam.dam_2025_recap")
class AppModule {
    @Single
    fun provideGson() = Gson()
}