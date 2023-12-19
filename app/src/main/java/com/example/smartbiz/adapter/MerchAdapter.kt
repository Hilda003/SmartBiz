package com.example.smartbiz.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smartbiz.databinding.ActivityMerchItemsBinding
import com.example.smartbiz.databinding.ItemMerchBinding

class MerchAdapter(private val context: Context) : RecyclerView.Adapter<MerchAdapter.ViewHolder>() {
    class ViewHolder (private val binding : ItemMerchBinding) : RecyclerView.ViewHolder(binding.root) {

        val itemName = binding.txtItemName
        val itemPrice = binding.txtPrice
        val itemQuantity = binding.txtQuantity

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMerchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }
}