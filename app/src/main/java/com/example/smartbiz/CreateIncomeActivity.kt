package com.example.smartbiz

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.smartbiz.databinding.ActivityCreateIncomeBinding
import java.text.SimpleDateFormat
import java.util.Calendar

class CreateIncomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateIncomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCreateIncomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }



}