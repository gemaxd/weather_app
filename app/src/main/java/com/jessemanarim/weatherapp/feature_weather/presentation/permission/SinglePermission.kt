@file:OptIn(ExperimentalPermissionsApi::class)

package com.jessemanarim.weatherapp.feature_weather.presentation

import android.Manifest.permission.ACCESS_FINE_LOCATION
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.jessemanarim.weatherapp.navigation.Screen

@ExperimentalPermissionsApi
@Composable
fun LocationPermissionScreen(
    navHostController: NavHostController,
    deniedMessage: String = "Give this app a permission to proceed. If it doesn't work, then you'll have to do it manually from the settings.",
    rationaleMessage: String = "To use this app's functionalities, you need to give us the permission.",
) {
    val permissionState = rememberPermissionState(ACCESS_FINE_LOCATION)
    var showPermissionHandle by remember{ mutableStateOf(false) }

    DisposableEffect(permissionState) {
        if (permissionState.status == PermissionStatus.Granted){
            navHostController.navigate(Screen.WeatherScreen.route)
        }else{
            showPermissionHandle = true
        }
        this.onDispose {}
    }

    if(showPermissionHandle){
        HandleRequest(
            permissionState = permissionState,
            deniedContent = { shouldShowRationale ->
                PermissionDeniedContent(
                    deniedMessage = deniedMessage,
                    rationaleMessage = rationaleMessage,
                    shouldShowRationale = shouldShowRationale,
                    onRequestPermission = { permissionState.launchPermissionRequest() }
                )
            },
            content = {
                Content(
                    text = "PERMISSION GRANTED!",
                    showButton = false
                ) {
                    navHostController.navigate(Screen.WeatherScreen.route)
                }
            }
        )
    }
}

@ExperimentalPermissionsApi
@Composable
private fun HandleRequest(
    permissionState: PermissionState,
    deniedContent: @Composable (Boolean) -> Unit,
    content: @Composable () -> Unit
) {
    when (permissionState.status) {
        is PermissionStatus.Granted -> {
            content()
        }
        is PermissionStatus.Denied -> {
            deniedContent((permissionState.status as PermissionStatus.Denied).shouldShowRationale)
        }
    }
}

@Composable
fun Content(text: String, showButton: Boolean = true, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = text, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(12.dp))
        if (showButton) {
            Button(onClick = onClick) {
                Text(text = "Request")
            }
        }else{
            Button(onClick = onClick) {
                Text(text = "To Weather")
            }
        }
    }
}

@ExperimentalPermissionsApi
@Composable
fun PermissionDeniedContent(
    deniedMessage: String,
    rationaleMessage: String,
    shouldShowRationale: Boolean,
    onRequestPermission: () -> Unit
) {
    if (shouldShowRationale) {
        AlertDialog(
            onDismissRequest = {},
            title = {
                Text(
                    text = "Permission Request",
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.h6.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                )
            },
            text = {
                Text(rationaleMessage)
            },
            confirmButton = {
                Button(onClick = onRequestPermission) {
                    Text("Give Permission")
                }
            }
        )
    } else {
        Content(text = deniedMessage, onClick = onRequestPermission)
    }

}