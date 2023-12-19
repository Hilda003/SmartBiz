package com.example.smartbiz.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.smartbiz.databinding.ActivitySplashBinding
import com.example.smartbiz.viewmodel.SettingViewModel
import java.util.Timer
import kotlin.concurrent.schedule

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    private val delay = 3000L
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()


        val pref = Settings.getInstance(dataStore)
        val setViewModel = ViewModelProvider(this, ProfileFragment.UserFactory(pref))[SettingViewModel::class.java]
        setViewModel.getThemeApp().observe(this) {
            if (it == 0) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
        @Suppress("DEPRECATION")
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        Timer().schedule(2000) {
            startActivity(Intent(
                this@SplashActivity,
                OnboardingActivity::class.java

            ))
            finish()
        }

    }
}