package com.adriano.whatsapppro

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val Blue = Color(0xff3771FF)

val LightColors = lightColors(
    primary = Blue,
    secondary = Blue,
    surface = Color.White,
    onSurface = Color.Black,
    onSecondary = Color.Black,
    background = Color.LightGray
)

val DarkColors = darkColors(
    primary = Blue,
    secondary = Blue,
    surface = Color.DarkGray,
    onSurface = Color.White,
    onSecondary = Color.White,
    background = Color.Black,
)