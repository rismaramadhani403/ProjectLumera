package com.praktikum.lumera.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.praktikum.lumera.R
import com.praktikum.lumera.components.MenuItemCard
import com.praktikum.lumera.data.SessionManager
import com.praktikum.lumera.datastore.UserPreferences
import com.praktikum.lumera.model.CartItem
import com.praktikum.lumera.model.Menu
import com.praktikum.lumera.ui.theme.Playfair
import com.praktikum.lumera.viewmodel.CartViewModel

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

    // Week 11: setiap kali daftar menu dari API (menus) berubah/selesai
    // dimuat, cocokkan ulang favoriteIds dengan data menu TERKINI,
    // supaya favorit tidak lagi bergantung pada data dummy MenuData.
    LaunchedEffect(menus) {

        if (menus.isNotEmpty()) {

            cartViewModel.refreshFavoriteMenus(menus)
        }
    }

    // =========================
    // USER STATE
    // =========================
    val user =
        SessionManager.currentUser.value

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

    val cartCount = cartViewModel.cart.sumOf {
        it.quantity
    }
    // =========================
    // GREETING BASED ON TIME
    // =========================
    val currentHour =
        java.util.Calendar.getInstance()
            .get(java.util.Calendar.HOUR_OF_DAY)

    val greeting = when (currentHour) {

        in 5..10 ->
            "Morning Brew ☕"

        in 11..14 ->
            "Coffee Time 🌤️"

        in 15..17 ->
            "Afternoon Blend ☕"

        else ->
            "Night Vibes 🌙"
    }

    Scaffold(

        containerColor = Color.Transparent,

        bottomBar = {

            Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(74.dp)
                    .padding(
                        horizontal = 20.dp,
                        vertical = 10.dp
                    )
                    .clip(
                        RoundedCornerShape(50.dp)
                    )
                    .background(
                        Color.White.copy(alpha = 0.08f)
                    )
                    .padding(horizontal = 18.dp),

                horizontalArrangement =
                    Arrangement.SpaceEvenly,

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                BottomNavItem(
                    selected = true,
                    icon = Icons.Default.Home,
                    label = "Home",
                    onClick = {
                        selectedCategory = "Coffee"
                    }
                )

                Box(
                    modifier = Modifier.clickable {
                        onCartClick()
                    }
                ) {

                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )

                    if (cartCount > 0) {

                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .size(18.dp)
                                .background(
                                    Color.Red,
                                    CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                text = cartCount.toString(),
                                color = Color.White,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
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

    ) { paddingValues ->

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
                .padding(paddingValues)
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
                            text = greeting,
                            color = Color(0xFFD99A3E),
                            fontSize = 14.sp
                        )

                        Text(
                            text = user?.name ?: "Guest",
                            color = Color.White,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold
                        )

                        Text(
                            text = "Discover handcrafted coffee today",
                            color = Color.LightGray,
                            fontSize = 13.sp
                        )
                    }

                }

                // RIGHT SIDE
                Row(

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

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

                        contentAlignment = Alignment.Center
                    ) {

                        Text(

                            text = user?.name
                                ?.firstOrNull()
                                ?.uppercase()
                                ?: "G",

                            color = Color.White,

                            fontWeight = FontWeight.Bold,

                            fontSize = 18.sp
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
                        text = "Espresso, Latte, Cappuccino...",
                        color = Color.Gray
                    )
                },

                leadingIcon = {

                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                },

                trailingIcon = {

                    Icon(
                        imageVector = Icons.Default.MoreVert,
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

                    focusedContainerColor = Color(0xFF2A1E18),
                    unfocusedContainerColor = Color(0xFF2A1E18),

                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,

                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,

                    cursorColor = Color.White
                )
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(28.dp))
            ) {

                Image(
                    painter = painterResource(
                        R.drawable.banner_coffee
                    ),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
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
                modifier = Modifier.height(24.dp)
            )

            // =========================
            // FEATURED MENU
            // =========================

            if (selectedCategory == "Favorite") {

                Text(
                    text = "Your Favorite Menu",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                if (favoriteMenus.isEmpty()) {

                    Column(

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 50.dp),

                        horizontalAlignment =
                            Alignment.CenterHorizontally
                    ) {

                        Icon(
                            imageVector =
                                Icons.Default.FavoriteBorder,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(70.dp)
                        )

                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )

                        Text(
                            text = "No favorite menu yet",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Tap ❤ to save your favorite menu",
                            color = Color.Gray
                        )
                    }

                } else {

                    LazyRow(
                        horizontalArrangement =
                            Arrangement.spacedBy(18.dp)
                    ) {

                        items(favoriteMenus) { menu ->

                            MenuItemCard(
                                menu = menu,
                                cartViewModel = cartViewModel,
                                onSelectMenu = onSelectMenu,
                                favoriteMenus = favoriteMenus,
                                allMenus = menus
                            )
                        }
                    }
                }

            } else {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement =
                        Arrangement.SpaceBetween
                ) {

                    Text(
                        text = when (selectedCategory) {
                            "Coffee" -> "Popular Coffee"
                            "Dessert" -> "Popular Dessert"
                            else -> "Popular Menu"
                        },
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )

                    Text(
                        text = "See All",
                        color = Color(0xFFD99A3E)
                    )
                }

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
                            cartViewModel = cartViewModel,
                            onSelectMenu = onSelectMenu,
                            favoriteMenus = favoriteMenus,
                            allMenus = menus
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(28.dp)
                )

                Text(
                    text = when (selectedCategory) {
                        "Coffee" -> "Recommended Coffee"
                        "Dessert" -> "Recommended Dessert"
                        else -> "Recommended For You"
                    },
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
                            cartViewModel = cartViewModel,
                            onSelectMenu = onSelectMenu,
                            favoriteMenus = favoriteMenus,
                            allMenus = menus
                        )
                    }
                }
            }
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