package com.example.smartbiz.database

import android.content.Context


class Preferences (context: Context) {
private val preferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)

    fun setUser(user: User){
        val editor = preferences.edit()
        editor.putInt("userId", user.userId!!)
        editor.apply()
    }

    fun getUser() : User {
        val user = User()
        user.userId = preferences.getInt("userId", 0)
        return user
    }
}