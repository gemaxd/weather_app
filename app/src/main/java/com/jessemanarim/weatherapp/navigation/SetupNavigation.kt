package com.jessemanarim.weatherapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.jessemanarim.weatherapp.feature_weather.presentation.LocationPermissionScreen
import com.jessemanarim.weatherapp.feature_weather.presentation.weather.WeatherScreen

@OptIn(ExperimentalPermissionsApi::class)
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun SetupNavigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.PermissionScreen.route){

        composable(
            route = Screen.WeatherScreen.route
        ){
            WeatherScreen(navHostController = navController)
        }

        composable(
            route = Screen.PermissionScreen.route
        ){
            LocationPermissionScreen(
                navHostController = navController
            )
        }

    }
}