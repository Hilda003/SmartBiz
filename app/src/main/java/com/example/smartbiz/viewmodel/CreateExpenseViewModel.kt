package com.example.smartbiz.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartbiz.data.Repository
import com.example.smartbiz.data.Result
import com.example.smartbiz.response.CreateExpense
import com.example.smartbiz.response.CreateIncome
import com.example.smartbiz.response.CreateIncomeRequest
import com.example.smartbiz.response.Expense
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CreateExpenseViewModel(private val repository: Repository) : ViewModel() {
    private val _selectedDate = MutableLiveData<String>()
    val selectedDate : LiveData<String> get()  = _selectedDate
    private val _createExpense = MutableLiveData<Result<CreateExpense>>()
    val createExpenseResult: LiveData<Result<CreateExpense>> get() = _createExpense

    fun createExpense(expense: Expense) {
        viewModelScope.launch {
            _createExpense.value = Result.Loading
            try {
                val response = repository.postCreateExpense(expense)
                _createExpense.value = Result.Success(response)
            } catch (e: Exception) {
                _createExpense.value = Result.Error(e.message.toString())
            }
        }
    }

    fun updateDate(calendar: Calendar) {
        val format = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        _selectedDate.value = sdf.format(calendar.time)
    }
}