package com.praktikum.lumera.screens.register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack

import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person

import androidx.compose.material3.*

import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.input.PasswordVisualTransformation

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.praktikum.lumera.R

@Composable
fun RegisterScreen(

    onBack: () -> Unit
) {

    var username by remember {

        mutableStateOf("")
    }

    var email by remember {

        mutableStateOf("")
    }

    var password by remember {

        mutableStateOf("")
    }

    Box(

        modifier = Modifier.fillMaxSize()
    ) {

        // =========================
        // BACKGROUND
        // =========================
        androidx.compose.foundation.Image(

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

                            Color.Black.copy(alpha = 0.7f),

                            Color.Black.copy(alpha = 0.4f),

                            Color.Black.copy(alpha = 0.85f)
                        )
                    )
                )
        )

        // =========================
        // BACK BUTTON
        // =========================
        Box(

            modifier = Modifier
                .padding(
                    top = 60.dp,
                    start = 24.dp
                )
                .size(54.dp)
                .clip(CircleShape)
                .background(Color(0xFFC47A2C))
                .clickable {

                    onBack()
                },

            contentAlignment = Alignment.Center
        ) {

            Icon(

                imageVector =
                    Icons.AutoMirrored.Filled.ArrowBack,

                contentDescription = null,

                tint = Color.White
            )
        }

        // =========================
        // CONTENT
        // =========================
        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),

            horizontalAlignment =
                Alignment.CenterHorizontally,

            verticalArrangement =
                Arrangement.Center
        ) {

            // =========================
            // LOGO
            // =========================
            Box(

                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.White),

                contentAlignment =
                    Alignment.Center
            ) {

                Text(

                    text = "☕",

                    fontSize = 54.sp
                )
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            // =========================
            // CARD
            // =========================
            Card(

                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(34.dp),

                colors = CardDefaults.cardColors(

                    containerColor =
                        Color.White.copy(alpha = 0.12f)
                )
            ) {

                Column(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),

                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {

                    Text(

                        text = "Daftar Akun",

                        color = Color.White,

                        fontWeight = FontWeight.ExtraBold,

                        fontSize = 34.sp
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Text(

                        text = "Buat akun baru untuk menikmati menu coffee terbaik.",

                        color = Color.White.copy(alpha = 0.8f),

                        fontSize = 14.sp
                    )

                    Spacer(
                        modifier = Modifier.height(28.dp)
                    )

                    // =========================
                    // USERNAME
                    // =========================
                    OutlinedTextField(

                        value = username,

                        onValueChange = {

                            username = it
                        },

                        leadingIcon = {

                            Icon(

                                imageVector =
                                    Icons.Default.Person,

                                contentDescription = null,

                                tint = Color.White
                            )
                        },

                        placeholder = {

                            Text(
                                text = "Username"
                            )
                        },

                        modifier = Modifier.fillMaxWidth(),

                        singleLine = true,

                        shape = RoundedCornerShape(50.dp)
                    )

                    Spacer(
                        modifier = Modifier.height(18.dp)
                    )

                    // =========================
                    // EMAIL
                    // =========================
                    OutlinedTextField(

                        value = email,

                        onValueChange = {

                            email = it
                        },

                        leadingIcon = {

                            Icon(

                                imageVector =
                                    Icons.Default.Email,

                                contentDescription = null,

                                tint = Color.White
                            )
                        },

                        placeholder = {

                            Text(
                                text = "Email"
                            )
                        },

                        modifier = Modifier.fillMaxWidth(),

                        singleLine = true,

                        shape = RoundedCornerShape(50.dp)
                    )

                    Spacer(
                        modifier = Modifier.height(18.dp)
                    )

                    // =========================
                    // PASSWORD
                    // =========================
                    OutlinedTextField(

                        value = password,

                        onValueChange = {

                            password = it
                        },

                        leadingIcon = {

                            Icon(

                                imageVector =
                                    Icons.Default.Lock,

                                contentDescription = null,

                                tint = Color.White
                            )
                        },

                        placeholder = {

                            Text(
                                text = "Password"
                            )
                        },

                        modifier = Modifier.fillMaxWidth(),

                        singleLine = true,

                        visualTransformation =
                            PasswordVisualTransformation(),

                        shape = RoundedCornerShape(50.dp)
                    )

                    Spacer(
                        modifier = Modifier.height(30.dp)
                    )

                    // =========================
                    // BUTTON
                    // =========================
                    Button(

                        onClick = {

                            onBack()
                        },

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp),

                        shape = RoundedCornerShape(50.dp),

                        colors = ButtonDefaults.buttonColors(

                            containerColor =
                                Color(0xFFC47A2C)
                        )
                    ) {

                        Text(

                            text = "DAFTAR",

                            color = Color.White,

                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}