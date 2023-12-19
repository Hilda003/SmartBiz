package com.example.smartbiz.ui

import android.animation.Animator
import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.smartbiz.R
import com.example.smartbiz.databinding.FragmentStatisticsBinding
import com.example.smartbiz.retrofit.ApiConfig
import com.example.smartbiz.retrofit.ApiService
import com.github.mikephil.charting.components.Legend
import kotlinx.coroutines.launch


class StatisticsFragment : Fragment() {
    private lateinit var apiService: ApiService
   private lateinit var binding : FragmentStatisticsBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatisticsBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        apiService = ApiConfig.getApiService()



    }


    private fun getData() {
        lifecycleScope.launch {
            val response = apiService.getProfit(1)
            if (response.isSuccessful){
                Log.e("Response", "getProfit success : ${response.body()?.totalExpense}")

            }
            else{
                Log.e("Response", "getProfit error : ${response.message()}")
            }
        }
    }

    private fun setUpPieChart() = with(binding.expenseChart) {
        isDrawHoleEnabled = true
        setUsePercentValues(true)
        setEntryLabelTextSize(12f)
        setEntryLabelColor(R.color.white)
        centerText = "Expense"
        description.isEnabled = false
        legend.apply {
             verticalAlignment = Legend.LegendVerticalAlignment.TOP
             horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
             orientation = Legend.LegendOrientation.VERTICAL
             setDrawInside(false)
             isEnabled = true
        }

    }

    private fun chartData() {

    }


}