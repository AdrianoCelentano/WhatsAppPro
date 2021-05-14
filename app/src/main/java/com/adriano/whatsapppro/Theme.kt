package com.adriano.whatsapppro

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun WhatsAppProTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = Blue,
        darkIcons = isSystemInDarkTheme()
    )

    MaterialTheme(
        colors = if (darkTheme) DarkColors else LightColors,
        content = content
    )
}

private val Blue = Color(0xff3771FF)

private val DarkColors = darkColors(
    primary = Blue,
    secondary = Blue,
    surface = Color.DarkGray,
    onSurface = Color.White,
    background = Color.Black,
)

private val LightColors = lightColors(
    primary = Blue,
    secondary = Blue,
    surface = Color.White,
    onSurface = Color.Black,
    background = Color.LightGray
)