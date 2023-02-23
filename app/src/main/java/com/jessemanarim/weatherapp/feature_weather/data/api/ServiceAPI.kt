package com.jessemanarim.weatherapp.feature_weather.data.api

import com.jessemanarim.weatherapp.feature_weather.data.remote.dto.ForecastWeather
import retrofit2.http.GET

interface ServiceAPI {

    @GET("forecast?latitude=-26.88&longitude=-49.10&daily=temperature_2m_max,apparent_temperature_max,rain_sum&timezone=Europe/Berlin")
    fun getWeatherReport(): ForecastWeather
}