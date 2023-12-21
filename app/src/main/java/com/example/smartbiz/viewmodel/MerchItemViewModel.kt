package com.example.smartbiz.viewmodel


import androidx.lifecycle.ViewModel
import com.example.smartbiz.data.Repository
import com.example.smartbiz.response.GetAllItem

class MerchItemViewModel(private val repository: Repository) : ViewModel() {

    suspend fun getItemsByUserId(userId: Int): GetAllItem {
        return repository.getItemByUserId(userId)
    }
}


