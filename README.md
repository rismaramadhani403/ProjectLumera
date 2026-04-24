☕ Lumera Cafe App

Aplikasi Sistem Pemesanan Menu Cafe berbasis Android yang dikembangkan menggunakan Kotlin dan Jetpack Compose. Aplikasi ini memungkinkan pengguna untuk melakukan pemesanan menu secara interaktif mulai dari proses login hingga pembayaran, dengan alur navigasi yang terstruktur dan pengalaman pengguna (UX) yang baik.

📌 Deskripsi Aplikasi

Lumera Cafe App merupakan aplikasi sederhana yang mensimulasikan sistem pemesanan di sebuah cafe. Pengguna dapat:

-Login atau mendaftar akun

-Melihat daftar menu berdasarkan kategori

-Melihat detail menu

-Menambahkan item ke keranjang

-Melakukan pembayaran

-Melihat struk pembelian

Aplikasi ini dibuat untuk memenuhi tugas praktikum dengan fokus pada Navigation 3 (state-driven navigation) serta penerapan konsep UI modern menggunakan Jetpack Compose.

🎯 Tujuan Pengembangan
1. Mengimplementasikan Jetpack Compose dalam pembuatan UI Android
2. Menerapkan Navigation 3 (state-based navigation) tanpa NavController
3. Menghubungkan antar screen dalam satu alur aplikasi
4. Menerapkan passing data antar screen
5. Meningkatkan pengalaman pengguna melalui validasi dan feedback

🚀 Fitur Utama

🔐 1. Authentication (Login & Register)

      -Login dengan validasi input (tidak boleh kosong)
      
      -Register akun baru
      
      -Validasi username unik (tidak bisa daftar 2x)
      
      -Password disembunyikan (secure input)
      
      -Notifikasi menggunakan Snackbar

🧭 2. Navigation (Navigation 3)

      -Menggunakan state-driven navigation
      
      -Tidak menggunakan NavController
      
      -Menggunakan backStack manual:
      
         -add() → pindah screen
         
         -removeLastOrNull() → kembali
         
      -Navigasi antar screen berjalan tanpa crash
      
🛒 3. Sistem Pemesanan

      -Menampilkan menu berdasarkan kategori (Coffee & Dessert)
      
      -Menampilkan dalam bentuk grid
      
      -Tambah menu ke keranjang
      
      -Update quantity otomatis jika item sudah ada
      
      -Hapus item dari keranjang
      
📄 4. Detail Menu

      -Menampilkan nama menu
      
      -Menampilkan harga
      
      -Passing data dari Home ke Detail
      
💳 5. Payment & Receipt

      -Menampilkan total harga
      
      -Menampilkan daftar item yang dibeli
      
      -Format harga dalam rupiah
      
      -Menampilkan waktu transaksi
      
      Tombol:
      
      Selesai → kembali ke login + reset cart
      
      Kembali ke Home → kembali ke menu
      
📦 6. Manajemen Data

      -UserData untuk menyimpan akun
      
      -CartItem untuk keranjang
      
      -Validasi login dan register
      
      -Data disimpan sementara (in-memory)
      
🔄 Alur Aplikasi
1. Login

<img width="435" height="734" alt="image" src="https://github.com/user-attachments/assets/8051a335-0eb7-4672-bbfa-98732a4ea74b" />


2. Register

<img width="410" height="773" alt="image" src="https://github.com/user-attachments/assets/0a20f26f-2323-466a-b889-c95aea986759" />


3. Login 

<img width="405" height="779" alt="image" src="https://github.com/user-attachments/assets/072a31cf-1371-430e-b6de-9e4ae5b83e69" />


4. Home

<img width="439" height="775" alt="image" src="https://github.com/user-attachments/assets/7d3b8162-a5fb-4563-959c-319de8ee6c85" />


<img width="402" height="775" alt="image" src="https://github.com/user-attachments/assets/96d0ca3a-6236-488f-9552-31ca66320d84" />


5. Detail

<img width="416" height="776" alt="image" src="https://github.com/user-attachments/assets/463dbf03-1053-4504-a81a-b40b09a85bd1" />


6. Cart

<img width="416" height="777" alt="image" src="https://github.com/user-attachments/assets/a119738b-ef06-4df7-9a6e-00d0aa708cbc" />


7. Payment

<img width="438" height="775" alt="image" src="https://github.com/user-attachments/assets/0f8182d9-a6de-4b19-9590-74a3c8a5cea8" />


8. Receipt

<img width="405" height="770" alt="image" src="https://github.com/user-attachments/assets/90f561f7-b1c2-481c-9561-b538d4c21e5c" />



Penjelasan:
1. User login atau register terlebih dahulu
2. Masuk ke halaman Home (menu)
3. Pilih menu → masuk Detail
4. Tambah ke cart
5. Checkout → Payment
6. Bayar → Receipt
7. Selesai atau kembali ke Home

🧱 Teknologi yang Digunakan
1. Kotlin
2. Jetpack Compose
3. Material 3
4. State Management:
   
   -remember
   
   -mutableStateOf
   
   -mutableStateListOf
6. Navigation 3 (State-driven navigation)









