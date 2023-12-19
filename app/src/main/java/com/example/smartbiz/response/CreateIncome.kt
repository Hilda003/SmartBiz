package com.example.smartbiz.response

import com.google.gson.annotations.SerializedName

data class CreateIncome(

	@field:SerializedName("barangId")
	val barangId: Int,

	@field:SerializedName("jumlahBarang")
	val jumlahBarang: Int,

	@field:SerializedName("jam")
	val jam: String,

	@field:SerializedName("tanggal")
	val tanggal: String,

	@field:SerializedName("userId")
	val userId: Int
)
