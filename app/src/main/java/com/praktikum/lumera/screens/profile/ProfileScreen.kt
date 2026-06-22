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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
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
import com.praktikum.lumera.data.SessionManager
import com.praktikum.lumera.datastore.UserPreferences

@Composable
fun ProfileScreen(

    userPreferences: UserPreferences,

    onBackClick: () -> Unit,

    onLogoutClick: () -> Unit,

    onHistoryClick: () -> Unit,

    onPaymentMethodClick: () -> Unit,

    onNotificationClick: () -> Unit,

    onSettingsClick: () -> Unit,

    onAddressClick: () -> Unit,

    // TODO: ganti default 0 ini dengan data asli dari CartViewModel/OrderViewModel
    // begitu data transaksi & favorit customer sudah tersedia
    orderCount: Int = 0,

    favoriteCount: Int = 0

) {

    // Pakai SessionManager (di-update buat SEMUA role saat login),
    // bukan userPreferences.getUser (cuma ke-update pas Customer register/login)
    val user = SessionManager.currentUser.value
    val roleBadge = when (user?.role) {

        "Admin" -> "Administrator"

        "Kasir" -> "Cashier"

        else -> "Member"
    }

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

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {

            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clip(CircleShape)
                    .background(Color(0xFFD99A3E))
            ) {

                Icon(
                    imageVector =
                        Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }

            Text(
                text = "Profile",

                modifier = Modifier.align(
                    Alignment.Center
                ),

                color = Color.White,

                fontWeight = FontWeight.Bold,

                fontSize = 26.sp
            )
        }

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        Card(

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(32.dp),

            colors = CardDefaults.cardColors(

                containerColor =
                    Color(0xFF2A1A12)
            )
        ) {

            Column(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),

                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {

                Box(

                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFD99A3E)),

                    contentAlignment =
                        Alignment.Center
                ) {

                    Text(

                        text =
                            user?.name
                                ?.firstOrNull()
                                ?.uppercase()
                                ?: "G",

                        color = Color.White,

                        fontSize = 42.sp,

                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Text(

                    text =
                        user?.name ?: "Guest",

                    color = Color.White,

                    fontWeight = FontWeight.ExtraBold,

                    fontSize = 30.sp
                )

                Text(

                    text =
                        user?.email ?: "",

                    color = Color.LightGray
                )

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                Surface(

                    color = Color(0xFFD99A3E),

                    shape = RoundedCornerShape(50.dp)
                ) {

                    Text(

                        text = roleBadge,

                        modifier = Modifier.padding(

                            horizontal = 22.dp,
                            vertical = 10.dp
                        ),

                        color = Color.White,

                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        when (user?.role) {

            "Admin" -> {

                RoleCard(
                    title = "Administrator",
                    value = "System Control",
                    subtitle = "Manage products, users and reports"
                )
            }

            "Kasir" -> {

                RoleCard(
                    title = "Cashier Dashboard",
                    value = "Ready To Serve",
                    subtitle = "Manage customer orders efficiently"
                )
            }

            else -> {

                RoleCard(
                    title = "Customer",
                    value = "Lumera Member",
                    subtitle = "Enjoy your coffee experience"
                )
            }
        }

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        Card(

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(24.dp),

            colors = CardDefaults.cardColors(

                containerColor =
                    Color.White.copy(alpha = 0.05f)
            )
        ) {

            Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),

                horizontalArrangement =
                    Arrangement.SpaceEvenly
            ) {

                when (user?.role) {

                    "Admin" -> {

                        StatisticColumn(
                            "18",
                            "Customers"
                        )

                        StatisticColumn(
                            "5",
                            "Cashiers"
                        )
                    }

                    "Kasir" -> {

                        StatisticColumn(
                            "08:00",
                            "Start"
                        )

                        StatisticColumn(
                            "16:00",
                            "Finish"
                        )
                    }

                    else -> {

                        StatisticColumn(
                            orderCount.toString(),
                            "Orders"
                        )

                        StatisticColumn(
                            favoriteCount.toString(),
                            "Favorites"
                        )
                    }
                }
            }
        }

        Spacer(
            modifier = Modifier.height(28.dp)
        )

        when (user?.role) {

            "Admin" -> {

                ProfileMenuItem(
                    Icons.Default.History,
                    "Sales Report",
                    onHistoryClick
                )

                ProfileMenuItem(
                    Icons.Default.Payment,
                    "Manage Products",
                    onPaymentMethodClick
                )

                ProfileMenuItem(
                    Icons.Default.Notifications,
                    "Manage Users",
                    onNotificationClick
                )

                ProfileMenuItem(
                    Icons.Default.LocationOn,
                    "Address",
                    onAddressClick
                )

                ProfileMenuItem(
                    Icons.Default.Settings,
                    "System Settings",
                    onSettingsClick
                )
            }

            "Kasir" -> {

                ProfileMenuItem(
                    Icons.Default.History,
                    "Transaction History",
                    onHistoryClick
                )

                ProfileMenuItem(
                    Icons.Default.Payment,
                    "Today's Orders",
                    onPaymentMethodClick
                )

                ProfileMenuItem(
                    Icons.Default.Notifications,
                    "Notifications",
                    onNotificationClick
                )

                ProfileMenuItem(
                    Icons.Default.LocationOn,
                    "Address",
                    onAddressClick
                )

                ProfileMenuItem(
                    Icons.Default.Settings,
                    "Shift Settings",
                    onSettingsClick
                )
            }

            else -> {

                ProfileMenuItem(
                    Icons.Default.History,
                    "Order History",
                    onHistoryClick
                )

                ProfileMenuItem(
                    Icons.Default.Payment,
                    "Payment Method",
                    onPaymentMethodClick
                )

                ProfileMenuItem(
                    Icons.Default.Notifications,
                    "Notifications",
                    onNotificationClick
                )

                ProfileMenuItem(
                    Icons.Default.LocationOn,
                    "Delivery Address",
                    onAddressClick
                )

                ProfileMenuItem(
                    Icons.Default.Settings,
                    "Account Settings",
                    onSettingsClick
                )
            }
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Button(

            onClick = onLogoutClick,

            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),

            shape = RoundedCornerShape(20.dp),

            colors = ButtonDefaults.buttonColors(

                containerColor =
                    Color(0xFFD99A3E)
            )
        ) {

            Icon(

                imageVector =
                    Icons.AutoMirrored.Filled.ExitToApp,

                contentDescription = null,

                tint = Color.White
            )

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Text(

                text = "Logout",

                color = Color.White,

                fontWeight = FontWeight.Bold
            )
        }

        Spacer(
            modifier = Modifier.height(30.dp)
        )
    }
}

@Composable
fun ProfileMenuItem(

    icon: ImageVector,

    title: String,

    onClick: () -> Unit
) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable {

                onClick()
            },

        colors = CardDefaults.cardColors(

            containerColor =
                Color.White.copy(alpha = 0.08f)
        ),

        shape = RoundedCornerShape(20.dp)
    ) {

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),

            horizontalArrangement =
                Arrangement.SpaceBetween,

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Row(

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Icon(

                    imageVector = icon,

                    contentDescription = null,

                    tint = Color(0xFFD99A3E)
                )

                Spacer(
                    modifier = Modifier.width(14.dp)
                )

                Text(

                    text = title,

                    color = Color.White,

                    fontWeight = FontWeight.Medium
                )
            }

            Icon(

                imageVector =
                    Icons.AutoMirrored.Filled.ArrowForward,

                contentDescription = null,

                tint = Color.Gray
            )
        }
    }
}

@Composable
fun RoleCard(

    title: String,

    value: String,

    subtitle: String
) {

    Card(

        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(28.dp),

        colors = CardDefaults.cardColors(

            containerColor =
                Color(0xFF2A1A12)
        )
    ) {

        Column(

            modifier = Modifier.padding(24.dp)
        ) {

            Text(

                text = title,

                color = Color(0xFFD99A3E),

                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(

                text = value,

                color = Color.White,

                fontSize = 36.sp,

                fontWeight = FontWeight.ExtraBold
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(

                text = subtitle,

                color = Color.LightGray
            )
        }
    }
}

@Composable
fun StatisticColumn(

    value: String,

    title: String
) {

    Column(

        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {

        Text(

            text = value,

            color = Color.White,

            fontSize = 24.sp,

            fontWeight = FontWeight.Bold
        )

        Text(

            text = title,

            color = Color.LightGray
        )
    }
}