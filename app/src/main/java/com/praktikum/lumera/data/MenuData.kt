package com.praktikum.lumera.data

import androidx.compose.runtime.mutableStateListOf
import com.praktikum.lumera.R
import com.praktikum.lumera.model.Menu

object MenuData {

    val menuList = mutableStateListOf(

        // =========================
        // COFFEE
        // =========================
        Menu(

            id = 1,

            name = "Mocha Caramel",

            price = 25000,

            category = "Coffee",

            image = R.drawable.kopi1,

            isBestSeller = true,

            rating = 4.9,

            reviews = 320,

            description =
                "Sweet caramel blended with rich mocha espresso."
        ),

        Menu(

            id = 2,

            name = "Toffee Nut Latte",

            price = 18000,

            category = "Coffee",

            image = R.drawable.kopi2,

            rating = 4.6,

            reviews = 98,

            description =
                "Creamy latte with sweet toffee nut flavor."
        ),

        Menu(

            id = 3,

            name = "Kopi Kenangan Mantan",

            price = 19000,

            category = "Coffee",

            image = R.drawable.kopi3,

            isBestSeller = true,

            rating = 4.8,

            reviews = 410,

            description =
                "Signature Indonesian milk coffee with aren sugar."
        ),

        Menu(

            id = 4,

            name = "Caramel Latte",

            price = 25000,

            category = "Coffee",

            image = R.drawable.kopi4,

            rating = 4.7,

            reviews = 175,

            description =
                "Smooth espresso mixed with creamy caramel milk."
        ),

        Menu(

            id = 5,

            name = "Hazelnut Latte",

            price = 25000,

            category = "Coffee",

            image = R.drawable.kopi5,

            isBestSeller = true,

            rating = 4.9,

            reviews = 295,

            description =
                "Nutty hazelnut flavor with silky milk texture."
        ),

        Menu(

            id = 11,

            name = "Avocado Coffee",

            price = 20000,

            category = "Coffee",

            image = R.drawable.kopi6,

            rating = 4.5,

            reviews = 88,

            description =
                "Fresh avocado blended with premium espresso."
        ),

        Menu(

            id = 12,

            name = "Butterscotch Sea Salt Latte",

            price = 24000,

            category = "Coffee",

            image = R.drawable.kopi7,

            isBestSeller = true,

            rating = 4.9,

            reviews = 350,

            description =
                "Sweet butterscotch balanced with sea salt cream."
        ),

        Menu(

            id = 13,

            name = "Mocha Macchiato",

            price = 23000,

            category = "Coffee",

            image = R.drawable.kopi8,

            rating = 4.7,

            reviews = 166,

            description =
                "Espresso layered with chocolate and milk foam."
        ),

        Menu(

            id = 14,

            name = "Brown Sugar Caramel",

            price = 24000,

            category = "Coffee",

            image = R.drawable.kopi9,

            rating = 4.8,

            reviews = 260,

            description =
                "Brown sugar sweetness mixed with creamy caramel."
        ),

        Menu(

            id = 15,

            name = "Spanish Iced Latte",

            price = 24000,

            category = "Coffee",

            image = R.drawable.kopi10,

            isBestSeller = true,

            rating = 4.9,

            reviews = 390,

            description =
                "Refreshing iced latte with Spanish creamy flavor."
        ),

        // =========================
        // DESSERT
        // =========================
        Menu(

            id = 6,

            name = "Aren Apple Pie",

            price = 18000,

            category = "Dessert",

            image = R.drawable.dessert1,

            rating = 4.5,

            reviews = 74,

            description =
                "Apple pie with sweet aren sugar filling."
        ),

        Menu(

            id = 7,

            name = "Salt Bread Original",

            price = 12000,

            category = "Dessert",

            image = R.drawable.dessert2,

            rating = 4.4,

            reviews = 62,

            description =
                "Soft buttery bread with savory sea salt topping."
        ),

        Menu(

            id = 8,

            name = "Donut Almond",

            price = 14000,

            category = "Dessert",

            image = R.drawable.dessert3,

            isBestSeller = true,

            rating = 4.8,

            reviews = 240,

            description =
                "Fluffy donut topped with crunchy almond slices."
        ),

        Menu(

            id = 9,

            name = "Roti Gulung Aren",

            price = 15000,

            category = "Dessert",

            image = R.drawable.dessert4,

            rating = 4.6,

            reviews = 103,

            description =
                "Soft bread roll filled with aren sugar cream."
        ),

        Menu(

            id = 10,

            name = "Roti Sisir Coklat",

            price = 14000,

            category = "Dessert",

            image = R.drawable.dessert5,

            rating = 4.5,

            reviews = 91,

            description =
                "Chocolate pull-apart bread with soft texture."
        ),

        Menu(

            id = 16,

            name = "Croissant Butter",

            price = 18000,

            category = "Dessert",

            image = R.drawable.dessert6,

            rating = 4.7,

            reviews = 132,

            description =
                "Classic buttery croissant with crispy layers."
        ),

        Menu(

            id = 17,

            name = "Choco Croissant",

            price = 20000,

            category = "Dessert",

            image = R.drawable.dessert7,

            isBestSeller = true,

            rating = 4.9,

            reviews = 310,

            description =
                "Premium croissant filled with melted chocolate."
        ),

        Menu(

            id = 18,

            name = "Cheese Cake Slice",

            price = 22000,

            category = "Dessert",

            image = R.drawable.dessert8,

            rating = 4.8,

            reviews = 185,

            description =
                "Creamy cheesecake with smooth cheesy flavor."
        ),

        Menu(

            id = 19,

            name = "Banana Cake",

            price = 17000,

            category = "Dessert",

            image = R.drawable.dessert9,

            rating = 4.4,

            reviews = 69,

            description =
                "Moist banana cake with natural sweet aroma."
        ),

        Menu(

            id = 20,

            name = "Tiramisu Cup",

            price = 25000,

            category = "Dessert",

            image = R.drawable.dessert10,

            isBestSeller = true,

            rating = 4.9,

            reviews = 420,

            description =
                "Classic tiramisu dessert with coffee cream layers."
        )
    )
}