package com.jessemanarim.weatherapp.feature_weather.presentation.weather

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.jessemanarim.weatherapp.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.*
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.jessemanarim.weatherapp.feature_weather.data.remote.dto.ForecastWeather
import com.jessemanarim.weatherapp.feature_weather.presentation.WeatherContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.withContext
import kotlinx.coroutines.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@ExperimentalCoroutinesApi
@ExperimentalPermissionsApi
@Composable
fun WeatherScreen(
    navHostController: NavHostController,
    viewModel: WeatherViewModel = hiltViewModel()
){
    val state = viewModel.uiState.value
    val context = LocalContext.current
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    var currentLocation by remember { mutableStateOf<Location?>(null) }

    LaunchedEffect(true) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return@LaunchedEffect
        }

        withContext(Dispatchers.IO) {
            currentLocation = getLastLocation(fusedLocationClient)
        }

        currentLocation?.let {
            withContext(Dispatchers.IO) {
                viewModel.fetchCurrentWeatherInfo(currentLocation)
            }
        }
    }

    if(state.isLoading) {
        Loading()
    } else {
        if(state.isForecasting){
            WeatherForecastContent(state.forecastWeatherResponse?.data)
        } else {
            WeatherContent(state.currentWeatherResponse?.data)
        }
        if(state.exception != null) ShowError(state.exception)
    }
}

@Composable
fun Loading(){
    Column(
        modifier = Modifier.fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        LoadingLottieAnimation()
    }
}

@Composable
fun LoadingLottieAnimation() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.cloud_losing))

    val progress by animateLottieCompositionAsState(
        composition,
        iterations = Int.MAX_VALUE,
    )

    LottieAnimation(
        composition,
        progress,
    )
}

@Composable
fun ResultLottieAnimation() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.forecast_result))

    val progress by animateLottieCompositionAsState(
        composition
    )

    LottieAnimation(
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillHeight,
        composition = composition,
        progress = progress,
    )
}

@Composable
fun ShowError(exception: Exception?){
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Error: ${exception?.message}")
    }
}

@Composable
fun WeatherForecastContent(content: ForecastWeather?){
    if(content != null){
        ResultLottieAnimation()
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Longitude: ${content.longitude}")
            Text(text = "Latitude: ${content.latitude}")
            Text(text = "Generation Time in MS: ${content.generationtime_ms}")
            Text(text = "UTC Offset in seconds: ${content.utc_offset_seconds}")
            Text(text = "Timezone: ${content.timezone}")
            Text(text = "Timezone Abbreviation ${content.timezone_abbreviation}")
            Text(text = "Longitude ${content.elevation}")
            Text(text = "Daily Units")
            Text(text = " - Time: ${content.daily_units?.time}")
            Text(text = " - Temperature 2m Max: ${content.daily_units?.temperature_2m_max}")
            Text(text = "Daily ")
            Text(text = " - Time ")
            content.daily?.time?.forEach {
                Text(text = " - $it")
            }
            Text(text = " - Temperature ")
            content.daily?.temperature_2m_max?.forEach {
                Text(text = " - $it")
            }
        }
    } else {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Nothing to show!")
        }
    }
}

@SuppressLint("MissingPermission")
@ExperimentalCoroutinesApi
suspend fun getLastLocation(fusedLocationClient: FusedLocationProviderClient): Location? {
    return suspendCoroutine { continuation ->
        fusedLocationClient.lastLocation.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                continuation.resume(task.result)
            } else {
                continuation.resume(null)
            }
        }
    }
}