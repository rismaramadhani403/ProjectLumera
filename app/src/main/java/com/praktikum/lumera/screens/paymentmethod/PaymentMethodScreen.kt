package com.praktikum.lumera.screens.paymentmethod

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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

data class CustomPaymentMethod(
    val id: Int,
    val name: String,
    val description: String
)

@Composable
fun PaymentMethodScreen(
    onBack: () -> Unit
) {
    var selectedMethod by remember { mutableStateOf("Cash") }

    val customMethods = remember {
        mutableStateListOf<CustomPaymentMethod>()
    }

    var showAddDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var selectedCustom by remember { mutableStateOf<CustomPaymentMethod?>(null) }
    var newMethodName by remember { mutableStateOf("") }
    var newMethodDesc by remember { mutableStateOf("") }
    var nextId by remember { mutableStateOf(1) }

    // =========================
    // ADD DIALOG
    // =========================
    if (showAddDialog) {
        AlertDialog(
            onDismissRequest = {
                showAddDialog = false
                newMethodName = ""
                newMethodDesc = ""
            },
            containerColor = Color(0xFF1A0F0A),
            title = {
                Text(
                    text = "Add Payment Method",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = newMethodName,
                        onValueChange = { newMethodName = it },
                        label = { Text("Method Name (e.g. BCA Virtual Account)", color = Color.LightGray) },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFD99A3E),
                            unfocusedBorderColor = Color.Gray,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedTextField(
                        value = newMethodDesc,
                        onValueChange = { newMethodDesc = it },
                        label = { Text("Description", color = Color.LightGray) },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFD99A3E),
                            unfocusedBorderColor = Color.Gray,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (newMethodName.isNotBlank()) {
                            customMethods.add(
                                CustomPaymentMethod(
                                    id = nextId++,
                                    name = newMethodName.trim(),
                                    description = newMethodDesc.trim().ifBlank { "Custom payment method" }
                                )
                            )
                            newMethodName = ""
                            newMethodDesc = ""
                            showAddDialog = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD99A3E))
                ) {
                    Text("Save", color = Color.White)
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showAddDialog = false
                    newMethodName = ""
                    newMethodDesc = ""
                }) {
                    Text("Cancel", color = Color.LightGray)
                }
            }
        )
    }

    // =========================
    // DELETE DIALOG
    // =========================
    if (showDeleteDialog && selectedCustom != null) {
        AlertDialog(
            onDismissRequest = {
                showDeleteDialog = false
                selectedCustom = null
            },
            containerColor = Color(0xFF1A0F0A),
            title = {
                Text("Hapus Metode Pembayaran", color = Color.White, fontWeight = FontWeight.Bold)
            },
            text = {
                Text(
                    text = "Hapus \"${selectedCustom!!.name}\"?",
                    color = Color.LightGray
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        customMethods.removeIf { it.id == selectedCustom!!.id }
                        if (selectedMethod == selectedCustom!!.name) selectedMethod = "Cash"
                        showDeleteDialog = false
                        selectedCustom = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red.copy(alpha = 0.8f))
                ) {
                    Text("Hapus", color = Color.White)
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDeleteDialog = false
                    selectedCustom = null
                }) {
                    Text("Batal", color = Color.LightGray)
                }
            }
        )
    }

    // =========================
    // MAIN UI
    // =========================
    LazyColumn(
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
        item {
            Spacer(modifier = Modifier.height(30.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = { onBack() },
                    modifier = Modifier.clip(CircleShape).background(Color(0xFFD99A3E))
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = "Payment Method",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFD99A3E).copy(alpha = 0.2f))
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Available Payment Methods",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Choose your preferred payment method.", color = Color.LightGray)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            PaymentCard(
                icon = Icons.Default.AttachMoney,
                title = "Cash",
                subtitle = "Pay directly at cashier",
                isSelected = selectedMethod == "Cash",
                onClick = { selectedMethod = "Cash" }
            )
            Spacer(modifier = Modifier.height(18.dp))
            PaymentCard(
                icon = Icons.Default.QrCode,
                title = "QRIS",
                subtitle = "Fast digital payment",
                isSelected = selectedMethod == "QRIS",
                onClick = { selectedMethod = "QRIS" }
            )
            Spacer(modifier = Modifier.height(18.dp))
            PaymentCard(
                icon = Icons.Default.CreditCard,
                title = "Debit Card",
                subtitle = "Visa / Mastercard supported",
                isSelected = selectedMethod == "Debit Card",
                onClick = { selectedMethod = "Debit Card" }
            )
            Spacer(modifier = Modifier.height(18.dp))
            PaymentCard(
                icon = Icons.Default.CreditCard,
                title = "Credit Card",
                subtitle = "Secure online transaction",
                isSelected = selectedMethod == "Credit Card",
                onClick = { selectedMethod = "Credit Card" }
            )
            Spacer(modifier = Modifier.height(18.dp))
            PaymentCard(
                icon = Icons.Default.AccountBalanceWallet,
                title = "E-Wallet",
                subtitle = "OVO, DANA, GoPay & ShopeePay",
                isSelected = selectedMethod == "E-Wallet",
                onClick = { selectedMethod = "E-Wallet" }
            )

            if (customMethods.isNotEmpty()) {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Custom Methods",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        // Custom methods list - deletable
        items(customMethods) { method ->
            CustomPaymentCard(
                title = method.name,
                subtitle = method.description,
                isSelected = selectedMethod == method.name,
                onClick = { selectedMethod = method.name },
                onDelete = {
                    selectedCustom = method
                    showDeleteDialog = true
                }
            )
            Spacer(modifier = Modifier.height(18.dp))
        }

        item {
            Spacer(modifier = Modifier.height(6.dp))

            PaymentDetailCard(selectedMethod = selectedMethod)

            Spacer(modifier = Modifier.height(24.dp))

            // TOMBOL ADD PAYMENT METHOD - SUDAH BERFUNGSI
            Button(
                onClick = {
                    newMethodName = ""
                    newMethodDesc = ""
                    showAddDialog = true
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD99A3E))
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = Color.White)
                Spacer(modifier = Modifier.padding(4.dp))
                Text(text = "Add Payment Method", color = Color.White)
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun CustomPaymentCard(
    title: String,
    subtitle: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.08f))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier.clip(CircleShape).background(Color(0xFFD99A3E)).padding(14.dp)
                ) {
                    Icon(Icons.Default.AccountBalanceWallet, contentDescription = null, tint = Color.White)
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Column {
                    Text(text = title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = subtitle, color = Color.LightGray, fontSize = 13.sp)
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (isSelected) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50.dp))
                            .background(Color(0xFFD99A3E))
                            .padding(horizontal = 14.dp, vertical = 6.dp)
                    ) {
                        Text(text = "Selected", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                    Spacer(modifier = Modifier.padding(4.dp))
                }
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.Red,
                    modifier = Modifier.clickable { onDelete() }
                )
            }
        }
    }
}

@Composable
fun PaymentDetailCard(selectedMethod: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.08f))
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = "$selectedMethod Payment",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            when (selectedMethod) {
                "Cash" -> Text(text = "Pay directly at cashier.", color = Color.LightGray)
                "QRIS" -> {
                    Text(text = "Scan QRIS below.", color = Color.LightGray)
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .background(Color.White, RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "QRIS CODE", color = Color.Black)
                    }
                }
                "Debit Card" -> Text(text = "Supported: Visa, Mastercard", color = Color.LightGray)
                "Credit Card" -> Text(text = "Secure payment transaction.", color = Color.LightGray)
                "E-Wallet" -> Text(text = "Available: OVO, DANA, GoPay, ShopeePay", color = Color.LightGray)
                else -> Text(text = "Custom payment method selected.", color = Color.LightGray)
            }
        }
    }
}

@Composable
fun PaymentCard(
    icon: ImageVector,
    title: String,
    subtitle: String,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.08f))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier.clip(CircleShape).background(Color(0xFFD99A3E)).padding(14.dp)
                ) {
                    Icon(imageVector = icon, contentDescription = null, tint = Color.White)
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Column {
                    Text(text = title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = subtitle, color = Color.LightGray, fontSize = 13.sp)
                }
            }
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .background(Color(0xFFD99A3E))
                        .padding(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    Text(text = "Selected", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
            }
        }
    }
}