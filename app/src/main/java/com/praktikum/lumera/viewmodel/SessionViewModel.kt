package com.praktikum.lumera.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.praktikum.lumera.datastore.UserPreferences
import com.praktikum.lumera.model.User

import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

import javax.inject.Inject

/**
 * ViewModel untuk mengelola session pengguna.
 * Menggunakan Hilt untuk meng-inject UserPreferences
 * yang berasal dari DataStore.
 */
@HiltViewModel
class SessionViewModel @Inject constructor(
    private val userPreferences: UserPreferences
) : ViewModel() {

    val currentUser = userPreferences.getUser
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = User(
                name = "",
                email = "",
                role = "Customer"
            )
        )

    val isLogin = userPreferences.isLogin
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )
}