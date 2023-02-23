package com.jessemanarim.weatherapp.feature_weather.data.remote

object HttpRoutes {
    private const val BASE_ULR_WEATHER = "https://api.open-meteo.com"
    const val WEATHER = "https://api.open-meteo.com/v1/forecast?latitude=-26.88&longitude=-49.10&daily=temperature_2m_max&timezone=Europe/Berlin"
    const val CURRENT_WEATHER = "$BASE_ULR_WEATHER/v1/forecast?"
}