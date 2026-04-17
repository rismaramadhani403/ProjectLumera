☕Lumera Cafe - Sistem Pemesanan Menu

📖 Deskripsi

Lumera Cafe - Sistem Pemesanan Menu adalah aplikasi Android yang dikembangkan menggunakan Kotlin dengan teknologi modern seperti Jetpack Compose dan Material 3. Aplikasi ini dirancang untuk mensimulasikan sistem pemesanan menu pada sebuah cafe bernama Lumera.
Melalui aplikasi ini, pengguna dapat melakukan pemesanan makanan dan minuman secara digital dengan alur yang terstruktur, mulai dari login, memilih menu, menambahkan ke keranjang, melakukan pembayaran, hingga menerima struk transaksi. Aplikasi ini memberikan gambaran bagaimana sistem pemesanan cafe dapat diimplementasikan dalam bentuk aplikasi mobile yang sederhana namun fungsional.

✨ Fitur Utama

🔐 Login Pengguna
Akses awal sebelum masuk ke sistem

🍽️ Menu Cafe Lumera
Menampilkan daftar makanan dan minuman
Tampilan modern dan user-friendly

🛒 Keranjang Pesanan
Menyimpan menu yang dipilih
Mengatur jumlah pesanan
Menampilkan total harga otomatis

💳 Pembayaran
Menampilkan total tagihan
Simulasi pembayaran sederhana

🧾 Struk Transaksi
Menampilkan detail pesanan
Ringkasan pembayaran

🔄 Alur Sistem
Alur penggunaan aplikasi:
Pengguna login ke aplikasi
Memilih menu makanan/minuman dari cafe Lumera
Menambahkan pesanan ke keranjang
Melakukan checkout
Melakukan pembayaran
Melihat struk transaksi

📸 Preview Aplikasi
Tambahkan screenshot di folder /screenshots
[Login]
<img width="1918" height="985" alt="image" src="https://github.com/user-attachments/assets/d354d081-701f-4aa8-9ea7-9bc97b416ab0" />
[Menu]
Kopi
<img width="1919" height="1019" alt="image" src="https://github.com/user-attachments/assets/f4978cdb-9a3e-476d-a3cd-2b04cd352240" />
Deseert
<img width="1919" height="1018" alt="image" src="https://github.com/user-attachments/assets/fcd5f344-cf35-42d0-97e4-50981b652003" />
[Cart]
<img width="1919" height="985" alt="image" src="https://github.com/user-attachments/assets/142336f0-c036-4166-a5c0-9a1cfe189fd8" />
[Payment]
<img width="1913" height="1018" alt="image" src="https://github.com/user-attachments/assets/7e95febc-6589-4573-b859-16e05b92ff2b" />
[Receipt]
<img width="1915" height="1025" alt="image" src="https://github.com/user-attachments/assets/2fe9b990-c623-4f47-be59-97f3e1744998" />
🛠️ Teknologi yang Digunakan
Kotlin
Jetpack Compose
Material 3
Navigation Compose
State Management (Compose State)
📂 Struktur Proyek
com.praktikum.lumera

│
├── navigation/
│   └── AppNavigation.kt
│
├── screens/
│   ├── login/
│   ├── home/
│   ├── cart/
│   ├── payment/
│   └── receipt/
│
├── model/
│   └── CartItem.kt
│
└── MainActivity.kt

⚙️ Cara Menjalankan Aplikasi

Clone repository:

git clone https://github.com/username/lumera.git
Buka di Android Studio
Pastikan:
Minimum SDK: 21+
Compile SDK: 34
Sync Gradle
Jalankan aplikasi (▶️)
💡 Implementasi Penting
State Keranjang
val cart = remember { mutableStateListOf<CartItem>() }
Perhitungan Total Harga
val total = cart.sumOf { it.menu.price * it.quantity }
🎯 Tujuan Pengembangan
Aplikasi ini dibuat untuk:
Memahami Jetpack Compose
Mengimplementasikan navigasi antar halaman
Mengelola state dalam aplikasi Android
Membuat simulasi sistem pemesanan cafe
🚧 Pengembangan Selanjutnya
Integrasi database (Room / Firebase)
Login autentikasi real
Fitur pencarian menu
UI/UX lebih interaktif
Integrasi payment gateway
👨‍💻 Kelompok 4:
1.Risma Ramadhani(L0324030)
2.Wizad Akmalia Zulfaa(L0324036)
3.Zefanya Christian Natasha(L0324037)
Praktikum Pemrograman Aplikasi Bergerak
⭐ Penutup
Lumera Cafe - Sistem Pemesanan Menu merupakan implementasi aplikasi Android modern yang menunjukkan bagaimana proses pemesanan di cafe dapat didigitalisasi secara sederhana, efektif, dan mudah digunakan.
