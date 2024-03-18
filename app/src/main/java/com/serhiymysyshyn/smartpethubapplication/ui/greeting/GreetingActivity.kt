package com.serhiymysyshyn.smartpethubapplication.ui.greeting

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.serhiymysyshyn.smartpethubapplication.PetsSmartHubApplication.Companion.appComponent
import com.serhiymysyshyn.smartpethubapplication.R
import com.serhiymysyshyn.smartpethubapplication.cache.sharedPreferences.PrefsManager
import com.serhiymysyshyn.smartpethubapplication.databinding.ActivityGreetingBinding
import com.serhiymysyshyn.smartpethubapplication.logic.adapters.GreetingPagerAdapter
import com.serhiymysyshyn.smartpethubapplication.ui.login.LoginActivity
import javax.inject.Inject

class GreetingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGreetingBinding

    @Inject
    lateinit var sharedPreferences: PrefsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGreetingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appComponent.inject(this)

        val customPagerAdapter = GreetingPagerAdapter(this)
        binding.greetingViewPager.adapter = customPagerAdapter

        binding.springDotsIndicator.attachTo(binding.greetingViewPager)

        binding.skipGreetingButton.setOnClickListener {
            sharedPreferences.setFirstLaunch(false)
            startActivity(Intent(this@GreetingActivity, LoginActivity::class.java))
            this@GreetingActivity.finish()
        }

        binding.greetingViewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                when (position) {
                    0 -> {
                        binding.greetingNextButton.apply {
                            this.text = resources.getText(R.string.next_string)
                            this.setOnClickListener {
                                binding.greetingViewPager.currentItem = binding.greetingViewPager.currentItem + 1
                            }
                        }
                    }
                    1 -> {
                        binding.greetingNextButton.apply {
                            this.text = resources.getText(R.string.next_string)
                            this.setOnClickListener {
                                binding.greetingViewPager.currentItem = binding.greetingViewPager.currentItem + 1
                            }
                        }
                    }
                    2 -> {
                        binding.greetingNextButton.apply {
                            this.text = resources.getText(R.string.next_string)
                            this.setOnClickListener {
                                binding.greetingViewPager.currentItem = binding.greetingViewPager.currentItem + 1
                            }
                        }
                    }
                    3 -> {
                        binding.greetingNextButton.apply {
                            this.text = resources.getText(R.string.lets_go)
                            this.setOnClickListener {
                                sharedPreferences.setFirstLaunch(false)
                                startActivity(Intent(this@GreetingActivity, LoginActivity::class.java))
                                this@GreetingActivity.finish()
                            }
                        }
                    }
                }
            }
        })
    }
}