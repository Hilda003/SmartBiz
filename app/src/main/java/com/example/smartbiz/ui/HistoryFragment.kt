package com.example.smartbiz.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.smartbiz.databinding.FragmentHistoryBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior


class HistoryFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters

    private lateinit var binding : FragmentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        BottomSheetBehavior.from(binding.sheet).apply {
            peekHeight = 300
            state = BottomSheetBehavior.STATE_EXPANDED
        }


    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HistoryFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }


}