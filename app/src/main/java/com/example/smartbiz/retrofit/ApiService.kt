package com.example.smartbiz.retrofit

import retrofit2.Call
import com.example.smartbiz.response.CreateIncome
import com.example.smartbiz.response.CreateIncomeRequest
import com.example.smartbiz.response.DataItem
import com.example.smartbiz.response.DataProfit
import com.example.smartbiz.response.EditItem
import com.example.smartbiz.response.Expense
import com.example.smartbiz.response.ForgotPasswordRequest
import com.example.smartbiz.response.GetAllItem
import com.example.smartbiz.response.InputItem
import com.example.smartbiz.response.Item
import com.example.smartbiz.response.Login
import com.example.smartbiz.response.Register
import com.example.smartbiz.response.ResetToken
import com.example.smartbiz.response.ResponseLogin
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
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

    @FormUrlEncoded
    @POST("create_expense")
    suspend fun createExpense(
        @Field("userId") userId: Int,
        @Field("barangId") barangId: Int,
        @Field("tanggal") tanggal: String,
        @Field("jumlahBarang") jumlahBarang: Int,
        @Field("HargaBarang") hargaBarang: Int,
        @Field("total_expense") totalExpense: Int,
    ) : Expense

    @FormUrlEncoded
    @POST("total_profit/{userId}")
    suspend fun getTotalProfit(
        @Field("userId") userId: Int,
    ) : DataProfit


    @POST("create_income")
    suspend fun createIncome(@Body request: CreateIncomeRequest): CreateIncome

    @GET("get_all_transactions/{userId}")
    suspend fun getAllTransactions(
        @Field("userId") userId: Int
    ) : Call<List<DataProfit>>


    @GET("get_all_barang/{userId}")
    suspend fun getAllItem(
        @Path("userId") userId: Int
    ) : GetAllItem

    @PUT("/edit_barang/{barangId}")
    suspend fun editBarang(
        @Path("barangId") barangId: Int,
        @Body editedBarang: EditItem
    ): Response<EditItem>


    @POST("/forgot-password")
    suspend fun requestResetToken(@Body request: ForgotPasswordRequest): ResetToken


}