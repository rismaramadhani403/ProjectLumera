package com.praktikum.lumera

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room

import com.praktikum.lumera.data.local.database.LumeraDatabase
import com.praktikum.lumera.datastore.UserPreferences

import com.praktikum.lumera.navigation.AppNavigation
import com.praktikum.lumera.ui.theme.LUMERATheme

import com.praktikum.lumera.viewmodel.CartViewModel
import com.praktikum.lumera.viewmodel.MenuViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(

        savedInstanceState: Bundle?
    ) {

        super.onCreate(savedInstanceState)

        // =========================
        // USER PREFERENCES
        // =========================
        val userPreferences =

            UserPreferences(this)

        val database =

            Room.databaseBuilder(
                this,
                LumeraDatabase::class.java,
                "lumera_database"
            ).fallbackToDestructiveMigration()
                .build()

        // =========================
        // CART VIEWMODEL
        // =========================
        val cartViewModel =

            CartViewModel(
                userPreferences,
                database.transactionDao()
            )

        // =========================
        // MENU VIEWMODEL
        // =========================
        val menuViewModel =

            MenuViewModel()

        setContent {

            LUMERATheme {

                AppNavigation(

                    cartViewModel =
                        cartViewModel,

                    menuViewModel =
                        menuViewModel
                )
            }
        }
    }
}
