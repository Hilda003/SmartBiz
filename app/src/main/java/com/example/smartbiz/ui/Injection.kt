package com.example.smartbiz.ui

import android.content.Context
import com.example.smartbiz.data.Repository
import com.example.smartbiz.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): Repository {

        val apiService = ApiConfig.getApiService()
        return Repository(apiService)
    }

}
