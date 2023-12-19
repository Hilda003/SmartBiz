package com.example.smartbiz.ui

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartbiz.adapter.CreateIncomeAdapter
import com.example.smartbiz.databinding.ActivityCreateIncomeBinding
import com.example.smartbiz.viewmodel.CreateIncomeViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CreateIncomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateIncomeBinding
    private val createIncomeViewModel : CreateIncomeViewModel by viewModels()
    private val adapter = CreateIncomeAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCreateIncomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // layout manager
        val layout = LinearLayoutManager(this)
        layout.orientation = LinearLayoutManager.VERTICAL



        val calendar = Calendar.getInstance()
        val date = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            // Set the selected date to the calendar
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            createIncomeViewModel.updateDate(calendar)


        }


        binding.ivDate.setOnClickListener {

            startActivity(Intent(this, NotificationActivity::class.java))

        }

        createIncomeViewModel.selectedDate.observe(this, Observer {
            binding.tvDatePicker.text = it
        })





    }





}