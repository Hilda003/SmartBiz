package com.example.smartbiz.ui

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.Observer
import com.example.smartbiz.R
import com.example.smartbiz.data.Result
import com.example.smartbiz.databinding.ActivityForgotPasswordBinding
import com.example.smartbiz.viewmodel.ForgotPasswordViewModel
import com.example.smartbiz.viewmodel.InputItemViewModel

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding

    companion object {
        const val NOTIFICATION_CHANNEL_ID = "forgot_password_channel"
        const val NOTIFICATION_ID = 1
    }

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
        forgotPasswordViewModel.resetToken.observe(this, Observer {
            when (it) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, "Reset Password Success. Check your email", Toast.LENGTH_SHORT).show()
                    showNotification("Password Reset Successful", "Your password has been reset successfully. Check your email for the new token to use reset your password.")
                    startActivity(Intent(this, RecoveryPasswordActivity::class.java))
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d("YourActivity", "forgotPassword: ${it.error}")
                    Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun showNotification(title: String, content: String) {
        createNotificationChannel()

        val builder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.notification)
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        with(NotificationManagerCompat.from(this)) {
            if (ActivityCompat.checkSelfPermission(
                    this@ForgotPasswordActivity,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(this@ForgotPasswordActivity, "Permission not granted", Toast.LENGTH_SHORT).show()
                return
            }
            notify(NOTIFICATION_ID, builder.build())
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "Forgot Password Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Channel for forgot password notifications"
            }

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}
