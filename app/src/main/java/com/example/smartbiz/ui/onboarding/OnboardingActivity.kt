package com.example.smartbiz.ui.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartbiz.R
import com.example.smartbiz.databinding.ActivityOnboardingBinding
import com.example.smartbiz.ui.login.LoginActivity

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding : ActivityOnboardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.tvSkip.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.btnContinue.setOnClickListener {

        }
    }
}