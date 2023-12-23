package com.example.smartbiz.retrofit

import com.example.smartbiz.response.CreateExpense
import retrofit2.Call
import com.example.smartbiz.response.CreateIncome
import com.example.smartbiz.response.CreateIncomeRequest
import com.example.smartbiz.response.DataItem
import com.example.smartbiz.response.DataProfit
import com.example.smartbiz.response.EditItem
import com.example.smartbiz.response.Expense
import com.example.smartbiz.response.ForgotPasswordRequest
import com.example.smartbiz.response.GetAllItem
import com.example.smartbiz.response.GetDetailProfile
import com.example.smartbiz.response.History
import com.example.smartbiz.response.InputItem
import com.example.smartbiz.response.Profit
import com.example.smartbiz.response.Register
import com.example.smartbiz.response.ResetToken
import com.example.smartbiz.response.ResponseLogin
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    suspend fun loginResponse(
        @Field("username") username: String,
        @Field("password") password: String
    ): ResponseLogin

    @FormUrlEncoded
    @POST("signup")
    suspend fun registerResponse(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Register

    @FormUrlEncoded
    @POST("add_barang")
    suspend fun inputItem(
        @Field("userId") userId: Int,
        @Field("namaBarang") namaBarang: String,
        @Field("jumlahBarang") jumlahBarang: Int,
        @Field("hargaBarang") hargaBarang: Int
    ) : InputItem

    @POST("create_income")
    suspend fun createIncome(@Body request: CreateIncomeRequest): CreateIncome
    @POST("create_expense")
    suspend fun createExpense(@Body request: Expense): CreateExpense



    @GET("get_all_barang/{userId}")
    suspend fun getAllItem(
        @Path("userId") userId: Int
    ) : GetAllItem

    @GET("get_profile/{userId}")
    suspend fun getDetailProfile(
        @Path("userId") userId: Int
    ) : GetDetailProfile

    @GET("history/{userId}")
    suspend fun getHistory(
        @Path("userId") userId: Int
    ) : History

    @GET("user_profit/{userId}")
    suspend fun getAllTransaction(
        @Path("userId") userId: Int
    ) : Profit



    @PUT("/edit_barang/{barangId}")
    suspend fun editBarang(
        @Path("barangId") barangId: Int,
        @Body editedBarang: EditItem
    ): Response<EditItem>


    @POST("/forgot-password")
    suspend fun requestResetToken(@Body request: ForgotPasswordRequest): ResetToken


}