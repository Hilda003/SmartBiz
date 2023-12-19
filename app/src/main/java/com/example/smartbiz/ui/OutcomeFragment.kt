package com.example.smartbiz.ui

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.smartbiz.MainActivity
import com.example.smartbiz.R
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.smartbiz.databinding.FragmentOutcomeBinding
import com.example.smartbiz.utils.Constanta
import com.example.smartbiz.viewmodel.OutcomeFragmentViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class OutcomeFragment : Fragment(R.layout.fragment_outcome) {
    private lateinit var binding: FragmentOutcomeBinding
    private lateinit var viewModel: OutcomeFragmentViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOutcomeBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        setUpView()

    }

    private fun setUpView() {
        with(binding) {
            inputFields.etTransactionCategory.setAdapter(
                ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    Constanta(requireContext()).transaction_category

                )
            )


            inputFields.etTransactionDate.apply {
                isClickable = true
                isFocusable = true
                isFocusableInTouchMode = false
                val calender = Calendar.getInstance()
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val date = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    calender.set(Calendar.YEAR, year)
                    calender.set(Calendar.MONTH, month)
                    calender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    setText(sdf.format(calender.time))
                }

                val datePickerDialog = DatePickerDialog(
                    requireContext(),
                    date,
                    calender.get(Calendar.YEAR),
                    calender.get(Calendar.MONTH),
                    calender.get(Calendar.DAY_OF_MONTH)
                )
                datePickerDialog.datePicker.maxDate = Date().time
                setOnClickListener {
                    datePickerDialog.show()
                }
            }




            lifecycleScope.launchWhenCreated {
                viewModel.selectedPrice.collect {
                    inputFields.tilTransactionPrice.prefixText = it
                }
            }
            lifecycleScope.launchWhenCreated {
                viewModel.selectedQuantity.collect {
                    inputFields.tilTransactionPrice.prefixText = it
                }
            }

            btnSaveOutcome.setOnClickListener {
                validateInput()


            }
        }
    }

    private fun validateInput(): Boolean {
        when {
            binding.inputFields.etTransactionPrice.text.isNullOrEmpty() -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.missing_field),
                    Toast.LENGTH_SHORT
                ).show()
                binding.inputFields.etTransactionPrice.error = getString(R.string.required_field)
            }
            binding.inputFields.etTransactionDate.text.isNullOrEmpty() -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.missing_field),
                    Toast.LENGTH_SHORT
                ).show()
                binding.inputFields.etTransactionDate.error = getString(R.string.required_field)
            }
            binding.inputFields.etTransactionCategory.text.isNullOrEmpty() -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.missing_field),
                    Toast.LENGTH_SHORT
                ).show()
                binding.inputFields.etTransactionCategory.error = getString(R.string.required_field)
            }
            binding.inputFields.etTransactionQuantity.text.isNullOrEmpty() -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.missing_field),
                    Toast.LENGTH_SHORT
                ).show()
                binding.inputFields.etTransactionQuantity.error = getString(R.string.required_field)

            }

        }
        return true
    }

    private fun insertData() {
        if (
            with(binding.inputFields) {
                etTransactionPrice.text.toString().isNotEmpty()
                        && etTransactionDate.text.toString().isNotEmpty()
                        && etTransactionCategory.text.toString().isNotEmpty()
                        && etTransactionQuantity.text.toString().isNotEmpty()
            }
        ) {
        }
    }
}