package com.example.smartbiz.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smartbiz.databinding.HistoryItemBinding
import com.example.smartbiz.response.DataHistory
import com.example.smartbiz.response.History

class HistoryAdapter(private var history : List<DataHistory>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    class ViewHolder(private val binding : HistoryItemBinding) : RecyclerView.ViewHolder(binding.root){
        val iconHistory = binding.iconHistory
        val nameItem = binding.txtItem
        val priceItem = binding.txtPrice
        val date = binding.txtDate

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = HistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return history.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val historyItem = history[position]
        holder.apply {
            if (historyItem.jenis == "income") {
                iconHistory.setImageResource(com.example.smartbiz.R.drawable.income_icon)
            } else {
                iconHistory.setImageResource(com.example.smartbiz.R.drawable.outcome_icon)
            }
            nameItem.text = historyItem.namaBarang
            priceItem.text = historyItem.totalHarga.toString()
            date.text = historyItem.tanggal
        }
    }

    fun updateData(newData: List<DataHistory>) {
        history = newData
        notifyDataSetChanged()
    }
}