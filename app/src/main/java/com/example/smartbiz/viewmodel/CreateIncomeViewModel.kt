package com.example.smartbiz.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartbiz.data.Repository
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CreateIncomeViewModel(private val repository: Repository) : ViewModel() {
    private val _selectedDate = MutableLiveData<String>()
    val selectedDate : LiveData<String> get()  = _selectedDate


    fun updateDate(calendar: Calendar) {
        val format = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        _selectedDate.value = sdf.format(calendar.time)
    }


    fun postCreateIncome(
        userId: Int,
        barangId: Int,
        tanggal: String,
        jumlahBarang: Int,
        hargaBarang: Int,
        totalExpense: Int
    ) = repository.postCreateIncome(userId, barangId, tanggal, jumlahBarang, hargaBarang, totalExpense)


}