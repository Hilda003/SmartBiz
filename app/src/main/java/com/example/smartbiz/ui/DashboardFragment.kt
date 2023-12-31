package com.example.smartbiz.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.smartbiz.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding



    @SuppressLint("SetTextI18n")
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

        val username = arguments?.getString("username")
        binding.tvUser.text = "Hello, $username"
    }


        @SuppressLint("SuspiciousIndentation")
        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                }
            })
            return binding.root
    }
}