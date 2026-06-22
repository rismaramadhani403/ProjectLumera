package com.praktikum.lumera.di

import android.content.Context
import androidx.room.Room
import com.praktikum.lumera.data.local.dao.TransactionDao
import com.praktikum.lumera.data.local.dao.UserDao
import com.praktikum.lumera.data.local.database.LumeraDatabase
import com.praktikum.lumera.data.remote.ApiService
import com.praktikum.lumera.data.remote.NetworkConfig
import com.praktikum.lumera.datastore.UserPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // =========================
    // USER PREFERENCES
    // =========================
    @Provides
    @Singleton
    fun provideUserPreferences(
        @ApplicationContext context: Context
    ): UserPreferences {

        return UserPreferences(context)
    }

    // =========================
    // ROOM DATABASE
    // =========================
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): LumeraDatabase {

        return Room.databaseBuilder(
            context,
            LumeraDatabase::class.java,
            "lumera_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    // =========================
    // USER DAO
    // =========================
    @Provides
    fun provideUserDao(
        database: LumeraDatabase
    ): UserDao {

        return database.userDao()
    }

    // =========================
    // TRANSACTION DAO
    // =========================
    @Provides
    fun provideTransactionDao(
        database: LumeraDatabase
    ): TransactionDao {

        return database.transactionDao()
    }

    // =========================
    // OKHTTP CLIENT (Week 11)
    // =========================
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    // =========================
    // RETROFIT (Week 11)
    // =========================
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl(NetworkConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // =========================
    // API SERVICE (Week 11)
    // =========================
    @Provides
    @Singleton
    fun provideApiService(
        retrofit: Retrofit
    ): ApiService {

        return retrofit.create(ApiService::class.java)
    }
}