package com.example.smartbiz.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartbiz.adapter.MerchAdapter
import com.example.smartbiz.databinding.ActivityMerchItemsBinding
import com.example.smartbiz.viewmodel.MerchItemViewModel
import kotlinx.coroutines.launch

class MerchItemsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMerchItemsBinding
    private lateinit var merchAdapter : MerchAdapter
    private lateinit var recyclerView: RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMerchItemsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val viewModelFactory = ViewModelFactory.getInstance(this)
        val merchItemViewModel : MerchItemViewModel by viewModels {
            viewModelFactory
        }

        recyclerView = binding.recyclerView
        merchAdapter = MerchAdapter(emptyList())

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MerchItemsActivity)
            adapter = merchAdapter
        }


        val userId = 8
        lifecycleScope.launch {
            val getAllItem = merchItemViewModel.getItemsByUserId(userId)
            merchAdapter.updateData(getAllItem.data)
        }





        binding.buttonAddItem.setOnClickListener {
            startActivity(Intent(this, AddItem::class.java))
        }
    }
}