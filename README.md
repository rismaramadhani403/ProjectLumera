# ☕ Lumera Cafe App

Aplikasi **Sistem Pemesanan Menu Cafe** berbasis Android yang dikembangkan menggunakan **Kotlin** dan **Jetpack Compose**. Aplikasi ini memungkinkan pengguna untuk melakukan pemesanan menu secara interaktif mulai dari proses login hingga pembayaran, dengan alur navigasi yang terstruktur dan pengalaman pengguna (UX) yang baik.

---

## 📌 Deskripsi Aplikasi

**Lumera Cafe App** merupakan aplikasi sederhana yang mensimulasikan sistem pemesanan di sebuah cafe. Pengguna dapat:

• Login atau mendaftar akun  
• Melihat daftar menu berdasarkan kategori  
• Melihat detail menu  
• Menambahkan item ke keranjang  
• Melakukan pembayaran  
• Melihat struk pembelian  

Aplikasi ini dibuat untuk memenuhi tugas praktikum dengan fokus pada **Navigation 3 (state-driven navigation)** serta penerapan konsep UI modern menggunakan **Jetpack Compose**.

---

## 🎯 Tujuan Pengembangan

1. Mengimplementasikan Jetpack Compose dalam pembuatan UI Android  
2. Menerapkan Navigation 3 (state-based navigation) tanpa NavController  
3. Menghubungkan antar screen dalam satu alur aplikasi  
4. Menerapkan passing data antar screen  
5. Meningkatkan pengalaman pengguna melalui validasi dan feedback  

---

## 🚀 Fitur Utama

### 🔐 1. Authentication (Login & Register)

• Login dengan validasi input (tidak boleh kosong)  
• Register akun baru  
• Validasi username unik (tidak bisa daftar 2x)  
• Password disembunyikan (secure input)  
• Notifikasi menggunakan Snackbar  

---

### 🧭 2. Navigation (Navigation 3)

• Menggunakan state-driven navigation  
• Tidak menggunakan NavController  
• Menggunakan backStack manual:  
  • `add()` → pindah screen  
  • `removeLastOrNull()` → kembali  
• Navigasi antar screen berjalan tanpa crash  

---

### 🛒 3. Sistem Pemesanan

• Menampilkan menu berdasarkan kategori (Coffee & Dessert)  
• Menampilkan dalam bentuk grid  
• Tambah menu ke keranjang  
• Update quantity otomatis jika item sudah ada  
• Hapus item dari keranjang  

---

### 📄 4. Detail Menu

• Menampilkan nama menu  
• Menampilkan harga  
• Passing data dari Home ke Detail  

---

### 💳 5. Payment & Receipt

• Menampilkan total harga  
• Menampilkan daftar item yang dibeli  
• Format harga dalam rupiah  
• Menampilkan waktu transaksi  

**Tombol:**

• Selesai → kembali ke login + reset cart  
• Kembali ke Home → kembali ke menu  

---

### 📦 6. Manajemen Data

• `UserData` untuk menyimpan akun  
• `CartItem` untuk keranjang  
• Validasi login dan register  
• Data disimpan sementara (in-memory)  

---
🔄 Alur Aplikasi

### 1. Login
<p align="center">
  <img src="https://github.com/user-attachments/assets/8051a335-0eb7-4672-bbfa-98732a4ea74b" width="300"/>
</p>

---

### 2. Register
<p align="center">
  <img src="https://github.com/user-attachments/assets/0a20f26f-2323-466a-b889-c95aea986759" width="300"/>
</p>

---

### 3. Login
<p align="center">
  <img src="https://github.com/user-attachments/assets/072a31cf-1371-430e-b6de-9e4ae5b83e69" width="300"/>
</p>

---

### 4. Home
<p align="center">
  <img src="https://github.com/user-attachments/assets/7d3b8162-a5fb-4563-959c-319de8ee6c85" width="300"/>
  <img src="https://github.com/user-attachments/assets/96d0ca3a-6236-488f-9552-31ca66320d84" width="300"/>
</p>

---

### 5. Detail
<p align="center">
  <img src="https://github.com/user-attachments/assets/463dbf03-1053-4504-a81a-b40b09a85bd1" width="300"/>
</p>

---

### 6. Cart
<p align="center">
  <img src="https://github.com/user-attachments/assets/a119738b-ef06-4df7-9a6e-00d0aa708cbc" width="300"/>
</p>

---

### 7. Payment
<p align="center">
  <img src="https://github.com/user-attachments/assets/0f8182d9-a6de-4b19-9590-74a3c8a5cea8" width="300"/>
</p>

---

### 8. Receipt
<p align="center">
  <img src="https://github.com/user-attachments/assets/90f561f7-b1c2-481c-9561-b538d4c21e5c" width="300"/>
</p>
🔄 Alur Aplikasi

**Penjelasan:**

1. User login atau register terlebih dahulu  
2. Masuk ke halaman Home (menu)  
3. Pilih menu → masuk ke Detail  
4. Tambah ke cart  
5. Checkout → Payment  
6. Bayar → Receipt  
7. Selesai atau kembali ke Home  

---

## 🧱 Teknologi yang Digunakan

• Kotlin  
• Jetpack Compose  
• Material 3  

**State Management:**

• `remember`  
• `mutableStateOf`  
• `mutableStateListOf`  

• Navigation 3 (State-driven navigation)  

## 📁 Struktur Project

```
com.praktikum.lumera
│
├── model
│   ├── Menu.kt
│   ├── CartItem.kt
│   └── UserData.kt
│
├── navigation
│   ├── AppNavigation.kt
│   └── Routes.kt
│
├── screens
│   ├── login
│   ├── register
│   ├── home
│   ├── detail
│   ├── cart
│   ├── payment
│   └── receipt
│
├── components
└── data
```
## ⚙️ Cara Menjalankan Project

**Clone repository:**
```bash
git clone https://github.com/rismaramadhani403/ProjectLumera.git
```

• Buka project di Android Studio  
• Sync Gradle  
• Jalankan aplikasi:  
  • Emulator Android  
  • atau perangkat fisik  

---

## 🎬 Demo Aplikasi

**Fitur yang ditampilkan dalam demo:**

• Login dan validasi  
• Register (tidak bisa duplikat)  
• Navigasi antar screen  
• Tambah menu ke cart  
• Checkout dan pembayaran  
• Tampilan struk  

---

## 📊 Ketentuan yang Dipenuhi

✔ Menggunakan Jetpack Compose dan Material 3  
✔ Menggunakan Navigation 3 (state-driven)  
✔ Implementasi Back Navigation  
✔ Passing parameter antar screen  
✔ Conditional Navigation (validasi login)  
✔ Tidak terjadi crash saat navigasi  

## 👥 Kelompok 4

• **Risma Ramadhani** (L0324030)  
• **Wizad Akmalia Zulfaa** (L0324036)  
• **Zefanya Christian Natasha** (L0324037)  
## ⭐ Penutup

Aplikasi **Lumera Cafe App** merupakan implementasi sederhana dari sistem pemesanan menu berbasis Android yang dikembangkan menggunakan **Jetpack Compose** dan **Navigation 3 (state-driven navigation)**.

Melalui aplikasi ini, seluruh alur pemesanan dapat dilakukan secara terstruktur, mulai dari proses autentikasi pengguna, pemilihan menu, pengelolaan keranjang, hingga tahap pembayaran dan penampilan struk.

Pengembangan aplikasi ini tidak hanya memenuhi ketentuan tugas praktikum, tetapi juga menerapkan konsep **UI modern, manajemen state, serta navigasi yang efisien**, sehingga menghasilkan aplikasi yang stabil, mudah digunakan, dan memiliki pengalaman pengguna (UX) yang baik.

Diharapkan aplikasi ini dapat menjadi dasar untuk pengembangan sistem yang lebih kompleks di masa mendatang.

---



