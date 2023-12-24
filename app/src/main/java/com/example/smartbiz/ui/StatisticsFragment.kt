package com.example.smartbiz.ui

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.viewModels
import com.example.smartbiz.R
import com.example.smartbiz.database.Preferences
import com.example.smartbiz.database.User
import com.example.smartbiz.databinding.FragmentStatisticsBinding
import com.example.smartbiz.viewmodel.StatisticViewModel
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate


class StatisticsFragment : Fragment() {

    private lateinit var binding : FragmentStatisticsBinding
    private lateinit var user: User
    private lateinit var preferences: Preferences
    private lateinit var progressBar: ProgressBar

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

        progressBar = binding.progressBar
        preferences = Preferences(requireContext())
        user = preferences.getUser()
        preferences = Preferences(requireContext())
        observeUserData()
        fetchData()
        setUpPieChart()
        observePieChartData()
    }

    private fun setUpPieChart() = with(binding.expenseChart) {
        isDrawHoleEnabled = true
        setUsePercentValues(true)
        setEntryLabelTextSize(12f)
        setEntryLabelColor(R.color.white)
        centerText = "Budget Overview"
        description.isEnabled = false
        legend.apply {
             verticalAlignment = Legend.LegendVerticalAlignment.TOP
             horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
             orientation = Legend.LegendOrientation.VERTICAL
             setDrawInside(false)
             isEnabled = true
        }
        holeRadius = 40f
    }

    private fun observePieChartData() {
        progressBar.visibility = View.VISIBLE
        statisticViewModel.userData.observe(viewLifecycleOwner) {
            progressBar.visibility = View.GONE
            val entryValues = ArrayList<PieEntry>()
            val chart = binding.expenseChart
            val expense = it.data.totalExpense
            val income = it.data.totalIncome

            entryValues.add(PieEntry(income.toFloat(), getString(R.string.income)))
            entryValues.add(PieEntry(expense.toFloat(), getString(R.string.expense)))


            val colors = ArrayList<Int>()
            for (color in ColorTemplate.MATERIAL_COLORS) {
                colors.add(color)
            }
            for (color in ColorTemplate.VORDIPLOM_COLORS) {
                colors.add(color)
            }
            val dataSet = PieDataSet(entryValues, "Budget Overview")
            dataSet.colors = colors


            val pieData = PieData(dataSet)

            val tf = Typeface.DEFAULT
            pieData.apply {
                setDrawValues(true)
                setValueFormatter(PercentFormatter(chart))
                setValueTextSize(12f)
                setValueTextColor(R.color.black)
                setValueTypeface(tf)
            }
            chart.data = pieData
            chart.invalidate()

            chart.animateY(1400, Easing.EaseInOutQuad)

        }
    }



    private fun fetchData() {
        user.userId?.let { statisticViewModel.getAllTransaction(it) }
    }

    @SuppressLint("SetTextI18n")
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


