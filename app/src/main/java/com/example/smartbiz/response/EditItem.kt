package com.example.smartbiz.response

import com.google.gson.annotations.SerializedName

data class EditItem(
    @SerializedName("barangId")
    val barangId: Int,
    @SerializedName("namaBarang")
    val namaBarang: String,
    @SerializedName("hargaBarang")
    val hargaBarang: Int
)