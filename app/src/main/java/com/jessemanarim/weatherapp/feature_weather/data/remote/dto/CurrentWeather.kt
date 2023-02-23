package com.jessemanarim.weatherapp.feature_weather.data.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeather(
    @SerializedName("latitude")
    val latitude: Float = 0.0f,
    @SerializedName("longitude")
    val longitude: Float = 0.0f,
    @SerializedName("generationtime_ms")
    val generationtime_ms: Float = 0.0f,
    @SerializedName("utc_offset_seconds")
    val utc_offset_seconds: Long = 0L,
    @SerializedName("timezone")
    val timezone: String = "",
    @SerializedName("timezone_abbreviation")
    val timezone_abbreviation: String = "",
    @SerializedName("elevation")
    val elevation: Float = 0.0f,
    @SerializedName("current_weather")
    val current_weather: CurrentWeatherDetails? = null
)

@Serializable
data class CurrentWeatherDetails(
    @SerializedName("temperature")
    val temperature: Float = 0.0f,
    @SerializedName("windspeed")
    val windspeed: Float = 0.0f,
    @SerializedName("winddirection")
    val winddirection: Float = 0.0f,
    @SerializedName("weathercode")
    val weathercode: Int = 0,
    @SerializedName("time")
    val time: String = ""
)
