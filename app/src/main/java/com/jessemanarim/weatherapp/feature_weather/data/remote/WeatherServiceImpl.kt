package com.jessemanarim.weatherapp.feature_weather.data.remote

import android.location.Location
import com.jessemanarim.weatherapp.feature_weather.data.remote.response.CurrentWeatherResponse
import com.jessemanarim.weatherapp.feature_weather.data.remote.response.ForecastWeatherResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*

class WeatherServiceImpl(
    private val client: HttpClient
) : WeatherService {

    override suspend fun getWeatherForecast(): ForecastWeatherResponse {
        val response = ForecastWeatherResponse()

        return try {
            response.data = client.get {
                url(HttpRoutes.WEATHER)
            }
            response.apply { success = data != null }
        } catch(e: RedirectResponseException) {
            response.apply { exception = e }
        } catch(e: ClientRequestException) {
            response.apply { exception = e }
        } catch(e: ServerResponseException) {
            response.apply { exception = e }
        } catch(e: Exception) {
            response.apply { exception = e }
        }
    }

    override suspend fun getCurrentWeather(location: Location): CurrentWeatherResponse {
        val response = CurrentWeatherResponse()

        return try {
            val urlBuilder = URLBuilder(HttpRoutes.CURRENT_WEATHER)
            urlBuilder.parameters.append("latitude", location.latitude.toString())
            urlBuilder.parameters.append("longitude", location.longitude.toString())
            urlBuilder.parameters.append("current_weather", "true")

            response.data = client.get {
                url(urlBuilder.build())
            }
            response.apply {
                success = data != null
            }
        } catch(e: RedirectResponseException) {
            response.apply { exception = e }
        } catch(e: ClientRequestException) {
            response.apply { exception = e }
        } catch(e: ServerResponseException) {
            response.apply { exception = e }
        } catch(e: Exception) {
            response.apply { exception = e }
        }
    }
}