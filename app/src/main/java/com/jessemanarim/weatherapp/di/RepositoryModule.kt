package com.jessemanarim.weatherapp.di

import com.jessemanarim.weatherapp.feature_weather.data.remote.WeatherService
import com.jessemanarim.weatherapp.feature_weather.data.repository.WeatherRepositoryImpl
import com.jessemanarim.weatherapp.feature_weather.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(
        api: WeatherService
    ): WeatherRepository{
        return WeatherRepositoryImpl(
            weatherService = api
        )
    }
}