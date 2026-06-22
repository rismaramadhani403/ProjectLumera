package com.praktikum.lumera.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.praktikum.lumera.R
import com.praktikum.lumera.ui.theme.Playfair
import com.praktikum.lumera.ui.theme.PlayfairItalic
import com.praktikum.lumera.ui.theme.Poppins

@Composable
fun OnboardingScreen(

    onNextClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        // Background Image
        Image(
            painter = painterResource(
                id = R.drawable.bg_coffee
            ),

            contentDescription = null,

            modifier = Modifier.fillMaxSize(),

            contentScale = ContentScale.Crop,

            alpha = 0.25f
        )

        // Dark Overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(

                        colors = listOf(

                            Color.Black.copy(alpha = 0.82f),

                            Color.Transparent,

                            Color.Black.copy(alpha = 0.92f)
                        )
                    )
                )
        )

        // Main Content
        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 28.dp,
                    vertical = 40.dp
                ),

            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {

            Spacer(
                modifier = Modifier.height(55.dp)
            )

            // Coffee Image
            Box(

                modifier = Modifier
                    .size(220.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.radialGradient(

                            colors = listOf(

                                Color(0xFF8B5A2B),

                                Color.Transparent
                            )
                        )
                    ),

                contentAlignment = Alignment.Center
            ) {

                Image(
                    painter = painterResource(
                        id = R.drawable.kopi1
                    ),

                    contentDescription = null,

                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape),

                    contentScale = ContentScale.Crop
                )
            }

            // TITLE DOWN
            Spacer(
                modifier = Modifier.height(50.dp)
            )

            // Title
            Text(
                text = "Premium Coffee",
                color = Color.White,
                fontFamily = Playfair,
                fontSize = 32.sp
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = "Crafted With Passion",

                color = Color(0xFFC47A2C),

                fontFamily = PlayfairItalic,

                fontSize = 17.sp
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            // Description
            Text(
                text =
                    "Nikmati kopi pilihan dengan cita rasa terbaik yang diracik khusus untuk menemani harimu.",

                color = Color.White.copy(alpha = 0.85f),

                fontFamily = Poppins,

                fontSize = 15.sp,

                lineHeight = 26.sp,

                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(42.dp)
            )

            // Indicator
            Row(

                horizontalArrangement =
                    Arrangement.spacedBy(8.dp),

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .width(30.dp)
                        .height(8.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFFC47A2C))
                )

                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                )

                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                )
            }

            Spacer(
                modifier = Modifier.weight(1f)
            )

            // BUTTON
            Box(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(62.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color(0xFFC47A2C))
                    .clickable {

                        onNextClick()
                    },

                contentAlignment = Alignment.Center
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Get Started",

                        color = Color.White,

                        fontFamily = Poppins,

                        fontWeight = FontWeight.Bold,

                        fontSize = 18.sp
                    )

                    Spacer(
                        modifier = Modifier.width(10.dp)
                    )

                    Text(
                        text = "→",

                        color = Color.White,

                        fontSize = 20.sp
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )
        }
    }
}