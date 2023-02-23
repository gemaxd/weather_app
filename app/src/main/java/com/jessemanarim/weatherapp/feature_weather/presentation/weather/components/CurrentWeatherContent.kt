package com.jessemanarim.weatherapp.feature_weather.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.jessemanarim.weatherapp.feature_weather.data.remote.dto.CurrentWeather
import com.jessemanarim.weatherapp.feature_weather.data.remote.dto.CurrentWeatherDetails
import com.jessemanarim.weatherapp.feature_weather.presentation.weather.*

@Composable
fun WeatherContent(content: CurrentWeather?){
    if(content != null) {
        ResultLottieAnimation()
        ConstraintLayout(
            modifier = Modifier.fillMaxSize(),
            constraintSet = weatherConstraintSet()
        ) {

            Text(
                modifier = Modifier
                    .layoutId(WEATHER_TIMEZONE)
                    .padding(top = 16.dp),
                text = "Timezone: ${content.timezone}"
            )

            LatitudeLongitudeContent(
                modifier = Modifier.layoutId(WEATHER_LATITUDE_LONGITUDE_AREA),
                lat = content.latitude,
                lon = content.longitude
            )

            Spacer(modifier = Modifier.layoutId(WEATHER_MIDDLE_GUIDELINE))

            TemperatureContent(
                modifier = Modifier.layoutId(WEATHER_TEMPERATURE),
                temperature = content.current_weather?.temperature
            )

            WindContent(
                modifier = Modifier.layoutId(WEATHER_WIND_CONTENT),
                windSpeed = content.current_weather?.windspeed,
                windDirection = content.current_weather?.winddirection
            )
        }
    }
}

@Composable
fun WindContent(
    modifier: Modifier,
    windSpeed: Float? = 0.0f,
    windDirection: Float? = 0.0f
){
    Card(modifier = modifier) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "WIND DETAILS",
                fontWeight = FontWeight.Light,
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Row {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Speed",
                        fontSize = 18.sp
                    )
                    Text(
                        text = windSpeed.toString(),
                        fontSize = 22.sp
                    )
                }
                Spacer(modifier = Modifier.padding(16.dp))
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Direction",
                        fontSize = 18.sp
                    )
                    Text(
                        text = windDirection.toString(),
                        fontSize = 22.sp
                    )
                }

            }
        }
    }
}

@Composable
fun TemperatureContent(modifier: Modifier, temperature: Float? = 0.0f){
    Card(modifier = modifier.padding(8.dp)) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Temperature",
                fontSize = 20.sp
            )
            Text(
                text = temperature.toString(),
                fontSize = 45.sp
            )
        }
    }
}

@Composable
fun LatitudeLongitudeContent(
    modifier: Modifier,
    lat: Float? = 0.0f,
    lon: Float? = 0.0f
){
    Card(modifier = modifier.padding(8.dp)) {
        Row(
            modifier = modifier.padding(8.dp)
        ) {
            Text(
                text = "LAT: ",
                fontSize = 16.sp
            )
            Text(
                text = lat.toString(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.padding(8.dp))

            Text(
                text = "LON: ",
                fontSize = 16.sp
            )
            Text(
                text = lon.toString(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewWeatherContent(){
    val currentWeather = CurrentWeather(
        latitude = 48.873f,
        longitude = 2.2954f,
        timezone = "BRT",
        current_weather = CurrentWeatherDetails(
            temperature = 20.0f,
            windspeed = 20.0f,
            winddirection = 215.0f
        )
    )

    WeatherContent(content = currentWeather)
}