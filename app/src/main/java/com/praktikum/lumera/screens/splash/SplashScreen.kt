package com.praktikum.lumera.screens.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.praktikum.lumera.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(

    onFinished: () -> Unit
) {

    // =========================
    // ANIMATION STATE
    // =========================
    var visible by remember {

        mutableStateOf(false)
    }

    // =========================
    // SPLASH DELAY
    // =========================
    LaunchedEffect(Unit) {

        visible = true

        delay(2500)

        onFinished()
    }

    Box(

        modifier = Modifier
            .fillMaxSize()
    ) {

        // =========================
        // BACKGROUND IMAGE
        // =========================
        Image(

            painter = painterResource(
                id = R.drawable.bg_coffee
            ),

            contentDescription = null,

            modifier = Modifier.fillMaxSize(),

            contentScale = ContentScale.Crop
        )

        // =========================
        // DARK OVERLAY
        // =========================
        Box(

            modifier = Modifier
                .fillMaxSize()
                .background(

                    Brush.verticalGradient(

                        colors = listOf(

                            Color.Black.copy(alpha = 0.65f),

                            Color.Transparent,

                            Color.Black.copy(alpha = 0.15f),

                            Color.Black.copy(alpha = 0.85f)
                        )
                    )
                )
        )

        // =========================
        // CONTENT WITH ANIMATION
        // =========================
        AnimatedVisibility(

            visible = visible,

            enter =
                fadeIn() +
                        scaleIn()
        ) {

            Column(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 90.dp),

                verticalArrangement =
                    Arrangement.Bottom,

                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {

                // =========================
                // APP TITLE
                // =========================
                Text(

                    text = "LUMERA ☕",

                    color = Color.White,

                    fontSize = 46.sp,

                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                // =========================
                // SUBTITLE
                // =========================
                Text(

                    text = "Coffee Shop",

                    color = Color(0xFFC47A2C),

                    fontSize = 20.sp,

                    fontWeight = FontWeight.Medium
                )

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                // =========================
                // TAGLINE
                // =========================
                Text(

                    text = "Brew Your Day ☕",

                    color = Color.White.copy(
                        alpha = 0.82f
                    ),

                    fontSize = 15.sp
                )
            }
        }
    }
}