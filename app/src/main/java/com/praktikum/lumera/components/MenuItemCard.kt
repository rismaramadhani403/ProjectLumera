package com.praktikum.lumera.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import coil.compose.AsyncImage
import com.praktikum.lumera.model.Menu
import com.praktikum.lumera.utils.formatRupiah
import com.praktikum.lumera.viewmodel.CartViewModel

@Composable
fun MenuItemCard(

    menu: Menu,

    cartViewModel: CartViewModel,

    onSelectMenu: (Menu) -> Unit,

    favoriteMenus: SnapshotStateList<Menu>,

    allMenus: List<Menu> = emptyList()
) {

    // =========================
    // FAVORITE CHECK
    // =========================
    val isFavorite =
        favoriteMenus.contains(menu)
    val isInCart =
        cartViewModel.cart.any {
            it.menu.id == menu.id
        }

    // =========================
    // CARD
    // =========================
    Card(

        modifier = Modifier
            .width(190.dp)
            .animateContentSize()
            .clickable {

                onSelectMenu(menu)
            },

        shape = RoundedCornerShape(24.dp),

        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),

        colors = CardDefaults.cardColors(

            containerColor =
                Color(0xFF241814)
        )
    ) {

        Column {

            // =========================
            // IMAGE SECTION
            // =========================
            Box {

                if (menu.imageUrl.isNotBlank()) {

                    // Gambar dari server (data API), Week 11
                    AsyncImage(
                        model = menu.imageUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(130.dp),
                        contentScale = ContentScale.Crop
                    )

                } else {

                    // Fallback: gambar drawable lokal (data dummy lama)
                    Image(

                        painter = painterResource(
                            id = menu.image
                        ),

                        contentDescription = null,

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(130.dp),

                        contentScale = ContentScale.Crop
                    )
                }

                // =========================
                // DARK OVERLAY
                // =========================
                Box(

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                        .background(

                            Color.Black.copy(
                                alpha = 0.15f
                            )
                        )
                )

                // =========================
                // FAVORITE BUTTON
                // =========================
                Box(

                    modifier = Modifier
                        .padding(12.dp)
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(
                            Color.Black.copy(alpha = 0.4f)
                        )
                        .clickable {

                            cartViewModel.toggleFavorite(
                                menu,
                                allMenus
                            )
                        }
                        .align(Alignment.TopEnd),

                    contentAlignment =
                        Alignment.Center
                ) {

                    Icon(

                        imageVector =

                            if (isFavorite)
                                Icons.Default.Favorite

                            else
                                Icons.Default.FavoriteBorder,

                        contentDescription = null,

                        tint =

                            if (isFavorite)
                                Color.Red

                            else
                                Color.White
                    )
                }
            }

            // =========================
            // CONTENT
            // =========================
            Column(
                modifier = Modifier.padding(16.dp)
            ){

                // =========================
                // MENU NAME
                // =========================
                Text(
                    text = menu.name,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    maxLines = 2,
                    minLines = 2
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = menu.description.ifBlank { "Freshly brewed coffee" },
                    color = Color.LightGray,
                    fontSize = 13.sp,
                    maxLines = 1,
                    minLines = 1
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Box(
                    modifier = Modifier.height(28.dp)
                ) {

                    if (menu.isBestSeller) {

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50.dp))
                                .background(Color(0xFFFFC107))
                                .padding(
                                    horizontal = 10.dp,
                                    vertical = 4.dp
                                )
                        ) {

                            Text(
                                text = "⭐ Best Seller",
                                color = Color.Black,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                // =========================
                // RATING
                // =========================
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(0xFFFFC107),
                        modifier = Modifier.size(18.dp)
                    )

                    Spacer(
                        modifier = Modifier.width(4.dp)
                    )

                    Text(
                        text = menu.rating.toString(),
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                // =========================
                // PRICE + ADD BUTTON
                // =========================
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = formatRupiah(menu.price),
                        color = Color(0xFFD99A3E),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )

                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFD99A3E))
                            .clickable {
                                cartViewModel.addToCart(menu, 1)
                            },
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = "+",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}