package com.jessemanarim.weatherapp.navigation

sealed class Screen(val route: String){
    object WeatherScreen: Screen("weather_screen")
    object PermissionScreen: Screen("permission_screen")

    fun withArgs(vararg args: Any) : String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}