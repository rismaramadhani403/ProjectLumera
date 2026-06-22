package com.praktikum.lumera.data

import androidx.compose.runtime.mutableStateOf
import com.praktikum.lumera.model.User

object SessionManager {

    val currentUser = mutableStateOf<User?>(null)
}