package com.example.smartbiz

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartbiz.databinding.ActivityCreateOutcomeBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CreateOutcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateOutcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCreateOutcomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val calendar = Calendar.getInstance()
        val date = DatePickerDialog.OnDateSetListener { _, _, _, _ ->
            calendar.get(Calendar.YEAR)
            calendar.get(Calendar.MONTH)
            calendar.get(Calendar.DAY_OF_MONTH)
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


    }

    private fun updateDate(calendar: Calendar) {
        val format = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        binding.tvDatePicker.text = sdf.format(calendar.time)
    }


}