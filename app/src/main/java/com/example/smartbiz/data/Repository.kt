package com.example.smartbiz.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.smartbiz.response.CreateExpense
import com.example.smartbiz.response.CreateIncome
import com.example.smartbiz.response.CreateIncomeRequest
import com.example.smartbiz.response.DataItem
import com.example.smartbiz.response.EditItem
import com.example.smartbiz.response.Expense
import com.example.smartbiz.response.ForgotPasswordRequest
import com.example.smartbiz.response.GetAllItem
import com.example.smartbiz.response.GetDetailProfile
import com.example.smartbiz.response.History
import com.example.smartbiz.response.InputItem
import com.example.smartbiz.response.Login
import com.example.smartbiz.response.Profit
import com.example.smartbiz.response.RecoveryPassword
import com.example.smartbiz.response.Register
import com.example.smartbiz.response.ResponseLogin
import com.example.smartbiz.retrofit.ApiService
import retrofit2.Response

class Repository(
    private val apiService: ApiService
) {

    fun postLogin(
        username: String,
        password: String
    ) : LiveData<Result<ResponseLogin>> = liveData {
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


    suspend fun postCreateExpense(expense: Expense) : CreateExpense  {
        return apiService.createExpense(expense)
    }

    suspend fun postCreateIncome(createIncomeRequest: CreateIncomeRequest) : CreateIncome {
        return apiService.createIncome(createIncomeRequest)
    }


    suspend fun getItemByUserId(userId: Int) : GetAllItem {
        return apiService.getAllItem(userId)
    }
    suspend fun getDetailProfile(userId: Int) : GetDetailProfile {
        return apiService.getDetailProfile(userId)
    }

    suspend fun getHistory(userId: Int) : History {
        return apiService.getHistory(userId)
    }

    suspend fun getAllTransaction(userId: Int) : Profit {
        return apiService.getAllTransaction(userId)
    }


    suspend fun recoverPassword(email: String, token: String, newPassword: String): Response<RecoveryPassword> {
        val request = RecoveryPassword(email, token, newPassword)
        return apiService.recoverPassword(request)
    }

    suspend fun requestToken(email: String) = apiService.requestResetToken(ForgotPasswordRequest(email))
}