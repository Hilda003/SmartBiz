package com.example.smartbiz.database

import android.content.Context


class Preferences (context: Context) {
private val preferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)

    fun setUser(user: Int){
        val editor = preferences.edit()
        editor.putInt("userId", user)
        editor.apply()
    }

    fun getUser() : Int {
       val user = preferences.getInt("userId", 0)
        return user
    }
}