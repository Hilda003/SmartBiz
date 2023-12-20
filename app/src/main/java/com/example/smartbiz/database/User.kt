package com.example.smartbiz.database

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var userId: Int? = null,
) : Parcelable
