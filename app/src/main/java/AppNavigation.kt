package com.praktikum.lumera.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
import com.praktikum.lumera.screens.register.RegisterScreen
import com.praktikum.lumera.screens.settings.AccountSettingsScreen
import com.praktikum.lumera.screens.splash.SplashScreen
import com.praktikum.lumera.viewmodel.CartViewModel
import com.praktikum.lumera.viewmodel.MenuViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.praktikum.lumera.screens.receipt.ReceiptScreen

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

                onLogin = { username, role ->

                    if (role == "Kasir") {

                        activeCashier = username
                    }

                    currentUser = User(

                        name = username,

                        email =
                            "$username@gmail.com",

                        role = role
                    )

                    CoroutineScope(
                        Dispatchers.IO
                    ).launch {

                        userPreferences.saveUser(
                            currentUser!!
                        )
                    }

                    if (role == "Admin") {

                        navController.navigate(
                            "admin"
                        ) {

                            popUpTo("login") {

                                inclusive = true
                            }
                        }

                    } else {

                        navController.navigate(
                            "home"
                        ) {

                            popUpTo("login") {

                                inclusive = true
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

                onCheckout = {
                        selectedPayment,
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

                    onBack = {

                        navController.popBackStack()
                    },

                    cartViewModel =
                        cartViewModel
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

                        customerName =
                            customer,

                        total =
                            finalPaymentTotal,

                        paymentMethod =
                            selectedPayment,

                        cashierName =

                            if (currentUser?.role == "Kasir") {

                                currentUser?.name
                                    ?: activeCashier

                            } else {

                                activeCashier
                            },

                        date =
                            System.currentTimeMillis()
                                .toString()
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

                        it.menu.price * it.quantity
                    },

                discount =
                    4700,

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

            ProfileScreen(

                userPreferences =
                    userPreferences,

                onBackClick = {

                    navController.popBackStack()
                },

                onLogoutClick = {

                    CoroutineScope(
                        Dispatchers.IO
                    ).launch {

                        userPreferences.saveUser(

                            User(
                                name = "",
                                email = "",
                                role = ""
                            )
                        )
                    }

                    navController.navigate(
                        "login"
                    ) {

                        popUpTo(0)
                    }
                },

                onHistoryClick = {

                    navController.navigate(
                        "history"
                    )
                },

                onAddressClick = {

                    navController.navigate(
                        "address"
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
                }
            )
        }
    }
}