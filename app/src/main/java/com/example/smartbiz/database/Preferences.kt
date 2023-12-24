package com.example.smartbiz.database

import android.content.Context
import android.util.Log


class Preferences (context: Context) {
private val preferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)

    fun setUser(user: User){
        val editor = preferences.edit()
        user.userId?.let { editor.putInt("userId", it) }
        editor.apply()
    }

    fun getUser() : User {
        val user = User()
        user.userId = preferences.getInt("userId", 0)
        return user
    }
    fun getUserId(): Int {
        val userId = preferences.getInt("userId", 0)
        Log.d("Preferences", "User ID retrieved: $userId")
        return userId

    }

    fun getItem() : String {
        val item = preferences.getString("namaBarang", "").toString()
        Log.d("Preferences", "Item retrieved: $item")
        return item
    }

    fun getUsername(): String {
        val username = preferences.getString("username", "").toString()
        Log.d("Preferences", "Username retrieved: $username")
        return username
    }


}