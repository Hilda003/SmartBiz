package com.example.smartbiz.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.smartbiz.R
import com.example.smartbiz.database.Preferences
import com.example.smartbiz.database.User
import com.example.smartbiz.databinding.FragmentStatisticsBinding
import com.example.smartbiz.viewmodel.StatisticViewModel
import com.github.mikephil.charting.components.Legend


class StatisticsFragment : Fragment() {

    private lateinit var binding : FragmentStatisticsBinding
    private lateinit var user: User
    private lateinit var preferences: Preferences

    private val statisticViewModel: StatisticViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatisticsBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferences = Preferences(requireContext())
        user = preferences.getUser()
        preferences = Preferences(requireContext())
        observeUserData()
        fetchData()
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


    private fun fetchData() {
        user.userId?.let { statisticViewModel.getAllTransaction(it) }
    }

    private fun observeUserData() {
        statisticViewModel.userData.observe(viewLifecycleOwner) {result ->
            result?.data?.let { profit ->
                binding.apply {
                    priceIncome.text = "Rp. ${profit.totalIncome}"
                    priceExpense.text = "Rp. ${profit.totalExpense}"
                }
            }



            }
        }
    }


