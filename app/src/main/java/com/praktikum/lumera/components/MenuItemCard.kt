package com.praktikum.lumera.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.praktikum.lumera.model.Menu
import java.text.NumberFormat
import java.util.Locale

@Composable
fun MenuItemCard(menu: Menu, onAdd: () -> Unit) {

    val formatRupiah = remember {
        NumberFormat.getInstance(Locale("in", "ID"))
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {

            Image(
                painter = painterResource(id = menu.image),
                contentDescription = menu.name,
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(menu.name)

            Text("Rp ${formatRupiah.format(menu.price)}")

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Favorite, contentDescription = null, tint = Color.Red)

                Spacer(modifier = Modifier.width(8.dp))

                Button(onClick = onAdd) {
                    Text("+")
                }
            }
        }
    }
}