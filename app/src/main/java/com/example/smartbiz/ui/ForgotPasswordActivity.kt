package com.example.smartbiz.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.smartbiz.data.Result
import com.example.smartbiz.databinding.ActivityForgotPasswordBinding
import com.example.smartbiz.viewmodel.ForgotPasswordViewModel
import com.example.smartbiz.viewmodel.InputItemViewModel

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModelFactory = ViewModelFactory.getInstance(this)
        val forgotPasswordViewModel: ForgotPasswordViewModel by viewModels {
            viewModelFactory
        }

        binding.back.setOnClickListener {
            onBackPressed()
        }

        binding.btnForgot.setOnClickListener {
            val email = binding.txtEmail.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            } else {
                forgotPasswordViewModel.requestToken(email)
            }
        }

        // Observe the LiveData outside the click listener
        forgotPasswordViewModel.resetToken.observe(this, Observer {
            when (it) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, "Reset Password Success", Toast.LENGTH_SHORT).show()
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d("YourActivity", "forgotPassword: ${it.error}")
                    Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
