package com.example.smartbiz.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartbiz.data.Repository
import com.example.smartbiz.response.EditItem
import kotlinx.coroutines.launch
import com.example.smartbiz.data.Result

class EditItemViewModel(private val repository: Repository) : ViewModel() {

    private val _editBarangResult = MutableLiveData<Result<EditItem>>()
    val editBarangResult: LiveData<Result<EditItem>> get() = _editBarangResult

}
