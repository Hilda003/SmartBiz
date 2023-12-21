package com.example.smartbiz.ui


import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast

import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartbiz.R
import com.example.smartbiz.adapter.CreateIncomeAdapter

import com.example.smartbiz.databinding.ActivityCreateIncomeBinding
import com.example.smartbiz.response.CreateIncomeRequest
import com.example.smartbiz.viewmodel.CreateIncomeViewModel
import com.example.smartbiz.viewmodel.CreateOutcomeViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import com.example.smartbiz.data.Result

class CreateIncomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateIncomeBinding
    private val adapter = CreateIncomeAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCreateIncomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val viewModelFactory = ViewModelFactory.getInstance(this)
        val createIncomeViewModel: CreateIncomeViewModel by viewModels {
            viewModelFactory
        }

        setupSpinner()

        val calendar = Calendar.getInstance()
        val date = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            // Set the selected date to the calendar
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            createIncomeViewModel.updateDate(calendar)
        }


        binding.ivDate.setOnClickListener {
            DatePickerDialog(
                this,
                date,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()

        }

        createIncomeViewModel.selectedDate.observe(this, Observer {
            binding.tvDatePicker.text = it
        })

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

        binding.btnSaveIncome.setOnClickListener {
            val createIncomeRequest = CreateIncomeRequest(
                14,
                binding.tvDatePicker.text.toString(),
                "roti",
                binding.edtQuantity.text.toString().toInt(),
                binding.edtPrice.text.toString().toInt(),
                binding.txtTotal.text.toString().toInt()

            )
            createIncomeViewModel.createIncome(createIncomeRequest)
        }
        createIncomeViewModel.createIncomeResult.observe(this) {
            when (it) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, "Create Income Success", Toast.LENGTH_SHORT).show()
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d("YourActivity", "createIncomeViewModel.createIncome failed: ${it.error}")
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, "Error: ${it.error}", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun updateTotal() {
        val quantity = binding.edtQuantity.text.toString().toIntOrNull() ?: 0
        val price = binding.edtPrice.text.toString().toIntOrNull() ?: 0
        val total = quantity * price
        binding.txtTotal.text = total.toString()
    }

    private fun setupSpinner(){
        val itemName = arrayOf("Telor", "Permen", "Jelly", "Shoes")
        val spinner = binding.dropdownMenu
        val adapter = ArrayAdapter(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, itemName)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(this@CreateIncomeActivity, "Selected item: ${itemName[position]}", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(this@CreateIncomeActivity, "No item selected", Toast.LENGTH_SHORT).show()
            }

        }
    }
}