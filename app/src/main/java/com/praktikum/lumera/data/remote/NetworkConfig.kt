package com.praktikum.lumera.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkConfig {

    /**
     * GANTI SESUAI KEBUTUHAN TESTING:
     *
     * 1) Emulator Android + XAMPP/Laragon di laptop yang sama:
     *      "http://10.0.2.2/lumera-coffee/"
     *    (10.0.2.2 adalah alamat khusus yang dipakai emulator untuk
     *     menunjuk ke "localhost" milik laptop/komputer host)
     *
     * 2) HP fisik + laptop, harus satu jaringan WiFi yang sama:
     *      "http://192.168.x.x/lumera-coffee/"
     *    (ganti 192.168.x.x dengan IP lokal laptop, cek lewat `ipconfig`
     *     di Windows pada bagian "IPv4 Address")
     *
     * 3) Kalau web sudah online/hosting:
     *      "https://nama-domain-kamu.com/lumera-coffee/"
     */
    const val BASE_URL = "http://10.0.2.2/lumera-coffee/"

    /**
     * Dipakai oleh MenuViewModel sebagai default value, karena MainActivity
     * membuat ViewModel secara manual (bukan lewat Hilt). Kalau nanti
     * MainActivity diubah untuk pakai @AndroidEntryPoint + hiltViewModel(),
     * ApiService cukup di-@Inject langsung dan fungsi ini tidak perlu lagi.
     */
    fun defaultApiService(): ApiService {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }
}