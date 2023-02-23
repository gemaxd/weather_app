package com.jessemanarim.weatherapp.feature_weather.data.repository

import android.location.Location
import com.jessemanarim.weatherapp.feature_weather.data.remote.WeatherService
import com.jessemanarim.weatherapp.feature_weather.data.remote.response.CurrentWeatherResponse
import com.jessemanarim.weatherapp.feature_weather.data.remote.response.ForecastWeatherResponse
import com.jessemanarim.weatherapp.feature_weather.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherService: WeatherService
): WeatherRepository {
    override suspend fun getWeatherForecastInfo(): ForecastWeatherResponse {
        return weatherService.getWeatherForecast()
    }

    override suspend fun getCurrentWeatherInfo(location: Location): CurrentWeatherResponse {
        return weatherService.getCurrentWeather(location)
    }
}