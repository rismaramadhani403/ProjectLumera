package com.praktikum.lumera.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack

import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AccountSettingsScreen(

    onBack: () -> Unit
) {

    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(

                Brush.verticalGradient(

                    colors = listOf(

                        Color(0xFF120B08),
                        Color(0xFF1A0F0A),
                        Color.Black
                    )
                )
            )
            .padding(20.dp)
    ) {

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        // =========================
        // HEADER
        // =========================
        Row(

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            IconButton(

                onClick = onBack,

                modifier = Modifier
                    .clip(CircleShape)
                    .background(
                        Color(0xFFD99A3E)
                    )
            ) {

                Icon(

                    imageVector =
                        Icons.AutoMirrored.Filled.ArrowBack,

                    contentDescription = null,

                    tint = Color.White
                )
            }

            Spacer(
                modifier = Modifier.padding(8.dp)
            )

            Text(

                text = "Account Settings",

                color = Color.White,

                fontWeight = FontWeight.Bold,

                fontSize = 28.sp
            )
        }

        Spacer(
            modifier = Modifier.height(34.dp)
        )

        // =========================
        // EDIT PROFILE
        // =========================
        SettingsCard(

            icon = Icons.Default.Person,

            title = "Edit Profile",

            subtitle =
                "Change your username and email"
        )

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        // =========================
        // CHANGE PASSWORD
        // =========================
        SettingsCard(

            icon = Icons.Default.Lock,

            title = "Change Password",

            subtitle =
                "Update your account password"
        )

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        // =========================
        // DARK MODE
        // =========================
        SettingsCard(

            icon = Icons.Default.Home,

            title = "Dark Mode",

            subtitle =
                "Enable premium dark appearance"
        )

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        // =========================
        // LANGUAGE
        // =========================
        SettingsCard(

            icon = Icons.Default.Notifications,

            title = "Language",

            subtitle =
                "English / Indonesia"
        )
    }
}

@Composable
fun SettingsCard(

    icon: ImageVector,

    title: String,

    subtitle: String
) {

    Card(

        modifier = Modifier
            .fillMaxWidth(),

        shape = RoundedCornerShape(28.dp),

        colors = CardDefaults.cardColors(

            containerColor =
                Color.White.copy(
                    alpha = 0.08f
                )
        )
    ) {

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Box(

                modifier = Modifier
                    .clip(CircleShape)
                    .background(
                        Color(0xFFD99A3E)
                    )
                    .padding(14.dp)
            ) {

                Icon(

                    imageVector = icon,

                    contentDescription = null,

                    tint = Color.White
                )
            }

            Spacer(
                modifier = Modifier.padding(10.dp)
            )

            Column {

                Text(

                    text = title,

                    color = Color.White,

                    fontWeight = FontWeight.Bold,

                    fontSize = 18.sp
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(

                    text = subtitle,

                    color = Color.LightGray,

                    fontSize = 13.sp
                )
            }
        }
    }
}