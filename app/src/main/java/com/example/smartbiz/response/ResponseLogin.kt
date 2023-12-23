package com.example.smartbiz.response

import com.google.gson.annotations.SerializedName

data class ResponseLogin(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String,

	@field:SerializedName("userId")
	val userId: Int,

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("username")
	val username: String,
)
