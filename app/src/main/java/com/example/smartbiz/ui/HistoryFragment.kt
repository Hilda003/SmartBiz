package com.example.smartbiz.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartbiz.R
import com.example.smartbiz.adapter.HistoryAdapter
import com.example.smartbiz.database.Preferences
import com.example.smartbiz.databinding.FragmentHistoryBinding
import com.example.smartbiz.response.DataHistory
import com.example.smartbiz.response.DataItem
import com.example.smartbiz.response.History
import com.example.smartbiz.viewmodel.HistoryViewModel
import com.example.smartbiz.viewmodel.ProfileViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.coroutines.launch


class HistoryFragment : Fragment() {

    private lateinit var binding : FragmentHistoryBinding
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var preferences: Preferences
    private lateinit var progressBar: ProgressBar
    private var isFilteringByType = true
    private var isSortingByDate = true


    private val historyViewModel: HistoryViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferences = Preferences(requireContext())

        progressBar = binding.progressBar
        recyclerView = binding.recyclerView
        historyAdapter = HistoryAdapter(emptyList())
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = historyAdapter
        }

        val userId = preferences.getUserId()
        lifecycleScope.launch {
            val getAllHistory = historyViewModel.getHistory(userId)
            updateHistory(getAllHistory.data)
        }

        binding.imgFilter.setOnClickListener {
            showFilterMenu(it)

        }
        binding.imgSort.setOnClickListener {

            showSortMenu(it)

        }
    }

    private fun showFilterMenu(view: View) {
        val menu = PopupMenu(requireContext(), view)
        menu.inflate(R.menu.filter_menu)
        menu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_income -> {
                    isFilteringByType = true
                    filterHistoryList()
                    true
                }
                R.id.menu_expense -> {
                    isFilteringByType = false
                    filterHistoryList()
                    true
                }
                else -> false
            }
        }
        menu.show()
    }

    private fun showSortMenu(view: View) {
        val menu = PopupMenu(requireContext(), view)
        menu.inflate(R.menu.sort_menu)
        menu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_date -> {
                    isFilteringByType = true
                    sortHistoryList()
                    true
                }
                R.id.menu_price -> {
                    isFilteringByType = false
                    sortHistoryList()
                    true
                }
                else -> false
            }
        }
        menu.show()
    }


    private fun filterHistoryList() {
        lifecycleScope.launch {
            val userId = preferences.getUserId()
            val getAllHistory = historyViewModel.getHistory(userId)

            val filterHistory = if (isFilteringByType) {
                getAllHistory.data.filter { it.jenis == "income" }
            } else {
                getAllHistory.data.filter { it.jenis == "expense" }
            }

            historyAdapter.updateData(filterHistory)
        }
    }

    private fun sortHistoryList() {
        lifecycleScope.launch {
            val userId = preferences.getUserId()
            val getAllHistory = historyViewModel.getHistory(userId)
            val sortedHistory = if (isFilteringByType) {
                getAllHistory.data.sortedBy { it.tanggal }
            }
            else {
                getAllHistory.data.sortedByDescending { it.totalHarga }
            }

            historyAdapter.updateData(sortedHistory)
        }
    }

    private fun updateHistory(history: List<DataHistory>) {
        if (history.isEmpty()) {
            binding.progressBar.visibility = View.GONE
            recyclerView.visibility = View.GONE
            Toast.makeText(requireContext(), "Data not found", Toast.LENGTH_SHORT).show()
        }
        else {
            binding.progressBar.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            historyAdapter.updateData(history)
        }
    }




}