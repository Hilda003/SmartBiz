package com.example.smartbiz.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.smartbiz.database.Preferences
import com.example.smartbiz.database.User
import com.example.smartbiz.databinding.FragmentProfileBinding
import com.example.smartbiz.response.Login
import com.example.smartbiz.response.LoginResult
import com.example.smartbiz.viewmodel.MerchItemViewModel
import com.example.smartbiz.viewmodel.ProfileViewModel
import com.example.smartbiz.viewmodel.SettingViewModel

class ProfileFragment : Fragment() {

    val userData = MutableLiveData<LoginResult>()
    private lateinit var binding : FragmentProfileBinding
    private lateinit var user: User
    private lateinit var preferences: Preferences

    private val profileViewModel: ProfileViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


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
        startActivity(Intent(requireContext(), LoginActivity::class.java))
    }

    private fun fetchData() {
        user.userId?.let { profileViewModel.getDetailProfile(it) }
    }
    private fun observeUserData() {
        profileViewModel.userData.observe(viewLifecycleOwner) {
            binding.apply {
                tvNameUser.text = it.data.username
                tvUsername.text = it.data.username
                tvGmail.text = it.data.email
                tvPassword.text = it.data.password
            }
        }
    }


}

