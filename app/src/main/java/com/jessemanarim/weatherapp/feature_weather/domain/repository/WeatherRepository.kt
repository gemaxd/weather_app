package com.jessemanarim.weatherapp.feature_weather.domain.repository

import android.location.Location
import com.jessemanarim.weatherapp.feature_weather.data.remote.response.CurrentWeatherResponse
import com.jessemanarim.weatherapp.feature_weather.data.remote.response.ForecastWeatherResponse

interface WeatherRepository {
    suspend fun getWeatherForecastInfo(): ForecastWeatherResponse
    suspend fun getCurrentWeatherInfo(location: Location): CurrentWeatherResponse
}