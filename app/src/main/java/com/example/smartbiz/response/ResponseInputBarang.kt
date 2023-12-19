package com.example.smartbiz.response

import com.google.gson.annotations.SerializedName

data class ResponseInputBarang(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)
