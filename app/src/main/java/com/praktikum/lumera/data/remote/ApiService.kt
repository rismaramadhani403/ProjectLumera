package com.praktikum.lumera.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface Retrofit yang merepresentasikan endpoint-endpoint PHP
 * di folder "lumera-coffee/api/".
 *
 * Retrofit otomatis mengubah pemanggilan fungsi di bawah ini menjadi
 * HTTP request ke server, lalu mem-parsing response JSON menjadi
 * objek Kotlin (lewat Gson).
 */
interface ApiService {

    /**
     * Memanggil: GET api/get_menu.php
     * Contoh URL lengkap: http://10.0.2.2/lumera-coffee/api/get_menu.php
     *
     * @param cari    opsional, untuk fitur search nama menu (param ?cari=)
     * @param kategori opsional, untuk filter kategori (param ?kategori=)
     */
    @GET("api/get_menu.php")
    suspend fun getMenu(
        @Query("cari") cari: String? = null,
        @Query("kategori") kategori: String? = null
    ): List<MenuResponse>
}