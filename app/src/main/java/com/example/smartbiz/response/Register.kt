package com.example.smartbiz.response

import com.google.gson.annotations.SerializedName

data class Register(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String
)
