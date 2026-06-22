package com.praktikum.lumera.screens.register

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.praktikum.lumera.datastore.UserPreferences
import com.praktikum.lumera.model.User
import com.praktikum.lumera.ui.theme.Playfair
import com.praktikum.lumera.ui.theme.PlayfairItalic
import com.praktikum.lumera.ui.theme.Poppins
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    userPreferences: UserPreferences,
    onBack: () -> Unit
){

    var username by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // BACKGROUND
        Image(
            painter = painterResource(R.drawable.bg_coffee),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // DARK OVERLAY
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.85f),
                            Color.Black.copy(alpha = 0.55f),
                            Color.Black.copy(alpha = 0.85f)
                        )
                    )
                )
        )

        // CONTENT
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // LOGO
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
                    .background(Color.White),

                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "☕",
                    fontSize = 42.sp
                )
            }

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            // TITLE
            Row {

                Text(
                    text = "Buat ",
                    color = Color.White,
                    fontSize = 38.sp,
                    fontFamily = Playfair
                )

                Text(
                    text = "Akun",
                    color = Color(0xFFC47A2C),
                    fontSize = 38.sp,
                    fontFamily = Playfair
                )
            }
            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Daftar untuk menikmati",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 15.sp,
                fontFamily = Poppins
            )

            Text(
                text = "kopi terbaik dari Lumera",
                color = Color(0xFFC47A2C),
                fontSize = 17.sp,
                fontFamily = PlayfairItalic
            )

            Spacer(
                modifier = Modifier.height(40.dp)
            )

            // USERNAME
            OutlinedTextField(
                value = username,
                onValueChange = {
                    username = it
                },

                modifier = Modifier.fillMaxWidth(),

                singleLine = true,

                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = Color.White
                    )
                },

                placeholder = {
                    Text(
                        text = "Username",
                        color = Color.White.copy(alpha = 0.45f),
                        fontFamily = Poppins
                    )
                },

                shape = RoundedCornerShape(28.dp),

                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,

                    focusedBorderColor = Color(0xFFC47A2C),
                    unfocusedBorderColor = Color.White.copy(alpha = 0.25f),

                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,

                    cursorColor = Color(0xFFC47A2C)
                )
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            // EMAIL
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },

                modifier = Modifier.fillMaxWidth(),

                singleLine = true,

                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null,
                        tint = Color.White
                    )
                },

                placeholder = {
                    Text(
                        text = "Email",
                        color = Color.White.copy(alpha = 0.45f)
                    )
                },

                shape = RoundedCornerShape(28.dp),

                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,

                    focusedBorderColor = Color(0xFFC47A2C),
                    unfocusedBorderColor = Color.White.copy(alpha = 0.25f),

                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,

                    cursorColor = Color(0xFFC47A2C)
                )
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            // PASSWORD
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },

                modifier = Modifier.fillMaxWidth(),

                singleLine = true,

                visualTransformation =
                    PasswordVisualTransformation(),

                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = Color.White
                    )
                },

                placeholder = {
                    Text(
                        text = "Password",
                        color = Color.White.copy(alpha = 0.45f)
                    )
                },

                shape = RoundedCornerShape(28.dp),

                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,

                    focusedBorderColor = Color(0xFFC47A2C),
                    unfocusedBorderColor = Color.White.copy(alpha = 0.25f),

                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,

                    cursorColor = Color(0xFFC47A2C)
                )
            )

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            // REGISTER BUTTON
            Button(
                onClick = {

                    scope.launch {

                        userPreferences.saveUser(

                            User(
                                name = username,
                                email = email,
                                password = password,
                                role = "Customer"
                            )
                        )

                        onBack()
                    }
                }
            ){

                Text(
                    text = "DAFTAR",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Poppins
                )
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Row {

                Text(
                    text = "Sudah punya akun? ",
                    color = Color.White.copy(alpha = 0.8f)
                )

                Text(
                    text = "Login",
                    color = Color(0xFFC47A2C),
                    fontWeight = FontWeight.Bold,

                    modifier = Modifier.clickable {
                        onBack()
                    }
                )
            }
        }
    }
}