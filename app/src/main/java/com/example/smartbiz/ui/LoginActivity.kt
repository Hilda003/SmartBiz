package com.example.smartbiz.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.smartbiz.MainActivity
import com.example.smartbiz.data.Result
import com.example.smartbiz.database.Preferences
import com.example.smartbiz.database.User
import com.example.smartbiz.databinding.ActivityLoginBinding
import com.example.smartbiz.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private var user : User = User()
    private lateinit var preferences: Preferences


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        preferences = Preferences(this)


        val viewModelFactory = ViewModelFactory.getInstance(this)
        val loginViewModel : LoginViewModel by viewModels {
            viewModelFactory
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.tvUsername.text.toString().trim()
            val password = binding.PasswordText.text.toString().trim()
            if(username.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            } else if (password.length < 8) {
                val alertDialogBuilder = AlertDialog.Builder(this)
                alertDialogBuilder.setTitle("Password Alert")
                alertDialogBuilder.setMessage("Password must be at least 8 characters long.")
                alertDialogBuilder.setPositiveButton("OK") { dialog, which ->
                    dialog.dismiss()
                }
            } else {
                loginViewModel.login(
                    binding.tvUsername.text.toString(),
                    binding.PasswordText.text.toString()
                ).observe(this) {
                    when (it) {
                        is Result.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Result.Success -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                            val response = it.data
                            intent.putExtra("username", response.userId.toString())
                            tokenSave(response.userId)
                            val intent = Intent(this, InputDataActivity::class.java)
                            startActivity(intent)
                            Log.d("Login", "Started InputDataActivity")
                        }
                        is Result.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Log.d("Login", it.error)
                            Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

        }
        binding.tvForgotPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }


        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun tokenSave(userId: Int){
        user.userId = userId
        preferences.setUser(user)

    }
}