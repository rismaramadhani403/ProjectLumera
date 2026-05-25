package com.praktikum.lumera.ui.theme

import android.os.Build
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(

    primary = SoftCaramel,

    secondary = SoftBrown,

    tertiary = LatteColor,

    background = DarkText,

    surface = Color(0xFF2A2A2A),

    onPrimary = WarmWhite,

    onSecondary = WarmWhite,

    onTertiary = WarmWhite,

    onBackground = WarmWhite,

    onSurface = WarmWhite
)

private val LightColorScheme = lightColorScheme(

    primary = SoftCaramel,

    secondary = SoftBrown,

    tertiary = LatteColor,

    background = CreamBackground,

    surface = WarmWhite,

    onPrimary = WarmWhite,

    onSecondary = SoftBrownText,

    onTertiary = SoftBrownText,

    onBackground = SoftBrownText,

    onSurface = SoftBrownText
)

@Composable
fun LUMERATheme(

    darkTheme: Boolean = false,

    dynamicColor: Boolean = false,

    content: @Composable () -> Unit
) {

    val colorScheme = when {

        dynamicColor &&
                Build.VERSION.SDK_INT >=
                Build.VERSION_CODES.S -> {

            val context = LocalContext.current

            if (darkTheme) {

                dynamicDarkColorScheme(context)

            } else {

                dynamicLightColorScheme(context)
            }
        }

        darkTheme -> DarkColorScheme

        else -> LightColorScheme
    }

    MaterialTheme(

        colorScheme = colorScheme,

        typography = Typography,

        content = content
    )
}