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
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.smartbiz.R
import com.example.smartbiz.adapter.CreateIncomeAdapter

import com.example.smartbiz.databinding.ActivityCreateIncomeBinding
import com.example.smartbiz.response.CreateIncomeRequest
import com.example.smartbiz.viewmodel.CreateIncomeViewModel
import java.util.Calendar
import com.example.smartbiz.data.Result
import com.example.smartbiz.database.Preferences
import com.example.smartbiz.database.User
import com.example.smartbiz.databinding.PopupInputBinding
import com.example.smartbiz.response.CreateExpense
import com.example.smartbiz.response.DataItem
import com.example.smartbiz.viewmodel.ItemViewModel

class CreateIncomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateIncomeBinding
    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCreateIncomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val viewModelFactory = ViewModelFactory.getInstance(this)
        val createIncomeViewModel: CreateIncomeViewModel by viewModels {
            viewModelFactory
        }

        preferences = Preferences(this)
        setupSpinner()

        val calendar = Calendar.getInstance()
        val date = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            createIncomeViewModel.updateDate(calendar)
        }


        binding.tvDatePicker.setOnClickListener {
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
        binding.edtPrice.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                updateTotal()
            }
        })
        preferences = Preferences(this)
        val userId = preferences.getUserId()

        val itemId = preferences.getItem()


        binding.btnSaveIncome.setOnClickListener {
            val quantity = binding.edtQuantity.text.toString().toIntOrNull()
            val price = binding.edtPrice.text.toString().toIntOrNull()
            if (quantity == null || price == null) {
                Toast.makeText(this, "Please enter valid quantity and price", Toast.LENGTH_SHORT)
                    .show()
            } else{
                val createIncomeRequest = CreateIncomeRequest(
                    userId,
                    binding.tvDatePicker.text.toString(),
                    binding.dropdownMenu.selectedItem.toString(),
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

        val itemName = arrayListOf("coklat", "kopi", "teh")
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

    private fun showDialogError() {
        showDialogData("Create Income Failed", R.drawable.cancel)
    }
    private fun showDialogSuccess() {
        showDialogData("Create Income Success", R.drawable.check_circle)
    }

    private fun showDialogData(message: String, iconResId: Int) {
        val binding = PopupInputBinding.inflate(layoutInflater)
        binding.message.text = message
        binding.icon.setImageResource(iconResId)

        val dialog = Dialog(this@CreateIncomeActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(binding.root)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        binding.btnOk.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}