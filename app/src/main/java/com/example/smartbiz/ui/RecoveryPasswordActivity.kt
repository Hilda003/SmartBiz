package com.example.smartbiz.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.smartbiz.data.Result
import com.example.smartbiz.databinding.ActivityRecoveryPasswordBinding
import com.example.smartbiz.viewmodel.RecoveryPasswordViewModel

class RecoveryPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecoveryPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecoveryPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener {
            onBackPressed()
        }
        val viewModelFactory = ViewModelFactory.getInstance(this)
        val recoveryPasswordViewModel: RecoveryPasswordViewModel by viewModels {
            viewModelFactory
        }


        binding.btnSave.setOnClickListener {
            val email = binding.txtEmail.text.toString().trim()
            val newPassword = binding.txtPassword.text.toString().trim()
            val token = binding.txtToken.text.toString().trim()

            if (email.isEmpty() || newPassword.isEmpty() || token.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            } else {
                recoveryPasswordViewModel.recoverPassword(email, token, newPassword)
            }

        }
        recoveryPasswordViewModel.recoveryPassword.observe(this, Observer {
            when (it) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, "Password Changed", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d("YourActivity", "forgotPassword: ${it.error}")
                    Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                }

                else -> {
                    binding.progressBar.visibility = View.GONE
                }
            }


        })
    }
}