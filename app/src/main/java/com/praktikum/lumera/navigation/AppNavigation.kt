package com.praktikum.lumera.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.praktikum.lumera.data.SessionManager
import com.praktikum.lumera.datastore.UserPreferences
import com.praktikum.lumera.model.Menu
import com.praktikum.lumera.model.Transaction
import com.praktikum.lumera.model.User
import com.praktikum.lumera.screens.address.DeliveryAddressScreen
import com.praktikum.lumera.screens.admin.AdminScreen
import com.praktikum.lumera.screens.cart.CartScreen
import com.praktikum.lumera.screens.detail.DetailScreen
import com.praktikum.lumera.screens.history.OrderHistoryScreen
import com.praktikum.lumera.screens.home.HomeScreen
import com.praktikum.lumera.screens.login.LoginScreen
import com.praktikum.lumera.screens.notification.NotificationScreen
import com.praktikum.lumera.screens.onboarding.OnboardingScreen
import com.praktikum.lumera.screens.payment.PaymentScreen
import com.praktikum.lumera.screens.paymentmethod.PaymentMethodScreen
import com.praktikum.lumera.screens.profile.ProfileScreen
import com.praktikum.lumera.screens.receipt.ReceiptScreen
import com.praktikum.lumera.screens.register.RegisterScreen
import com.praktikum.lumera.screens.settings.AccountSettingsScreen
import com.praktikum.lumera.screens.splash.SplashScreen
import com.praktikum.lumera.viewmodel.CartViewModel
import com.praktikum.lumera.viewmodel.MenuViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AppNavigation(

    cartViewModel: CartViewModel,

    menuViewModel: MenuViewModel
) {

    // =========================
    // NAV CONTROLLER
    // =========================
    val navController =
        rememberNavController()

    // =========================
    // CONTEXT
    // =========================
    val context =
        LocalContext.current

    // =========================
    // DATASTORE
    // =========================
    val userPreferences =
        UserPreferences(context)
    val savedUser by userPreferences
        .getUser
        .collectAsState(
            initial = User(
                "",
                "",
                "",
                "Customer"
            )
        )

    // =========================
    // CURRENT USER
    // =========================
    var currentUser by remember {

        mutableStateOf<User?>(null)
    }

    // =========================
    // ACTIVE CASHIER
    // =========================
    var activeCashier by remember {

        mutableStateOf("Kasir Lumera")
    }

    // =========================
    // SELECTED MENU
    // =========================
    var selectedMenu by remember {

        mutableStateOf<Menu?>(null)
    }

    // =========================
    // PAYMENT METHOD
    // =========================
    var paymentMethod by remember {

        mutableStateOf("Cash")
    }

    // =========================
    // FINAL PAYMENT TOTAL
    // =========================
    var finalPaymentTotal by remember {

        mutableStateOf(0)
    }

    // =========================
    // CUSTOMER NAME
    // =========================
    var customerName by remember {

        mutableStateOf("")
    }

    // =========================
    // NAV HOST
    // =========================
    NavHost(

        navController = navController,

        startDestination = "splash"
    ) {

        // =========================
        // SPLASH SCREEN
        // =========================
        composable("splash") {

            SplashScreen(

                onFinished = {

                    navController.navigate(
                        "onboarding"
                    ) {

                        popUpTo("splash") {

                            inclusive = true
                        }
                    }
                }
            )
        }

        // =========================
        // ONBOARDING SCREEN
        // =========================
        composable("onboarding") {

            OnboardingScreen(

                onNextClick = {

                    navController.navigate(
                        "login"
                    ) {

                        popUpTo("onboarding") {

                            inclusive = true
                        }
                    }
                }
            )
        }

        // =========================
        // LOGIN SCREEN
        // =========================
        composable("login") {

            LoginScreen(

                onBackClick = {

                    navController.popBackStack()
                },

                onLogin = { username, password, role ->

                    when (role) {

                        "Admin" -> {

                            if (password == "admin123") {

                                currentUser = User(
                                    name = username,
                                    email = "",
                                    password = password,
                                    role = "Admin"
                                )

                                SessionManager.currentUser.value =
                                    currentUser

                                navController.navigate("admin") {

                                    popUpTo("login") {
                                        inclusive = true
                                    }
                                }
                            }
                        }

                        "Kasir" -> {

                            if (password == "kasir123") {

                                activeCashier = username

                                currentUser = User(
                                    name = username,
                                    email = "",
                                    password = password,
                                    role = "Kasir"
                                )

                                SessionManager.currentUser.value =
                                    currentUser

                                navController.navigate("home") {

                                    popUpTo("login") {
                                        inclusive = true
                                    }
                                }
                            }
                        }

                        "Customer" -> {

                            if (
                                username == savedUser.name &&
                                password == savedUser.password
                            ) {

                                currentUser = savedUser

                                SessionManager.currentUser.value =
                                    currentUser

                                navController.navigate("home") {

                                    popUpTo("login") {
                                        inclusive = true
                                    }
                                }
                            }
                        }
                    }
                },

                onRegisterClick = {

                    navController.navigate(
                        "register"
                    )
                }
            )
        }

        // =========================
        // REGISTER SCREEN
        // =========================
        composable("register") {

            RegisterScreen(

                userPreferences = userPreferences,

                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // =========================
        // HOME SCREEN
        // =========================
        composable("home") {

            HomeScreen(

                userPreferences =
                    userPreferences,

                menus =
                    menuViewModel.menus,

                cart =
                    cartViewModel.cart,

                cartViewModel =
                    cartViewModel,

                onCartClick = {

                    navController.navigate(
                        "cart"
                    )
                },

                onSelectMenu = { menu ->

                    selectedMenu = menu

                    navController.navigate(
                        "detail"
                    )
                },

                onProfileClick = {

                    navController.navigate(
                        "profile"
                    )
                },

                onBack = {

                    navController.navigate(
                        "login"
                    ) {

                        popUpTo("home") {

                            inclusive = true
                        }
                    }
                }
            )
        }

        // =========================
        // ADMIN SCREEN
        // =========================
        composable("admin") {

            AdminScreen(

                transactions =
                    cartViewModel.transactions,

                menuViewModel =
                    menuViewModel,

                onBack = {

                    navController.navigate(
                        "login"
                    ) {

                        popUpTo("admin") {

                            inclusive = true
                        }
                    }
                }
            )
        }

        // =========================
        // CART SCREEN
        // =========================
        composable("cart") {

            CartScreen(

                cart =
                    cartViewModel.cart,

                cartViewModel =
                    cartViewModel,

                onBack = {

                    navController.popBackStack()
                },

                onCheckout = { selectedPayment,
                               finalTotal ->

                    paymentMethod = selectedPayment

                    finalPaymentTotal = finalTotal

                    navController.navigate(
                        "payment"
                    )
                }
            )
        }

        // =========================
        // DETAIL SCREEN
        // =========================
        composable("detail") {

            selectedMenu?.let { menu ->

                DetailScreen(

                    menu = menu,

                    cartViewModel = cartViewModel,

                    onBack = {
                        navController.popBackStack()
                    },

                    onCartClick = {
                        navController.navigate("cart")
                    }
                )
            }
        }

        // =========================
        // PAYMENT SCREEN
        // =========================
        composable("payment") {

            PaymentScreen(

                total =
                    finalPaymentTotal,

                customerName =

                    if (currentUser?.role == "Customer") {

                        currentUser?.name ?: ""

                    } else {

                        ""
                    },

                selectedPaymentFromCart =
                    paymentMethod,

                onBack = {

                    navController.popBackStack()
                },

                onPay = { selectedPayment, customer ->

                    paymentMethod =
                        selectedPayment

                    customerName =
                        customer

                    val transaction = Transaction(

                        customerName = customer,

                        items = cartViewModel.cart.joinToString {

                            it.menu.name
                        },

                        total = finalPaymentTotal,

                        paymentMethod = selectedPayment,

                        cashierName =

                            if (currentUser?.role == "Kasir") {

                                currentUser?.name
                                    ?: activeCashier

                            } else {

                                activeCashier
                            },

                        date =
                            SimpleDateFormat(
                                "dd MMM yyyy, HH:mm",
                                Locale("in", "ID")
                            ).format(Date())
                    )
                    cartViewModel.addTransaction(
                        transaction
                    )

                    navController.navigate(
                        "receipt"
                    )
                }
            )
        }

        // =========================
        // RECEIPT SCREEN
        // =========================
        composable("receipt") {

            ReceiptScreen(

                cart =
                    cartViewModel.cart,

                paymentMethod =
                    paymentMethod,

                customerName =
                    customerName,

                cashierName =

                    if (currentUser?.role == "Kasir") {

                        currentUser?.name
                            ?: activeCashier

                    } else {

                        activeCashier
                    },

                subtotal =
                    cartViewModel.cart.sumOf {

                        (it.menu.price + it.customPrice) * it.quantity
                    },
                discount =
                    0,

                deliveryFee =
                    0,

                total =
                    finalPaymentTotal,

                onFinish = {

                    cartViewModel.clearCart()

                    navController.navigate(
                        "home"
                    )
                },

                onBackToHome = {

                    cartViewModel.clearCart()

                    navController.navigate(
                        "home"
                    ) {

                        popUpTo("receipt") {

                            inclusive = true
                        }
                    }
                }
            )
        }

        // =========================
        // ORDER HISTORY SCREEN
        // =========================
        composable("history") {

            OrderHistoryScreen(

                transactions =
                    cartViewModel.transactions,

                onBack = {

                    navController.popBackStack()
                }
            )
        }

        // =========================
        // DELIVERY ADDRESS SCREEN
        // =========================
        composable("address") {

            DeliveryAddressScreen(

                userPreferences =
                    userPreferences,

                onBack = {

                    navController.popBackStack()
                }
            )
        }

        // =========================
        // PAYMENT METHOD SCREEN
        // =========================
        composable("payment_method") {

            PaymentMethodScreen(

                onBack = {

                    navController.popBackStack()
                }
            )
        }

        // =========================
        // NOTIFICATION SCREEN
        // =========================
        composable("notification") {

            NotificationScreen(

                onBack = {

                    navController.popBackStack()
                }
            )
        }

        // =========================
        // SETTINGS SCREEN
        // =========================
        composable("settings") {

            AccountSettingsScreen(

                onBack = {

                    navController.popBackStack()
                }
            )
        }

        // =========================
        // PROFILE SCREEN
        // =========================
        composable("profile") {

            // Hitung statistik customer dari data CartViewModel,
            // difilter berdasarkan nama user yang lagi login.
            // (Transaction belum punya field id/email customer,
            // jadi pencocokan masih pakai nama)
            val orderCount =
                cartViewModel.transactions.count {

                    it.customerName ==
                            SessionManager.currentUser.value?.name
                }

            val favoriteCount =
                cartViewModel.favoriteMenus.size

            ProfileScreen(

                userPreferences =
                    userPreferences,

                orderCount =
                    orderCount,

                favoriteCount =
                    favoriteCount,

                onBackClick = {

                    navController.popBackStack()
                },

                onLogoutClick = {

                    currentUser = null

                    SessionManager.currentUser.value = null

                    customerName = ""

                    selectedMenu = null

                    paymentMethod = "Cash"

                    finalPaymentTotal = 0

                    CoroutineScope(
                        Dispatchers.IO
                    ).launch {

                        userPreferences.logout()
                    }

                    navController.navigate("login") {

                        popUpTo(0)
                    }
                },

                onHistoryClick = {

                    navController.navigate(
                        "history"
                    )
                },

                onPaymentMethodClick = {

                    navController.navigate(
                        "payment_method"
                    )
                },

                onNotificationClick = {

                    navController.navigate(
                        "notification"
                    )
                },

                onSettingsClick = {

                    navController.navigate(
                        "settings"
                    )
                },

                onAddressClick = {

                    navController.navigate(
                        "address"
                    )
                }
            )
        }
    }
}