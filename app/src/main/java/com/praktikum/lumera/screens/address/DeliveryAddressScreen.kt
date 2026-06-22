package com.praktikum.lumera.screens.address

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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.praktikum.lumera.datastore.UserPreferences
import com.praktikum.lumera.model.AddressItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun DeliveryAddressScreen(
    userPreferences: UserPreferences,
    onBack: () -> Unit
) {

    // =========================
    // STATE
    // =========================
    val savedAddresses = remember {
        mutableStateListOf<AddressItem>()
    }

    val scope = rememberCoroutineScope()

    var showAddDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var selectedAddress by remember { mutableStateOf<AddressItem?>(null) }
    var newTitle by remember { mutableStateOf("") }
    var newAddress by remember { mutableStateOf("") }
    var nextId by remember { mutableStateOf(1) }

    // =========================
    // LOAD DARI DATASTORE
    // =========================
    LaunchedEffect(Unit) {

        val stored = userPreferences.getAddresses.first()

        if (stored.isEmpty()) {

            // Default awal kalau user belum pernah nyimpen apa-apa
            val defaults = listOf(
                AddressItem(1, "Office", "Jl. Slamet Riyadi No.99, Surakarta"),
                AddressItem(2, "Campus", "Universitas Sebelas Maret PSDKU Kebumen")
            )

            savedAddresses.addAll(defaults)

            userPreferences.saveAddresses(defaults)

        } else {

            savedAddresses.addAll(stored)
        }

        nextId = (savedAddresses.maxOfOrNull { it.id } ?: 0) + 1
    }

    // =========================
    // ADD DIALOG
    // =========================
    if (showAddDialog) {
        AlertDialog(
            onDismissRequest = {
                showAddDialog = false
                newTitle = ""
                newAddress = ""
            },
            containerColor = Color(0xFF1A0F0A),
            title = {
                Text(
                    text = "Add New Address",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = newTitle,
                        onValueChange = { newTitle = it },
                        label = { Text("Label (e.g. Home)", color = Color.LightGray) },
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
                        value = newAddress,
                        onValueChange = { newAddress = it },
                        label = { Text("Full Address", color = Color.LightGray) },
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
                        if (newTitle.isNotBlank() && newAddress.isNotBlank()) {
                            savedAddresses.add(
                                AddressItem(nextId++, newTitle.trim(), newAddress.trim())
                            )
                            scope.launch {
                                userPreferences.saveAddresses(savedAddresses)
                            }
                            newTitle = ""
                            newAddress = ""
                            showAddDialog = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD99A3E)
                    )
                ) {
                    Text("Save", color = Color.White)
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showAddDialog = false
                    newTitle = ""
                    newAddress = ""
                }) {
                    Text("Cancel", color = Color.LightGray)
                }
            }
        )
    }

    // =========================
    // EDIT DIALOG
    // =========================
    if (showEditDialog && selectedAddress != null) {
        AlertDialog(
            onDismissRequest = {
                showEditDialog = false
                selectedAddress = null
            },
            containerColor = Color(0xFF1A0F0A),
            title = {
                Text(
                    text = "Edit Address",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = newTitle,
                        onValueChange = { newTitle = it },
                        label = { Text("Label", color = Color.LightGray) },
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
                        value = newAddress,
                        onValueChange = { newAddress = it },
                        label = { Text("Full Address", color = Color.LightGray) },
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
                        val idx = savedAddresses.indexOfFirst { it.id == selectedAddress!!.id }
                        if (idx >= 0 && newTitle.isNotBlank() && newAddress.isNotBlank()) {
                            savedAddresses[idx] = AddressItem(
                                selectedAddress!!.id,
                                newTitle.trim(),
                                newAddress.trim()
                            )
                            scope.launch {
                                userPreferences.saveAddresses(savedAddresses)
                            }
                        }
                        showEditDialog = false
                        selectedAddress = null
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD99A3E)
                    )
                ) {
                    Text("Update", color = Color.White)
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showEditDialog = false
                    selectedAddress = null
                }) {
                    Text("Cancel", color = Color.LightGray)
                }
            }
        )
    }

    // =========================
    // DELETE CONFIRMATION DIALOG
    // =========================
    if (showDeleteDialog && selectedAddress != null) {
        AlertDialog(
            onDismissRequest = {
                showDeleteDialog = false
                selectedAddress = null
            },
            containerColor = Color(0xFF1A0F0A),
            title = {
                Text(
                    text = "Delete Address",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = "Hapus alamat \"${selectedAddress!!.title}\"?",
                    color = Color.LightGray
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        savedAddresses.removeIf { it.id == selectedAddress!!.id }
                        scope.launch {
                            userPreferences.saveAddresses(savedAddresses)
                        }
                        showDeleteDialog = false
                        selectedAddress = null
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red.copy(alpha = 0.8f)
                    )
                ) {
                    Text("Hapus", color = Color.White)
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDeleteDialog = false
                    selectedAddress = null
                }) {
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
                text = "Delivery Address",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )
        }

        Spacer(modifier = Modifier.height(34.dp))

        AddressCard(
            icon = Icons.Default.Home,
            title = "Home",
            subtitle = "Jl. Mawar No.12, Surakarta"
        )

        Spacer(modifier = Modifier.height(18.dp))

        AddressCard(
            icon = Icons.Default.LocationOn,
            title = "City",
            subtitle = "Central Java, Indonesia"
        )

        Spacer(modifier = Modifier.height(18.dp))

        AddressCard(
            icon = Icons.Default.Phone,
            title = "Phone Number",
            subtitle = "081234567890"
        )

        Spacer(modifier = Modifier.height(28.dp))

        // TOMBOL ADD ADDRESS - SUDAH BERFUNGSI
        Button(
            onClick = {
                newTitle = ""
                newAddress = ""
                showAddDialog = true
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD99A3E)
            )
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = Color.White
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(text = "Add New Address", color = Color.White)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Saved Addresses",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = "${savedAddresses.size} address",
                color = Color.LightGray,
                fontSize = 13.sp
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        LazyColumn {
            items(savedAddresses) { item ->
                SavedAddressCard(
                    title = item.title,
                    address = item.address,
                    onEdit = {
                        selectedAddress = item
                        newTitle = item.title
                        newAddress = item.address
                        showEditDialog = true
                    },
                    onDelete = {
                        selectedAddress = item
                        showDeleteDialog = true
                    }
                )
                Spacer(modifier = Modifier.height(14.dp))
            }
        }
    }
}

@Composable
fun AddressCard(
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
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.White
                )
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
                Text(text = subtitle, color = Color.LightGray)
            }
        }
    }
}

@Composable
fun SavedAddressCard(
    title: String,
    address: String,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.08f)
        )
    ) {

        Column(modifier = Modifier.padding(20.dp)) {

            Text(
                text = title,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(text = address, color = Color.LightGray)

            Spacer(modifier = Modifier.height(14.dp))

            Row {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = Color(0xFFD99A3E),
                    modifier = Modifier.clickable { onEdit() }
                )

                Spacer(modifier = Modifier.padding(12.dp))

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