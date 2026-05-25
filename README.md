# ☕ LUMERA - Coffee Shop Mobile App

<p align="center">
  <img width="397" height="830" alt="image" src="https://github.com/user-attachments/assets/76c7c3d6-bbc2-4fc2-815a-71f1001f7c65" />
  <img width="408" height="844" alt="image" src="https://github.com/user-attachments/assets/6e617279-245e-4b88-8bab-34b7e9371fcf" />
</p>

<h3 align="center">
Modern Coffee Shop Mobile Application built using Kotlin & Jetpack Compose
</h3>

---

# 📌 About Project

LUMERA is a modern Android-based coffee shop application developed using Kotlin and Jetpack Compose. This application is designed to provide a digital ordering experience for customers with a modern, interactive, elegant, and user-friendly interface.

The application supports multiple user roles:

- 👤 Customer
- 🧑‍💼 Admin
- 💳 Cashier

LUMERA implements various modern Android Development concepts such as:

- Jetpack Compose
- MVVM Architecture
- Navigation Compose
- State Management
- DataStore Preferences
- Session Management
- CRUD System
- Payment System
- Multi Role Authentication
- Modern UI/UX Design

---

# 🎯 Project Objectives

The objectives of developing LUMERA are:

- Build a modern coffee shop mobile application
- Implement Android modern architecture
- Provide digital ordering system
- Create payment & transaction features
- Implement multi-role user system
- Improve user experience using modern UI

---

# ✨ Main Features

# 👤 Customer Features

- Login & Register
- Browse coffee & dessert menu
- Search menu
- Add item to cart
- Edit item quantity
- Delete item from cart
- Checkout order
- Select payment method
- Choose order type:
  - Dine In
  - Pickup
  - Delivery
- Apply promo/voucher
- Payment receipt
- Transaction history
- User profile
- Logout system

---

# 🧑‍💼 Admin Features

- Admin authentication
- Add product/menu
- Edit product/menu
- Delete product/menu
- Manage product data
- Transaction monitoring
- Customer monitoring
- Dashboard management

---

# 💳 Cashier Features

- Cashier authentication
- View customer order
- Payment verification
- Transaction processing
- Receipt display
- Transaction status monitoring

---

# 🚀 Application Advantages

LUMERA has several advantages:

- Modern UI using Jetpack Compose
- Clean MVVM Architecture
- Multi-role authentication system
- Dynamic cart system
- Modern payment system
- Transaction receipt system
- Responsive UI
- Reusable components
- Navigation Compose implementation
- Session management using DataStore
- User-friendly interface
- Elegant coffee shop design

---

# 🏗️ Project Architecture

LUMERA uses the **MVVM (Model View ViewModel)** architecture.

---

# 📂 Project Structure

```bash
LUMERA App
│
├── app
│   └── src
│       ├── androidTest
│       │   └── java/com/praktikum/lumera
│       │       └── ExampleInstrumentedTest.kt
│       │
│       ├── main
│       │   │
│       │   ├── AndroidManifest.xml
│       │   │
│       │   ├── java
│       │   │   ├── AppNavigation.kt
│       │   │   │
│       │   │   └── com/praktikum/lumera
│       │   │       │
│       │   │       ├── components
│       │   │       │   └── MenuItemCard.kt
│       │   │       │
│       │   │       ├── data
│       │   │       │   ├── MenuData.kt
│       │   │       │   ├── SessionManager.kt
│       │   │       │   └── UserPreferences.kt
│       │   │       │
│       │   │       ├── model
│       │   │       │   ├── CartItem.kt
│       │   │       │   ├── Menu.kt
│       │   │       │   ├── OrderHistory.kt
│       │   │       │   ├── OrderHistoryData.kt
│       │   │       │   ├── Transaction.kt
│       │   │       │   └── User.kt
│       │   │       │
│       │   │       ├── navigation
│       │   │       │   └── Routes.kt
│       │   │       │
│       │   │       ├── screens
│       │   │       │   │
│       │   │       │   ├── address
│       │   │       │   │   └── DeliveryAddressScreen.kt
│       │   │       │   │
│       │   │       │   ├── admin
│       │   │       │   │   └── AdminScreen.kt
│       │   │       │   │
│       │   │       │   ├── cart
│       │   │       │   │   └── CartScreen.kt
│       │   │       │   │
│       │   │       │   ├── detail
│       │   │       │   │   └── DetailScreen.kt
│       │   │       │   │
│       │   │       │   ├── history
│       │   │       │   │   └── OrderHistoryScreen.kt
│       │   │       │   │
│       │   │       │   ├── home
│       │   │       │   │   └── HomeScreen.kt
│       │   │       │   │
│       │   │       │   ├── login
│       │   │       │   │   └── LoginScreen.kt
│       │   │       │   │
│       │   │       │   ├── notification
│       │   │       │   │   └── NotificationScreen.kt
│       │   │       │   │
│       │   │       │   ├── onboarding
│       │   │       │   │   └── OnboardingScreen.kt
│       │   │       │   │
│       │   │       │   ├── payment
│       │   │       │   │   └── PaymentScreen.kt
│       │   │       │   │
│       │   │       │   ├── paymentmethod
│       │   │       │   │   └── PaymentMethodScreen.kt
│       │   │       │   │
│       │   │       │   ├── profile
│       │   │       │   │   └── ProfileScreen.kt
│       │   │       │   │
│       │   │       │   ├── receipt
│       │   │       │   │   └── ReceiptScreen.kt
│       │   │       │   │
│       │   │       │   ├── register
│       │   │       │   │   └── RegisterScreen.kt
│       │   │       │   │
│       │   │       │   ├── settings
│       │   │       │   │   └── AccountSettingsScreen.kt
│       │   │       │   │
│       │   │       │   └── splash
│       │   │       │       └── SplashScreen.kt
│       │   │       │
│       │   │       ├── ui/theme
│       │   │       │   ├── Color.kt
│       │   │       │   ├── Font.kt
│       │   │       │   ├── Theme.kt
│       │   │       │   └── Type.kt
│       │   │       │
│       │   │       ├── utils
│       │   │       │   └── CurrencyUtils.kt
│       │   │       │
│       │   │       ├── viewmodel
│       │   │       │   ├── MenuViewModel.kt
│       │   │       │   └── viewmodel.kt
│       │   │       │
│       │   │       └── MainActivity.kt
│       │   │
│       │   └── res
│       │       ├── drawable
│       │       ├── mipmap
│       │       ├── values
│       │       └── xml
│       │
│       └── test
│           └── java/com/praktikum/lumera
│               └── ExampleUnitTest.kt
│
├── gradle
│
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
└── README.md
```

# 🧠 Android Concepts Implemented

| Concept | Description |
|---|---|
| MVVM Architecture | UI & Business Logic Separation |
| Navigation Compose | Navigation between screens |
| State Management | Compose State Management |
| DataStore | Session storage |
| Reusable Components | Reusable UI Components |
| Material Design 3 | Modern Android UI |
| Lazy Layout | LazyColumn & LazyRow |
| Payment System | Digital transaction |
| Cart System | Dynamic order system |
| Multi Role User | Admin, Cashier, Customer |

---

# 🔄 Application Workflow

# 👤 Customer Workflow

```text
Login/Register
      ↓
Home Screen
      ↓
Choose Menu
      ↓
Add To Cart
      ↓
Checkout
      ↓
Choose Payment Method
      ↓
Payment Success
      ↓
Receipt Displayed
      ↓
Saved to History
```

---

# 🧑‍💼 Admin Workflow

```text
Admin Login
      ↓
Dashboard Admin
      ↓
Manage Menu
      ↓
Add/Edit/Delete Product
      ↓
Menu Updated
```

---

# 💳 Cashier Workflow

```text
Cashier Login
      ↓
Cashier Dashboard
      ↓
View Customer Order
      ↓
Verify Payment
      ↓
Transaction Success
      ↓
Receipt Displayed
```

---

# 🎨 UI/UX Design

LUMERA uses a modern coffee shop design concept:

- Elegant UI
- Modern Layout
- Soft Color Palette
- Minimalist Design
- Responsive Design
- Interactive Components
- User Friendly Navigation

---

# 💳 Payment System

Supported payment methods:

- QRIS
- Cash
- E-Wallet
- Debit/Credit Card

Payment features:

- Automatic total calculation
- Transaction verification
- Payment status
- Receipt generation
- Cashier information
- Customer information

---

# 🧾 Receipt System

Receipt transaction displays:

- Customer name
- Cashier name
- Ordered items
- Total payment
- Payment method
- Transaction status
- Transaction date

---

# 🔐 Session Management

LUMERA uses **DataStore Preferences** to store:

- Login session
- User role
- User data
- Application session

Advantages:

- Secure
- Asynchronous
- Modern
- Better than SharedPreferences

---

# 🧮 Technologies Used

| Technology | Function |
|---|---|
| Kotlin | Programming Language |
| Jetpack Compose | UI Framework |
| Navigation Compose | Navigation |
| ViewModel | State Management |
| DataStore | Session Storage |
| Android Studio | IDE |
| Material Design 3 | UI Design |

---

# 📦 Dependencies

```kotlin
implementation("androidx.navigation:navigation-compose")
implementation("androidx.lifecycle:lifecycle-viewmodel-compose")
implementation("androidx.datastore:datastore-preferences")
implementation("androidx.compose.material3:material3")
implementation("androidx.activity:activity-compose")
```

---

# 📸 Screenshot Documentation

> ⚠️ NOTE:
> Semua screenshot aplikasi akan ditambahkan pada bagian berikut berdasarkan POV user:
>
> - 👤 Customer
> - 🧑‍💼 Admin
> - 💳 Cashier

---

# 👤 CUSTOMER POV

# 📌 Customer Workflow

```text
Login/Register
      ↓
Home Screen
      ↓
Choose Menu
      ↓
Add To Cart
      ↓
Checkout
      ↓
Choose Payment Method
      ↓
Payment Success
      ↓
Receipt Displayed
      ↓
Saved to History
```

---

## 1️⃣ Customer Login Screen

📌 Customer login page.

<img width="403" height="851" alt="image" src="https://github.com/user-attachments/assets/f314803d-08d0-4ba2-b538-14f579f0a8af" />

---

## 2️⃣ Customer Register Screen

📌 Customer registration page.

<img width="382" height="848" alt="image" src="https://github.com/user-attachments/assets/97460f88-23e7-4278-bfa3-4b102230f10c" />

---

## 3️⃣ Customer Home Screen

📌 Displays coffee & dessert menu.

<img width="410" height="832" alt="image" src="https://github.com/user-attachments/assets/0b4cba96-e7dc-47e3-918e-e524c68936c7" />

---
<img width="414" height="844" alt="image" src="https://github.com/user-attachments/assets/3b916f35-ebc0-40a5-8605-49bf5747ad3f" />

---
<img width="414" height="839" alt="image" src="https://github.com/user-attachments/assets/f0369bcb-882f-4af6-bb4b-fba121bdf6f5" />

---

## 4️⃣ Customer Detail Menu Screen

📌 Displays selected menu details.

<img width="409" height="833" alt="image" src="https://github.com/user-attachments/assets/ec18c241-a986-4eee-be7c-4f5bcca36666" />

---
<img width="408" height="839" alt="image" src="https://github.com/user-attachments/assets/57d1549b-c206-4518-8a7e-720b2fa0ea2e" />

---
<img width="453" height="901" alt="image" src="https://github.com/user-attachments/assets/c6ff604a-a0e1-4215-840a-dd97c457e38a" />

---
<img width="450" height="929" alt="image" src="https://github.com/user-attachments/assets/42992328-05f2-423e-886e-2426cf0d17cb" />

---

## 5️⃣ Customer Cart Screen

📌 Displays cart items & total payment.

<img width="464" height="373" alt="image" src="https://github.com/user-attachments/assets/75f3e801-02b5-41de-95dc-e1b2bee1c9db" />

---

<img width="438" height="884" alt="image" src="https://github.com/user-attachments/assets/abb047f7-588e-4f0c-a5b2-f28d51b4a134" />

---

## 6️⃣ Customer Checkout Screen

📌 Checkout process before payment.

<img width="438" height="884" alt="image" src="https://github.com/user-attachments/assets/815cd5cc-e110-4263-8919-bd8929210169" />

---

## 7️⃣ Customer Payment Screen

📌 Customer chooses payment method.

<img width="432" height="881" alt="image" src="https://github.com/user-attachments/assets/4c00e7ce-fbce-41d1-9758-295ad5a0bc09" />

---

## 8️⃣ Customer Promo Screen

📌 Apply promo or voucher discount.

<img width="429" height="908" alt="image" src="https://github.com/user-attachments/assets/b1745865-3f25-41bd-ac21-beebda3eac35" />

---

## 9️⃣ Customer Order Type Screen

📌 Select order type:
- Dine In
- Pickup
- Delivery

<img width="438" height="884" alt="image" src="https://github.com/user-attachments/assets/8df2f33b-a7a3-4575-b584-858ed81c7434" />

---

## 🔟 Customer Receipt Screen

📌 Displays payment receipt.

<img width="490" height="977" alt="image" src="https://github.com/user-attachments/assets/446bcac4-9838-46d1-b7cf-6723a33ffdf1" />

---

## 1️⃣1️⃣ Customer History Screen

📌 Displays transaction history.

<img width="415" height="846" alt="image" src="https://github.com/user-attachments/assets/21339d8c-f09e-4c88-a948-624b19b15bc9" />

---

## 1️⃣2️⃣ Customer Profile Screen

📌 Displays customer profile information.

<img width="419" height="853" alt="image" src="https://github.com/user-attachments/assets/253f0b13-dec0-4128-8f3d-e2141ddf42a5" />

---

# 🧑‍💼 ADMIN POV

# 📌 Admin Workflow

```text
Admin Login
      ↓
Dashboard Admin
      ↓
Manage Menu
      ↓
Add Product
      ↓
Edit Product
      ↓
Delete Product
      ↓
Menu Updated
```

---

## 1️⃣ Admin Login Screen

📌 Admin login page.

<img width="424" height="878" alt="image" src="https://github.com/user-attachments/assets/c6c0aa0c-31ad-4d32-b1da-74938ca7be19" />

---

## 2️⃣ Admin Dashboard Screen

📌 Main admin dashboard.

<img width="425" height="868" alt="image" src="https://github.com/user-attachments/assets/478a5360-6cb5-451a-a945-0954619c930e" />

---

## 3️⃣ Admin Manage Menu Screen

📌 Manage all menu data.

<img width="465" height="988" alt="image" src="https://github.com/user-attachments/assets/5d1f6e04-c1dc-40af-9f15-da4fd2262c60" />

---

## 4️⃣ Admin Add Product Screen

📌 Add new menu/product.

<img width="355" height="193" alt="image" src="https://github.com/user-attachments/assets/0f71f2ea-e00f-422d-9b15-dc6444d4257d" />

---

## 5️⃣ Admin Edit Product Screen

📌 Edit product information.

<img width="465" height="988" alt="image" src="https://github.com/user-attachments/assets/6c3fc147-03ef-446a-b031-cb3869debc8d" />

---

## 6️⃣ Admin Delete Product Screen

📌 Delete product from system.

<img width="465" height="988" alt="image" src="https://github.com/user-attachments/assets/f961928e-5c1c-4c84-a8dc-d41519b0656c" />

---

## 7️⃣ Admin Transaction Monitoring Screen

📌 Monitor all transactions.

<img width="480" height="980" alt="image" src="https://github.com/user-attachments/assets/2d7057a3-6cdf-41b7-8507-5f08929ac306" />

---

# 💳 CASHIER POV

# 📌 Cashier Workflow

```text
Cashier Login
      ↓
Cashier Dashboard
      ↓
View Customer Order
      ↓
Verify Payment
      ↓
Transaction Success
      ↓
Receipt Displayed
```

---

## 1️⃣ Cashier Login Screen

📌 Cashier login page.

<img width="408" height="837" alt="image" src="https://github.com/user-attachments/assets/e992c580-a92f-4390-a9ba-9098bb9148f3" />

---

## 2️⃣ Cashier Dashboard Screen

📌 Main cashier dashboard.

<img width="420" height="831" alt="image" src="https://github.com/user-attachments/assets/50754a88-aae4-4bf4-87bb-236c8b99e3d0" />

---
<img width="414" height="829" alt="image" src="https://github.com/user-attachments/assets/74183ec0-5214-4189-a09a-c7647a210af9" />

---
<img width="392" height="831" alt="image" src="https://github.com/user-attachments/assets/d0ece445-0ec1-45d8-96bb-ca0167fd83bb" />

---
<img width="404" height="518" alt="image" src="https://github.com/user-attachments/assets/d678422f-dfe9-4b6e-8435-516b51f47ff4" />

---
<img width="403" height="239" alt="image" src="https://github.com/user-attachments/assets/5a9b058b-abd0-416e-9249-db8a9c42bbfc" />


## 3️⃣ Cashier Order Detail Screen

📌 View customer order details.

<img width="401" height="844" alt="image" src="https://github.com/user-attachments/assets/c54efb61-b64c-4948-9b85-c3d75fbb82ae" />

---
<img width="411" height="835" alt="image" src="https://github.com/user-attachments/assets/39bc08ef-cbf2-4b9c-b7fb-bf1c899f2817" />

---
<img width="407" height="839" alt="image" src="https://github.com/user-attachments/assets/f6ba752f-0113-4c21-88a0-b8be67fafe05" />

---
<img width="402" height="840" alt="image" src="https://github.com/user-attachments/assets/d1d3dc58-6d9c-4f18-b5cd-b59df72d5a74" />

---
<img width="413" height="839" alt="image" src="https://github.com/user-attachments/assets/bcc5faba-001f-4986-ac31-9ca35869372c" />

---


## 4️⃣ Cashier Payment Verification Screen

📌 Verify customer payment.

<img width="413" height="844" alt="image" src="https://github.com/user-attachments/assets/eeab0cb3-ca0c-4c6a-8e4d-6a33ed5d597f" />

---

## 5️⃣ Cashier Transaction Success Screen

📌 Transaction successfully processed.

<img width="413" height="844" alt="image" src="https://github.com/user-attachments/assets/eeab0cb3-ca0c-4c6a-8e4d-6a33ed5d597f" />

---

## 6️⃣ Cashier Receipt Screen

📌 Display transaction receipt.

<img width="407" height="840" alt="image" src="https://github.com/user-attachments/assets/7ea6220e-1667-428e-8c93-fd21a72780d0" />

---

# ⚙️ Installation Guide

# 1️⃣ Clone Repository

```bash
https://github.com/rismaramadhani403/ProjectLumera/edit/main/README.md
```

---

# 2️⃣ Open Android Studio

```text
File → Open
```

Select LUMERA project folder.

---

# 3️⃣ Sync Gradle

Wait until:

```text
Gradle Sync Finished
```

---

# 4️⃣ Run Emulator / Android Device

Use:
- Android Emulator
- Physical Android Device

---

# 5️⃣ Run Application

Click:

```text
▶ Run
```

---

# 🧪 System Testing

Testing performed on:

- Login system
- Navigation system
- Cart system
- Payment system
- Receipt system
- History system
- CRUD menu
- Session management

---

# 📊 Project Results

Successfully implemented:

- Modern UI using Compose
- Multi-role authentication
- Cart management
- Transaction system
- Payment verification
- Receipt generation
- CRUD functionality
- Session persistence
- Navigation system

---

# 🔮 Future Development

Possible future improvements:

- Firebase Authentication
- Cloud Database
- REST API Integration
- Push Notification
- Midtrans Payment Gateway
- AI Menu Recommendation
- Real-time Tracking
- Analytics Dashboard
- Dark Mode
- Multi-language Support

---

# 📚 Conclusion

LUMERA successfully implements a modern Android coffee shop application using Kotlin and Jetpack Compose. The project applies various Android Development concepts such as MVVM Architecture, Navigation Compose, State Management, and DataStore.

The application provides a digital ordering experience for customers while helping admins and cashiers manage menus and transactions efficiently.

---

# 👨‍💻 Developer

1.	RISMA RAMADHANI			         (L0324030)
2.	WIZAD AKMALIA ZULFAA       (L0324036)
3.	ZEFANYA CHRISTIAN NATASHA		(L0324037)

---

# 📄 License

This project was created for educational and Android Development learning purposes.
