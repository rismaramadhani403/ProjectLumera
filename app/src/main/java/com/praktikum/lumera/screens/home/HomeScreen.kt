package com.praktikum.lumera.screens.home

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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.praktikum.lumera.datastore.UserPreferences
import com.praktikum.lumera.model.CartItem
import com.praktikum.lumera.model.Menu
import com.praktikum.lumera.ui.theme.Poppins
import com.praktikum.lumera.viewmodel.CartViewModel
import com.praktikum.lumera.components.MenuItemCard

@Composable
fun HomeScreen(

    userPreferences: UserPreferences,

    menus: List<Menu>,

    cart: List<CartItem>,

    cartViewModel: CartViewModel,

    onCartClick: () -> Unit,

    onSelectMenu: (Menu) -> Unit,

    onProfileClick: () -> Unit,

    onBack: () -> Unit
) {

    // =========================
    // SEARCH STATE
    // =========================
    var searchQuery by remember {

        mutableStateOf("")
    }

    // =========================
    // CATEGORY STATE
    // =========================
    var selectedCategory by remember {

        mutableStateOf("Coffee")
    }

    // =========================
    // FAVORITE MENUS
    // =========================
    val favoriteMenus =
        cartViewModel.favoriteMenus

    // =========================
    // USER STATE
    // =========================
    val user by userPreferences
        .getUser
        .collectAsState(

            initial = null
        )

    val isLoggedIn =

        !user?.email.isNullOrEmpty()

    // =========================
    // FILTERED MENU
    // =========================
    val filteredMenus = when (selectedCategory) {

        "Coffee" ->

            menus.filter {

                it.category == "Coffee"
                        &&
                        it.name.contains(
                            searchQuery,
                            true
                        )
            }

        "Dessert" ->

            menus.filter {

                it.category == "Dessert"
                        &&
                        it.name.contains(
                            searchQuery,
                            true
                        )
            }

        "Favorite" ->

            favoriteMenus.filter {

                it.name.contains(
                    searchQuery,
                    true
                )
            }

        else ->

            menus.filter {

                it.category == "Coffee"
                        &&
                        it.name.contains(
                            searchQuery,
                            true
                        )
            }
    }

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
            .verticalScroll(
                rememberScrollState()
            )
            .padding(20.dp)
    ) {

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        // =========================
        // PREMIUM HEADER
        // =========================
        Row(

            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement =
                Arrangement.SpaceEvenly,

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            // LEFT SIDE
            Row(

                modifier = Modifier.weight(1f),

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                // BACK BUTTON
                IconButton(

                    onClick = {

                        onBack()
                    },

                    modifier = Modifier
                        .size(54.dp)
                        .background(

                            Color(0xFFD99A3E),

                            shape = CircleShape
                        )
                ) {

                    Icon(

                        imageVector =
                            Icons.AutoMirrored.Filled.ArrowBack,

                        contentDescription = null,

                        tint = Color.White
                    )
                }

                Spacer(
                    modifier = Modifier.width(10.dp)
                )

                // TITLE
                Column {

                    Text(

                        text = if (isLoggedIn) {

                            "Hi, ${user?.name} ☕"

                        } else {

                            "Welcome ☕"
                        },

                        color = Color.White,

                        fontFamily = Poppins,

                        fontWeight = FontWeight.ExtraBold,

                        fontSize = 22.sp
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(

                        text = "Enjoy your favorite coffee",

                        color = Color.LightGray,

                        fontSize = 13.sp
                    )
                }
            }

            Spacer(
                modifier = Modifier.width(12.dp)
            )

            // RIGHT SIDE
            Row(

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                // CART BUTTON
                Box(

                    modifier = Modifier
                        .padding(end = 10.dp)
                ) {

                    Box(

                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(
                                Color(0xFFD99A3E)
                            )
                            .clickable {

                                onCartClick()
                            },

                        contentAlignment =
                            Alignment.Center
                    ) {

                        Icon(

                            imageVector =
                                Icons.Default.ShoppingCart,

                            contentDescription = null,

                            tint = Color.White
                        )
                    }

                    // CART BADGE
                    if (cart.isNotEmpty()) {

                        Box(

                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .offset(
                                    x = 4.dp,
                                    y = (-4).dp
                                )
                                .size(18.dp)
                                .clip(CircleShape)
                                .background(Color.Red),

                            contentAlignment =
                                Alignment.Center
                        ) {

                            Text(

                                text = cart.size.toString(),

                                color = Color.White,

                                fontSize = 9.sp,

                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                // PROFILE BUTTON
                Box(

                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(
                            Color(0xFFD99A3E)
                        )
                        .clickable {

                            onProfileClick()
                        },

                    contentAlignment =
                        Alignment.Center
                ) {

                    Icon(

                        imageVector =
                            Icons.Default.Person,

                        contentDescription = null,

                        tint = Color.White
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        // =========================
        // SEARCH BAR
        // =========================
        OutlinedTextField(

            value = searchQuery,

            onValueChange = {

                searchQuery = it
            },

            placeholder = {

                Text(

                    text = "Search menu...",

                    color = Color.Gray
                )
            },

            leadingIcon = {

                Icon(

                    imageVector =
                        Icons.Default.Search,

                    contentDescription = null,

                    tint = Color.Gray
                )
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),

            singleLine = true,

            shape = RoundedCornerShape(30.dp),

            colors = OutlinedTextFieldDefaults.colors(

                focusedContainerColor =
                    Color(0xFF2A1E18),

                unfocusedContainerColor =
                    Color(0xFF2A1E18),

                focusedBorderColor =
                    Color.Transparent,

                unfocusedBorderColor =
                    Color.Transparent,

                focusedTextColor =
                    Color.White,

                unfocusedTextColor =
                    Color.White,

                cursorColor =
                    Color.White
            )
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
                .clip(RoundedCornerShape(28.dp))
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFD99A3E),
                            Color(0xFFB8741A)
                        )
                    )
                )
        ) {

            Column(
                modifier = Modifier.padding(24.dp)
            ) {

                Text(
                    text = "SPECIAL OFFER",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(
                    text = "BUY 1 GET 1",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Text(
                    text = "For All Coffee Menu",
                    color = Color.White.copy(alpha = 0.9f)
                )
            }
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        // =========================
        // CATEGORY
        // =========================
        LazyRow(

            horizontalArrangement =
                Arrangement.spacedBy(12.dp)
        ) {

            items(

                listOf(

                    "Coffee",
                    "Dessert",
                    "Favorite"
                )
            ) { item ->

                Box(

                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(50.dp)
                        )
                        .background(

                            if (selectedCategory == item)
                                Color(0xFFD99A3E)

                            else
                                Color(0xFF2A1E18)
                        )
                        .clickable {

                            selectedCategory = item
                        }
                        .padding(
                            horizontal = 22.dp,
                            vertical = 10.dp
                        )
                ) {

                    Text(

                        text = item,

                        color = Color.White,

                        fontWeight =
                            FontWeight.SemiBold
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        // =========================
        // PREMIUM MENU TITLE
        // =========================
        Text(

            text =

                if (selectedCategory == "Favorite")
                    "Your Favorite Coffee"

                else
                    "Latte Art Collection",

            color = Color.White,

            fontWeight = FontWeight.ExtraBold,

            fontSize = 26.sp
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(

            text =

                if (selectedCategory == "Favorite")
                    "Coffee you loved the most ☕"

                else
                    "Freshly brewed premium coffee",

            color = Color.LightGray,

            fontSize = 14.sp
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        // =========================
        // FEATURED MENU
        // =========================
        Text(
            text = "Popular Menu",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        LazyRow(
            horizontalArrangement =
                Arrangement.spacedBy(18.dp)
        ) {

            items(filteredMenus.take(5)) { menu ->

                MenuItemCard(

                    menu = menu,

                    cartViewModel =
                        cartViewModel,

                    onSelectMenu =
                        onSelectMenu,

                    favoriteMenus =
                        favoriteMenus
                )
            }
        }

        Spacer(
            modifier = Modifier.height(28.dp)
        )

        Text(
            text = "Recommended For You",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        LazyRow(
            horizontalArrangement =
                Arrangement.spacedBy(18.dp)
        ) {

            items(filteredMenus.reversed().take(5)) { menu ->

                MenuItemCard(

                    menu = menu,

                    cartViewModel =
                        cartViewModel,

                    onSelectMenu =
                        onSelectMenu,

                    favoriteMenus =
                        favoriteMenus
                )
            }
        }

        Spacer(
            modifier = Modifier.height(40.dp)
        )

        // =========================
        // BOTTOM NAVIGATION
        // =========================
        Row(

            modifier = Modifier
                .fillMaxWidth()
                .height(74.dp)
                .clip(
                    RoundedCornerShape(50.dp)
                )
                .background(
                    Color.White.copy(
                        alpha = 0.08f
                    )
                )
                .padding(
                    horizontal = 18.dp
                ),

            horizontalArrangement =
                Arrangement.SpaceEvenly,

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            // =========================
            // HOME
            // =========================
            BottomNavItem(

                selected = true,

                icon = Icons.Default.Home,

                label = "Home",

                onClick = {

                    selectedCategory = "Coffee"
                }
            )

            // =========================
            // FAVORITE
            // =========================
            BottomNavItem(

                selected =
                    selectedCategory == "Favorite",

                icon =

                    if (favoriteMenus.isNotEmpty())
                        Icons.Default.Favorite

                    else
                        Icons.Default.FavoriteBorder,

                label = "",

                iconTint =

                    if (selectedCategory == "Favorite")
                        Color.Red

                    else
                        Color.White,

                onClick = {

                    selectedCategory = "Favorite"
                }
            )

            // =========================
            // PROFILE
            // =========================
            BottomNavItem(

                selected = false,

                icon = Icons.Default.Person,

                label = "",

                onClick = {

                    onProfileClick()
                }
            )
        }
    }
}

@Composable
fun BottomNavItem(

    selected: Boolean,

    icon: ImageVector,

    label: String,

    iconTint: Color = Color.White,

    onClick: () -> Unit
) {

    Box(

        modifier = Modifier

            .height(

                if (label.isNotEmpty())
                    42.dp

                else
                    36.dp
            )

            .clip(
                RoundedCornerShape(50.dp)
            )

            .background(

                if (selected && label.isNotEmpty())
                    Color(0xFFFFF0D9)

                else
                    Color.Transparent
            )

            .clickable {

                onClick()
            }

            .padding(

                horizontal =

                    if (label.isNotEmpty())
                        18.dp

                    else
                        10.dp
            ),

        contentAlignment =
            Alignment.Center
    ) {

        Row(

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Icon(

                imageVector = icon,

                contentDescription = null,

                tint =

                    if (selected && label.isNotEmpty())
                        Color.Black

                    else
                        iconTint,

                modifier = Modifier.size(20.dp)
            )

            if (label.isNotEmpty()) {

                Spacer(
                    modifier = Modifier.width(6.dp)
                )

                Text(

                    text = label,

                    color = Color.Black,

                    fontWeight = FontWeight.Bold,

                    fontSize = 13.sp
                )
            }
        }
    }
}
