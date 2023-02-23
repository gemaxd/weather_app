package com.jessemanarim.weatherapp.feature_weather.data.remote.response

import com.jessemanarim.weatherapp.feature_weather.data.remote.dto.ForecastWeather

data class ForecastWeatherResponse(
    var success: Boolean = false,
    var data: ForecastWeather? = null,
    var exception: Exception? = null
)
