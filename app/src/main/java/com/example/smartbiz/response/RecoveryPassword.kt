package com.example.smartbiz.response

data class RecoveryPassword(
	val newPassword: String,
	val email: String,
	val token: String
)

