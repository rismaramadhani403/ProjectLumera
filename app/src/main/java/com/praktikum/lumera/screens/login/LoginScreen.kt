package com.praktikum.lumera.screens.login

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
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.praktikum.lumera.R
import com.praktikum.lumera.ui.theme.Playfair
import com.praktikum.lumera.ui.theme.PlayfairItalic
import com.praktikum.lumera.ui.theme.Poppins

@Composable
fun LoginScreen(

    onBackClick: () -> Unit,

    onLogin: (String, String, String) -> Unit,

    onRegisterClick: () -> Unit

) {

    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    var selectedRole by remember {
        mutableStateOf("Customer")
    }

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

        // OVERLAY
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

            Spacer(modifier = Modifier.height(12.dp))

            // TITLE
            Row {

                Text(
                    text = "Selamat ",
                    fontFamily = Playfair,
                    fontSize = 38.sp,
                    color = Color.White
                )

                Text(
                    text = "Datang",
                    fontFamily = Playfair,
                    fontSize = 38.sp,
                    color = Color(0xFFC47A2C)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Login untuk menikmati",
                color = Color.White.copy(alpha = 0.85f),
                fontFamily = Poppins,
                fontSize = 15.sp
            )

            Text(
                text = "kopi terbaik dari Lumera",
                color = Color(0xFFC47A2C),
                fontFamily = PlayfairItalic,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Pilih Role",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                RoleItem(
                    icon = "☕",
                    title = "Customer",
                    subtitle = "Coffee Lover",
                    selected = selectedRole == "Customer",
                    modifier = Modifier.weight(1.1f)
                ) {
                    selectedRole = "Customer"
                }

                RoleItem(
                    icon = "💳",
                    title = "Kasir",
                    subtitle = "Manage Orders",
                    selected = selectedRole == "Kasir",
                    modifier = Modifier.weight(1f)
                ) {
                    selectedRole = "Kasir"
                }

                RoleItem(
                    icon = "⚙️",
                    title = "Admin",
                    subtitle = "Control System",
                    selected = selectedRole == "Admin",
                    modifier = Modifier.weight(1f)
                ) {
                    selectedRole = "Admin"
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

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

            Spacer(modifier = Modifier.height(18.dp))

            // PASSWORD
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },

                modifier = Modifier.fillMaxWidth(),

                singleLine = true,

                visualTransformation =
                    if (passwordVisible)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation(),

                leadingIcon = {

                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = Color.White
                    )
                },

                trailingIcon = {

                    IconButton(
                        onClick = {
                            passwordVisible = !passwordVisible
                        }
                    ) {

                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
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

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {

                Text(
                    text = "Lupa password?",
                    color = Color(0xFFC47A2C),
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            // BUTTON LOGIN
            Button(
                onClick = {

                    onLogin(
                        username,
                        password,
                        selectedRole
                    )
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),

                shape = RoundedCornerShape(50.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFC47A2C)
                )
            ) {

                Text(
                    text = "LOGIN",
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

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

@Composable
fun RoleItem(
    icon: String,
    title: String,
    subtitle: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .height(115.dp)
            .clip(RoundedCornerShape(35.dp))
            .background(
                if (selected)
                    Color(0xFFC47A2C).copy(alpha = 0.25f)
                else
                    Color.White.copy(alpha = 0.08f)
            )
            .clickable {
                onClick()
            }
            .padding(12.dp),

        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(
                        Color(0xFFC47A2C).copy(alpha = 0.15f)
                    ),

                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = icon,
                    fontSize = 20.sp
                )
            }

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = title,
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = subtitle,
                fontFamily = PlayfairItalic
            )
        }
    }
}