package com.example.smartbiz.utils

import android.support.annotation.StringRes
import com.example.smartbiz.R

data class Category(
    @StringRes
    val name: Int,
) {

      companion object {
          val hoodie = Category(R.string.hoodie)
          val sweatshirt = Category(R.string.sweatshirt)
          val sweatpants = Category(R.string.sweatpants)
          val jacket = Category(R.string.jacket)


          val all = arrayListOf(hoodie, sweatshirt, sweatpants, jacket)
      }


}