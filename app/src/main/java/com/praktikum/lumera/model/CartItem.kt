package com.praktikum.lumera.model

data class CartItem(

    val menu: Menu,

    var quantity: Int,

    // =========================
    // CUSTOMIZATION
    // =========================
    val size: String = "",

    val ice: String = "",

    val sugar: String = "",

    val extraShot: Boolean = false,

    val notes: String = ""
)