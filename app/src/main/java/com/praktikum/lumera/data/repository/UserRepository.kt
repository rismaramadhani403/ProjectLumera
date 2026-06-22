package com.praktikum.lumera.data.repository

import com.praktikum.lumera.data.local.dao.UserDao
import com.praktikum.lumera.data.local.entity.UserEntity

class UserRepository(
    private val userDao: UserDao
) {

    suspend fun registerUser(
        user: UserEntity
    ) {
        userDao.insertUser(user)
    }

    suspend fun login(
        email: String,
        password: String
    ): UserEntity? {

        return userDao.login(
            email,
            password
        )
    }

    suspend fun getUserByEmail(
        email: String
    ): UserEntity? {

        return userDao.getUserByEmail(email)
    }
}