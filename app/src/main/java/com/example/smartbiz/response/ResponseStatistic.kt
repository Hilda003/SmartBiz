package com.example.smartbiz.response

import com.google.gson.annotations.SerializedName

data class ResponseStatistic(

	@field:SerializedName("data")
	val data: DataStatistic,

	@field:SerializedName("status")
	val status: String
)

data class DataStatistic(

	@field:SerializedName("total_expense")
	val totalExpense: Int,

	@field:SerializedName("total_income")
	val totalIncome: Int,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("total_profit")
	val totalProfit: Int,

	@field:SerializedName("id")
	val id: Int
)
