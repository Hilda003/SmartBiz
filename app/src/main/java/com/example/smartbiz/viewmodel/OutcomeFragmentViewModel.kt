package com.example.smartbiz.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class OutcomeFragmentViewModel : ViewModel() {
    private val _selectedDate = MutableLiveData<String>()
    val selectedDate : LiveData<String> get()  = _selectedDate

    private val _selectedPrice = MutableStateFlow("IDR")
    val selectedPrice = _selectedPrice

    private val _selectedQuantity = MutableStateFlow("1")
    val selectedQuantity = _selectedQuantity






    fun updateDate(calendar: Calendar) {
        val format = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        _selectedDate.value = sdf.format(calendar.time)


    }
}