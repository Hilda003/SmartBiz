package com.example.smartbiz.ui

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
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
import com.example.smartbiz.databinding.ActivityInputDataBinding
import com.example.smartbiz.databinding.PopupDialogBinding
import com.example.smartbiz.viewmodel.InputItemViewModel


class InputDataActivity : AppCompatActivity() {

    private lateinit var binding : ActivityInputDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputDataBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModelFactory = ViewModelFactory.getInstance(this)
        val inputItemViewModel : InputItemViewModel by viewModels {
            viewModelFactory
        }

        binding.btnAdd.setOnClickListener {
            inputItemViewModel.postInputItem(
                14,
                binding.product.text.toString(),
                binding.tvQuantity.text.toString().toInt(),
                binding.priceEditText.text.toString().toInt(),

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
                        Toast.makeText(this, "Input Data Failed", Toast.LENGTH_SHORT).show()
                    }

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