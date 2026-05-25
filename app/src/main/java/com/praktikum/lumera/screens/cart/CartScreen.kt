package com.praktikum.lumera.screens.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.graphics.Color
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

    var selectedPromo by remember {
        mutableStateOf("No Promo")
    }

    var showPaymentDialog by remember {
        mutableStateOf(false)
    }

    var showPromoDialog by remember {
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
    // PROMO DISCOUNT
    // =========================
    val discount =
        when (selectedPromo) {

            "DISKON10" -> subtotal * 10 / 100

            "DISKON20" -> subtotal * 20 / 100

            else -> 0
        }

    // =========================
    // TOTAL
    // =========================
    val total =
        subtotal + deliveryFee - discount

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

    // =========================
    // PROMO DIALOG
    // =========================
    if (showPromoDialog) {

        AlertDialog(

            onDismissRequest = {
                showPromoDialog = false
            },

            title = {
                Text("Available Promo")
            },

            text = {

                Column {

                    // PROMO 10%
                    Card(

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),

                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFFF3E0)
                        )
                    ) {

                        Column(
                            modifier = Modifier.padding(14.dp)
                        ) {

                            Text(

                                text = "DISKON10",

                                fontWeight = FontWeight.Bold,

                                color = Color(0xFFC68642)
                            )

                            Spacer(
                                modifier = Modifier.height(4.dp)
                            )

                            Text(
                                text =
                                    "Diskon 10% untuk pengguna pertama"
                            )

                            Spacer(
                                modifier = Modifier.height(10.dp)
                            )

                            Button(

                                onClick = {

                                    selectedPromo = "DISKON10"
                                    showPromoDialog = false
                                },

                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFC68642)
                                )
                            ) {

                                Text("Gunakan")
                            }
                        }
                    }

                    // PROMO 20%
                    Card(

                        modifier = Modifier
                            .fillMaxWidth(),

                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFFF3E0)
                        )
                    ) {

                        Column(
                            modifier = Modifier.padding(14.dp)
                        ) {

                            Text(

                                text = "DISKON20",

                                fontWeight = FontWeight.Bold,

                                color = Color(0xFFC68642)
                            )

                            Spacer(
                                modifier = Modifier.height(4.dp)
                            )

                            Text(
                                text =
                                    "Diskon 20% spesial ulang tahun"
                            )

                            Spacer(
                                modifier = Modifier.height(10.dp)
                            )

                            Button(

                                onClick = {

                                    selectedPromo = "DISKON20"
                                    showPromoDialog = false
                                },

                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFC68642)
                                )
                            ) {

                                Text("Gunakan")
                            }
                        }
                    }
                }
            },

            confirmButton = {

                TextButton(

                    onClick = {

                        selectedPromo = "No Promo"
                        showPromoDialog = false
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
                    imageVector =
                        Icons.Default.ArrowBack,

                    contentDescription = null
                )
            }

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Text(

                text = "Cart",

                fontSize = 26.sp,

                fontWeight = FontWeight.Bold
            )
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        // =========================
        // CART LIST
        // =========================
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

                    cartViewModel =
                        cartViewModel
                )
            }

            item {

                Spacer(
                    modifier = Modifier.height(10.dp)
                )
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
                    text = "Dine-in / Pickup"
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
                    text = "Delivery"
                )
            }
        }

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        // =========================
        // PAYMENT + PROMO
        // =========================
        Row(

            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement =
                Arrangement.spacedBy(12.dp)
        ) {

            Button(

                onClick = {

                    showPaymentDialog = true
                },

                modifier = Modifier
                    .weight(1f)
                    .height(52.dp),

                shape = RoundedCornerShape(18.dp),

                colors = ButtonDefaults.buttonColors(

                    containerColor =
                        Color(0xFFE9D6B8)
                )
            ) {

                Text(

                    text = selectedPayment,

                    color = Color(0xFF7A4E1D),

                    fontWeight = FontWeight.Bold
                )
            }

            Button(

                onClick = {

                    showPromoDialog = true
                },

                modifier = Modifier
                    .weight(1f)
                    .height(52.dp),

                shape = RoundedCornerShape(18.dp),

                colors = ButtonDefaults.buttonColors(

                    containerColor =
                        Color(0xFFE9D6B8)
                )
            ) {

                Text(

                    text = selectedPromo,

                    color = Color(0xFF7A4E1D),

                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        // =========================
        // PRICE DETAIL
        // =========================
        PriceRow(
            "Subtotal",
            formatRupiah(subtotal)
        )

        PriceRow(
            "Delivery Fee",
            formatRupiah(deliveryFee)
        )

        PriceRow(
            "Discount",
            "- ${formatRupiah(discount)}"
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Divider()

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        // =========================
        // TOTAL
        // =========================
        Row(

            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement =
                Arrangement.SpaceBetween
        ) {

            Text(

                text = "Total",

                fontWeight = FontWeight.Bold,

                fontSize = 22.sp
            )

            Text(

                text = formatRupiah(total),

                fontWeight = FontWeight.Bold,

                fontSize = 22.sp,

                color = Color(0xFFC68642)
            )
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
        ){

            Text(

                text = "Checkout",

                color = Color.White,

                fontSize = 18.sp,

                fontWeight = FontWeight.Bold
            )
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

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(

                        text = "Extra Shot",

                        color = Color(0xFFC68642),

                        fontWeight =
                            FontWeight.SemiBold,

                        fontSize = 13.sp
                    )
                }

                // =========================
                // NOTES
                // =========================
                if (item.notes.isNotEmpty()) {

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(

                        text = item.notes,

                        color = Color.Gray,

                        fontSize = 12.sp
                    )
                }

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                // =========================
                // QUANTITY
                // =========================
                Row(

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    // MINUS
                    IconButton(

                        onClick = {

                            cartViewModel
                                .decreaseQuantity(
                                    item
                                )
                        }
                    ) {

                        Text(

                            text = "-",

                            fontSize = 22.sp
                        )
                    }

                    // QUANTITY TEXT
                    Text(

                        text =
                            item.quantity.toString(),

                        fontWeight =
                            FontWeight.Bold,

                        fontSize = 18.sp
                    )

                    // PLUS
                    IconButton(

                        onClick = {

                            cartViewModel
                                .increaseQuantity(
                                    item
                                )
                        }
                    ) {

                        Text(

                            text = "+",

                            fontSize = 22.sp
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                // =========================
                // PRICE
                // =========================
                Text(

                    text = formatRupiah(

                        item.menu.price *
                                item.quantity
                    ),

                    color = Color(0xFFC68642),

                    fontWeight =
                        FontWeight.Bold,

                    fontSize = 16.sp
                )
            }

            // =========================
            // DELETE BUTTON
            // =========================
            IconButton(

                onClick = {

                    showDeleteDialog = true
                }
            ) {

                Icon(

                    imageVector =
                        Icons.Default.Delete,

                    contentDescription = null,

                    tint = Color.Red
                )
            }
        }
    }
}