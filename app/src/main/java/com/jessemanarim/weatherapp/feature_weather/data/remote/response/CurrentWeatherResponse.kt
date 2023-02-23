package com.jessemanarim.weatherapp.feature_weather.data.remote.response

import com.jessemanarim.weatherapp.feature_weather.data.remote.dto.CurrentWeather

data class CurrentWeatherResponse(
    var success: Boolean = false,
    var data: CurrentWeather? = null,
    var exception: Exception? = null
)
