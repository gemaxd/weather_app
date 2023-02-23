package com.jessemanarim.weatherapp.feature_weather.presentation.weather

import androidx.constraintlayout.compose.ConstraintSet

const val WEATHER_TIMEZONE = "WEATHER_TIMEZONE"
const val WEATHER_TEMPERATURE = "WEATHER_TEMPERATURE"
const val WEATHER_WIND_CONTENT = "WEATHER_WIND_CONTENT"
const val WEATHER_LATITUDE_LONGITUDE_AREA = "WEATHER_LATITUDE_LONGITUDE_AREA"
const val WEATHER_MIDDLE_GUIDELINE = "WEATHER_MIDDLE_GUIDELINE"

fun weatherConstraintSet() =
    ConstraintSet {

        val weatherTimezone = createRefFor(WEATHER_TIMEZONE)
        val weatherLatitudeLongitudeArea = createRefFor(WEATHER_LATITUDE_LONGITUDE_AREA)
        val weatherMiddleGuideline = createRefFor(WEATHER_MIDDLE_GUIDELINE)
        val weatherTemperature = createRefFor(WEATHER_TEMPERATURE)
        val weatherWindContent = createRefFor(WEATHER_WIND_CONTENT)

        constrain(weatherTimezone){
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(weatherLatitudeLongitudeArea){
            top.linkTo(weatherTimezone.bottom)
            start.linkTo(weatherTimezone.start)
            end.linkTo(weatherTimezone.end)
        }

        constrain(weatherMiddleGuideline){
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(weatherTemperature){
            top.linkTo(weatherMiddleGuideline.top)
            bottom.linkTo(weatherMiddleGuideline.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(weatherWindContent){
            top.linkTo(weatherTemperature.bottom)
            start.linkTo(weatherTemperature.start)
            end.linkTo(weatherTemperature.end)
        }

    }