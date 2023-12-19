package com.example.smartbiz.response

import com.google.gson.annotations.SerializedName

data class Profit(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("status")
	val status: String
)

data class Data(

	@field:SerializedName("total_expense")
	val totalExpense: Int,

	@field:SerializedName("total_income")
	val totalIncome: Int,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("total_profit")
	val totalProfit: Int
)
