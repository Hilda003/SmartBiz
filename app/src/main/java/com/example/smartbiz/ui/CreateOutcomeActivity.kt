package com.example.smartbiz.ui

import android.app.DatePickerDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.smartbiz.data.Result
import com.example.smartbiz.database.Preferences
import com.example.smartbiz.databinding.ActivityCreateOutcomeBinding
import com.example.smartbiz.databinding.PopupInputBinding
import com.example.smartbiz.response.Expense
import com.example.smartbiz.viewmodel.CreateExpenseViewModel
import com.google.android.material.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CreateOutcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateOutcomeBinding
    private lateinit var preferences: Preferences
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCreateOutcomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val viewModelFactory = ViewModelFactory.getInstance(this)
        val createExpenseViewModel: CreateExpenseViewModel by viewModels {
            viewModelFactory
        }
        preferences = Preferences(this)
        setupSpinner()

        val calendar = Calendar.getInstance()
        val date = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            createExpenseViewModel.updateDate(calendar)
        }

        binding.tvDatePicker.setOnClickListener {

            DatePickerDialog(
                this,
                date,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        createExpenseViewModel.selectedDate.observe(this, Observer {
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
        val userId = preferences.getUserId()

        binding.btnSaveOutcome.setOnClickListener {
            val quantity = binding.edtQuantity.text.toString().toIntOrNull()
            val price = binding.edtPrice.text.toString().toIntOrNull()
            if (price == null || quantity == null) {
                Toast.makeText(this, "Please enter valid quantity and price", Toast.LENGTH_SHORT).show()
            }
            else {
                val createExpense = Expense(
                    userId,
                    binding.tvDatePicker.text.toString(),
                    "coklat",
                    binding.edtQuantity.text.toString().toInt(),
                    binding.edtPrice.text.toString().toInt(),
                    binding.txtTotal.text.toString().toInt()
                )
                createExpenseViewModel.createExpense(createExpense)
            }
            createExpenseViewModel.createExpenseResult.observe(this) {
                when (it) {
                    is Result.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is Result.Success -> {
                        binding.progressBar.visibility = View.GONE
                        showDialogSuccess()
                        binding.tvDatePicker.text = ""
                        binding.edtQuantity.text.clear()
                        binding.edtPrice.text.clear()
                        binding.txtTotal.text = "0"
                    }

                    is Result.Error -> {
                        binding.progressBar.visibility = View.GONE
                        showDialogError()
                    }
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
        val itemName = arrayListOf("coklat, pie, apple")
        val spinner = binding.dropdownMenu
        val adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, itemName)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(this@CreateOutcomeActivity, "Selected item: ${itemName[position]}", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(this@CreateOutcomeActivity, "No item selected", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun showDialogData(message: String, iconResId: Int) {
        val binding = PopupInputBinding.inflate(layoutInflater)
        binding.message.text = message
        binding.icon.setImageResource(iconResId)

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(binding.root)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        binding.btnOk.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
    private fun showDialogError() {
        showDialogData("Create Outcome Failed", com.example.smartbiz.R.drawable.cancel)
    }
    private fun showDialogSuccess() {
        showDialogData("Create Outcome Success", com.example.smartbiz.R.drawable.check_circle)
    }






}