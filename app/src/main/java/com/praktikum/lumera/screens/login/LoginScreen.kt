package com.praktikum.lumera.screens.login

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.praktikum.lumera.R
import com.praktikum.lumera.data.SessionManager
import com.praktikum.lumera.model.User

@Composable
fun LoginScreen(

    onBackClick: () -> Unit,

    onLogin: (String, String) -> Unit,

    onRegisterClick: () -> Unit
) {

    // =========================
    // STATE
    // =========================
    var username by remember {

        mutableStateOf("")
    }

    var email by remember {

        mutableStateOf("")
    }

    var password by remember {

        mutableStateOf("")
    }

    var passwordVisible by remember {

        mutableStateOf(false)
    }

    var expanded by remember {

        mutableStateOf(false)
    }

    val roles = listOf(

        "Admin",
        "Kasir",
        "Customer"
    )

    var selectedRole by remember {

        mutableStateOf("Customer")
    }

    // =========================
    // MAIN
    // =========================
    Box(

        modifier = Modifier.fillMaxSize()
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
                .zIndex(10f)
                .padding(
                    top = 60.dp,
                    start = 24.dp
                )
                .size(54.dp)
                .clip(CircleShape)
                .background(Color(0xFFC47A2C))
                .clickable {

                    onBackClick()
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
            // LOGIN CARD
            // =========================
            Card(

                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(34.dp),

                colors = CardDefaults.cardColors(

                    containerColor =
                        Color.White.copy(alpha = 0.12f)
                ),

                border = BorderStroke(

                    1.dp,

                    Color.White.copy(alpha = 0.3f)
                )
            ) {

                Column(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),

                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {

                    // =========================
                    // TITLE
                    // =========================
                    Text(

                        text = "Selamat Datang",

                        color = Color.White,

                        fontWeight = FontWeight.ExtraBold,

                        fontSize = 34.sp
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Text(

                        text = "Login untuk memesan minuman terbaik.",

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

                        shape = RoundedCornerShape(50.dp),

                        colors = OutlinedTextFieldDefaults.colors(

                            focusedContainerColor =
                                Color.Transparent,

                            unfocusedContainerColor =
                                Color.Transparent,

                            focusedBorderColor =
                                Color.White,

                            unfocusedBorderColor =
                                Color.White.copy(alpha = 0.7f),

                            focusedTextColor =
                                Color.White,

                            unfocusedTextColor =
                                Color.White
                        )
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

                        keyboardOptions = KeyboardOptions(

                            keyboardType =
                                KeyboardType.Email
                        ),

                        shape = RoundedCornerShape(50.dp),

                        colors = OutlinedTextFieldDefaults.colors(

                            focusedContainerColor =
                                Color.Transparent,

                            unfocusedContainerColor =
                                Color.Transparent,

                            focusedBorderColor =
                                Color.White,

                            unfocusedBorderColor =
                                Color.White.copy(alpha = 0.7f),

                            focusedTextColor =
                                Color.White,

                            unfocusedTextColor =
                                Color.White
                        )
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

                        trailingIcon = {

                            IconButton(

                                onClick = {

                                    passwordVisible =
                                        !passwordVisible
                                }
                            ) {

                                Icon(

                                    imageVector =
                                        Icons.Default.Lock,

                                    contentDescription = null,

                                    tint = Color.White
                                )
                            }
                        },

                        placeholder = {

                            Text(
                                text = "Password"
                            )
                        },

                        modifier = Modifier.fillMaxWidth(),

                        singleLine = true,

                        visualTransformation =

                            if (passwordVisible)
                                VisualTransformation.None

                            else
                                PasswordVisualTransformation(),

                        shape = RoundedCornerShape(50.dp),

                        colors = OutlinedTextFieldDefaults.colors(

                            focusedContainerColor =
                                Color.Transparent,

                            unfocusedContainerColor =
                                Color.Transparent,

                            focusedBorderColor =
                                Color.White,

                            unfocusedBorderColor =
                                Color.White.copy(alpha = 0.7f),

                            focusedTextColor =
                                Color.White,

                            unfocusedTextColor =
                                Color.White
                        )
                    )

                    Spacer(
                        modifier = Modifier.height(18.dp)
                    )

                    // =========================
                    // ROLE DROPDOWN
                    // =========================
                    Column(

                        modifier = Modifier.fillMaxWidth()
                    ) {

                        OutlinedButton(

                            onClick = {

                                expanded = !expanded
                            },

                            modifier = Modifier
                                .fillMaxWidth()
                                .height(64.dp),

                            shape = RoundedCornerShape(50.dp),

                            border = BorderStroke(

                                1.dp,

                                Color.White.copy(alpha = 0.7f)
                            ),

                            colors = ButtonDefaults.outlinedButtonColors(

                                containerColor = Color.Transparent,

                                contentColor = Color.White
                            )
                        ) {

                            Row(

                                modifier = Modifier.fillMaxWidth(),

                                verticalAlignment =
                                    Alignment.CenterVertically
                            ) {

                                Icon(

                                    imageVector =
                                        Icons.Default.Person,

                                    contentDescription = null,

                                    tint = Color(0xFFC47A2C)
                                )

                                Spacer(
                                    modifier = Modifier.width(14.dp)
                                )

                                Text(

                                    text = selectedRole,

                                    modifier = Modifier.weight(1f),

                                    color = Color.White,

                                    fontSize = 18.sp
                                )

                                Text(

                                    text = "▼",

                                    color = Color.White
                                )
                            }
                        }

                        DropdownMenu(

                            expanded = expanded,

                            onDismissRequest = {

                                expanded = false
                            }
                        ) {

                            roles.forEach { role ->

                                DropdownMenuItem(

                                    text = {

                                        Text(
                                            text = role
                                        )
                                    },

                                    onClick = {

                                        selectedRole = role

                                        expanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(30.dp)
                    )

                    // =========================
                    // LOGIN BUTTON
                    // =========================
                    Button(

                        onClick = {

                            SessionManager.currentUser.value = User(
                                name = username,
                                email = email,
                                role = selectedRole
                            )

                            onLogin(
                                username,
                                selectedRole
                            )
                        },

                        modifier = Modifier
                            .width(180.dp)
                            .height(52.dp),

                        shape = RoundedCornerShape(50.dp),

                        colors = ButtonDefaults.buttonColors(

                            containerColor = Color(0xFFC47A2C)
                        )
                    ) {

                        Text(

                            text = "LOGIN",

                            color = Color.White,

                            fontWeight = FontWeight.Bold
                        )
                    }

                    // =========================
                    // REGISTER
                    // =========================
                    Row {

                        Text(

                            text = "Belum punya akun? ",

                            color = Color.White.copy(alpha = 0.8f)
                        )

                        Text(

                            text = "Daftar",

                            color = Color(0xFFC47A2C),

                            fontWeight = FontWeight.Bold,

                            modifier = Modifier.clickable {

                                onRegisterClick()
                            }
                        )
                    }
                }
            }
        }
    }
}