package com.example.smartbiz.response

import com.google.gson.annotations.SerializedName

data class CreateIncome(

	@field:SerializedName("namaBarang")
	val namaBarang: String,

	@field:SerializedName("jumlahBarang")
	val jumlahBarang: Int,

	@field:SerializedName("tanggal")
	val tanggal: String,

	@field:SerializedName("hargaBarang")
	val hargaBarang: Int,

	@field:SerializedName("totalIncome")
	val totalIncome: Int,

	@field:SerializedName("userId")
	val userId: Int
)
