package com.belajar.roomdecorapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = PrimaryNight,
    primaryVariant = PrimaryNight,
    background = Color.White,
    onBackground = Color.Black,
    onSurface = Color.White,
    surface = SurfaceNight,
    secondary = Secondary,

)

private val LightColorPalette = lightColors(
    primary = Primary,
    primaryVariant = Primary,
    background = Color.White,
    onBackground = Color.Black,
    onSurface = Color.White,
    surface = Color.Black,
    secondary = Secondary,

    /* Other default colors to override
    background = Color.White,
    ,
    onPrimary = Color.White,
    onSecondary = Color.Black,

    */
)

@Composable
fun RoomDecorAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val systemUiController = rememberSystemUiController()
    if (darkTheme) {
        systemUiController.setSystemBarsColor(
            color = Color.White
        )
    } else {
        systemUiController.setSystemBarsColor(
            color = Color.White.copy(0.1F)
        )
    }
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}