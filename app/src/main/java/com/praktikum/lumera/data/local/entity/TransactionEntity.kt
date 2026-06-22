package com.praktikum.lumera.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val customerName: String,

    val cashierName: String,

    val items: String,

    val total: Int,

    val paymentMethod: String,

    val date: String
)