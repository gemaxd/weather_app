package com.jessemanarim.weatherapp.feature_weather.data.remote

import android.location.Location
import com.jessemanarim.weatherapp.feature_weather.data.remote.response.CurrentWeatherResponse
import com.jessemanarim.weatherapp.feature_weather.data.remote.response.ForecastWeatherResponse

interface WeatherService {
    suspend fun getWeatherForecast(): ForecastWeatherResponse
    suspend fun getCurrentWeather(location: Location): CurrentWeatherResponse
}