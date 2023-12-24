package com.example.smartbiz.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartbiz.adapter.MerchAdapter
import com.example.smartbiz.database.Preferences
import com.example.smartbiz.databinding.ActivityMerchItemsBinding
import com.example.smartbiz.viewmodel.MerchItemViewModel
import kotlinx.coroutines.launch

class MerchItemsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMerchItemsBinding
    private lateinit var merchAdapter : MerchAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var preferences: Preferences
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMerchItemsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        preferences = Preferences(this)

        val viewModelFactory = ViewModelFactory.getInstance(this)
        val merchItemViewModel : MerchItemViewModel by viewModels {
            viewModelFactory
        }

        recyclerView = binding.recyclerView

        progressBar = binding.progressBar

        merchAdapter = MerchAdapter(emptyList())

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MerchItemsActivity)
            adapter = merchAdapter
        }


        val userId = preferences.getUserId()
        lifecycleScope.launch {
            try {
                progressBar.visibility = ProgressBar.VISIBLE
                val getAllItem = merchItemViewModel.getItemsByUserId(userId)
                merchAdapter.updateData(getAllItem.data)
            } catch (e: Exception) {
                Toast.makeText(this@MerchItemsActivity, e.message, Toast.LENGTH_SHORT).show()
            } finally {
                progressBar.visibility = ProgressBar.GONE

            }

        }
        binding.buttonAddItem.setOnClickListener {
            startActivity(Intent(this, AddItem::class.java))
        }
    }
}