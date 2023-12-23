package com.example.smartbiz.response

import com.google.gson.annotations.SerializedName

data class Expense(
	val userId: Int,
	val tanggal: String,
	val namaBarang: String,
	val jumlahBarang: Int,
	val hargaBarang: Int,
	val totalExpense: Int
)
