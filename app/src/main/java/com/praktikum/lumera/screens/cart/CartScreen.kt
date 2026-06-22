package com.praktikum.lumera.screens.cart

import androidx.compose.foundation.Image
import coil.compose.AsyncImage
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.praktikum.lumera.model.CartItem
import com.praktikum.lumera.utils.formatRupiah
import com.praktikum.lumera.viewmodel.CartViewModel

@Composable
fun CartScreen(

    cart: SnapshotStateList<CartItem>,

    cartViewModel: CartViewModel,

    onBack: () -> Unit,

    onCheckout: (String, Int) -> Unit
) {

    val subtotal = cartViewModel.getTotal()

    // =========================
    // STATE
    // =========================
    var selectedOrderType by remember {
        mutableStateOf("Dine-in / Pickup")
    }

    var selectedPayment by remember {
        mutableStateOf("Cash")
    }

    var showPaymentDialog by remember {
        mutableStateOf(false)
    }

    // =========================
    // DELIVERY FEE
    // =========================
    val deliveryFee =
        if (selectedOrderType == "Delivery")
            12000
        else
            0

    // =========================
    // TOTAL
    // =========================
    val total =
        subtotal + deliveryFee

    // =========================
    // PAYMENT DIALOG
    // =========================
    if (showPaymentDialog) {

        AlertDialog(

            onDismissRequest = {
                showPaymentDialog = false
            },

            title = {
                Text("Select Payment")
            },

            text = {

                Column {

                    // CASH
                    Card(

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp),

                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFFF3E0)
                        )
                    ) {

                        TextButton(

                            onClick = {

                                selectedPayment = "Cash"
                                showPaymentDialog = false
                            }
                        ) {

                            Text("💵 Cash")
                        }
                    }

                    // QRIS
                    Card(

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp),

                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFFF3E0)
                        )
                    ) {

                        TextButton(

                            onClick = {

                                selectedPayment = "QRIS"
                                showPaymentDialog = false
                            }
                        ) {

                            Text("📱 QRIS")
                        }
                    }

                    // DEBIT
                    Card(

                        modifier = Modifier
                            .fillMaxWidth(),

                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFFF3E0)
                        )
                    ) {

                        TextButton(

                            onClick = {

                                selectedPayment = "Debit"
                                showPaymentDialog = false
                            }
                        ) {

                            Text("💳 Debit")
                        }
                    }
                }
            },

            confirmButton = {

                TextButton(

                    onClick = {

                        showPaymentDialog = false
                    }
                ) {

                    Text("Close")
                }
            }
        )
    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F3EE))
            .padding(20.dp)

    ) {

        // =========================
        // TOP BAR
        // =========================
        Row(

            modifier = Modifier.fillMaxWidth(),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            IconButton(
                onClick = onBack
            ) {

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Column {

                Text(
                    text = "My Cart",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "${cart.size} items selected",
                    color = Color.Gray,
                    fontSize = 13.sp
                )
            }
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        // =========================
        // CART LIST
        // =========================
        if (cart.isEmpty()) {

            Column(

                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),

                horizontalAlignment =
                    Alignment.CenterHorizontally,

                verticalArrangement =
                    Arrangement.Center
            ) {

                Text(
                    text = "☕",
                    fontSize = 60.sp
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = "Your cart is empty",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Text(
                    text = "Add your favorite coffee first",
                    color = Color.Gray
                )
            }

        } else {

            LazyColumn(

                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),

                verticalArrangement =
                    Arrangement.spacedBy(14.dp)
            ) {

                items(cart) { item ->

                    CartItemCard(

                        item = item,

                        cartViewModel = cartViewModel
                    )
                }

                item {

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        // =========================
        // ORDER TYPE
        // =========================
        Text(

            text = "Order Type",

            fontWeight = FontWeight.Bold,

            fontSize = 18.sp
        )

        Spacer(
            modifier = Modifier.height(14.dp)
        )

        Row(

            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement =
                Arrangement.spacedBy(12.dp)
        ) {

            OutlinedButton(

                onClick = {

                    selectedOrderType =
                        "Dine-in / Pickup"
                },

                modifier = Modifier.weight(1f),

                shape = RoundedCornerShape(16.dp),

                colors = ButtonDefaults.outlinedButtonColors(

                    containerColor =

                        if (selectedOrderType ==
                            "Dine-in / Pickup"
                        )
                            Color(0xFFE9D6B8)
                        else
                            Color.White
                )
            ) {

                Text(
                    text = "☕ Pickup"
                )
            }

            OutlinedButton(

                onClick = {

                    selectedOrderType =
                        "Delivery"
                },

                modifier = Modifier.weight(1f),

                shape = RoundedCornerShape(16.dp),

                colors = ButtonDefaults.outlinedButtonColors(

                    containerColor =

                        if (selectedOrderType ==
                            "Delivery"
                        )
                            Color(0xFFE9D6B8)
                        else
                            Color.White
                )
            ) {

                Text(
                    text = "🚚 Delivery"
                )
            }
        }

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        // =========================
        // PAYMENT METHOD
        // =========================
        Card(

            modifier = Modifier
                .fillMaxWidth()
                .clickable {

                    showPaymentDialog = true
                },

            shape = RoundedCornerShape(18.dp),

            colors = CardDefaults.cardColors(

                containerColor = Color.White
            )
        ) {

            Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),

                horizontalArrangement =
                    Arrangement.SpaceBetween,

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Text(

                    text = "💳 $selectedPayment",

                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        // =========================
        // PAYMENT SUMMARY CARD
        // =========================
        Card(

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(22.dp),

            colors = CardDefaults.cardColors(

                containerColor = Color.White
            )
        ) {

            Column(

                modifier = Modifier.padding(18.dp)
            ) {

                Text(

                    text = "Payment Summary",

                    fontWeight = FontWeight.Bold,

                    fontSize = 18.sp
                )

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                PriceRow(
                    "Subtotal",
                    formatRupiah(subtotal)
                )

                PriceRow(
                    "Delivery Fee",
                    formatRupiah(deliveryFee)
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                HorizontalDivider()

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Row(

                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement =
                        Arrangement.SpaceBetween
                ) {

                    Text(

                        text = "Total Payment",

                        fontWeight = FontWeight.Bold,

                        fontSize = 20.sp
                    )

                    Text(

                        text = formatRupiah(total),

                        fontWeight = FontWeight.Bold,

                        fontSize = 20.sp,

                        color = Color(0xFFC68642)
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        // =========================
        // CHECKOUT BUTTON
        // =========================
        Button(

            onClick = {

                onCheckout(
                    selectedPayment,
                    total
                )
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),

            shape = RoundedCornerShape(20.dp),

            colors = ButtonDefaults.buttonColors(

                containerColor =
                    Color(0xFFC68642)
            )
        ) {

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceBetween,

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Column {

                    Text(
                        text = "Total",
                        color = Color.White,
                        fontSize = 12.sp
                    )

                    Text(
                        text = formatRupiah(total),
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    text = "Place Order →",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

            }

        }
    }
}

@Composable
fun PriceRow(

    title: String,

    value: String
) {

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),

        horizontalArrangement =
            Arrangement.SpaceBetween
    ) {

        Text(
            text = title
        )

        Text(
            text = value,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun CartItemCard(

    item: CartItem,

    cartViewModel: CartViewModel
) {

    // =========================
    // DELETE DIALOG STATE
    // =========================
    var showDeleteDialog by remember {

        mutableStateOf(false)
    }

    // =========================
    // DELETE DIALOG
    // =========================
    if (showDeleteDialog) {

        AlertDialog(

            onDismissRequest = {

                showDeleteDialog = false
            },

            title = {

                Text(
                    text = "Hapus Item"
                )
            },

            text = {

                Text(
                    text =
                        "Yakin menghapus item ini?"
                )
            },

            confirmButton = {

                TextButton(

                    onClick = {

                        cartViewModel.removeFromCart(
                            item
                        )

                        showDeleteDialog = false
                    }
                ) {

                    Text("Ya")
                }
            },

            dismissButton = {

                TextButton(

                    onClick = {

                        showDeleteDialog = false
                    }
                ) {

                    Text("Tidak")
                }
            }
        )
    }

    Spacer(
        modifier = Modifier.height(20.dp)
    )

    // =========================
    // CARD
    // =========================
    Card(

        modifier = Modifier
            .fillMaxWidth(),

        shape = RoundedCornerShape(24.dp),

        colors = CardDefaults.cardColors(

            containerColor =
                Color.White
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),

            horizontalArrangement =
                Arrangement.SpaceBetween,

            verticalAlignment =
                Alignment.Top
        ) {

            // =========================
            // MENU IMAGE
            // =========================
            if (item.menu.imageUrl.isNotBlank()) {

                // Gambar dari server (data API), Week 11
                AsyncImage(
                    model = item.menu.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(90.dp)
                        .clip(
                            RoundedCornerShape(18.dp)
                        ),
                    contentScale = ContentScale.Crop
                )

            } else {

                Image(

                    painter = painterResource(
                        id = item.menu.image
                    ),

                    contentDescription = null,

                    modifier = Modifier
                        .size(90.dp)
                        .clip(
                            RoundedCornerShape(18.dp)
                        ),

                    contentScale = ContentScale.Crop
                )
            }

            Spacer(
                modifier = Modifier.width(14.dp)
            )

            // =========================
            // LEFT CONTENT
            // =========================
            Column(

                modifier = Modifier.weight(1f)
            ) {

                // =========================
                // MENU NAME
                // =========================
                Text(

                    text = item.menu.name,

                    fontWeight =
                        FontWeight.Bold,

                    fontSize = 18.sp
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                // =========================
                // CUSTOMIZATION
                // =========================
                if (

                    item.menu.category == "Coffee"
                    &&
                    item.size.isNotEmpty()
                ) {

                    Text(

                        text =
                            "${item.size} • ${item.ice} • ${item.sugar}",

                        color = Color.Gray,

                        fontSize = 13.sp
                    )
                }

                // =========================
                // EXTRA SHOT
                // =========================
                if (item.extraShot) {

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFF5E6D3))
                            .padding(
                                horizontal = 10.dp,
                                vertical = 6.dp
                            )
                    ) {

                        Text(
                            text = "Extra Shot",
                            color = Color(0xFFC68642),
                            fontSize = 12.sp
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                // =========================
                // QUANTITY
                // =========================
                Row(

                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement =
                        Arrangement.SpaceBetween,

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Text(

                        text = formatRupiah(
                            (item.menu.price + item.customPrice)
                                    * item.quantity
                        ),

                        color = Color(0xFFC68642),

                        fontWeight = FontWeight.Bold,

                        fontSize = 18.sp
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFE9D6B8))
                                .clickable {
                                    cartViewModel.decreaseQuantity(item)
                                },

                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                text = "-",
                                color = Color(0xFF7A4E1D),
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        }

                        Spacer(
                            modifier = Modifier.width(10.dp)
                        )

                        Text(
                            text = item.quantity.toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )

                        Spacer(
                            modifier = Modifier.width(10.dp)
                        )

                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFC68642))
                                .clickable {
                                    cartViewModel.increaseQuantity(item)
                                },

                            contentAlignment = Alignment.Center
                        ) {

                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }

        // =========================
        // DELETE BUTTON
        // =========================
        Box(

            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0xFFF5F0E8))
                .clickable {
                    showDeleteDialog = true
                },

            contentAlignment = Alignment.Center
        ) {

            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null,
                tint = Color(0xFFC68642)
            )
        }
    }
}