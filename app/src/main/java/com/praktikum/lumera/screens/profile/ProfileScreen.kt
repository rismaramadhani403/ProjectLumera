package com.praktikum.lumera.screens.profile

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

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.foundation.verticalScroll

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.List

import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.praktikum.lumera.datastore.UserPreferences

@Composable
fun ProfileScreen(

    userPreferences: UserPreferences,

    onBackClick: () -> Unit,

    onLogoutClick: () -> Unit,

    onHistoryClick: () -> Unit,

    onAddressClick: () -> Unit,

    onPaymentMethodClick: () -> Unit,

    onNotificationClick: () -> Unit,

    onSettingsClick: () -> Unit
) {

    val user by userPreferences
        .getUser
        .collectAsState(

            initial = null
        )

    // =========================
    // LOGIN STATUS
    // =========================
    val isLoggedIn =

        !user?.email.isNullOrEmpty()

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
            .verticalScroll(
                rememberScrollState()
            )
            .padding(20.dp),

        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {

        Spacer(
            modifier = Modifier.height(40.dp)
        )

        // =========================
        // BACK BUTTON
        // =========================
        Row(

            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement =
                Arrangement.Start
        ) {

            Box(

                modifier = Modifier
                    .clip(CircleShape)
                    .background(
                        Color(0xFFD99A3E)
                    )
                    .clickable {

                        onBackClick()
                    }
                    .padding(12.dp)
            ) {

                Icon(

                    imageVector =
                        Icons.AutoMirrored.Filled.ArrowBack,

                    contentDescription = null,

                    tint = Color.White
                )
            }
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        // =========================
        // TITLE
        // =========================
        Text(

            text = "Profile",

            color = Color.White,

            fontWeight = FontWeight.Bold,

            fontSize = 30.sp
        )

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        // =========================
        // PROFILE PHOTO
        // =========================
        Icon(

            imageVector =
                Icons.Default.AccountCircle,

            contentDescription = null,

            tint = Color.White,

            modifier = Modifier.size(130.dp)
        )

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        // =========================
        // NAME
        // =========================
        Text(

            text = if (isLoggedIn) {

                user?.name ?: "User"

            } else {

                "Guest"
            },

            color = Color.White,

            fontWeight = FontWeight.ExtraBold,

            fontSize = 34.sp
        )

        Spacer(
            modifier = Modifier.height(6.dp)
        )

        // =========================
        // EMAIL
        // =========================
        Text(

            text = if (isLoggedIn) {

                user?.email ?: ""

            } else {

                "Please login to continue"
            },

            color = Color.LightGray,

            fontSize = 16.sp
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        // =========================
        // ROLE BADGE
        // =========================
        Box(

            modifier = Modifier
                .clip(
                    RoundedCornerShape(20.dp)
                )
                .background(
                    Color(0xFFD99A3E)
                )
                .padding(
                    horizontal = 22.dp,
                    vertical = 8.dp
                )
        ) {

            Text(

                text = if (isLoggedIn) {

                    user?.role ?: "Customer"

                } else {

                    "Guest Mode"
                },

                color = Color.White,

                fontWeight = FontWeight.Bold
            )
        }

        // =========================
        // SHOW ONLY IF LOGIN
        // =========================
        if (isLoggedIn) {

            // =========================
            // PREMIUM MEMBER CARD
            // =========================
            Spacer(
                modifier = Modifier.height(28.dp)
            )

            Card(

                modifier = Modifier
                    .fillMaxWidth(),

                colors = CardDefaults.cardColors(

                    containerColor =
                        Color(0xFFD99A3E)
                ),

                shape = RoundedCornerShape(30.dp)
            ) {

                Column(

                    modifier = Modifier.padding(22.dp)
                ) {

                    Text(

                        text = "Gold Member ☕",

                        color = Color.White,

                        fontWeight = FontWeight.Bold,

                        fontSize = 24.sp
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(

                        text =
                            "Enjoy 10% discount for every coffee purchase.",

                        color = Color.White.copy(
                            alpha = 0.9f
                        )
                    )
                }
            }

            // =========================
            // STATISTICS
            // =========================
            Spacer(
                modifier = Modifier.height(28.dp)
            )

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceBetween
            ) {

                StatisticItem(
                    "12",
                    "Orders"
                )

                StatisticItem(
                    "24",
                    "Favorites"
                )

                StatisticItem(
                    "120",
                    "Points"
                )
            }

            Spacer(
                modifier = Modifier.height(34.dp)
            )

            // =========================
            // PROFILE MENU
            // =========================
            ProfileItem(

                icon =
                    Icons.AutoMirrored.Filled.List,

                title = "Order History",

                onClick = {

                    onHistoryClick()
                }
            )

            ProfileItem(

                icon = Icons.Default.LocationOn,

                title = "Delivery Address",

                onClick = {

                    onAddressClick()
                }
            )

            ProfileItem(

                icon = Icons.Default.Email,

                title = "Payment Method",

                onClick = {

                    onPaymentMethodClick()
                }
            )

            ProfileItem(

                icon = Icons.Default.Notifications,

                title = "Notifications",

                onClick = {

                    onNotificationClick()
                }
            )

            ProfileItem(

                icon = Icons.Default.Person,

                title = "Account Settings",

                onClick = {

                    onSettingsClick()
                }
            )

            Spacer(
                modifier = Modifier.height(34.dp)
            )

            // =========================
            // LOGOUT BUTTON
            // =========================
            Button(

                onClick = {

                    onLogoutClick()
                },

                colors = ButtonDefaults.buttonColors(

                    containerColor =
                        Color(0xFFD99A3E)
                ),

                shape = RoundedCornerShape(24.dp),

                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp)
            ) {

                Icon(

                    imageVector =
                        Icons.AutoMirrored.Filled.ExitToApp,

                    contentDescription = null,

                    tint = Color.White
                )

                Spacer(
                    modifier = Modifier.width(10.dp)
                )

                Text(

                    text = "Logout",

                    color = Color.White,

                    fontWeight = FontWeight.Bold,

                    fontSize = 16.sp
                )
            }
        }

        Spacer(
            modifier = Modifier.height(30.dp)
        )
    }
}

@Composable
fun ProfileItem(

    icon: ImageVector,

    title: String,

    onClick: () -> Unit = {}
) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {

                onClick()
            },

        colors = CardDefaults.cardColors(

            containerColor =
                Color.White.copy(
                    alpha = 0.12f
                )
        ),

        shape = RoundedCornerShape(26.dp)
    ) {

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Icon(

                imageVector = icon,

                contentDescription = null,

                tint = Color(0xFFD99A3E)
            )

            Spacer(
                modifier = Modifier.width(16.dp)
            )

            Text(

                text = title,

                color = Color.White,

                fontWeight = FontWeight.Bold,

                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun StatisticItem(

    value: String,

    title: String
) {

    Card(

        modifier = Modifier.width(100.dp),

        colors = CardDefaults.cardColors(

            containerColor =
                Color.White.copy(
                    alpha = 0.1f
                )
        ),

        shape = RoundedCornerShape(24.dp)
    ) {

        Column(

            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),

            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {

            Text(

                text = value,

                color = Color.White,

                fontWeight = FontWeight.Bold,

                fontSize = 24.sp
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(

                text = title,

                color = Color.LightGray
            )
        }
    }
}