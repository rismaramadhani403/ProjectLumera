package com.praktikum.lumera.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.praktikum.lumera.data.MenuData
import com.praktikum.lumera.model.CartItem
import com.praktikum.lumera.model.Menu
import com.praktikum.lumera.components.MenuItemCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    cart: SnapshotStateList<CartItem>,
    onCartClick: () -> Unit,
    onSelectMenu: (Menu) -> Unit
) {

    var selectedCategory by remember { mutableStateOf("Coffee") }

    val menuList = remember(selectedCategory) {
        MenuData.menuList.filter { it.category == selectedCategory }
    }

    var selectedMenu by remember { mutableStateOf<Menu?>(null) }
    val sheetState = rememberModalBottomSheetState()
    val gridState = rememberLazyGridState()

    Column(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Lumera Cafe",
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "Nikmati kopi & dessert terbaik hari ini ☕🍰",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }

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

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            state = gridState,
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = menuList,
                key = { it.id }
            ) { menu ->
                MenuItemCard(menu = menu) {
                    selectedMenu = menu
                }
            }
        }

        Button(
            onClick = onCartClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("🛒 Lihat Keranjang")
        }
    }

    selectedMenu?.let { menu ->

        var quantity by remember(menu) { mutableStateOf(1) }

        ModalBottomSheet(
            onDismissRequest = { selectedMenu = null },
            sheetState = sheetState
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = menu.image),
                    contentDescription = menu.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(menu.name, style = MaterialTheme.typography.headlineSmall)

                Spacer(modifier = Modifier.height(4.dp))

                Text("Rp ${menu.price}")

                Spacer(modifier = Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Button(onClick = {
                        if (quantity > 1) quantity--
                    }) {
                        Text("-")
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Text("$quantity")

                    Spacer(modifier = Modifier.width(12.dp))

                    Button(onClick = {
                        quantity++
                    }) {
                        Text("+")
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        val existing = cart.find { it.menu.id == menu.id }

                        if (existing != null) {
                            val index = cart.indexOf(existing)
                            cart[index] = existing.copy(
                                quantity = existing.quantity + quantity
                            )
                        } else {
                            cart.add(CartItem(menu, quantity))
                        }

                        selectedMenu = null
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Tambah ke Keranjang ($quantity)")
                }
            }
        }
    }
}