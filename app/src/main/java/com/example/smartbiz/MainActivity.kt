package com.example.smartbiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.smartbiz.databinding.ActivityMainBinding
import com.example.smartbiz.ui.DashboardFragment
import com.example.smartbiz.ui.HelpActivity
import com.example.smartbiz.ui.HistoryFragment
import com.example.smartbiz.ui.NotificationActivity
import com.example.smartbiz.ui.ProfileFragment
import com.example.smartbiz.ui.StatisticsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


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