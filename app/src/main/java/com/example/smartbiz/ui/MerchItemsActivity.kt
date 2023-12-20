package com.example.smartbiz.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartbiz.adapter.MerchAdapter
import com.example.smartbiz.databinding.ActivityMerchItemsBinding
import com.example.smartbiz.response.InputItem
import com.example.smartbiz.response.Item

class MerchItemsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMerchItemsBinding
    private val adapter = MerchAdapter(this)
    private var arrayList: ArrayList<Item> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMerchItemsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val layout = LinearLayoutManager(this)
        layout.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerView.layoutManager = layout
        binding.recyclerView.adapter = adapter


        binding.buttonAddItem.setOnClickListener {
            startActivity(Intent(this, AddItem::class.java))
        }
    }
}