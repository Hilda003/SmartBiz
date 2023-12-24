package com.example.smartbiz.ui

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ProgressBar
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.smartbiz.database.Preferences
import com.example.smartbiz.database.User
import com.example.smartbiz.databinding.FragmentProfileBinding
import com.example.smartbiz.databinding.PopupDialogBinding
import com.example.smartbiz.databinding.PopupInputBinding
import com.example.smartbiz.response.Login
import com.example.smartbiz.response.LoginResult
import com.example.smartbiz.viewmodel.MerchItemViewModel
import com.example.smartbiz.viewmodel.ProfileViewModel
import com.example.smartbiz.viewmodel.SettingViewModel

class ProfileFragment : Fragment() {
    private lateinit var binding : FragmentProfileBinding
    private lateinit var user: User
    private lateinit var preferences: Preferences
    private lateinit var progressBar: ProgressBar

    private val profileViewModel: ProfileViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = binding.progressBar
        preferences = Preferences(requireContext())
        user = preferences.getUser()
        binding.logout.setOnClickListener {
            logout()
        }
        observeUserData()
        fetchData()

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root



    }



    private fun logout() {
        user.userId = 0
        preferences.setUser(user)
        showDialogData("Apakah anda yakin ingin keluar?")
    }

    private fun fetchData() {
        progressBar.visibility = View.VISIBLE
        user.userId?.let { profileViewModel.getDetailProfile(it) }
    }
    private fun observeUserData() {
        profileViewModel.userData.observe(viewLifecycleOwner) {
            progressBar.visibility = View.GONE
            binding.apply {
                tvNameUser.text = it.data.username
                tvUsername.text = it.data.username
                tvGmail.text = it.data.email
                tvPassword.text = it.data.password
            }
        }
    }

    private fun showDialogData(message: String) {
        val binding = PopupDialogBinding.inflate(layoutInflater)
        binding.message.text = message

        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(binding.root)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        binding.btnYes.setOnClickListener {
            startActivity(Intent(requireContext(), LoginActivity::class.java))
        }
        binding.btnNo.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }


}

