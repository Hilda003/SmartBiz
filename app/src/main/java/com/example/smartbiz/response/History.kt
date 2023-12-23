package com.example.smartbiz.response

import com.google.gson.annotations.SerializedName

data class History(

	@field:SerializedName("data")
	val data: List<DataHistory>,

	@field:SerializedName("status")
	val status: String
)

data class DataHistory(

	@field:SerializedName("jenis")
	val jenis: String,

	@field:SerializedName("nama_barang")
	val namaBarang: String,

	@field:SerializedName("total_harga")
	val totalHarga: Float,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("tanggal")
	val tanggal: String
)
