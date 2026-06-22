package com.praktikum.lumera.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.praktikum.lumera.data.remote.ApiService
import com.praktikum.lumera.data.remote.NetworkConfig
import com.praktikum.lumera.model.Menu

import kotlinx.coroutines.launch

/**
 * MenuViewModel (Week 11)
 *
 * Sebelumnya: menus langsung diambil dari MenuData.menuList (data dummy
 * yang ditulis manual di kode Kotlin).
 *
 * Sekarang: menus diisi dengan cara memanggil API PHP
 * (lumera-coffee/api/get_menu.php), yang membaca langsung dari database
 * MySQL "lumera_coffee". Jadi kalau ada menu baru ditambahkan lewat
 * halaman admin web, menu itu juga akan muncul di sini setelah fetchMenu()
 * dipanggil ulang (misalnya lewat pull-to-refresh atau buka ulang Home).
 *
 * apiService dikirim lewat constructor (bukan @Inject) supaya tetap
 * kompatibel dengan cara MainActivity membuat ViewModel ini secara manual:
 *   val menuViewModel = MenuViewModel(apiService)
 */
class MenuViewModel(
    private val apiService: ApiService = NetworkConfig.defaultApiService()
) : ViewModel() {

    // =========================
    // MENU LIST
    // =========================
    val menus = mutableStateListOf<Menu>()

    // =========================
    // UI STATE (loading & error)
    // =========================
    val isLoading = mutableStateOf(false)
    val errorMessage = mutableStateOf<String?>(null)

    init {
        fetchMenu()
    }

    // =========================
    // FETCH MENU DARI API (Week 11)
    // =========================
    fun fetchMenu(
        cari: String? = null,
        kategori: String? = null
    ) {

        viewModelScope.launch {

            isLoading.value = true
            errorMessage.value = null

            try {

                val response =
                    apiService.getMenu(cari = cari, kategori = kategori)

                val mapped =
                    response.map { it.toMenu(NetworkConfig.BASE_URL) }

                menus.clear()
                menus.addAll(mapped)

            } catch (e: Exception) {

                // Penyebab paling umum kalau gagal sampai sini:
                // 1. XAMPP/Laragon belum dijalankan (Apache + MySQL)
                // 2. BASE_URL di NetworkConfig salah (cek 10.0.2.2 vs IP WiFi)
                // 3. HP/emulator tidak satu jaringan dengan server
                errorMessage.value =
                    "Gagal memuat menu: ${e.message ?: "periksa koneksi ke server"}"

            } finally {

                isLoading.value = false
            }
        }
    }

    // =========================
    // ADD MENU (lokal, sementara di state Compose)
    // =========================
    fun addMenu(

        menu: Menu
    ) {

        menus.add(menu)
    }

    // =========================
    // REMOVE MENU (lokal, sementara di state Compose)
    // =========================
    fun removeMenu(

        menu: Menu
    ) {

        menus.remove(menu)
    }

    // =========================
    // UPDATE MENU (lokal, sementara di state Compose)
    // =========================
    fun updateMenu(

        oldMenu: Menu,

        newMenu: Menu
    ) {

        val index =
            menus.indexOf(oldMenu)

        if (index != -1) {

            menus[index] = newMenu
        }
    }
}