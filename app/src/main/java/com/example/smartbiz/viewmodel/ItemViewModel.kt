package com.example.smartbiz.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartbiz.data.Repository
import com.example.smartbiz.response.DataItem
import com.example.smartbiz.response.GetAllItem
import com.example.smartbiz.retrofit.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ItemViewModel(private val apiService: ApiService, val repository: Repository) : ViewModel() {


    private val _itemNameList = MutableLiveData<List<String>>()
    val itemNameList: LiveData<List<String>> get() = _itemNameList


    fun fetchData(userId: Int) {
        viewModelScope.launch {


        }

    }

}
