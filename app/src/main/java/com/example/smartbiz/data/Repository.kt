package com.example.smartbiz.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.smartbiz.response.CreateIncome
import com.example.smartbiz.response.CreateIncomeRequest
import com.example.smartbiz.response.DataItem
import com.example.smartbiz.response.Expense
import com.example.smartbiz.response.GetAllItem
import com.example.smartbiz.response.InputItem
import com.example.smartbiz.response.Login
import com.example.smartbiz.response.Register
import com.example.smartbiz.retrofit.ApiService
import retrofit2.Response

class Repository(
    private val apiService: ApiService
) {

    fun postLogin(
        username: String,
        password: String
    ) : LiveData<Result<Login>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.loginResponse(username, password)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d("Login Error", e.message.toString())
            emit(Result.Error(e.message.toString()))

        }
    }

    fun postRegister(
        username: String,
        email: String,
        password: String
    ) : LiveData<Result<Register>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.registerResponse(username, email, password)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d("Register Error", e.message.toString())
            emit(Result.Error(e.message.toString()))

        }
    }

    fun postInputBarang(
        userId: Int,
        namaBarang: String,
        hargaBarang: Int,
        jumlahBarang: Int
    ) : LiveData<Result<InputItem>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.inputItem(userId, namaBarang, hargaBarang, jumlahBarang)
            emit(Result.Success(response))
        }
        catch (e: Exception){
            Log.d("Input Error", e.message.toString())
            emit(Result.Error(e.message.toString()))
        }
    }


    fun postCreateExpense(
        userId: Int,
        barangId: Int,
        tanggal: String,
        jumlahBarang: Int,
        hargaBarang: Int,
        totalExpense: Int
    ) : LiveData<Result<Expense>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.createExpense(userId,barangId, tanggal, jumlahBarang, hargaBarang, totalExpense)
            emit(Result.Success(response))
        }
        catch (e: Exception){
            Log.d("Create Expense Error", e.message.toString())
            emit(Result.Error(e.message.toString()))
        }
    }

    suspend fun postCreateIncome(createIncomeRequest: CreateIncomeRequest) : CreateIncome {
        return apiService.createIncome(createIncomeRequest)
    }


    suspend fun getItemByUserId(userId: Int) : GetAllItem {
        return apiService.getAllItem(userId)
    }



}