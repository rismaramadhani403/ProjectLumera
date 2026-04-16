package com.praktikum.lumera.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.praktikum.lumera.model.Menu

@Composable
fun MenuItemCard(menu: Menu, onAdd: () -> Unit) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {

            Image(
                painter = painterResource(menu.image),
                contentDescription = menu.name,
                modifier = Modifier.height(100.dp),
                contentScale = ContentScale.Crop
            )

            Text(menu.name)
            Text("Rp ${menu.price}")

            Row {
                Icon(Icons.Default.Favorite, null, tint = Color.Red)

                Spacer(modifier = Modifier.width(8.dp))

                Button(onClick = onAdd) {
                    Text("+")
                }
            }
        }
    }
}