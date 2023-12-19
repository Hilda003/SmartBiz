package com.example.smartbiz.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smartbiz.data.OnBoardingItem
import com.example.smartbiz.databinding.ItemOnboardingBinding

class OnBoardingAdapter(private val onBoardingItems: List<OnBoardingItem>) : RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    class OnBoardingViewHolder(private val binding : ItemOnboardingBinding) : RecyclerView.ViewHolder(binding.root) {



        fun bind(onBoardingItem: OnBoardingItem) {
           binding.imageOnboarding.setImageResource(onBoardingItem.image)
            binding.txtTitle.text = onBoardingItem.title
            binding.txtDesc.text = onBoardingItem.description

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        val binding = ItemOnboardingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OnBoardingViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return onBoardingItems.size
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(onBoardingItems[position])
    }


}