package com.praktikum.lumera.viewmodel

import androidx.compose.runtime.mutableStateListOf

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.praktikum.lumera.datastore.UserPreferences

import com.praktikum.lumera.model.CartItem
import com.praktikum.lumera.model.Menu
import com.praktikum.lumera.model.Transaction

import kotlinx.coroutines.launch

class CartViewModel(

    private val userPreferences:
    UserPreferences
) : ViewModel() {

    // =========================
    // CART LIST
    // =========================
    val cart =
        mutableStateListOf<CartItem>()

    // =========================
    // TRANSACTION LIST
    // =========================
    val transactions =
        mutableStateListOf<Transaction>()

    // =========================
    // FAVORITE MENU
    // =========================
    val favoriteMenus =
        mutableStateListOf<Menu>()

    // =========================
    // ADD TO CART
    // =========================
    fun addToCart(

        menu: Menu,

        quantity: Int,

        size: String = "",

        ice: String = "",

        sugar: String = "",

        extraShot: Boolean = false,

        notes: String = ""
    ) {

        val existing = cart.find {

            it.menu.id == menu.id &&
                    it.size == size &&
                    it.ice == ice &&
                    it.sugar == sugar &&
                    it.extraShot == extraShot &&
                    it.notes == notes
        }

        if (existing != null) {

            val index =
                cart.indexOf(existing)

            cart[index] = existing.copy(

                quantity =
                    existing.quantity + quantity
            )

        } else {

            cart.add(

                CartItem(

                    menu = menu,

                    quantity = quantity,

                    size = size,

                    ice = ice,

                    sugar = sugar,

                    extraShot = extraShot,

                    notes = notes
                )
            )
        }
    }

    // =========================
    // REMOVE ITEM
    // =========================
    fun removeFromCart(

        item: CartItem
    ) {

        cart.remove(item)
    }

    // =========================
    // INCREASE QUANTITY
    // =========================
    fun increaseQuantity(

        item: CartItem
    ) {

        val index =
            cart.indexOf(item)

        if (index != -1) {

            val current = cart[index]

            cart[index] = current.copy(

                quantity =
                    current.quantity + 1
            )
        }
    }

    // =========================
    // DECREASE QUANTITY
    // =========================
    fun decreaseQuantity(

        item: CartItem
    ) {

        val index =
            cart.indexOf(item)

        if (index != -1) {

            val current = cart[index]

            if (current.quantity > 1) {

                cart[index] = current.copy(

                    quantity =
                        current.quantity - 1
                )
            }
        }
    }

    // =========================
    // TOGGLE FAVORITE
    // =========================
    fun toggleFavorite(

        menu: Menu
    ) {

        if (favoriteMenus.contains(menu)) {

            favoriteMenus.remove(menu)

        } else {

            favoriteMenus.add(menu)
        }

        saveFavorites()
    }

    // =========================
    // CHECK FAVORITE
    // =========================
    fun isFavorite(

        menu: Menu
    ): Boolean {

        return favoriteMenus.contains(menu)
    }

    // =========================
    // CLEAR FAVORITES
    // =========================
    fun clearFavorites() {

        favoriteMenus.clear()

        saveFavorites()
    }

    // =========================
    // SAVE FAVORITES
    // =========================
    private fun saveFavorites() {

        viewModelScope.launch {

            userPreferences.saveFavorites(

                favoriteMenus.map {

                    it.id
                }
            )
        }
    }

    // =========================
    // CLEAR CART
    // =========================
    fun clearCart() {

        cart.clear()
    }

    // =========================
    // TOTAL PRICE
    // =========================
    fun getTotal(): Int {

        return cart.sumOf {

            it.menu.price * it.quantity
        }
    }

    // =========================
    // ADD TRANSACTION
    // =========================
    fun addTransaction(

        transaction: Transaction
    ) {

        transactions.add(transaction)
    }
}