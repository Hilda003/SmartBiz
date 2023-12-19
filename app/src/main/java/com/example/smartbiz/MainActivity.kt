package com.example.smartbiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.smartbiz.databinding.ActivityMainBinding
import com.example.smartbiz.retrofit.ApiConfig
import com.example.smartbiz.ui.DashboardFragment
import com.example.smartbiz.ui.HelpActivity
import com.example.smartbiz.ui.HistoryFragment
import com.example.smartbiz.ui.NotificationActivity
import com.example.smartbiz.ui.ProfileFragment
import com.example.smartbiz.ui.StatisticsFragment
import com.example.smartbiz.viewmodel.OutcomeFragmentViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding : ActivityMainBinding
    lateinit var viewModel : OutcomeFragmentViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.frame_layout) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setupWithNavController(navController)

//        val navController = findNavController(R.id.frame_layout)
//        binding.bottomNavigation.setupWithNavController(navController)


        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.dashboard -> {
                    replaceFragment(DashboardFragment())
                    true
                }

                R.id.history -> {
                    replaceFragment(HistoryFragment())
                    true
                }
                R.id.stats -> {
                    replaceFragment(StatisticsFragment())
                    true
                }

                R.id.profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> {
                    false
                }

            }



        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.notification -> {
                startActivity(Intent(this, NotificationActivity::class.java))
            }
            R.id.help -> {
                startActivity(Intent(this, HelpActivity::class.java))
            }

        }
        return super.onOptionsItemSelected(item)
    }





    private fun replaceFragment(fragment: Fragment) {
        fragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }



}