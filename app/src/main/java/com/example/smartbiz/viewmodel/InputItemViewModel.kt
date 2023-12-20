package com.example.smartbiz.viewmodel

import androidx.lifecycle.ViewModel
import com.example.smartbiz.data.Repository

class InputItemViewModel(private val repository: Repository) : ViewModel() {

    fun postInputItem(
        userId : Int,
        namaBarang: String,
        hargaBarang: Int,
        jumlahBarang: Int

    ) = repository.postInputBarang(userId, namaBarang, hargaBarang, jumlahBarang)

}