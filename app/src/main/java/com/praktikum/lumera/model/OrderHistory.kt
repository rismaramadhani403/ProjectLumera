package com.praktikum.lumera.model

data class OrderHistory(

    val id: Int,

    val title: String,

    val total: Int,

    val paymentMethod: String,

    val date: String,

    val status: String
)