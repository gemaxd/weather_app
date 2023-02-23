package com.jessemanarim.weatherapp.feature_weather.presentation.weather

import android.location.Location
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.jessemanarim.weatherapp.feature_weather.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel() {

    private val _uiState = mutableStateOf(WeatherState())
    val uiState: State<WeatherState> = _uiState

    init {
        onEvent(WeatherEvent.Loading)
    }

    fun onEvent(event: WeatherEvent){
        when(event){
            is WeatherEvent.SuccessfulForecast -> {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isForecasting = true,
                    forecastWeatherResponse = event.data
                )
            }
            is WeatherEvent.Successful -> {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isForecasting = false,
                    currentWeatherResponse = event.data
                )
            }
            is WeatherEvent.Failure -> {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    exception = event.exception
                )
            }
            is WeatherEvent.Loading -> {
                _uiState.value = _uiState.value.copy(
                    isLoading = true
                )
            }
            is WeatherEvent.LocationUpdate -> {
                _uiState.value = _uiState.value.copy(
                    location = event.location
                )
            }
        }
    }

    suspend fun fetchWeatherForecastInfo() {
        val response = repository.getWeatherForecastInfo()

        if(response.success){
           onEvent(WeatherEvent.SuccessfulForecast(data = response))
        } else {
           onEvent(WeatherEvent.Failure(exception = response.exception))
        }
    }

    suspend fun fetchCurrentWeatherInfo(location: Location?) {
        location?.let {
            val response = repository.getCurrentWeatherInfo(location)

            if(response.success){
                onEvent(WeatherEvent.Successful(data = response))
            } else {
                onEvent(WeatherEvent.Failure(exception = response.exception))
            }
        }
    }
}