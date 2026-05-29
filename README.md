# ☕ LUMERA - Coffee Shop Mobile Application

<p align="center">
  <img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/1af783c6-31d6-4503-94b9-c93d1c9c8b25" />
  <img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/acc675a7-1f89-484e-a717-9b6ebdfd1122" />

  <h3 align="center">
    Modern Coffee Shop Mobile Application Built with Kotlin, Jetpack Compose, MVVM Architecture, DataStore Preferences, and Hilt Dependency Injection
  </h3>
</p>

---

## 📖 About Project

LUMERA adalah aplikasi mobile berbasis Android yang dirancang untuk memberikan pengalaman pemesanan menu cafe secara digital, modern, dan efisien. Aplikasi ini dikembangkan menggunakan Kotlin dan Jetpack Compose dengan menerapkan berbagai konsep Android Development modern seperti MVVM Architecture, Navigation Compose, DataStore Preferences, serta Dependency Injection menggunakan Hilt.

Aplikasi memungkinkan pengguna untuk melakukan registrasi akun, login, melihat daftar menu, melakukan pemesanan produk, mengelola keranjang belanja, melakukan pembayaran, melihat riwayat transaksi, serta mengelola profil pengguna.

Selain Customer, aplikasi juga mendukung role Admin dan Cashier sehingga dapat digunakan sebagai simulasi sistem manajemen cafe secara menyeluruh.

---

## 🎯 Project Objectives

Tujuan pengembangan aplikasi LUMERA adalah:

* Membangun aplikasi mobile coffee shop modern berbasis Android.
* Mengimplementasikan arsitektur MVVM pada aplikasi Android.
* Menggunakan Jetpack Compose sebagai framework UI modern.
* Mengimplementasikan Navigation Compose untuk perpindahan halaman.
* Menggunakan DataStore Preferences untuk penyimpanan data lokal.
* Mengimplementasikan Dependency Injection menggunakan Hilt.
* Mengelola state aplikasi menggunakan ViewModel.
* Menciptakan sistem pemesanan digital yang mudah digunakan.
* Mengembangkan aplikasi yang scalable dan mudah dipelihara.

---

# ✨ Main Features

## 👤 Customer Features

* Register Account
* Login System
* Session Management
* Browse Menu
* Search Menu
* Favorite Menu
* Product Detail
* Add To Cart
* Edit Quantity
* Delete Item
* Checkout Order
* Choose Payment Method
* Apply Promo
* Receipt Generation
* Transaction History
* User Profile
* Logout System

---

## 🧑‍💼 Admin Features

* Admin Authentication
* Dashboard Management
* Add Product
* Edit Product
* Delete Product
* Product Monitoring
* Customer Monitoring
* Transaction Monitoring

---

## 💳 Cashier Features

* Cashier Authentication
* Order Verification
* Payment Processing
* Transaction Monitoring
* Receipt Verification

---

# 🛠 Technology Stack

| Technology                | Function                      |
| ------------------------- | ----------------------------- |
| Kotlin                    | Main Programming Language     |
| Jetpack Compose           | Modern Android UI Toolkit     |
| Material Design 3         | User Interface Design         |
| Navigation Compose        | Navigation Management         |
| ViewModel                 | State Management              |
| Kotlin Coroutines         | Asynchronous Programming      |
| Kotlin Flow               | Reactive Data Stream          |
| DataStore Preferences     | Local Data Storage            |
| Hilt Dependency Injection | Dependency Management         |
| MVVM Architecture         | Software Architecture Pattern |

---

# 🏗️ Software Architecture

Project LUMERA menggunakan pola arsitektur **MVVM (Model View ViewModel)**.

MVVM digunakan untuk memisahkan Business Logic dari User Interface sehingga kode menjadi lebih mudah dipelihara dan dikembangkan.

## MVVM Architecture Flow

```text
Jetpack Compose UI
          ↓
      ViewModel
          ↓
      Repository
          ↓
      DataStore
```

### View Layer

Berisi seluruh tampilan aplikasi yang dibangun menggunakan Jetpack Compose.

Contoh:

* LoginScreen
* HomeScreen
* DetailScreen
* CartScreen
* PaymentScreen
* ReceiptScreen
* ProfileScreen

### ViewModel Layer

Berfungsi sebagai penghubung antara UI dan Data Layer.

Contoh:

* SessionViewModel
* CartViewModel
* MenuViewModel

### Data Layer

Mengelola sumber data aplikasi.

Contoh:

* UserPreferences
* MenuData
* SessionManager

---

# 💉 Dependency Injection with Hilt

## Overview

Pada Pertemuan 10, aplikasi LUMERA dikembangkan lebih lanjut dengan mengimplementasikan Dependency Injection menggunakan Hilt.

Hilt merupakan framework Dependency Injection resmi dari Google yang dibangun di atas Dagger dan dirancang khusus untuk Android.

Dependency Injection digunakan untuk menyediakan dependency yang dibutuhkan suatu class secara otomatis tanpa harus membuat objek secara manual.

---

## Why Hilt?

Implementasi Hilt memberikan beberapa keuntungan:

* Mengurangi boilerplate code
* Mempermudah dependency management
* Mengurangi coupling antar class
* Mempermudah maintenance
* Mendukung clean architecture
* Mempermudah testing
* Meningkatkan scalability project

---

## Hilt Dependencies

```kotlin
implementation(libs.hilt.android)

kapt(libs.hilt.compiler)

implementation(libs.androidx.hilt.navigation.compose)
```

---

## Hilt Android App

```kotlin
@HiltAndroidApp
class LumeraApplication : Application()
```

Digunakan untuk mengaktifkan Hilt pada level aplikasi dan membuat dependency container global.

---

## Hilt ViewModel

```kotlin
@HiltViewModel
class SessionViewModel @Inject constructor(
    private val userPreferences: UserPreferences
) : ViewModel()
```

Pada implementasi tersebut, UserPreferences akan diberikan secara otomatis oleh Hilt melalui constructor injection.

---

## Hilt with Compose

```kotlin
val sessionViewModel: SessionViewModel =
    hiltViewModel()
```

Hilt akan membuat ViewModel secara otomatis dan menghubungkannya dengan lifecycle Compose.

---

## Updated Architecture with Hilt

```text
Jetpack Compose UI
          ↓
      ViewModel
          ↓
       Hilt DI
          ↓
   UserPreferences
          ↓
      DataStore
```

---

# 💾 Session Management Using DataStore

LUMERA menggunakan DataStore Preferences sebagai media penyimpanan data lokal.

Data yang disimpan meliputi:

* User Name
* User Email
* User Role
* Login Status
* Session Information

Keuntungan DataStore:

* Asynchronous
* Modern API
* Type Safe
* Lebih aman dibanding SharedPreferences
* Direkomendasikan oleh Google

---

# 📂 Project Structure

```text
com.praktikum.lumera

├── components
├── datastore
├── di
├── model
├── navigation
├── screens
│   ├── login
│   ├── register
│   ├── home
│   ├── detail
│   ├── cart
│   ├── payment
│   ├── receipt
│   ├── history
│   ├── profile
│   ├── admin
│   └── cashier
│
├── ui
│   └── theme
│
├── utils
│
├── viewmodel
│
├── MainActivity.kt
│
└── LumeraApplication.kt
```

---

# 🔄 Application Workflow

## Customer Workflow

```text
Register / Login
        ↓
      Home
        ↓
   Select Menu
        ↓
   Menu Detail
        ↓
    Add To Cart
        ↓
     Checkout
        ↓
      Payment
        ↓
      Receipt
        ↓
      History
        ↓
      Profile
```

## Admin Workflow

```text
Admin Login
      ↓
Dashboard
      ↓
Manage Product
      ↓
Add/Edit/Delete Menu
      ↓
Update Product Data
```

## Cashier Workflow

```text
Cashier Login
       ↓
Cashier Dashboard
       ↓
View Customer Order
       ↓
Verify Payment
       ↓
Transaction Complete
```

---

# 📸 Screenshot Documentation

## 👤 Customer Screens

* Login Screen
  <p align="center">
  <img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/5f8b33e7-1754-43b9-8cf9-0d294194b948" />
  </p>
  
  ---
  
* Register Screen
  <p align="center">
  <img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/128dc3af-83bb-455c-85ac-c998a2329b8f" />
  </p>

    ---
  
* Home Screen
  <p align="center">
  <img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/8d5b36ca-e740-428b-8300-1474a6e66efd" />
  <img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/049b78fe-6418-4db6-8295-30b730557cbb" />
  <img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/8a63fcd7-9603-4a72-8cb6-cc9498f7ec0e" />
  <img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/941d7b27-89af-4ade-8a25-67ae0882bc93" />
  <img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/c3c23cd3-c963-4b96-a47b-4b63f4922ad3" />
  <img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/e907e818-9b62-4762-b179-dca5cca0f84c" />
  </p>

  ---
  
* Detail Screen
  <p align="center">
  <img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/bdca6dac-e6b3-4186-8c0d-941abbf1bddc" />
  <img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/7bb432fe-b6da-4cac-9948-5daffc9f9b77" />
  <img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/985ff7ad-dd2e-4437-b656-2dd664013ef6" />
  <img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/ad46c3d7-0053-4dde-88f3-ecf9c30e30b3" />
  <img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/ce562d7a-d2f1-4d4a-9e5c-b04eacac5e0c" />
  </p>

    ---
   
* Cart Screen
  <p align="center">
   <img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/0741bca3-3145-4cdc-8a21-48b8b305abdc" />
   <img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/07938e57-1267-4847-a611-b60365615c9f" />
   <img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/8a7a3484-b3f9-4f3e-8a6e-40ce288de5ef" />
   <img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/24135bf2-b9ff-4f1f-b455-97abdd3d386a" />
   <img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/2f14f9f7-7f3d-496e-9dce-63a4f1b92905" />
  </p>

  ---
  
* Payment Screen
  <p align="center">
  <img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/cdcde313-dda7-45bb-aa1a-3b4161ab24b5" />
  <img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/d2147480-2446-4a5e-b5e7-3065e533f00b" />
  </p>

  ---
  
* Receipt Screen
  <p align="center">
  <img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/21eedf0b-3451-4625-a848-b16bb36ef974" />
  </p>

  ---
  
* History Screen
  <p align="center">  
  <img width="300" height="150" alt="image" src="https://github.com/user-attachments/assets/c5fbfd58-921a-49f9-a887-3b8620eb9516" />
  </p>

  ---

## 🧑‍💼 Admin Screens

* Admin Login
  <p align="center"> 
  <img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/6e4d8d62-6300-409a-9eac-62dd1abbda00" />
  </p>

  ---
  
* Admin Dashboard
  <p align="center"> 
  <img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/2f445bf0-78fa-4f25-ad30-abcea48c3318" />
  </p>

  ---
  
* Product Management
  <p align="center"> 
  <img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/a98999ee-c82a-444c-9125-9aa424c2c182" />
  <img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/5462080e-499f-43d6-a05e-95b2e74b4bc5" />
  <img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/99b9bb6b-af7c-44bf-9a84-acb2a95e4d6a" />
  </p>

   ---
  
* Transaction Monitoring
  <p align="center"> 
  <img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/fceee956-79ff-4929-b1ab-034811fa01f3" />
  </p>

  ---
  
## 💳 Cashier Screens

* Cashier Login
  <p align="center"> 
  <img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/68f7e9d5-02ba-4724-818d-8b596bb164c1" />
  </p>

  --
  
* Cashier Dashboard
  <p align="center"> 
  <img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/c00b9e20-8609-44b3-924e-1d21be078c32" />
  <img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/c91e8ec3-1385-43b0-b74f-14cba262cdac" />
  </p>

  ---
  
* Payment Verification
  <p align="center"> 
  <img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/70fca4db-4059-4659-87d8-c7d2f6eb5bd5" />
  </p>

  ---
  
* Receipt Validation
  <p align="center"> 
  <img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/8dc51d75-ae16-4339-a465-1fd7d3b405ac" />
  </p>

  ---

# ⚙️ Installation Guide

## 1. Clone Repository

```bash
git clone <repository-url>
```

## 2. Open Android Studio

```text
File → Open Project
```

## 3. Sync Gradle

Tunggu hingga proses:

```text
Gradle Sync Finished
```

## 4. Run Application

Gunakan:

* Android Emulator
* Physical Android Device

Kemudian klik:

```text
Run ▶
```

---

# 🧪 Testing

Pengujian dilakukan pada fitur:

* Authentication System
* Navigation System
* DataStore Session
* Hilt Injection
* Cart Management
* Checkout Process
* Payment System
* Receipt System
* Transaction History
* Admin CRUD
* Cashier Verification

---

# 📊 Project Achievement

✅ Jetpack Compose UI

✅ MVVM Architecture

✅ Navigation Compose

✅ DataStore Preferences

✅ Hilt Dependency Injection

✅ Session Management

✅ Shopping Cart System

✅ Payment System

✅ Receipt System

✅ Multi Role Authentication

✅ CRUD Management

---

# 🚀 Future Development

Beberapa pengembangan yang dapat dilakukan di masa mendatang:

* Firebase Authentication
* Cloud Database Integration
* REST API Integration
* Push Notification
* Midtrans Payment Gateway
* AI Recommendation System
* Multi Language Support
* Dark Mode Customization
* Real-Time Order Tracking

---

# 📚 Conclusion

LUMERA berhasil dikembangkan sebagai aplikasi mobile coffee shop modern dengan memanfaatkan teknologi Android Development terkini seperti Jetpack Compose, MVVM Architecture, DataStore Preferences, dan Hilt Dependency Injection.

Implementasi Hilt pada Pertemuan 10 berhasil meningkatkan kualitas arsitektur aplikasi dengan menyediakan dependency secara otomatis, mengurangi boilerplate code, dan membuat project lebih modular, scalable, serta mudah dipelihara.

---

# 👨‍💻 Developers

1. Risma Ramadhani (L0324030)
3. Wizad Akmalia Zulfaa (L0324036)
4. Zefanya Christian Natasha (L0324037)

---
