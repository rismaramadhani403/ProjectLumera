package com.praktikum.lumera.data

import com.praktikum.lumera.R
import com.praktikum.lumera.model.Menu

object MenuData {

    val menuList = listOf(

        // Coffee
        Menu(1,"Mocha Caramel",25000,"Coffee", R.drawable.kopi1),
        Menu(2,"Toffee Nut Latte",18000,"Coffee", R.drawable.kopi2),
        Menu(3,"Kopi Kenangan Mantan",19000,"Coffee", R.drawable.kopi3),
        Menu(4,"Caramel Latte",25000,"Coffee", R.drawable.kopi4),
        Menu(5,"Hazelnut Latte",25000,"Coffee", R.drawable.kopi5),

        // Dessert
        Menu(6,"Aren Apple Pie",18000,"Dessert", R.drawable.dessert1),
        Menu(7,"Salt Bread Original",12000,"Dessert", R.drawable.dessert2),
        Menu(8,"Donut Almond",14000,"Dessert", R.drawable.dessert3),
        Menu(9,"Roti Gulung Aren",15000,"Dessert", R.drawable.dessert4),
        Menu(10,"Roti Sisir Coklat",14000,"Dessert", R.drawable.dessert5)
    )
}