package com.example.smartbiz.ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.activity.viewModels

import androidx.core.graphics.drawable.toDrawable
import com.example.smartbiz.MainActivity
import com.example.smartbiz.R
import com.example.smartbiz.data.*
import com.example.smartbiz.database.Preferences
import com.example.smartbiz.databinding.ActivityInputDataBinding
import com.example.smartbiz.databinding.PopupDialogBinding
import com.example.smartbiz.viewmodel.InputItemViewModel


class InputDataActivity : AppCompatActivity() {

    private lateinit var binding : ActivityInputDataBinding
    private lateinit var preferences: Preferences
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferences = Preferences(this)




        val viewModelFactory = ViewModelFactory.getInstance(this)
        val inputItemViewModel : InputItemViewModel by viewModels {
            viewModelFactory
        }

        binding.txtDesc.text = "Hello, ${preferences.getUsername()} Welcome to SmartBiz!"




        binding.btnAdd.setOnClickListener {
            val product = binding.product.text.toString().trim()
            val quantity = binding.tvQuantity.text.toString().trim()
            val price = binding.priceEditText.text.toString().trim()

            if (product.isEmpty() || quantity.isEmpty() || price.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            } else {
                try {
                    val userId = preferences.getUserId()
                    inputItemViewModel.postInputItem(
                        userId,
                        product,
                        quantity.toInt(),
                        price.toInt()
                    ).observe(this) {
                        when (it) {
                            is Result.Loading -> {
                                binding.progressBar.visibility = View.VISIBLE
                            }
                            is Result.Success -> {
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(this, "Input Data Success", Toast.LENGTH_SHORT).show()
                                binding.product.text.clear()
                                binding.tvQuantity.text.clear()
                                binding.priceEditText.text.clear()
                            }
                            is Result.Error -> {
                                binding.progressBar.visibility = View.GONE
                                Log.d("InputError", it.error)
                                Toast.makeText(this, "Input Data Failed", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                } catch (e: NumberFormatException) {
                    // Tangani jika ada kesalahan konversi ke tipe data Int
                    Toast.makeText(this, "Invalid quantity or price format", Toast.LENGTH_SHORT).show()
                }
            }
        }


        binding.btnNext.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val binding = PopupDialogBinding.inflate(layoutInflater)

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(binding.root)
        dialog.window?.setBackgroundDrawable(R.color.black_01.toDrawable())

        binding.message.text
        binding.btnYes.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.btnNo.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()

    }
}