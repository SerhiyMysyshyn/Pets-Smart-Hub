package com.serhiymysyshyn.smartpethubapplication.ui.feedingSchedule

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.serhiymysyshyn.smartpethubapplication.PetsSmartHubApplication
import com.serhiymysyshyn.smartpethubapplication.R
import com.serhiymysyshyn.smartpethubapplication.cache.sharedPreferences.PrefsManager
import com.serhiymysyshyn.smartpethubapplication.databinding.ActivityFeedingScheduleBinding
import com.serhiymysyshyn.smartpethubapplication.logic.adapters.FeedingScheduleAdapter
import com.serhiymysyshyn.smartpethubapplication.logic.adapters.FeedingTimeAdapter
import com.serhiymysyshyn.smartpethubapplication.logic.entities.FeedingHour
import com.serhiymysyshyn.smartpethubapplication.ui.login.LoginActivity

class FeedingScheduleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFeedingScheduleBinding
    private lateinit var viewModel: FeedingScheduleViewModel
    private lateinit var customPagerAdapter: FeedingScheduleAdapter
    private var feedingScheduleListLastCopy = listOf<FeedingHour>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedingScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        PetsSmartHubApplication.getInstance().setFeedingScheduleActivity(this@FeedingScheduleActivity)

        viewModel = ViewModelProvider(this@FeedingScheduleActivity).get(FeedingScheduleViewModel::class.java)

        customPagerAdapter = FeedingScheduleAdapter()
        binding.feedingScheduleViewPager.adapter = customPagerAdapter

        viewModel.getFeedingSchedule()

        viewModel.feedingScheduleList.observe(this@FeedingScheduleActivity) {
            if (it != null) {
                customPagerAdapter.updateRecyclerView(ArrayList<FeedingHour>(it))
                feedingScheduleListLastCopy = it
            }
        }

        binding.springDotsIndicator2.attachTo(binding.feedingScheduleViewPager)

        binding.backButtonFeeding.setOnClickListener {
            onBackPressed()
        }

        binding.saveFeedingScheduleButton.setOnClickListener {
            viewModel.saveNewFeedingSchedule(feedingScheduleListLastCopy)
        }

        binding.feedingScheduleViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                when (position) {
                    0 -> {
                        binding.feedingScheduleActivityTitle.text = resources.getString(R.string.schedule_dry_food)
                    }
                    1 -> {
                        binding.feedingScheduleActivityTitle.text = resources.getString(R.string.schedule_water)
                    }
                }
            }
        })
    }



    fun addNewScheduleItem(scheduleItem: FeedingHour) {
        viewModel.addNewItemToCurrentList(scheduleItem)
    }
}