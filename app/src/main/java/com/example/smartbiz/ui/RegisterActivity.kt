package com.example.smartbiz.ui


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.smartbiz.data.Result
import com.example.smartbiz.databinding.ActivityRegisterBinding
import com.example.smartbiz.viewmodel.RegisterViewModel
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val viewModelFactory = ViewModelFactory.getInstance(this)
        val registerViewModel : RegisterViewModel by viewModels {
            viewModelFactory
        }

        binding.btnRegister.setOnClickListener {
            val username = binding.NameText.text.toString().trim()
            val email = binding.EmailText.text.toString().trim()
            val password = binding.PasswordText.text.toString().trim()
            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            }
            else {
                registerViewModel.register(
                    username,
                    email,
                    password
                ).observe(this) {
                    when (it) {
                        is Result.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Result.Success -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        }
                        is Result.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this, it.error.compareTo(String()), Toast.LENGTH_SHORT).show()


                            }

                        }

                }
            }

        }

        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }


    }
}