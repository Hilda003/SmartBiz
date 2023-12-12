package com.example.smartbiz

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.smartbiz.databinding.ActivityMainBinding
import com.example.smartbiz.ui.dashboard.DashboardFragment
import com.example.smartbiz.ui.history.HistoryFragment
import com.example.smartbiz.ui.profile.ProfileFragment
import com.example.smartbiz.ui.statistics.StatisticsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.dashboard -> {
                    replaceFragment(DashboardFragment())
                }

                R.id.history -> {
                    replaceFragment(HistoryFragment())
                }
                R.id.stats -> {
                    replaceFragment(StatisticsFragment())
                }

                R.id.profile -> {
                    replaceFragment(ProfileFragment())
                }

            }
            true
        }

    }



    private fun replaceFragment(fragment: Fragment) {
        fragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

}