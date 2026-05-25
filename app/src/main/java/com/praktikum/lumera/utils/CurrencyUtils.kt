package com.praktikum.lumera.utils

import java.text.NumberFormat
import java.util.Locale

fun formatRupiah(amount: Int): String {

    val localeID = Locale("id", "ID")

    val format =
        NumberFormat.getNumberInstance(
            localeID
        )

    return "Rp ${format.format(amount)}"
}