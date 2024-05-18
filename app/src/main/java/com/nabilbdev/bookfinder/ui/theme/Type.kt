package com.nabilbdev.bookfinder.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nabilbdev.bookfinder.R

private val poppins = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_bold, FontWeight.Bold),
    Font(R.font.poppins_black, FontWeight.Black)
)

// Set of Material typography styles to start with
val typography = Typography(
    displayMedium = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.W600,
        fontSize = 45.sp,
        lineHeight = 52.0.sp,
        letterSpacing = 0.sp
    ),
    displaySmall = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 44.0.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.W600,
        fontSize = 28.sp,
        lineHeight = 36.0.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.W600,
        fontSize = 24.sp,
        lineHeight = 32.0.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.W600,
        fontSize = 22.sp,
        lineHeight = 28.0.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        lineHeight = 24.0.sp,
        letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        lineHeight = 20.0.sp,
        letterSpacing = 0.1.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = poppins,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.0.sp,
        letterSpacing = 0.4.sp
    ),
    labelLarge = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        lineHeight = 20.0.sp,
        letterSpacing = 0.1.sp
    ),
    labelSmall = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.W500,
        fontSize = 11.sp,
        lineHeight = 16.0.sp,
        letterSpacing = 0.5.sp
    )
)