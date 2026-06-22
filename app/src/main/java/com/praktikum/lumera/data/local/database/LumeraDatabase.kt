package com.praktikum.lumera.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.praktikum.lumera.data.local.dao.TransactionDao
import com.praktikum.lumera.data.local.dao.UserDao
import com.praktikum.lumera.data.local.entity.TransactionEntity
import com.praktikum.lumera.data.local.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        TransactionEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class LumeraDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun transactionDao(): TransactionDao
}