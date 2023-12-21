package com.example.smartbiz.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.smartbiz.data.Repository
import com.example.smartbiz.viewmodel.CreateIncomeViewModel
import com.example.smartbiz.viewmodel.CreateOutcomeViewModel
import com.example.smartbiz.viewmodel.InputItemViewModel
import com.example.smartbiz.viewmodel.LoginViewModel
import com.example.smartbiz.viewmodel.MerchItemViewModel
import com.example.smartbiz.viewmodel.RegisterViewModel

class ViewModelFactory private constructor(private val repository: Repository) : ViewModelProvider.NewInstanceFactory()

    {

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
            modelClass.isAssignableFrom(CreateOutcomeViewModel::class.java) -> {
                CreateOutcomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(CreateIncomeViewModel::class.java) -> {
                CreateIncomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(MerchItemViewModel::class.java) -> {
                MerchItemViewModel(repository) as T
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
