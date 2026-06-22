package com.praktikum.lumera.model

data class Menu(

    // =========================
    // BASIC DATA
    // =========================
    val id: Int,

    val name: String,

    val price: Int,

    val category: String,

    val image: Int,

    // URL gambar dari server (API), dipakai saat data berasal dari database.
    // Kalau kosong, berarti pakai drawable lokal (image) seperti sebelumnya.
    val imageUrl: String = "",

    // =========================
    // PREMIUM FEATURES
    // =========================
    val isBestSeller: Boolean = false,

    val rating: Double = 4.5,

    val reviews: Int = 50,

    val description: String = ""
)