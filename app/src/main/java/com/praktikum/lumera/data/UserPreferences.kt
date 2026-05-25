package com.praktikum.lumera.datastore

import android.content.Context

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey

import androidx.datastore.preferences.preferencesDataStore

import com.praktikum.lumera.model.User

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(

    name = "user_preferences"
)

class UserPreferences(

    private val context: Context
) {

    companion object {

        // =========================
        // USER DATA
        // =========================
        val USERNAME =
            stringPreferencesKey("username")

        val EMAIL =
            stringPreferencesKey("email")

        val ROLE =
            stringPreferencesKey("role")

        val IS_LOGIN =
            booleanPreferencesKey("is_login")

        // =========================
        // FAVORITE
        // =========================
        val FAVORITE_KEY =
            stringPreferencesKey(
                "favorite_key"
            )
    }

    // =========================
    // SAVE USER
    // =========================
    suspend fun saveUser(

        user: User
    ) {

        context.dataStore.edit { pref ->

            pref[USERNAME] =
                user.name

            pref[EMAIL] =
                user.email

            pref[ROLE] =
                user.role

            pref[IS_LOGIN] = true
        }
    }

    // =========================
    // GET USER
    // =========================
    val getUser: Flow<User> =

        context.dataStore.data.map { pref ->

            User(

                name =
                    pref[USERNAME] ?: "",

                email =
                    pref[EMAIL] ?: "",

                role =
                    pref[ROLE] ?: "Customer"
            )
        }

    // =========================
    // CHECK LOGIN
    // =========================
    val isLogin: Flow<Boolean> =

        context.dataStore.data.map { pref ->

            pref[IS_LOGIN] ?: false
        }

    // =========================
    // SAVE FAVORITES
    // =========================
    suspend fun saveFavorites(

        favoriteIds: List<Int>
    ) {

        context.dataStore.edit { pref ->

            pref[FAVORITE_KEY] =

                favoriteIds.joinToString(",")
        }
    }

    // =========================
    // GET FAVORITES
    // =========================
    val getFavorites: Flow<List<Int>> =

        context.dataStore.data.map { pref ->

            val favoriteString =

                pref[FAVORITE_KEY] ?: ""

            if (favoriteString.isEmpty()) {

                emptyList()

            } else {

                favoriteString.split(",")

                    .map {

                        it.toInt()
                    }
            }
        }

    // =========================
    // LOGOUT
    // =========================
    suspend fun logout() {

        context.dataStore.edit { pref ->

            pref.clear()
        }
    }
}