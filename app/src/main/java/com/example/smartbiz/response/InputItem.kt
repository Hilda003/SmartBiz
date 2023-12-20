package com.example.smartbiz.response

import androidx.room.Entity
import com.google.gson.annotations.SerializedName


data class InputItem(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("item")
	val item: List<Item>
)



data class Item(

	@field:SerializedName("jumlahBarang")
	val jumlahBarang: Int,

	@field:SerializedName("namaBarang")
	val namaBarang: String,

	@field:SerializedName("userId")
	val userId: Int,

	@field:SerializedName("hargaBarang")
	val hargaBarang: Int
)
