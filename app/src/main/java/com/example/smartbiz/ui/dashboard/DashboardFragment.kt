package com.example.smartbiz.ui.dashboard

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.smartbiz.CreateIncomeActivity
import com.example.smartbiz.CreateOutcomeActivity
import com.example.smartbiz.InsightBusinessActivity
import com.example.smartbiz.MerchItemsActivity
import com.example.smartbiz.R
import com.example.smartbiz.databinding.FragmentDashboardBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.layoutIncome.setOnClickListener {
            startActivity(Intent(context, CreateIncomeActivity::class.java))
        }

        binding.layoutOutcome.setOnClickListener {
            startActivity(Intent(context, CreateOutcomeActivity::class.java))
        }

        binding.layoutItems.setOnClickListener {
            startActivity(Intent(context, MerchItemsActivity::class.java))
        }

        binding.layoutInsight.setOnClickListener {
            startActivity(Intent(context, InsightBusinessActivity::class.java))
        }
    }


        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
            return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DashboardFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}