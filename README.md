# Pertemuan 12 — Local Persistence (Room Database & DataStore Preferences)

Materi: membuat aplikasi yang bisa menyimpan data secara permanen di
perangkat (local storage), menggunakan dua pendekatan berbeda sesuai
jenis datanya:

- **Room Database** — untuk data terstruktur dengan banyak baris/record
  (User, Transaction), yang perlu di-query.
- **DataStore Preferences** — untuk data sederhana berbentuk key-value
  (sesi login, daftar menu favorit, alamat pengiriman).

## Daftar File

### Room Database

| No | File | Peran |
|----|------|-------|
| 01 | `UserEntity.kt` | Entity Room — struktur tabel `user` di SQLite (id, nama, email, password, role) |
| 02 | `TransactionEntity.kt` | Entity Room — struktur tabel `transaction` (riwayat pesanan) |
| 03 | `UserDao.kt` | DAO (Data Access Object) — query Room untuk user: insert, login (cek email+password), cari berdasarkan email |
| 04 | `TransactionDao.kt` | DAO — query Room untuk transaksi: insert transaksi baru, ambil semua riwayat transaksi |
| 05 | `LumeraDatabase.kt` | Kelas database Room (`@Database`) — mendaftarkan entity & DAO, jadi satu pintu masuk ke SQLite |

### DataStore Preferences

| No | File | Peran |
|----|------|-------|
| 06 | `UserPreferences.kt` | Wrapper DataStore — menyimpan sesi login (status logged in, role), daftar ID menu favorit, dan data alamat pengiriman, dalam bentuk `Flow` yang reaktif |

### Penghubung (memakai Room + DataStore bersamaan)

| No | File | Peran |
|----|------|-------|
| 07 | `CartViewModel.kt` | ViewModel — `transactionDao` (Room) untuk riwayat transaksi, `userPreferences` (DataStore) untuk menyimpan & memuat daftar favorit |
| 08 | `AppModule.kt` | Modul Hilt — menyediakan instance `LumeraDatabase`, `UserDao`, `TransactionDao` (Room), dan `UserPreferences` (DataStore) sebagai singleton ke seluruh aplikasi |
| 09 | `UserRepository.kt` | Repository — lapisan akses ke `UserDao` (Room) untuk register, login, dan cari user berdasarkan email |
| 10 | `SessionManager.kt` | **Catatan**: ini BUKAN local persistence — hanya state in-memory (`mutableStateOf`) untuk user yang sedang login, hilang saat aplikasi ditutup paksa. Disertakan karena berkaitan langsung dengan alur sesi login yang sebagian datanya disimpan permanen lewat `UserPreferences` (DataStore) |

## Alur Singkat

**Room (data terstruktur, perlu di-query):**
```
Register/Login -> UserRepository -> UserDao -> Room (tabel "user")
Checkout       -> CartViewModel.addTransaction() -> TransactionDao -> Room (tabel "transaction")
Order History  -> TransactionDao.getAllTransactions() -> ditampilkan di UI
```

**DataStore (key-value sederhana):**
```
Login berhasil  -> UserPreferences.saveLoginSession() -> tersimpan permanen
                   (sehingga tidak perlu login ulang setiap buka app)
Tandai favorit  -> CartViewModel.toggleFavorite() -> UserPreferences.saveFavorites()
Buka app lagi   -> UserPreferences.getFavorites (Flow) -> favoriteIds dimuat ulang
```

## Mengapa Dua Teknologi Berbeda?

| Kebutuhan | Pilihan | Alasan |
|---|---|---|
| Data User & Transaction (banyak baris, perlu query/filter) | Room | Mendukung SQL, relasi antar tabel, query kompleks |
| Sesi login, favorit, alamat (sederhana, key-value) | DataStore | Lebih ringan, tidak perlu skema tabel, API berbasis `Flow` yang reaktif |

Kombinasi ini sesuai dengan praktik umum pengembangan Android: data
**relasional/terstruktur** disimpan di Room, sedangkan **preferensi/
pengaturan ringan** disimpan di DataStore.
