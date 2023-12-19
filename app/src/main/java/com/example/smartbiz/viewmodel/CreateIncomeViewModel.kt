package com.example.smartbiz.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CreateIncomeViewModel : ViewModel() {
    private val _selectedDate = MutableLiveData<String>()
    val selectedDate : LiveData<String> get()  = _selectedDate


    fun updateDate(calendar: Calendar) {
        val format = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        _selectedDate.value = sdf.format(calendar.time)
    }
}