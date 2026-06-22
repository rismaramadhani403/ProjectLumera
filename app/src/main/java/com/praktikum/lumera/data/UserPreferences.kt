package com.praktikum.lumera.datastore

import android.content.Context

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey

import androidx.datastore.preferences.preferencesDataStore

import com.praktikum.lumera.model.AddressItem
import com.praktikum.lumera.model.User

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(

    name = "user_preferences"
)

// =========================
// SEPARATOR UNTUK SERIALISASI ADDRESS
// (pakai control character biar gak bentrok
// sama input user biasa)
// =========================
private const val ADDRESS_FIELD_SEP = "\u001F"
private const val ADDRESS_ITEM_SEP = "\u001E"

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

        val PASSWORD =
            stringPreferencesKey("password")

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

        // =========================
        // SAVED ADDRESSES
        // =========================
        val ADDRESS_KEY =
            stringPreferencesKey(
                "address_key"
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

            pref[PASSWORD] =
                user.password

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

                password =
                    pref[PASSWORD] ?: "",

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

            pref[IS_LOGIN] = false
        }
    }

    // =========================
    // SAVE ADDRESSES
    // =========================
    suspend fun saveAddresses(

        addresses: List<AddressItem>
    ) {

        context.dataStore.edit { pref ->

            pref[ADDRESS_KEY] =

                addresses.joinToString(
                    separator = ADDRESS_ITEM_SEP
                ) { item ->

                    listOf(
                        item.id,
                        item.title,
                        item.address
                    ).joinToString(
                        separator = ADDRESS_FIELD_SEP
                    )
                }
        }
    }

    // =========================
    // GET ADDRESSES
    // =========================
    val getAddresses: Flow<List<AddressItem>> =

        context.dataStore.data.map { pref ->

            val raw =
                pref[ADDRESS_KEY] ?: ""

            if (raw.isEmpty()) {

                emptyList()

            } else {

                raw.split(ADDRESS_ITEM_SEP)

                    .mapNotNull { entry ->

                        val parts =
                            entry.split(ADDRESS_FIELD_SEP)

                        if (parts.size == 3) {

                            AddressItem(
                                id = parts[0].toIntOrNull() ?: 0,
                                title = parts[1],
                                address = parts[2]
                            )

                        } else {

                            null
                        }
                    }
            }
        }
}