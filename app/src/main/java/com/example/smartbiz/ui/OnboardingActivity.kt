package com.example.smartbiz.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.smartbiz.R
import com.example.smartbiz.adapter.OnBoardingAdapter
import com.example.smartbiz.data.OnBoardingItem
import com.example.smartbiz.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding : ActivityOnboardingBinding
    private lateinit var onBoardingAdapter: OnBoardingAdapter
    private lateinit var indicator : LinearLayout
    private lateinit var indicators: Array<ImageView?>
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        onBoardingItems()
        indicatorSetup()

        binding.onBoardingView.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateIndicators(position)
            }
        })

        binding.btnContinue.setOnClickListener {
            if (binding.onBoardingView.currentItem + 1 < onBoardingAdapter.itemCount){
                binding.onBoardingView.currentItem += 1
            }
            else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }

        binding.tvSkip.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }




    }
    private fun onBoardingItems()  {
        onBoardingAdapter = OnBoardingAdapter (
            listOf(
                OnBoardingItem(
                   title = resources.getString(R.string.create_income),
                    description = resources.getString(R.string.desc_income),
                    R.drawable.income
                ),
                OnBoardingItem(
                    title = resources.getString(R.string.create_outcome),
                    description = resources.getString(R.string.desc_outcome),
                    R.drawable.outcome
                ),
                OnBoardingItem(
                    title = resources.getString(R.string.your_items),
                    description = resources.getString(R.string.desc_items),
                    R.drawable.merch
                ),
                OnBoardingItem(
                    title = resources.getString(R.string.insight_business),
                    description = resources.getString(R.string.desc_insight),
                    R.drawable.insight
                )
            )
        )

        binding.onBoardingView.adapter = onBoardingAdapter


    }

    private fun indicatorSetup() {
        indicator = binding.indicator
        indicators = arrayOfNulls(onBoardingAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 0, 8, 0)
        for (
            i in indicators.indices
        ) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indikator_inactive
                    )
                )
                it.layoutParams = layoutParams
                binding.indicator.addView(it)
            }
        }
    }

    private fun updateIndicators(position: Int) {
        Log.d("OnboardingActivity", "Current position: $position")
        for (i in indicators.indices) {
            val imageView = indicators[i]!!
            val drawableId = if (i == position) R.drawable.indicator_active else R.drawable.indikator_inactive
            imageView.setImageDrawable(ContextCompat.getDrawable(applicationContext, drawableId))
        }

    }
}