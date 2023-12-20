package com.example.smartbiz.viewmodel

import androidx.lifecycle.ViewModel
import com.example.smartbiz.data.Repository

class CreateOutcomeViewModel(private val repository: Repository) : ViewModel() {

    fun postCreateOutcome(

        userId: Int,
        barangId: Int,
        tanggal: String,
        jumlahBarang: Int,
        hargaBarang: Int,
        totalExpense: Int

    ) = repository.postCreateExpense(userId, barangId, tanggal, jumlahBarang, hargaBarang, totalExpense)



}