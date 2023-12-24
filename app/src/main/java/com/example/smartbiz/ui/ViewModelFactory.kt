package com.example.smartbiz.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.smartbiz.data.Repository
import com.example.smartbiz.retrofit.ApiService
import com.example.smartbiz.viewmodel.CreateExpenseViewModel
import com.example.smartbiz.viewmodel.CreateIncomeViewModel
import com.example.smartbiz.viewmodel.EditItemViewModel
import com.example.smartbiz.viewmodel.ForgotPasswordViewModel
import com.example.smartbiz.viewmodel.HistoryViewModel
import com.example.smartbiz.viewmodel.InputItemViewModel
import com.example.smartbiz.viewmodel.ItemViewModel
import com.example.smartbiz.viewmodel.LoginViewModel
import com.example.smartbiz.viewmodel.MerchItemViewModel
import com.example.smartbiz.viewmodel.ProfileViewModel
import com.example.smartbiz.viewmodel.RecoveryPasswordViewModel
import com.example.smartbiz.viewmodel.RegisterViewModel
import com.example.smartbiz.viewmodel.StatisticViewModel

class ViewModelFactory private constructor(private val repository: Repository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }

            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(repository) as T
            }
            modelClass.isAssignableFrom(InputItemViewModel::class.java) -> {
                InputItemViewModel(repository) as T
            }
            modelClass.isAssignableFrom(CreateExpenseViewModel::class.java) -> {
                CreateExpenseViewModel(repository) as T
            }
            modelClass.isAssignableFrom(CreateIncomeViewModel::class.java) -> {
                CreateIncomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(MerchItemViewModel::class.java) -> {
                MerchItemViewModel(repository) as T
            }
            modelClass.isAssignableFrom(EditItemViewModel::class.java) -> {
                EditItemViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ForgotPasswordViewModel::class.java) -> {
                ForgotPasswordViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(repository) as T
            }
            modelClass.isAssignableFrom(HistoryViewModel::class.java) -> {
                HistoryViewModel(repository) as T
            }
            modelClass.isAssignableFrom(StatisticViewModel::class.java) -> {
                StatisticViewModel(repository) as T
            }
            modelClass.isAssignableFrom(RecoveryPasswordViewModel::class.java) -> {
                RecoveryPasswordViewModel(repository) as T
            }


            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }



    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }

}
