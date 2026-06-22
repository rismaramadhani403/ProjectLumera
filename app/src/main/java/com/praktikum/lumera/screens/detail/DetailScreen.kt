package com.praktikum.lumera.screens.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import coil.compose.AsyncImage
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.praktikum.lumera.model.Menu
import com.praktikum.lumera.utils.formatRupiah
import com.praktikum.lumera.viewmodel.CartViewModel

@Composable
fun DetailScreen(

    menu: Menu,

    cartViewModel: CartViewModel,

    onBack: () -> Unit,

    onCartClick: () -> Unit
){

    // =========================
    // STATES
    // =========================
    var quantity by remember {

        mutableStateOf(1)
    }

    var selectedIce by remember {

        mutableStateOf("Normal Ice")
    }

    var selectedSugar by remember {

        mutableStateOf("Normal Sugar")
    }

    var selectedSize by remember {

        mutableStateOf("Medium")
    }

    var extraShot by remember {

        mutableStateOf(false)
    }

    var notes by remember {

        mutableStateOf("")
    }

    // =========================
    // PRICE
    // =========================
    val extraPrice =

        if (extraShot) 3000
        else 0

    val sizePrice =

        if (menu.category == "Coffee") {

            when (selectedSize) {

                "Small" -> 0

                "Medium" -> 4000

                else -> 8000
            }

        } else {

            0
        }

    val totalPrice =

        (menu.price +
                extraPrice +
                sizePrice) * quantity

    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(0xFFF7F3EE)
            )
            .verticalScroll(
                rememberScrollState()
            )
    ) {

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
                        .height(280.dp),
                    contentScale = ContentScale.Crop
                )

            } else {

                Image(

                    painter = painterResource(
                        id = menu.image
                    ),

                    contentDescription = null,

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp),

                    contentScale = ContentScale.Crop
                )
            }

            // =========================
            // GRADIENT OVERLAY
            // =========================
            Box(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .background(

                        Brush.verticalGradient(

                            colors = listOf(

                                Color.Transparent,

                                Color.Black.copy(alpha = 0.55f)
                            )
                        )
                    )
            )

            // =========================
            // BACK BUTTON
            // =========================
            IconButton(

                onClick = onBack,

                modifier = Modifier
                    .padding(18.dp)
                    .clip(CircleShape)
                    .background(
                        Color.White
                    )
            ) {

                Icon(

                    imageVector =
                        Icons.Default.ArrowBack,

                    contentDescription = null
                )
            }
        }

        // =========================
        // CONTENT
        // =========================
        Column(

            modifier = Modifier.padding(22.dp)
        ) {

            // =========================
            // MENU NAME
            // =========================
            Text(

                text = menu.name,

                fontSize = 28.sp,

                fontWeight = FontWeight.ExtraBold
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .background(Color(0xFFFFE5C2))
                    .padding(
                        horizontal = 14.dp,
                        vertical = 6.dp
                    )
            ) {

                Text(
                    text =
                        if (menu.category == "Coffee")
                            "☕ Coffee"
                        else
                            "🍰 Dessert",
                    color = Color(0xFFD99A3E),
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }

            // =========================
            // RATING
            // =========================
            Row(

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Text(
                    text =
                        "⭐ ${menu.rating} • ${menu.reviews} Reviews",

                    color = Color.Gray,

                    fontSize = 14.sp
                )
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            // =========================
            // DESCRIPTION
            // =========================
            Text(

                text = menu.description,

                color = Color.Gray,

                lineHeight = 24.sp
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            // =========================
            // PRICE
            // =========================
            Text(

                text = formatRupiah(totalPrice),

                fontSize = 28.sp,

                color = Color(0xFFD99A3E),

                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            // =========================
            // COFFEE OPTION ONLY
            // =========================
            if (menu.category == "Coffee") {

                // =========================
                // SIZE OPTION
                // =========================
                Text(
                    text = "Size",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    SizeChip(
                        text = "S",
                        selected = selectedSize == "Small"
                    ) {
                        selectedSize = "Small"
                    }

                    SizeChip(
                        text = "M",
                        selected = selectedSize == "Medium"
                    ) {
                        selectedSize = "Medium"
                    }

                    SizeChip(
                        text = "L",
                        selected = selectedSize == "Large"
                    ) {
                        selectedSize = "Large"
                    }
                }

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                // =========================
                // ICE OPTION
                // =========================
                Text(
                    text = "Ice Level",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    SizeChip(
                        text = "Less",
                        selected = selectedIce == "Less Ice"
                    ) {
                        selectedIce = "Less Ice"
                    }

                    SizeChip(
                        text = "Normal",
                        selected = selectedIce == "Normal Ice"
                    ) {
                        selectedIce = "Normal Ice"
                    }
                }

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                // =========================
                // SUGAR OPTION
                // =========================
                Text(
                    text = "Sugar Level",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    SizeChip(
                        text = "No",
                        selected = selectedSugar == "No Sugar"
                    ) {
                        selectedSugar = "No Sugar"
                    }

                    SizeChip(
                        text = "Less",
                        selected = selectedSugar == "Less Sugar"
                    ) {
                        selectedSugar = "Less Sugar"
                    }

                    SizeChip(
                        text = "Normal",
                        selected = selectedSugar == "Normal Sugar"
                    ) {
                        selectedSugar = "Normal Sugar"
                    }
                }

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                // =========================
                // EXTRA SHOT
                // =========================
                Text(
                    text = "Extra Shot",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Row(

                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(18.dp))
                        .background(Color.White)
                        .padding(
                            horizontal = 18.dp,
                            vertical = 14.dp
                        ),

                    horizontalArrangement =
                        Arrangement.SpaceBetween,

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Text(
                        text = "☕ + Rp 3.000"
                    )

                    Checkbox(
                        checked = extraShot,
                        onCheckedChange = {
                            extraShot = it
                        }
                    )
                }

                Spacer(
                    modifier = Modifier.height(24.dp)
                )
            }

            // =========================
            // NOTES
            // =========================
            Text(

                text = "Notes",

                fontWeight = FontWeight.Bold,

                fontSize = 20.sp
            )

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            OutlinedTextField(

                value = notes,

                onValueChange = {
                    notes = it
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),

                placeholder = {
                    Text(
                        text = "Less sweet please..."
                    )
                },

                shape = RoundedCornerShape(18.dp)
            )
        }

        Spacer(
            modifier = Modifier.height(36.dp)
        )

        // =========================
        // QUANTITY + TOTAL
        // =========================
        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            QuantityButton("-") {
                if (quantity > 1) quantity--
            }

            Text(

                text = quantity.toString(),

                modifier = Modifier.padding(
                    horizontal = 12.dp
                ),

                fontWeight = FontWeight.Bold,

                fontSize = 18.sp
            )

            QuantityButton("+") {
                quantity++
            }

            Spacer(
                modifier = Modifier.width(24.dp)
            )

            Column {

                Text(

                    text = "Total",

                    fontSize = 15.sp,

                    color = Color.Gray
                )

                Text(

                    text = formatRupiah(totalPrice),

                    fontSize = 20.sp,

                    fontWeight = FontWeight.Bold,

                    color = Color.Black
                )
            }
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Button(

            onClick = {

                cartViewModel.addToCart(

                    menu = menu,

                    quantity = quantity,

                    size = selectedSize,

                    ice = selectedIce,

                    sugar = selectedSugar,

                    extraShot = extraShot,

                    notes = notes,

                    customPrice =
                        sizePrice +
                                if (extraShot) 3000 else 0
                )

                onCartClick()
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),

            shape = RoundedCornerShape(14.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD99A3E)
            )

        ) {

            Text(
                text = "Add To Cart",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

        }

    }

}
@Composable
fun SizeChip(

    text: String,

    selected: Boolean,

    onClick: () -> Unit

) {

    Box(

        modifier = Modifier
            .clip(
                RoundedCornerShape(18.dp)
            )
            .background(
                if (selected)
                    Color(0xFFFFF3E0)
                else
                    Color.White
            )
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 28.dp,
                vertical = 14.dp
            )

    ) {

        Text(

            text = text,

            color =
                if (selected)
                    Color(0xFFD99A3E)
                else
                    Color.Black,

            fontWeight =
                FontWeight.Bold
        )
    }
}
@Composable
fun PremiumOptionCard(

    title: String,

    selected: Boolean,

    onClick: () -> Unit
) {

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(22.dp)
            )
            .background(

                if (selected)
                    Color(0xFFFFF3E0)

                else
                    Color.White
            )
            .padding(18.dp),

        horizontalArrangement =
            Arrangement.SpaceBetween,

        verticalAlignment =
            Alignment.CenterVertically
    ) {

        Text(
            text = title
        )

        RadioButton(

            selected = selected,

            onClick = onClick
        )
    }
}

@Composable
fun QuantityButton(

    text: String,

    onClick: () -> Unit
) {

    OutlinedButton(

        onClick = onClick,

        modifier = Modifier.size(40.dp),

        shape = CircleShape,

        border = BorderStroke(
            1.dp,
            Color.LightGray
        ),

        contentPadding = PaddingValues(0.dp)

    ) {

        Text(

            text = text,

            color = Color.Black,

            fontSize = 18.sp,

            fontWeight = FontWeight.Bold
        )
    }
}