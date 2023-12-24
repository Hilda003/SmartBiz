package com.example.smartbiz.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.smartbiz.R
import com.example.smartbiz.databinding.ActivityEditItemBinding
import com.example.smartbiz.response.EditItem
import com.example.smartbiz.viewmodel.EditItemViewModel
import com.example.smartbiz.data.Result

class EditItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEditItemBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val itemName = intent.getStringExtra("itemName")
        val itemPrice = intent.getIntExtra("itemPrice", 0)

        binding.edtName.setText(itemName)
        binding.edtPrice.setText(itemPrice.toString())


        val viewModelFactory = ViewModelFactory.getInstance(this)
        val editItemViewModel: EditItemViewModel by viewModels {
            viewModelFactory
        }

        binding.buttonSave.setOnClickListener {

            val name = binding.edtName.text.toString()
            val price = binding.edtPrice.text.toString().toIntOrNull()

            if (name.isEmpty() || price == null) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val barangId = intent.getIntExtra("barangId", 0)
            val editItem = EditItem(
                barangId = barangId?:0,
                namaBarang = name,
                hargaBarang = price
            )

        }
        editItemViewModel.editBarangResult.observe(this) {
            when (it) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, "Edit Item Success", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MerchItemsActivity::class.java))
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Log.e("ItemError", "Item Error: ${it.error}")
                    Toast.makeText(this, "Edit Item Failed", Toast.LENGTH_SHORT).show()

                }
            }

        }
    }
}