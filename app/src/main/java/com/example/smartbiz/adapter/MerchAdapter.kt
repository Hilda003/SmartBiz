package com.example.smartbiz.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smartbiz.ui.EditItemActivity
import com.example.smartbiz.databinding.ItemMerchBinding
import com.example.smartbiz.response.DataItem


class MerchAdapter(private var items: List<DataItem>) : RecyclerView.Adapter<MerchAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemMerchBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemName = binding.txtItemName
        val itemPrice = binding.txtPrice
        val itemQuantity = binding.txtQuantity
        val btnEdit = binding.imgEdt
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMerchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)


    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataItem = items[position]
        holder.apply {
            itemName.text = dataItem.namaBarang
            itemPrice.text = dataItem.hargaBarang.toString()
            itemQuantity.text = dataItem.jumlahBarang.toString()
        }
        holder.btnEdit.setOnClickListener {
            val intent = Intent(it.context, EditItemActivity::class.java)
            intent.putExtra("itemId", dataItem.id)
            intent.putExtra("itemName", dataItem.namaBarang)
            intent.putExtra("itemPrice", dataItem.hargaBarang)
            intent.putExtra("itemQuantity", dataItem.jumlahBarang)


            it.context.startActivity(intent)
        }


    }

    fun updateData(newData: List<DataItem>) {
        items = newData
        notifyDataSetChanged()
    }


}
