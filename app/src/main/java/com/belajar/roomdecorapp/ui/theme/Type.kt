package com.belajar.roomdecorapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.belajar.roomdecorapp.R

// Set of Material typography styles to start with
val font = FontFamily(
    Font(
        R.font.gosa_regular,
        weight = FontWeight.Normal
    ),
    Font(
        R.font.gosa_bold,
        weight = FontWeight.Bold
    )
)
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    h1 = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)