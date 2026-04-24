package com.praktikum.lumera.model

data class User(
    val username: String,
    val password: String
)

object UserData {

    private val users = mutableListOf<User>()

    fun isUserExists(username: String): Boolean {
        return users.any { it.username.equals(username, ignoreCase = true) }
    }

    fun addUser(username: String, password: String) {
        users.add(User(username, password))
    }
}