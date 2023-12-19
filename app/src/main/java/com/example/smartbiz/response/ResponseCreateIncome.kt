package com.example.smartbiz.response

import com.google.gson.annotations.SerializedName

data class ResponseCreateIncome(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)
