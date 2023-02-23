package com.jessemanarim.weatherapp.feature_weather.presentation.weather

import android.location.Location
import com.jessemanarim.weatherapp.feature_weather.data.remote.response.CurrentWeatherResponse
import com.jessemanarim.weatherapp.feature_weather.data.remote.response.ForecastWeatherResponse

data class WeatherState(
    val isLoading: Boolean = false,
    val isForecasting: Boolean = false,
    val forecastWeatherResponse: ForecastWeatherResponse? = null,
    val currentWeatherResponse: CurrentWeatherResponse? = null,
    val location: Location? = null,
    val exception: Exception? = null
)
