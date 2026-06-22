package com.praktikum.lumera.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.praktikum.lumera.data.local.entity.UserEntity

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(
        user: UserEntity
    )

    @Query(
        "SELECT * FROM users WHERE email = :email"
    )
    suspend fun getUserByEmail(
        email: String
    ): UserEntity?

    @Query(
        """
        SELECT * FROM users
        WHERE email = :email
        AND password = :password
        """
    )
    suspend fun login(
        email: String,
        password: String
    ): UserEntity?

    @Query(
        "SELECT * FROM users"
    )
    suspend fun getAllUsers(): List<UserEntity>
}