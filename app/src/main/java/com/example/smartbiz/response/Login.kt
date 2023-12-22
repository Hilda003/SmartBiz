package com.example.smartbiz.response

import com.google.gson.annotations.SerializedName



data class Login(

	@field:SerializedName("loginResult")
	val loginResult: LoginResult,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class LoginResult(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("username")
	val username: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("token")
	val token: String
)
