package com.example.smartbiz.response

import com.google.gson.annotations.SerializedName

data class DataItem(

	@field:SerializedName("jumlah_barang")
	val jumlahBarang: Int,

	@field:SerializedName("nama_barang")
	val namaBarang: String,

	@field:SerializedName("harga_barang")
	val hargaBarang: Int,

	@field:SerializedName("id")
	val id: Int
)

data class GetAllItem(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("status")
	val status: String
)
