package com.example.smartbiz.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.smartbiz.MainActivity
import com.example.smartbiz.database.Preferences
import com.example.smartbiz.database.User
import com.example.smartbiz.databinding.ActivitySplashBinding
import com.example.smartbiz.viewmodel.SettingViewModel
import java.util.Timer
import kotlin.concurrent.schedule

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var modelUser : User
    private lateinit var preferences: Preferences

    private val delay = 3000L
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()
        preferences = Preferences(this)
        modelUser = preferences.getUser()
        Handler(Looper.getMainLooper()).postDelayed({
            if (modelUser.userId == 0){
                startActivity(Intent(
                    this@SplashActivity,
                    OnboardingActivity::class.java
                ))
                finish()
            }else{
                startActivity(Intent(
                    this@SplashActivity,
                    MainActivity::class.java
                ))
            }
            finish()
            }
            , delay)
        }
        }
