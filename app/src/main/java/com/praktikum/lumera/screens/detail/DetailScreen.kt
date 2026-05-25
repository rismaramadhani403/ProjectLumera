package com.praktikum.lumera.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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

    onBack: () -> Unit
) {

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

            Image(

                painter = painterResource(
                    id = menu.image
                ),

                contentDescription = null,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(370.dp),

                contentScale = ContentScale.Crop
            )

            // =========================
            // GRADIENT OVERLAY
            // =========================
            Box(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(370.dp)
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

                fontSize = 34.sp,

                fontWeight = FontWeight.ExtraBold
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            // =========================
            // RATING
            // =========================
            Row(

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Text(

                    text =
                        "⭐ ${menu.rating}"
                )

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Text(

                    text =
                        "(${menu.reviews} Reviews)",

                    color = Color.Gray
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

                fontSize = 24.sp,

                color = Color(0xFFD99A3E),

                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(34.dp)
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

                    fontSize = 20.sp
                )

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                PremiumOptionCard(

                    title = "Small",

                    selected =
                        selectedSize == "Small",

                    onClick = {

                        selectedSize = "Small"
                    }
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                PremiumOptionCard(

                    title = "Medium (+4K)",

                    selected =
                        selectedSize == "Medium",

                    onClick = {

                        selectedSize = "Medium"
                    }
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                PremiumOptionCard(

                    title = "Large (+8K)",

                    selected =
                        selectedSize == "Large",

                    onClick = {

                        selectedSize = "Large"
                    }
                )

                Spacer(
                    modifier = Modifier.height(30.dp)
                )

                // =========================
                // ICE OPTION
                // =========================
                Text(

                    text = "Ice",

                    fontWeight = FontWeight.Bold,

                    fontSize = 20.sp
                )

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                PremiumOptionCard(

                    title = "Less Ice",

                    selected =
                        selectedIce == "Less Ice",

                    onClick = {

                        selectedIce =
                            "Less Ice"
                    }
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                PremiumOptionCard(

                    title = "Normal Ice",

                    selected =
                        selectedIce == "Normal Ice",

                    onClick = {

                        selectedIce =
                            "Normal Ice"
                    }
                )

                Spacer(
                    modifier = Modifier.height(30.dp)
                )

                // =========================
                // SUGAR OPTION
                // =========================
                Text(

                    text = "Sugar",

                    fontWeight = FontWeight.Bold,

                    fontSize = 20.sp
                )

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                PremiumOptionCard(

                    title = "Normal Sugar",

                    selected =
                        selectedSugar ==
                                "Normal Sugar",

                    onClick = {

                        selectedSugar =
                            "Normal Sugar"
                    }
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                PremiumOptionCard(

                    title = "Less Sugar",

                    selected =
                        selectedSugar ==
                                "Less Sugar",

                    onClick = {

                        selectedSugar =
                            "Less Sugar"
                    }
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                PremiumOptionCard(

                    title = "No Sugar",

                    selected =
                        selectedSugar ==
                                "No Sugar",

                    onClick = {

                        selectedSugar =
                            "No Sugar"
                    }
                )

                Spacer(
                    modifier = Modifier.height(30.dp)
                )

                // =========================
                // EXTRA SHOT
                // =========================
                Text(

                    text = "Extra",

                    fontWeight = FontWeight.Bold,

                    fontSize = 20.sp
                )

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                Row(

                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(24.dp)
                        )
                        .background(
                            Color.White
                        )
                        .padding(18.dp),

                    horizontalArrangement =
                        Arrangement.SpaceBetween,

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Text(

                        text =
                            "Extra Shot +${formatRupiah(3000)}"
                    )

                    Checkbox(

                        checked = extraShot,

                        onCheckedChange = {

                            extraShot = it
                        }
                    )
                }

                Spacer(
                    modifier = Modifier.height(30.dp)
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

                modifier = Modifier.fillMaxWidth(),

                placeholder = {

                    Text(
                        text =
                            "Less sweet please..."
                    )
                },

                shape = RoundedCornerShape(20.dp)
            )

            Spacer(
                modifier = Modifier.height(36.dp)
            )

            // =========================
            // QUANTITY + BUTTON
            // =========================
            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceBetween,

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Row(

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    QuantityButton(
                        text = "-"
                    ) {

                        if (quantity > 1)
                            quantity--
                    }

                    Text(

                        text = quantity.toString(),

                        modifier = Modifier
                            .padding(
                                horizontal = 20.dp
                            ),

                        fontSize = 22.sp,

                        fontWeight =
                            FontWeight.Bold
                    )

                    QuantityButton(
                        text = "+"
                    ) {

                        quantity++
                    }
                }

                Button(

                    onClick = {

                        cartViewModel.addToCart(

                            menu = menu,

                            quantity = quantity,

                            size = selectedSize,

                            ice = selectedIce,

                            sugar = selectedSugar,

                            extraShot = extraShot,

                            notes = notes
                        )

                        onBack()
                    },

                    shape = RoundedCornerShape(
                        24.dp
                    ),

                    colors =
                        ButtonDefaults.buttonColors(

                            containerColor =
                                Color(0xFFC68642)
                        )
                ) {

                    Text(

                        text =
                            "Tambah • ${formatRupiah(totalPrice)}",

                        color = Color.White,

                        modifier = Modifier.padding(

                            horizontal = 14.dp,

                            vertical = 8.dp
                        )
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(30.dp)
            )
        }
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

    Button(

        onClick = onClick,

        shape = CircleShape,

        colors = ButtonDefaults.buttonColors(

            containerColor =
                Color(0xFFD99A3E)
        )
    ) {

        Text(

            text = text,

            color = Color.White,

            fontSize = 20.sp
        )
    }
}