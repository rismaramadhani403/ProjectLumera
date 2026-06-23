# ☕ LUMERA — Aplikasi Pemesanan Kopi Android

<p align="center">
  <img src="https://img.shields.io/badge/Platform-Android-brightgreen?style=for-the-badge&logo=android&logoColor=white" />
  <img src="https://img.shields.io/badge/Language-Kotlin%202.0.21-blueviolet?style=for-the-badge&logo=kotlin&logoColor=white" />
  <img src="https://img.shields.io/badge/UI-Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white" />
  <img src="https://img.shields.io/badge/Min%20SDK-24%20(Android%207.0)-orange?style=for-the-badge" />
  <img src="https://img.shields.io/badge/DI-Hilt%202.56-red?style=for-the-badge" />
  <img src="https://img.shields.io/badge/DB-Room%202.7.2-teal?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Network-Retrofit%202.11.0-blue?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Version-1.0-lightgrey?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Status-Completed-success?style=for-the-badge" />
</p>

---

## 📖 Deskripsi

**LUMERA** adalah aplikasi mobile Android untuk pemesanan kopi (coffee shop) yang dibangun menggunakan **Jetpack Compose** dan **Kotlin**. Aplikasi ini mengintegrasikan proses pemesanan kopi bagi pelanggan, pengelolaan transaksi oleh kasir, serta kontrol menu dan laporan keuangan oleh admin.

Aplikasi membagi hak akses ke dalam **tiga role utama** — Customer, Kasir, dan Admin — untuk memastikan operasional kedai kopi berjalan secara efisien dan terstruktur. Backend menggunakan **PHP + MySQL** yang diakses melalui REST API.

Proyek ini dikembangkan sebagai tugas akhir mata kuliah **Pemrograman Aplikasi Berbasis (PAB)**, Program Studi Informatika, Fakultas Teknologi Informasi dan Sains Data, **Universitas Sebelas Maret** — 2026.

---

## 👥 Tim Pengembang

**Kelompok 4**

| Nama | NIM |
|---|---|
| Risma Ramadhani | L0324030 |
| Wizad Akmalia Zulfaa | L0324036 |
| Zefanya Christian Natasha | L0324037 |

---

## 📱 Screenshot Aplikasi

### Autentikasi

| Register | Login Customer | Login Admin | Login Kasir |
|:---:|:---:|:---:|:---:|
|<img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/7b9d467a-ad9c-4d9b-b680-2ac2b12da8b5" />|<img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/cdd78cbf-d1b1-4d9e-8413-9bae12ceee8c" />|<img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/0931b423-8496-48db-8690-f2040394dfe1" />|<img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/b41025d6-8c0e-4b4c-923e-5235ee332b7d" />|

### Customer — Beranda & Favorit

| Halaman Favorit | Keranjang (Cart) |
|:---:|:---:|
|<img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/de03d739-146b-4c45-bba5-d2958c2db5c6" />|<img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/9e71f7bc-ce23-4048-932c-d5822b2e8c44" />|

### Pembayaran & Struk

| Payment | Struk / Receipt |
|:---:|:---:|
|<img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/fed4f8c9-9a2a-4728-96b9-913b3d4c8810" />|<img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/ae6e2f5f-cc4e-4208-adaa-476361e9e2ac" />|

### Profil & Admin

| Profile Customer | Profile Kasir | Admin — Kelola Menu | Admin — Riwayat Transaksi |
|:---:|:---:|:---:|:---:|
|<img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/77aadeb5-8226-4b72-a65b-fbb64fa2321d" />|<img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/3e9870a4-4c45-4e93-ba13-2b2cc2005929" />|<img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/7569a7ff-3dda-444b-997f-8b5d2226e450" />|<img width="300" height="600" alt="image" src="https://github.com/user-attachments/assets/c8e0de78-8de4-4110-8e61-be3a7bf0720d" />|

---

## ✨ Fitur Utama

### 🔐 Autentikasi & Role
- **Register** — Daftar akun baru (nama, email, password)
- **Login Multi-Role** — Satu halaman login dengan pemilihan peran: Customer, Kasir, atau Admin
- Sesi login tersimpan persisten menggunakan DataStore

### 👤 Customer
- **Home** — Daftar menu dari API dengan pencarian dan filter kategori (Coffee, Dessert, Favorite)
- **Detail Menu** — Kustomisasi pesanan: ukuran, es, gula, extra shot, dan catatan khusus
- **Keranjang (Cart)** — Review pesanan, pilih tipe order (Pickup / Delivery), ringkasan pembayaran
- **Favorit** — Simpan menu kesukaan, akses cepat langsung ke keranjang
- **Payment** — Pilih metode bayar: Cash, QRIS, atau Debit
- **Struk Digital** — Bukti transaksi lengkap dengan nomor nota unik (INV-xxx)
- **Profil** — Statistik pesanan & favorit, badge status Member
- **Alamat Pengiriman** — Tambah & kelola alamat tersimpan
- **Riwayat Pesanan** — Daftar histori transaksi

### 🧾 Kasir
- Login khusus peran Kasir
- Profil Kasir dengan info shift kerja (jam mulai & selesai)
- Setiap transaksi tercatat atas nama kasir yang memproses

### 🔧 Admin
- **Dashboard Admin** — Pantau total transaksi dan income hari ini
- **Manajemen Menu** — Tambah & hapus menu langsung dari aplikasi
- **Riwayat Transaksi Real-time** — Detail: customer, kasir, total, metode, status

---

## 🛠️ Tech Stack

### Bahasa & Platform
- **Kotlin** 2.0.21
- **Android** — Min SDK 24 (Android 7.0), Target SDK 34, Compile SDK 35

### UI
- **Jetpack Compose** (BOM 2024.09.00)
- **Material 3**
- **Material Icons Extended**

### Arsitektur & DI
- **MVVM** (Model-View-ViewModel)
- **Hilt** 2.56 — Dependency Injection
- **Hilt Navigation Compose** 1.2.0

### Navigasi
- **Navigation Compose** 2.7.7

### Persistensi Data
- **Room Database** 2.7.2 — Database SQLite lokal
- **DataStore Preferences** 1.1.1 — Penyimpanan sesi & preferensi

### Jaringan
- **Retrofit** 2.11.0
- **Gson Converter** 2.11.0
- **OkHttp Logging Interceptor** 4.12.0

### Gambar
- **Coil Compose** 2.6.0

### Build
- **AGP** 8.5.2 · **KSP** 2.0.21-1.0.28 · **Java** 17 · **Gradle** 9.3.1

---

## 🗂️ Struktur Proyek

```
LUMERA/
├── docs/
│   └── screenshots/              # Screenshot tampilan aplikasi
├── app/
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml
│           └── java/com/praktikum/lumera/
│               ├── LumeraApplication.kt
│               ├── MainActivity.kt
│               ├── navigation/
│               │   ├── AppNavigation.kt
│               │   └── Routes.kt
│               ├── screens/
│               │   ├── splash/         SplashScreen.kt
│               │   ├── onboarding/     OnboardingScreen.kt
│               │   ├── login/          LoginScreen.kt
│               │   ├── register/       RegisterScreen.kt
│               │   ├── home/           HomeScreen.kt
│               │   ├── detail/         DetailScreen.kt
│               │   ├── cart/           CartScreen.kt
│               │   ├── address/        DeliveryAddressScreen.kt
│               │   ├── paymentmethod/  PaymentMethodScreen.kt
│               │   ├── payment/        PaymentScreen.kt
│               │   ├── receipt/        ReceiptScreen.kt
│               │   ├── history/        OrderHistoryScreen.kt
│               │   ├── notification/   NotificationScreen.kt
│               │   ├── profile/        ProfileScreen.kt
│               │   ├── settings/       AccountSettingsScreen.kt
│               │   └── admin/          AdminScreen.kt
│               ├── viewmodel/
│               │   ├── viewmodel.kt              # CartViewModel (@HiltViewModel)
│               │   ├── MenuViewModel.kt
│               │   └── SessionViewModel.kt
│               ├── data/
│               │   ├── MenuData.kt
│               │   ├── SessionManager.kt
│               │   ├── UserPreferences.kt
│               │   ├── local/
│               │   │   ├── database/LumeraDatabase.kt
│               │   │   ├── dao/
│               │   │   │   ├── UserDao.kt
│               │   │   │   └── TransactionDao.kt
│               │   │   └── entity/
│               │   │       ├── UserEntity.kt
│               │   │       └── TransactionEntity.kt
│               │   ├── remote/
│               │   │   ├── ApiService.kt
│               │   │   ├── MenuResponse.kt
│               │   │   └── NetworkConfig.kt
│               │   └── repository/
│               │       └── UserRepository.kt
│               ├── model/
│               │   ├── Menu.kt · CartItem.kt · Transaction.kt
│               │   ├── User.kt · AddressItem.kt
│               │   └── OrderHistory.kt · OrderHistoryData.kt
│               ├── components/
│               │   └── MenuItemCard.kt
│               ├── di/
│               │   └── AppModule.kt
│               ├── utils/
│               │   └── CurrencyUtils.kt
│               └── ui/theme/
│                   ├── Color.kt · Font.kt · Theme.kt · Type.kt
├── gradle/
│   ├── libs.versions.toml
│   └── wrapper/gradle-wrapper.properties
├── build.gradle.kts
└── gradle.properties
```

---

## 🗄️ Skema Database

### Database Lokal — Room (`lumera_database`)

#### Tabel `users`

| Kolom | Tipe | Keterangan |
|---|---|---|
| `id` | INT (PK, auto) | ID unik pengguna |
| `name` | TEXT | Username |
| `email` | TEXT | Alamat email |
| `password` | TEXT | Kata sandi |
| `role` | TEXT | `Customer` / `Kasir` / `Admin` |

#### Tabel `transactions`

| Kolom | Tipe | Keterangan |
|---|---|---|
| `id` | INT (PK, auto) | ID unik transaksi |
| `customerName` | TEXT | Nama pelanggan |
| `cashierName` | TEXT | Nama kasir yang memproses |
| `items` | TEXT | Daftar item (serialized) |
| `total` | INT | Total harga (Rupiah) |
| `paymentMethod` | TEXT | `Cash` / `QRIS` / `Debit` |
| `date` | TEXT | Waktu transaksi |

---

### Database Backend — MySQL (`lumera_coffee`)

#### Tabel `menu`

| Kolom | Tipe MySQL | Keterangan |
|---|---|---|
| `id_menu` | INT (PK) | ID unik menu |
| `nama_menu` | VARCHAR | Nama menu |
| `harga` | INT | Harga dalam Rupiah |
| `stok` | INT | Jumlah stok tersedia |
| `kategori` | VARCHAR | `Coffee` / `Dessert` / dll. |
| `deskripsi` | TEXT | Deskripsi singkat menu |
| `gambar` | VARCHAR | Nama file gambar di `assets/images/` |

---

## 💾 DataStore Preferences

| Key | Tipe | Keterangan |
|---|---|---|
| `username` | String | Nama pengguna aktif |
| `email` | String | Email pengguna aktif |
| `password` | String | Password (lokal) |
| `role` | String | `Customer` / `Kasir` / `Admin` |
| `is_login` | Boolean | Status sesi login |
| `favorite_key` | String | ID favorit (comma-separated, misal `"1,3,7"`) |
| `address_key` | String | Daftar alamat (serialized) |

---

## 🌐 API Endpoint

| Method | Endpoint | Parameter | Deskripsi |
|---|---|---|---|
| `GET` | `/api/get_menu.php` | `cari` *(opsional)*, `kategori` *(opsional)* | Ambil daftar menu dari MySQL |

**Contoh Response:**
```json
[
  {
    "id_menu": 1,
    "nama_menu": "Spanish Iced Latte",
    "harga": 24000,
    "stok": 50,
    "kategori": "Coffee",
    "deskripsi": "Refreshing iced latte",
    "gambar": "spanish_iced_latte.jpg"
  }
]
```

---

## ⚙️ Konfigurasi BASE_URL

Sesuaikan `BASE_URL` di `app/src/main/java/com/praktikum/lumera/data/remote/NetworkConfig.kt`:

| Skenario | BASE_URL |
|---|---|
| **Emulator** + XAMPP/Laragon di laptop yang sama | `http://10.0.2.2/lumera-coffee/` |
| **HP fisik** + laptop (satu jaringan WiFi) | `http://192.168.x.x/lumera-coffee/` |
| **Server hosting** online | `https://nama-domain-kamu.com/lumera-coffee/` |

```kotlin
// NetworkConfig.kt
const val BASE_URL = "http://10.0.2.2/lumera-coffee/"  // ← ubah sesuai skenario
```

---

## 🏗️ Arsitektur MVVM

```
┌─────────────────────────────────────────┐
│           UI Layer (Compose)            │
│  HomeScreen · CartScreen · AdminScreen  │
│  LoginScreen · ProfileScreen · ...      │
└──────────────────┬──────────────────────┘
                   │ observes state
┌──────────────────▼──────────────────────┐
│         ViewModel Layer                 │
│  CartViewModel · MenuViewModel          │
│  SessionViewModel                       │
└──────────┬───────────────┬─────────────┘
           │               │
┌──────────▼──────┐ ┌──────▼──────────────┐
│  Local Data     │ │  Remote Data         │
│  Room Database  │ │  Retrofit ApiService │
│  DataStore      │ │  PHP + MySQL         │
└─────────────────┘ └──────────────────────┘
```

**Hilt** menyuntikkan semua dependensi melalui `AppModule`.

---

## 🧭 Alur Navigasi

```
SplashScreen → OnboardingScreen → LoginScreen ←→ RegisterScreen
                                       │
               ┌───────────────────────┼──────────────────────┐
               │ Customer              │ Kasir                 │ Admin
               ▼                       ▼                       ▼
           HomeScreen            ProfileScreen           AdminScreen
               │                  (Kasir info)       (Dashboard, Kelola
               ├─ DetailScreen                         Menu, Transaksi)
               ├─ CartScreen
               │    └─ DeliveryAddressScreen
               │    └─ PaymentMethodScreen
               │    └─ PaymentScreen
               │         └─ ReceiptScreen
               ├─ OrderHistoryScreen
               ├─ NotificationScreen
               └─ ProfileScreen → AccountSettingsScreen
```

---

## 🚀 Cara Menjalankan Proyek

### Prasyarat
- **Android Studio** Hedgehog 2023.1.1 atau lebih baru
- **JDK 17**
- **XAMPP** atau **Laragon** (untuk backend PHP & MySQL lokal)
- Emulator Android atau perangkat fisik dengan **Android 7.0+ (API 24)**

### Langkah Setup

**1. Ekstrak & buka proyek**
```bash
unzip LUMERA_Final.zip
# Android Studio → File → Open → pilih folder LUMERA
```

**2. Setup backend PHP**
- Salin folder `lumera-coffee` ke root server lokal:
  - XAMPP → `C:\xampp\htdocs\lumera-coffee\`
  - Laragon → `C:\laragon\www\lumera-coffee\`
- Import database `lumera_coffee` melalui **phpMyAdmin**
- Pastikan Apache & MySQL sudah berjalan

**3. Sesuaikan BASE_URL**
```kotlin
// NetworkConfig.kt
const val BASE_URL = "http://10.0.2.2/lumera-coffee/"   // emulator
// const val BASE_URL = "http://192.168.x.x/lumera-coffee/" // HP fisik
```

**4. Sync & Run**
```
File → Sync Project with Gradle Files → ▶️ Run
```

---

## 🔑 Akun Default untuk Testing

| Role | Username | Password |
|---|---|---|
| Customer | *(daftar via Register)* | *(bebas)* |
| Kasir | `risma` | `kasir123` |
| Admin | `zefa` | `admin123` |

---

## 📦 Dependensi Lengkap

```kotlin
dependencies {
    // Core & Lifecycle
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.2")

    // Jetpack Compose (BOM 2024.09.00)
    implementation(platform("androidx.compose:compose-bom:2024.09.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.1.1")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.56")
    kapt("com.google.dagger:hilt-android-compiler:2.56")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // Room
    implementation("androidx.room:room-runtime:2.7.2")
    implementation("androidx.room:room-ktx:2.7.2")
    kapt("androidx.room:room-compiler:2.7.2")

    // Retrofit & OkHttp
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Coil
    implementation("io.coil-kt:coil-compose:2.6.0")
}
```

---

## ⚠️ Catatan Penting

- **`android:usesCleartextTraffic="true"`** diaktifkan untuk HTTP ke server lokal. Gunakan HTTPS untuk produksi.
- **`fallbackToDestructiveMigration()`** pada Room akan menghapus data lokal jika skema database berubah.
- **Favorit** disinkronisasi dengan data menu terbaru dari API setiap kali HomeScreen dibuka.
- **MenuViewModel** dibuat manual di `MainActivity` agar kompatibel dengan setup saat ini.

---

## 📋 Informasi Proyek

| Detail | Keterangan |
|---|---|
| **Package Name** | `com.praktikum.lumera` |
| **Version** | 1.0 (versionCode 1) |
| **Min Android** | Android 7.0 (API 24) |
| **Target Android** | Android 14 (API 34) |
| **Compile SDK** | 35 |
| **Branch Git** | `Project-Final-PAB` |
| **Mata Kuliah** | Pemrograman Aplikasi Berbasis (PAB) |
| **Program Studi** | Informatika — FTISDA UNS |
| **Tahun** | 2026 |

---

<p align="center">
  Dibuat dengan ☕ oleh <strong>Kelompok 4</strong> — FTISDA Universitas Sebelas Maret 2026
</p>
