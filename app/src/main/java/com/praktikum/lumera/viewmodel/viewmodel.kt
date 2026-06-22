package com.praktikum.lumera.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.praktikum.lumera.data.local.dao.TransactionDao
import com.praktikum.lumera.data.local.entity.TransactionEntity
import com.praktikum.lumera.datastore.UserPreferences
import com.praktikum.lumera.model.CartItem
import com.praktikum.lumera.model.Menu
import com.praktikum.lumera.model.Transaction

import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(

    private val userPreferences: UserPreferences,

    private val transactionDao: TransactionDao

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
    // FAVORITE MENU (Menu lengkap, hasil pencocokan favoriteIds
    // dengan daftar menu API terkini)
    // =========================
    val favoriteMenus =
        mutableStateListOf<Menu>()

    // =========================
    // FAVORITE MENU ID (disimpan terpisah dari objek Menu,
    // supaya tidak bergantung pada data dummy MenuData lagi)
    // =========================
    private val favoriteIds =
        mutableStateListOf<Int>()

    init {

        loadTransactions()

        loadFavoriteIds()
    }

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

        notes: String = "",

        customPrice: Int = 0
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

                    notes = notes,

                    customPrice = customPrice
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

        menu: Menu,

        allMenus: List<Menu> = favoriteMenus
    ) {

        if (favoriteIds.contains(menu.id)) {

            favoriteIds.remove(menu.id)

        } else {

            favoriteIds.add(menu.id)
        }

        refreshFavoriteMenus(allMenus.ifEmpty { listOf(menu) })

        saveFavorites()
    }

    // =========================
    // CHECK FAVORITE
    // =========================
    fun isFavorite(

        menu: Menu
    ): Boolean {

        return favoriteIds.contains(menu.id)
    }

    // =========================
    // CLEAR FAVORITES
    // =========================
    fun clearFavorites() {

        favoriteIds.clear()

        favoriteMenus.clear()

        saveFavorites()
    }

    // =========================
    // SAVE FAVORITES
    // =========================
    private fun saveFavorites() {

        viewModelScope.launch {

            userPreferences.saveFavorites(

                favoriteIds.toList()
            )
        }
    }

    // =========================
    // LOAD FAVORITE IDS (dari DataStore, saat ViewModel dibuat)
    // =========================
    private fun loadFavoriteIds() {

        viewModelScope.launch {

            val savedIds = withContext(Dispatchers.IO) {

                userPreferences.getFavorites.first()
            }

            favoriteIds.clear()

            favoriteIds.addAll(savedIds)
        }
    }

    // =========================
    // REFRESH FAVORITE MENUS (Week 11)
    //
    // Dipanggil dari UI (misalnya lewat LaunchedEffect di HomeScreen)
    // setiap kali daftar menu dari API (MenuViewModel.menus) berubah,
    // supaya favoriteMenus selalu mencocokkan favoriteIds dengan data
    // menu TERKINI dari server -- bukan data dummy MenuData yang lama.
    // =========================
    fun refreshFavoriteMenus(

        allMenus: List<Menu>
    ) {

        val matched =
            allMenus.filter { menu ->

                menu.id in favoriteIds
            }

        favoriteMenus.clear()

        favoriteMenus.addAll(matched)
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

            (it.menu.price + it.customPrice) * it.quantity
        }
    }

    // =========================
    // ADD TRANSACTION
    // =========================
    fun addTransaction(

        transaction: Transaction
    ) {

        transactions.add(transaction)

        viewModelScope.launch(Dispatchers.IO) {

            transactionDao.insertTransaction(

                transaction.toEntity()
            )
        }
    }

    private fun loadTransactions() {

        viewModelScope.launch {

            val savedTransactions = withContext(Dispatchers.IO) {

                transactionDao.getAllTransactions()
                    .map {

                        it.toTransaction()
                    }
            }

            transactions.clear()

            transactions.addAll(savedTransactions)
        }
    }

    private fun Transaction.toEntity(): TransactionEntity {

        return TransactionEntity(

            customerName = customerName,

            cashierName = cashierName,

            items = items,

            total = total,

            paymentMethod = paymentMethod,

            date = date
        )
    }

    private fun TransactionEntity.toTransaction(): Transaction {

        return Transaction(

            customerName = customerName,

            items = items,

            total = total,

            paymentMethod = paymentMethod,

            cashierName = cashierName,

            date = date
        )
    }
}