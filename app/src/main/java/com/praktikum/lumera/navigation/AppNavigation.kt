package com.praktikum.lumera.navigation

import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateListOf
import com.praktikum.lumera.model.CartItem
import com.praktikum.lumera.model.Menu
import com.praktikum.lumera.screens.cart.CartScreen
import com.praktikum.lumera.screens.home.HomeScreen
import com.praktikum.lumera.screens.payment.PaymentScreen
import com.praktikum.lumera.screens.receipt.ReceiptScreen
import com.praktikum.lumera.screens.login.LoginScreen
import com.praktikum.lumera.screens.detail.DetailScreen
import com.praktikum.lumera.screens.register.RegisterScreen

@Composable
fun AppNavigation() {

    val backStack = remember { mutableStateListOf<Screen>(Screen.Login) }
    val cart = remember { mutableStateListOf<CartItem>() }

    val currentScreen = backStack.last()

    when (currentScreen) {

        is Screen.Login -> LoginScreen(
            onLogin = {
                backStack.add(Screen.Home)
            },
            onRegisterClick = {
                backStack.add(Screen.Register)
            }
        )

        is Screen.Register -> RegisterScreen(
            onBack = {
                backStack.removeLastOrNull()
            }
        )

        is Screen.Home -> HomeScreen(
            cart = cart,
            onCartClick = {
                backStack.add(Screen.Cart)
            },
            onSelectMenu = { menu: Menu ->
                backStack.add(Screen.Detail(menu.name, menu.price))
            }
        )

        is Screen.Detail -> DetailScreen(
            itemName = currentScreen.itemName,
            price = currentScreen.price,
            onBack = { backStack.removeLastOrNull() },
            onAddToCart = {
                backStack.add(Screen.Cart)
            }
        )

        is Screen.Cart -> CartScreen(
            cart = cart,
            onBack = { backStack.removeLastOrNull() },
            onCheckout = {
                backStack.add(Screen.Payment)
            }
        )

        is Screen.Payment -> PaymentScreen(
            total = cart.sumOf { it.menu.price * it.quantity },
            onBack = { backStack.removeLastOrNull() },
            onPay = {
                backStack.add(Screen.Receipt)
            }
        )

        is Screen.Receipt -> ReceiptScreen(
            cart = cart,
            onFinish = {
                cart.clear()
                backStack.clear()
                backStack.add(Screen.Login)
            },
            onBackToHome = {
                backStack.clear()
                backStack.add(Screen.Home)
            }
        )
    }
}