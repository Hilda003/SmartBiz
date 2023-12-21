package com.example.smartbiz.viewmodel

import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModel
import com.example.smartbiz.data.Repository

class CreateOutcomeViewModel(private val repository: Repository) : ViewModel() {

    var selectedItem = arrayOf("Hoodie", "Jacket", "T-shirt", "Shoes")
    val NEW_SPINNER_ID = 1



    fun postCreateOutcome(

        userId: Int,
        barangId: Int,
        tanggal: String,
        jumlahBarang: Int,
        hargaBarang: Int,
        totalExpense: Int

    ) = repository.postCreateExpense(userId, barangId, tanggal, jumlahBarang, hargaBarang, totalExpense)



}