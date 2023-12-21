package com.example.smartbiz.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartbiz.data.Repository
import com.example.smartbiz.response.CreateIncome
import com.example.smartbiz.response.CreateIncomeRequest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import com.example.smartbiz.data.Result

class CreateIncomeViewModel(private val repository: Repository) : ViewModel() {
    private val _selectedDate = MutableLiveData<String>()
    val selectedDate : LiveData<String> get()  = _selectedDate
    private val _createIncomeResult = MutableLiveData<Result<CreateIncome>>()
    val createIncomeResult: LiveData<Result<CreateIncome>> get() = _createIncomeResult


    fun updateDate(calendar: Calendar) {
        val format = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        _selectedDate.value = sdf.format(calendar.time)
    }




    fun createIncome(createIncomeRequest: CreateIncomeRequest) {
        viewModelScope.launch {
            _createIncomeResult.value = Result.Loading
            try {
                val response = repository.postCreateIncome(createIncomeRequest)
                _createIncomeResult.value = Result.Success(response)
            } catch (e: Exception) {
                _createIncomeResult.value = Result.Error(e.message.toString())
            }
        }
    }


}