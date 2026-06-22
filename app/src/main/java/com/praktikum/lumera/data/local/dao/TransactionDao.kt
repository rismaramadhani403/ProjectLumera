package com.praktikum.lumera.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.praktikum.lumera.data.local.entity.TransactionEntity

@Dao
interface TransactionDao {

    @Insert
    suspend fun insertTransaction(
        transaction: TransactionEntity
    )

    @Query(
        """
        SELECT * FROM transactions
        WHERE customerName = :name
        """
    )
    suspend fun getCustomerTransactions(
        name: String
    ): List<TransactionEntity>

    @Query(
        """
        SELECT * FROM transactions
        WHERE cashierName = :name
        """
    )
    suspend fun getCashierTransactions(
        name: String
    ): List<TransactionEntity>

    @Query(
        """
        SELECT * FROM transactions
        """
    )
    suspend fun getAllTransactions():
            List<TransactionEntity>
}