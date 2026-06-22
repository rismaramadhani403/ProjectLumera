<div align="center">

<img src="https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white"/>
<img src="https://img.shields.io/badge/Language-Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white"/>
<img src="https://img.shields.io/badge/UI-Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white"/>
<img src="https://img.shields.io/badge/Design-Material%203-757575?style=for-the-badge&logo=material-design&logoColor=white"/>

<br/>
<br/> 

```
  ██╗     ██╗   ██╗███╗   ███╗███████╗██████╗  █████╗
  ██║     ██║   ██║████╗ ████║██╔════╝██╔══██╗██╔══██╗
  ██║     ██║   ██║██╔████╔██║█████╗  ██████╔╝███████║
  ██║     ██║   ██║██║╚██╔╝██║██╔══╝  ██╔══██╗██╔══██║
  ███████╗╚██████╔╝██║ ╚═╝ ██║███████╗██║  ██║██║  ██║
  ╚══════╝ ╚═════╝ ╚═╝     ╚═╝╚══════╝╚═╝  ╚═╝╚═╝  ╚═╝
```

# ☕ Lumera Cafe App

### _Sistem Pemesanan Menu Cafe berbasis Android_

<br/>

> Aplikasi Android modern yang memungkinkan pengguna memesan menu cafe secara interaktif — dari login hingga struk pembayaran — dibangun dengan **Jetpack Compose** dan **Navigation 3 (state-driven)**.

<br/>

[![GitHub Stars](https://img.shields.io/github/stars/rismaramadhani403/ProjectLumera?style=social)](https://github.com/rismaramadhani403/ProjectLumera/stargazers)
[![GitHub Forks](https://img.shields.io/github/forks/rismaramadhani403/ProjectLumera?style=social)](https://github.com/rismaramadhani403/ProjectLumera/network)

</div>

---

## 📖 Daftar Isi

<div align="center">

[![Tentang](https://img.shields.io/badge/🌟_Tentang_Aplikasi-8A2BE2?style=for-the-badge)](#-tentang-aplikasi)
[![Fitur](https://img.shields.io/badge/🚀_Fitur_Utama-1D9E75?style=for-the-badge)](#-fitur-utama)
[![Alur](https://img.shields.io/badge/🔄_Alur_Aplikasi-D85A30?style=for-the-badge)](#-alur-aplikasi)
[![Teknologi](https://img.shields.io/badge/🧱_Teknologi-185FA5?style=for-the-badge)](#-teknologi)
[![Struktur](https://img.shields.io/badge/📁_Struktur_Project-BA7517?style=for-the-badge)](#-struktur-project)
[![Instalasi](https://img.shields.io/badge/⚙️_Cara_Menjalankan-639922?style=for-the-badge)](#-cara-menjalankan)
[![Tim](https://img.shields.io/badge/👥_Tim_Pengembang-D4537E?style=for-the-badge)](#-tim-pengembang)

</div>

---

## 🌟 Tentang Aplikasi

**Lumera Cafe App** adalah aplikasi simulasi sistem pemesanan cafe berbasis Android yang dikembangkan sebagai tugas praktikum dengan fokus pada:

- ✅ Implementasi **Jetpack Compose** untuk UI modern
- ✅ Penerapan **Navigation 3** (state-driven navigation tanpa NavController)
- ✅ **Passing data** antar screen
- ✅ Manajemen state dengan `remember` dan `mutableStateOf`
- ✅ Pengalaman pengguna (UX) yang terstruktur & intuitif

---

## 🚀 Fitur Utama

<table>
  <tr>
    <td align="center" width="50%">
      <h3>🔐 Autentikasi</h3>
      <ul align="left">
        <li>Login dengan validasi input</li>
        <li>Register akun baru</li>
        <li>Validasi username unik (anti duplikat)</li>
        <li>Password tersembunyi (secure input)</li>
        <li>Feedback via Snackbar</li>
      </ul>
    </td>
    <td align="center" width="50%">
      <h3>🛒 Pemesanan Menu</h3>
      <ul align="left">
        <li>Menu berdasarkan kategori (Coffee & Dessert)</li>
        <li>Tampilan grid yang rapi</li>
        <li>Tambah item ke keranjang</li>
        <li>Update quantity otomatis</li>
        <li>Hapus item dari keranjang</li>
      </ul>
    </td>
  </tr>
  <tr>
    <td align="center" width="50%">
      <h3>🧭 Navigasi Cerdas</h3>
      <ul align="left">
        <li>State-driven navigation</li>
        <li>Tanpa NavController</li>
        <li>Back stack manual (<code>add</code> / <code>removeLastOrNull</code>)</li>
        <li>Navigasi stabil tanpa crash</li>
      </ul>
    </td>
    <td align="center" width="50%">
      <h3>💳 Pembayaran & Struk</h3>
      <ul align="left">
        <li>Tampilan total harga</li>
        <li>Daftar item yang dibeli</li>
        <li>Format harga Rupiah</li>
        <li>Waktu transaksi tercatat</li>
        <li>Reset cart setelah selesai</li>
      </ul>
    </td>
  </tr>
</table>

---

## 🔄 Alur Aplikasi

```
┌─────────┐    ┌──────────┐    ┌──────┐    ┌────────┐
│  Login  │───▶│ Register │    │      │    │        │
│ Screen  │◀───│ Screen   │    │      │    │        │
└────┬────┘    └──────────┘    │      │    │        │
     │                         │      │    │        │
     ▼                         │      │    │        │
┌─────────┐    ┌──────────┐    │ Cart │───▶│Payment │
│  Home   │───▶│  Detail  │───▶│      │    │        │
│ Screen  │    │  Screen  │    │      │    │        │
└─────────┘    └──────────┘    └──────┘    └───┬────┘
     ▲                                          │
     │              ┌──────────┐                ▼
     └──────────────│ Receipt  │◀───────────────┘
                    │ Screen   │
                    └──────────┘
```

### Langkah Penggunaan

| # | Langkah | Keterangan |
|---|---------|------------|
| 1 | 🔐 **Login / Register** | Masuk atau buat akun baru |
| 2 | 🏠 **Home** | Lihat daftar menu per kategori |
| 3 | 📋 **Detail** | Lihat detail menu yang dipilih |
| 4 | 🛒 **Cart** | Kelola keranjang belanja |
| 5 | 💳 **Payment** | Konfirmasi & lakukan pembayaran |
| 6 | 🧾 **Receipt** | Lihat struk transaksi |

---

## 🧱 Teknologi

<div align="center">

| Teknologi | Kegunaan |
|-----------|----------|
| ![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=flat&logo=kotlin&logoColor=white) | Bahasa pemrograman utama |
| ![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=flat&logo=jetpackcompose&logoColor=white) | Framework UI deklaratif |
| ![Material 3](https://img.shields.io/badge/Material%203-757575?style=flat&logo=material-design&logoColor=white) | Design system & komponen UI |
| `Navigation 3` | State-driven navigation (tanpa NavController) |
| `remember` + `mutableStateOf` | State management |
| `mutableStateListOf` | State management untuk list |

</div>

---

## 📁 Struktur Project

```
com.praktikum.lumera
│
├── 📂 model
│   ├── Menu.kt          # Data class menu cafe
│   ├── CartItem.kt      # Data class item keranjang
│   └── UserData.kt      # Data class akun pengguna
│
├── 📂 navigation
│   ├── AppNavigation.kt # State-driven navigation handler
│   └── Routes.kt        # Definisi rute/screen
│
├── 📂 screens
│   ├── 🔐 login         # Halaman login
│   ├── 📝 register      # Halaman register
│   ├── 🏠 home          # Halaman daftar menu
│   ├── 📋 detail        # Halaman detail menu
│   ├── 🛒 cart          # Halaman keranjang
│   ├── 💳 payment       # Halaman pembayaran
│   └── 🧾 receipt       # Halaman struk
│
├── 📂 components        # Komponen UI reusable
└── 📂 data              # Data statis (menu, dll)
```

---

## ⚙️ Cara Menjalankan

### Prasyarat

- Android Studio **Hedgehog** atau lebih baru
- Android SDK minimal **API 26**
- Kotlin **1.9+**

### Langkah Instalasi

```bash
# 1. Clone repository
git clone https://github.com/rismaramadhani403/ProjectLumera.git

# 2. Buka di Android Studio
# File → Open → pilih folder ProjectLumera

# 3. Sync Gradle (otomatis, atau klik "Sync Now")

# 4. Jalankan aplikasi
# Klik tombol ▶ Run, pilih emulator atau perangkat fisik
```

> **💡 Tips:** Gunakan emulator dengan API 33+ (Android 13) untuk hasil terbaik.

---

## ✅ Ketentuan Terpenuhi

| Ketentuan | Status |
|-----------|--------|
| Jetpack Compose & Material 3 | ✅ Terpenuhi |
| Navigation 3 (state-driven) | ✅ Terpenuhi |
| Back Navigation | ✅ Terpenuhi |
| Passing parameter antar screen | ✅ Terpenuhi |
| Conditional Navigation (validasi login) | ✅ Terpenuhi |
| Tidak crash saat navigasi | ✅ Terpenuhi |

---

## 👥 Tim Pengembang

<div align="center">

| Nama | NIM |
|------|-----|
| 👩‍💻 **Risma Ramadhani** | L0324030 |
| 👩‍💻 **Wizad Akmalia Zulfaa** | L0324036 |
| 👩‍💻 **Zefanya Christian Natasha** | L0324037 |

_Kelompok 4 — Praktikum Mobile Programming_

</div>

---

## 📝 Catatan Pengembangan

Aplikasi ini menggunakan pendekatan **in-memory storage** (data tidak tersimpan permanen). Data pengguna dan keranjang akan di-reset saat aplikasi ditutup — sesuai dengan kebutuhan tugas praktikum.

---

<div align="center">

**Lumera Cafe App** · Dibuat dengan ❤️ menggunakan Jetpack Compose

_"From bean to screen — order your favorite brew."_ ☕

</div>
