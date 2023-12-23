package com.example.smartbiz.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartbiz.data.Repository
import com.example.smartbiz.response.GetDetailProfile
import com.example.smartbiz.response.History
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: Repository) : ViewModel() {
    val userData = MutableLiveData<History>()

    suspend fun getHistory(userId: Int) : History{
       return repository.getHistory(userId)
    }
}