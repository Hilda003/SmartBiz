package com.example.smartbiz.response

import com.google.gson.annotations.SerializedName

data class Login(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("username")
	val username: String
)
