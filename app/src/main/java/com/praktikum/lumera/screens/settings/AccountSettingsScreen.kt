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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AccountSettingsScreen(
    onBack: () -> Unit,
    onLogout: () -> Unit = {}
) {
    var showLogoutDialog by remember { mutableStateOf(false) }

    // =========================
    // LOGOUT CONFIRMATION DIALOG
    // =========================
    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            containerColor = Color(0xFF1A0F0A),
            title = {
                Text(
                    text = "Konfirmasi Logout",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = "Apakah kamu yakin ingin keluar dari akun ini?",
                    color = Color.LightGray
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showLogoutDialog = false
                        onLogout()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red.copy(alpha = 0.8f)
                    )
                ) {
                    Text("Logout", color = Color.White, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) {
                    Text("Batal", color = Color.LightGray)
                }
            }
        )
    }

    // =========================
    // MAIN UI
    // =========================
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
            .verticalScroll(rememberScrollState())
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        // HEADER
        Row(verticalAlignment = Alignment.CenterVertically) {

            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color(0xFFD99A3E))
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.padding(8.dp))

            Text(
                text = "Account Settings",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ACCOUNT SUMMARY
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFD99A3E).copy(alpha = 0.2f)
            )
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "My Account",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Manage your profile and preferences.",
                    color = Color.LightGray
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        SettingsCard(
            icon = Icons.Default.Person,
            title = "Edit Profile",
            subtitle = "Change your username and email"
        )
        Spacer(modifier = Modifier.height(18.dp))

        SettingsCard(
            icon = Icons.Default.Lock,
            title = "Change Password",
            subtitle = "Update your account password"
        )
        Spacer(modifier = Modifier.height(18.dp))

        SettingsCard(
            icon = Icons.Default.Home,
            title = "Dark Mode",
            subtitle = "Enable premium dark appearance"
        )
        Spacer(modifier = Modifier.height(18.dp))

        SettingsCard(
            icon = Icons.Default.Notifications,
            title = "Language",
            subtitle = "English / Indonesia"
        )
        Spacer(modifier = Modifier.height(18.dp))

        SettingsCard(
            icon = Icons.Default.Notifications,
            title = "Notification Settings",
            subtitle = "Manage app notifications"
        )
        Spacer(modifier = Modifier.height(18.dp))

        SettingsCard(
            icon = Icons.Default.Lock,
            title = "Privacy & Security",
            subtitle = "Protect your account information"
        )
        Spacer(modifier = Modifier.height(18.dp))

        SettingsCard(
            icon = Icons.Default.Person,
            title = "Help Center",
            subtitle = "FAQ and customer support"
        )

        Spacer(modifier = Modifier.height(24.dp))

        // TOMBOL LOGOUT - SUDAH BERFUNGSI
        Button(
            onClick = { showLogoutDialog = true },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red.copy(alpha = 0.8f)
            )
        ) {
            Text(
                text = "Logout",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun SettingsCard(
    icon: ImageVector,
    title: String,
    subtitle: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.08f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color(0xFFD99A3E))
                    .padding(14.dp)
            ) {
                Icon(imageVector = icon, contentDescription = null, tint = Color.White)
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Column {
                Text(
                    text = title,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = subtitle, color = Color.LightGray, fontSize = 13.sp)
            }
        }
    }
}