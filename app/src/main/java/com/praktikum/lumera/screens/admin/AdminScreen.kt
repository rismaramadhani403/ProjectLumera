package com.praktikum.lumera.screens.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.praktikum.lumera.model.Menu
import com.praktikum.lumera.model.Transaction
import com.praktikum.lumera.viewmodel.MenuViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(

    transactions: List<Transaction>,

    menuViewModel: MenuViewModel,

    onBack: () -> Unit
) {

    // =========================
    // TOTAL INCOME
    // =========================
    val totalIncome =

        transactions.sumOf {

            it.total
        }

    // =========================
    // FORM STATE
    // =========================
    var menuName by remember {

        mutableStateOf("")
    }

    var menuPrice by remember {

        mutableStateOf("")
    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(

                        text = "Admin Dashboard",

                        fontWeight =
                            FontWeight.Bold
                    )
                },

                navigationIcon = {

                    IconButton(

                        onClick = onBack
                    ) {

                        Icon(

                            imageVector =
                                Icons.AutoMirrored
                                    .Filled
                                    .ArrowBack,

                            contentDescription =
                                "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->

        LazyColumn(

            modifier = Modifier

                .fillMaxSize()

                .background(
                    Color(0xFFF7F3EE)
                )

                .padding(padding)

                .padding(16.dp),

            verticalArrangement =
                Arrangement.spacedBy(18.dp)
        ) {

            // =========================
            // DASHBOARD CARD
            // =========================
            item {

                Card(

                    modifier = Modifier
                        .fillMaxWidth(),

                    shape =
                        RoundedCornerShape(
                            24.dp
                        ),

                    colors =
                        CardDefaults.cardColors(

                            containerColor =
                                Color(0xFFC68642)
                        )
                ) {

                    Column(

                        modifier = Modifier
                            .padding(20.dp)
                    ) {

                        Text(

                            text = "Total Transaksi",

                            color = Color.White,

                            fontSize = 16.sp
                        )

                        Spacer(
                            modifier =
                                Modifier.height(8.dp)
                        )

                        Text(

                            text =
                                "${transactions.size}",

                            color = Color.White,

                            fontSize = 32.sp,

                            fontWeight =
                                FontWeight.Bold
                        )

                        Spacer(
                            modifier =
                                Modifier.height(20.dp)
                        )

                        Text(

                            text = "Income Hari Ini",

                            color = Color.White,

                            fontSize = 16.sp
                        )

                        Spacer(
                            modifier =
                                Modifier.height(8.dp)
                        )

                        Text(

                            text =
                                "Rp $totalIncome",

                            color = Color.White,

                            fontSize = 30.sp,

                            fontWeight =
                                FontWeight.Bold
                        )
                    }
                }
            }

            // =========================
            // ADD MENU
            // =========================
            item {

                Text(

                    text = "Tambah Menu",

                    fontWeight =
                        FontWeight.Bold,

                    fontSize = 22.sp
                )

                Spacer(
                    modifier =
                        Modifier.height(12.dp)
                )

                OutlinedTextField(

                    value = menuName,

                    onValueChange = {

                        menuName = it
                    },

                    label = {

                        Text("Nama Menu")
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(
                    modifier =
                        Modifier.height(12.dp)
                )

                OutlinedTextField(

                    value = menuPrice,

                    onValueChange = {

                        menuPrice = it
                    },

                    label = {

                        Text("Harga")
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(
                    modifier =
                        Modifier.height(14.dp)
                )

                Button(

                    onClick = {

                        if (

                            menuName.isNotEmpty()

                            &&

                            menuPrice.isNotEmpty()
                        ) {

                            menuViewModel.addMenu(

                                Menu(

                                    id =
                                        menuViewModel
                                            .menus
                                            .size + 1,

                                    name =
                                        menuName,

                                    price =
                                        menuPrice.toInt(),

                                    image = 0,

                                    category =
                                        "Coffee"
                                )
                            )

                            menuName = ""
                            menuPrice = ""
                        }
                    },

                    colors =
                        ButtonDefaults.buttonColors(

                            containerColor =
                                Color(0xFFC68642)
                        )
                ) {

                    Text(

                        text = "Tambah Menu"
                    )
                }
            }

            // =========================
            // MENU LIST
            // =========================
            item {

                Text(

                    text = "Kelola Menu",

                    fontWeight =
                        FontWeight.Bold,

                    fontSize = 22.sp
                )
            }

            items(menuViewModel.menus) { menu ->

                Card(

                    modifier = Modifier
                        .fillMaxWidth(),

                    shape =
                        RoundedCornerShape(
                            20.dp
                        )
                ) {

                    Row(

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp),

                        horizontalArrangement =
                            Arrangement.SpaceBetween
                    ) {

                        Column {

                            Text(

                                text =
                                    menu.name,

                                fontWeight =
                                    FontWeight.Bold,

                                fontSize = 18.sp
                            )

                            Spacer(
                                modifier =
                                    Modifier.height(4.dp)
                            )

                            Text(

                                text =
                                    "Rp ${menu.price}"
                            )
                        }

                        IconButton(

                            onClick = {

                                menuViewModel.removeMenu(
                                    menu
                                )
                            }
                        ) {

                            Icon(

                                imageVector =
                                    Icons.Default.Delete,

                                contentDescription =
                                    "Delete",

                                tint = Color.Red
                            )
                        }
                    }
                }
            }

            // =========================
            // TRANSACTION TITLE
            // =========================
            item {

                Text(

                    text = "Riwayat Transaksi",

                    fontWeight =
                        FontWeight.Bold,

                    fontSize = 22.sp
                )
            }

            // =========================
            // TRANSACTION LIST
            // =========================
            items(transactions) { transaction ->

                Card(

                    modifier = Modifier
                        .fillMaxWidth(),

                    shape =
                        RoundedCornerShape(
                            20.dp
                        )
                ) {

                    Column(

                        modifier = Modifier
                            .padding(18.dp)
                    ) {

                        Text(

                            text =
                                "Customer : ${transaction.customerName}",

                            fontWeight =
                                FontWeight.Bold,

                            fontSize = 18.sp
                        )

                        Spacer(
                            modifier =
                                Modifier.height(6.dp)
                        )

                        Text(

                            text =
                                "Kasir : ${transaction.cashierName}"
                        )

                        Spacer(
                            modifier =
                                Modifier.height(4.dp)
                        )

                        Text(

                            text =
                                "Total : Rp ${transaction.total}"
                        )

                        Spacer(
                            modifier =
                                Modifier.height(4.dp)
                        )

                        Text(

                            text =
                                "Metode : ${transaction.paymentMethod}"
                        )

                        Spacer(
                            modifier =
                                Modifier.height(4.dp)
                        )

                        Text(

                            text =
                                "Status : Berhasil ✅"
                        )
                    }
                }
            }
        }
    }
}