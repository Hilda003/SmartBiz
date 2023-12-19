package com.example.smartbiz.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.smartbiz.ui.Settings
import kotlinx.coroutines.launch

class SettingViewModel(private val pref: Settings) : ViewModel() {
    fun getThemeApp() : LiveData<Int> {
        return pref.getThemeApp().asLiveData()
    }

    fun saveThemeApp(theme: Int) {
        viewModelScope.launch {
            pref.saveThemeApp(theme)
        }
    }
}