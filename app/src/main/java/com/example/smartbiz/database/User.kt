package com.example.smartbiz.database

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var userId: Int? = 0,
    var namaBarang : String? = "",
) : Parcelable
