package com.example.smartbiz.retrofit


import android.viewbinding.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import com.example.smartbiz.response.Login
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiConfig {
    companion object {
        fun getApiService(): ApiService {
            val loggingInterceptor =
                if(BuildConfig.DEBUG){
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                } else {
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
                }
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://gopreto.ts.r.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}
