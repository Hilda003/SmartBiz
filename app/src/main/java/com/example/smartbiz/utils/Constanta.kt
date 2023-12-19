package com.example.smartbiz.utils

import android.content.Context
import androidx.annotation.StringRes
import com.example.smartbiz.R

class Constanta(context: Context) {

    val transaction_category = listOf(
        context.getString(
            Category.hoodie.name
        ),
        context.getString(
            Category.sweatshirt.name
        ),
        context.getString(
            Category.sweatpants.name
        ),
        context.getString(
            Category.jacket.name
        )
    )


}