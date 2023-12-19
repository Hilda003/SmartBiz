package com.example.smartbiz.retrofit

import android.telecom.Call
import com.example.smartbiz.response.Data
import com.example.smartbiz.response.InputItem
import com.example.smartbiz.response.Login
import com.example.smartbiz.response.Register
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    suspend fun loginResponse(
        @Field("username") username: String,
        @Field("password") password: String
    ): Login

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
        @Field("hargaBarang") hargaBarang: Int,
        @Field("jumlahBarang") jumlahBarang: Int
    ) : InputItem


    @GET("total_profit/{userId}")
    fun getProfit(
        @Field("userId") userId: Int
    ) : Response<Data>







}