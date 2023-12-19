//package com.example.smartbiz.ui
//
//import android.app.DatePickerDialog
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import androidx.activity.viewModels
//import androidx.lifecycle.Observer
//import com.example.smartbiz.databinding.ActivityCreateOutcomeBinding
//import com.example.smartbiz.viewmodel.CreateOutcomeViewModel
//import java.text.SimpleDateFormat
//import java.util.Calendar
//import java.util.Locale
//
//class CreateOutcomeActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityCreateOutcomeBinding
//    private val createOutcomeViewModel : CreateOutcomeViewModel by viewModels()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        binding = ActivityCreateOutcomeBinding.inflate(layoutInflater)
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        val calendar = Calendar.getInstance()
//        val date = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
//            // Set the selected date to the calendar
//            calendar.set(Calendar.YEAR, year)
//            calendar.set(Calendar.MONTH, month)
//            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
//
//            createOutcomeViewModel.updateDate(calendar)
//
//
//        }
//
//        binding.ivDateOutcome.setOnClickListener {
//
//            DatePickerDialog(
//                this,
//                date,
//                calendar.get(Calendar.YEAR),
//                calendar.get(Calendar.MONTH),
//                calendar.get(Calendar.DAY_OF_MONTH)).show()
//
//        }
//
//        createOutcomeViewModel.selectedDate.observe(this, Observer {
//            binding.tvDatePicker.text = it
//        })
//    }
//
////    private fun setUpView() {
////        with(binding) {
////            binding.dropdownMenu.adapter(
////                ArrayAdapter(
////                    this@CreateOutcomeActivity,
////                    android.R.layout.simple_list_item_1,
////
////                )
////            )
////
////        }
////    }
//
//
//
//
//
//
//
//}