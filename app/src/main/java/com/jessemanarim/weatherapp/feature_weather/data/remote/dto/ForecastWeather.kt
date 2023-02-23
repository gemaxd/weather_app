package com.jessemanarim.weatherapp.feature_weather.data.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastWeather(
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
    @SerializedName("daily_units")
    val daily_units: DailyUnits? = null,
    @SerializedName("daily")
    val daily: Daily? = null
)

@Serializable
data class DailyUnits(
    @SerializedName("time")
    val time: String = "",
    @SerializedName("temperature_2m_max")
    val temperature_2m_max: String = ""
)

@Serializable
data class Daily(
    @SerializedName("time")
    val time: List<String> = emptyList(),
    @SerializedName("temperature_2m_max")
    val temperature_2m_max: List<Float> = emptyList()
)
