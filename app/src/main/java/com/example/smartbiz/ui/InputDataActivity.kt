package com.example.smartbiz.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.smartbiz.MainActivity
import com.example.smartbiz.data.*
import com.example.smartbiz.databinding.ActivityInputDataBinding
import com.example.smartbiz.viewmodel.InputItemViewModel
import com.example.smartbiz.viewmodel.LoginViewModel

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
                4,
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
                        Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }

        binding.btnNext.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()


        }



    }
}