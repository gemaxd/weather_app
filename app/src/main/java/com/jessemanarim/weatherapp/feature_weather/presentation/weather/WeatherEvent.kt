package com.jessemanarim.weatherapp.feature_weather.presentation.weather

import android.location.Location
import com.jessemanarim.weatherapp.feature_weather.data.remote.response.CurrentWeatherResponse
import com.jessemanarim.weatherapp.feature_weather.data.remote.response.ForecastWeatherResponse

sealed interface WeatherEvent {
    data class SuccessfulForecast(val data: ForecastWeatherResponse): WeatherEvent
    data class Successful(val data: CurrentWeatherResponse): WeatherEvent
    data class Failure(val exception: Exception?): WeatherEvent
    data class LocationUpdate(val location: Location?): WeatherEvent
    object Loading: WeatherEvent
}