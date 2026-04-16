package com.praktikum.lumera.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.praktikum.lumera.data.MenuData
import com.praktikum.lumera.model.CartItem
import com.praktikum.lumera.components.MenuItemCard

@Composable
fun HomeScreen(navController: NavController, cart: MutableList<CartItem>) {

    var selectedCategory by remember { mutableStateOf("Coffee") }

    val menuList = MenuData.menuList.filter {
        it.category == selectedCategory
    }

    Column {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            listOf("Coffee", "Dessert").forEach { category ->

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {
                        selectedCategory = category
                    }
                ) {

                    Text(category)

                    if (selectedCategory == category) {
                        Box(
                            modifier = Modifier
                                .height(2.dp)
                                .width(40.dp)
                                .background(Color(0xFFB98068))
                        )
                    }
                }
            }
        }

        LazyVerticalGrid(columns = GridCells.Fixed(2)) {

            items(menuList) { menu ->

                MenuItemCard(menu) {

                    val existing = cart.find { it.menu.id == menu.id }

                    if (existing != null) {
                        existing.quantity++
                    } else {
                        cart.add(CartItem(menu, 1))
                    }
                }
            }
        }

        Button(
            onClick = { navController.navigate("cart") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Lihat Keranjang")
        }
    }
}