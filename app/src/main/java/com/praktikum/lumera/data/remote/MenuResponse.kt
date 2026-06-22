package com.praktikum.lumera.data.remote

import com.google.gson.annotations.SerializedName
import com.praktikum.lumera.model.Menu

/**
 * DTO yang merepresentasikan satu baris JSON dari get_menu.php
 * (hasil SELECT * FROM menu di database lumera_coffee).
 *
 * Tipe data disesuaikan dengan tipe kolom di MySQL:
 * - harga: INT  → Int
 * - stok:  INT  → Int?
 * - nama_menu, kategori, deskripsi, gambar → String
 */
data class MenuResponse(

    @SerializedName("id_menu")
    val idMenu: Int,

    @SerializedName("nama_menu")
    val namaMenu: String,

    @SerializedName("harga")
    val harga: Int,

    @SerializedName("stok")
    val stok: Int? = null,

    @SerializedName("kategori")
    val kategori: String,

    @SerializedName("deskripsi")
    val deskripsi: String? = null,

    @SerializedName("gambar")
    val gambar: String? = null

) {
    fun toMenu(baseUrl: String): Menu {

        val imageUrl =
            if (gambar.isNullOrBlank()) ""
            else baseUrl + "assets/images/" + gambar

        return Menu(
            id       = idMenu,
            name     = namaMenu,
            price    = harga,
            category = kategori,
            image    = 0,
            imageUrl = imageUrl,
            description = deskripsi ?: ""
        )
    }
}