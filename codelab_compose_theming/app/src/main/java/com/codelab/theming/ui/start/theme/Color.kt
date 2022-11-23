package com.codelab.theming.ui.start.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

// When defining colors, we name them "literally", based on the color value,
// rather than "semantically" e.g. Red500 not primary. This enables us to define
// multiple themes e.g. another color might be considered primary in dark theme or
// on a differently styled screen.
private val Red700 = Color(0xffdd0d3c)
private val Red800 = Color(0xffd00036)
private val Red900 = Color(0xffc20029)
private val Red200 = Color(0xfff297a2)
private val Red300 = Color(0xffea6d7e)

// Here we use the lightColors function to build our Colors,
// this provides sensible defaults so we don't have to specify all colors that make up
// a Material color palette. For example, notice that we haven't specified a background color
// or many of the ‘on' colors, we'll use the defaults.
val LightColors = lightColors(
    primary = Red700,
    primaryVariant = Red900,
    onPrimary = Color.White,
    secondary = Red700,
    secondaryVariant = Red900,
    onSecondary = Color.White,
    error = Red800
)

val DarkColors = darkColors(
    primary = Red300,
    primaryVariant = Red700,
    onPrimary = Color.Black,
    secondary = Red300,
    onSecondary = Color.Black,
    error = Red200
)