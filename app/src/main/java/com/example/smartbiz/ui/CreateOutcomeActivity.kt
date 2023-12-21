package com.example.smartbiz.ui

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.smartbiz.data.Result
import com.example.smartbiz.databinding.ActivityCreateOutcomeBinding
import com.example.smartbiz.viewmodel.CreateOutcomeViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CreateOutcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateOutcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCreateOutcomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)




        val viewModelFactory = ViewModelFactory.getInstance(this)
        val createOutcomeViewModel : CreateOutcomeViewModel by viewModels {
            viewModelFactory
        }

        val calendar = Calendar.getInstance()
        val date = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            // Set the selected date to the calendar
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDate(calendar)
        }

        binding.ivDate.setOnClickListener {

            DatePickerDialog(
                this,
                date,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
        binding.edtQuantity.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                updateTotal()
            }
        })

        // Initialize TextWatcher for edtPrice
        binding.edtPrice.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                updateTotal()
            }
        })



        binding.btnSaveOutcome.setOnClickListener {
            createOutcomeViewModel.postCreateOutcome(
                4,
                9,
                binding.tvDatePicker.text.toString(),
                binding.edtQuantity.text.toString().toInt(),
                binding.edtPrice.text.toString().toInt(),
                binding.txtTotal.text.toString().toInt()
            ).observe(this) {
                when (it) {
                    is Result.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, "Input Outcome Success", Toast.LENGTH_SHORT).show()
                    }
                    is Result.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Log.d("Outcome", it.error)
                        Toast.makeText(this, "Input Outcome Failed", Toast.LENGTH_SHORT).show()

                    }
                }
            }
        }







    }
    private fun updateDate(calendar: Calendar) {
        val format = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        binding.tvDatePicker.tag = sdf.format(calendar.time)
    }



    private fun updateTotal() {
        val quantity = binding.edtQuantity.text.toString().toIntOrNull() ?: 0
        val price = binding.edtPrice.text.toString().toIntOrNull() ?: 0
        val total = quantity * price
        binding.txtTotal.text = total.toString()
    }







}