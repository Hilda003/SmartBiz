package com.example.smartbiz.response

data class CreateIncomeRequest(
    val userId: Int,
    val tanggal: String,
    val namaBarang: String,
    val jumlahBarang: Int,
    val hargaBarang: Int,
    val totalIncome: Int
)
