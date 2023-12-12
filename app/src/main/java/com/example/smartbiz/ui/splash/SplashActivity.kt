package com.example.smartbiz.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartbiz.MainActivity
import com.example.smartbiz.databinding.ActivitySplashBinding
import com.example.smartbiz.ui.login.LoginActivity
import com.example.smartbiz.ui.onboarding.OnboardingActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    private val delay = 3000L
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        startActivity(Intent(this,  OnboardingActivity::class.java))
        finish()
        delay
    }
}