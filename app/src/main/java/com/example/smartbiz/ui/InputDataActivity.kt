package com.example.smartbiz.ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import com.example.smartbiz.databinding.PopupInputBinding
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

        binding.btnAdd.setOnClickListener {
            val product = binding.product.text.toString().trim()
            val quantity = binding.tvQuantity.text.toString().trim()
            val price = binding.priceEditText.text.toString().trim()

            if (product.isEmpty() || quantity.isEmpty() || price.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            } else if (quantity.toInt() <= 0 || price.toInt() <= 0) {
                Toast.makeText(this, "Quantity and price must be greater than 0", Toast.LENGTH_SHORT).show()
            } else if (!product.matches(Regex("[a-zA-Z]+\$"))) {
                Toast.makeText(this, "Invalid product format. Product should be a text, not a number", Toast.LENGTH_SHORT).show()
            }
            else {
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
                                showDialogSuccess()
                                binding.product.text.clear()
                                binding.tvQuantity.text.clear()
                                binding.priceEditText.text.clear()
                            }
                            is Result.Error -> {
                                binding.progressBar.visibility = View.GONE
                                showDialogError()
                            }
                        }
                    }
                } catch (e: NumberFormatException) {
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

    private fun showDialogError() {
        showDialogData("Input data failed", R.drawable.cancel)
    }
    private fun showDialogSuccess() {
        showDialogData("Input data success", R.drawable.check_circle)
    }

    private fun showDialogData(message: String, iconResId: Int) {
        val binding = PopupInputBinding.inflate(layoutInflater)
        binding.message.text = message
        binding.icon.setImageResource(iconResId)

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(binding.root)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        binding.btnOk.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}