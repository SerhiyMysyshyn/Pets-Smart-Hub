package com.serhiymysyshyn.smartpethubapplication.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.serhiymysyshyn.smartpethubapplication.R
import com.serhiymysyshyn.smartpethubapplication.databinding.FragmentHomeBinding
import com.serhiymysyshyn.smartpethubapplication.logic.core.PicassoHelper
import com.serhiymysyshyn.smartpethubapplication.logic.entities.SmartHubV1SensorsData
import com.serhiymysyshyn.smartpethubapplication.ui.addNewHub.AddNewSmartHubActivity
import com.serhiymysyshyn.smartpethubapplication.ui.feedingSchedule.FeedingScheduleActivity
import com.serhiymysyshyn.smartpethubapplication.ui.hubCamera.HubCameraActivity


class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        // Запит чи є зареєстровані хаби для даного користувача
        checkIsSmartHubExist()


        viewModel.isSmartHubExist.observe(viewLifecycleOwner) { result ->
            when (result) {
                true -> showMainControlPanel()
                false -> showAddHubLayout()
                null -> showAddHubLayout()
            }
            hideLoadingProgress()
        }

        viewModel.smartHubV1SensorsData.observe(viewLifecycleOwner) { sensorsData ->
            if (sensorsData != null) {
                showSensorsData(sensorsData)
            }
        }

        showAddHubLayout()

        binding.lockHubButton.setOnClickListener {
            binding.lockHubButtonImg.visibility = View.INVISIBLE
            binding.lockHubButtonProgress.visibility = View.VISIBLE
        }

        binding.setFeedingButton.setOnClickListener {
            binding.setFeedingButtonImg.visibility = View.INVISIBLE
            binding.setFeedingButtonProgress.visibility = View.VISIBLE
            startActivity(Intent(activity, FeedingScheduleActivity::class.java))
        }

        binding.hubCameraButton.setOnClickListener {
            binding.hubCameraButtonImg.visibility = View.INVISIBLE
            binding.hubCameraButtonProgress.visibility = View.VISIBLE
            startActivity(Intent(activity, HubCameraActivity::class.java))
        }

        binding.hubSettingsButton.setOnClickListener {
            binding.hubSettingsButtonImg.visibility = View.INVISIBLE
            binding.hubSettingsButtonProgress.visibility = View.VISIBLE
        }

        binding.pourFeedButton.setOnClickListener {
            binding.pourFeedButtonImg.visibility = View.INVISIBLE
            binding.pourFeedButtonProgress.visibility = View.VISIBLE
        }

        binding.pourWaterButton.setOnClickListener {
            binding.pourWaterButtonImg.visibility = View.INVISIBLE
            binding.pourWaterButtonProgress.visibility = View.VISIBLE
        }
    }

    private fun checkIsSmartHubExist() {
        showLoadingProgress()
        viewModel.isSmartHubExist()
    }

    private fun showAddHubLayout() {
        binding.homeFragmentProgress.visibility = View.GONE
        binding.addHubLayout.visibility = View.VISIBLE
        PicassoHelper().loadDrawableToImageView(R.drawable.cat_1, binding.imageView7)
        binding.addNewHubButton.setOnClickListener {
            startActivity(Intent(requireContext(), AddNewSmartHubActivity::class.java))
        }
    }

    private fun showMainControlPanel() {
        binding.homeFragmentProgress.visibility = View.GONE
        binding.addHubLayout.visibility = View.GONE

        binding.mainControlPanel.visibility = View.VISIBLE

        getSmartHubSensorsData()
    }

    private fun getSmartHubSensorsData() {
        viewModel.getSmartHubSensorsData()
    }

    private fun updateSensorsData(sensorsData: SmartHubV1SensorsData) {
        showSensorsData(sensorsData)
    }

    private fun showSensorsData(sensorsData: SmartHubV1SensorsData) {
        when (sensorsData.wifiConnectionLevel) {
            0 -> binding.hubWiFiConnectionIndicator.background = resources.getDrawable(R.drawable.baseline_wifi_off_24)
            1 -> binding.hubWiFiConnectionIndicator.background = resources.getDrawable(R.drawable.baseline_wifi_1_bar_24)
            2 -> binding.hubWiFiConnectionIndicator.background = resources.getDrawable(R.drawable.baseline_wifi_2_bar_24)
            3 -> binding.hubWiFiConnectionIndicator.background = resources.getDrawable(R.drawable.baseline_wifi_24)
            -1 -> binding.hubWiFiConnectionIndicator.background = resources.getDrawable(R.drawable.baseline_wifi_find_24)
            else -> binding.hubWiFiConnectionIndicator.background = resources.getDrawable(R.drawable.baseline_signal_wifi_statusbar_connected_no_internet_4_24)
        }

        binding.hubNameText.text = sensorsData.hubName

        when (sensorsData.batteryLevel) {
            0 -> binding.hubBatteryIndicator.background = resources.getDrawable(R.drawable.baseline_battery_0_bar_24)
            1 -> binding.hubBatteryIndicator.background = resources.getDrawable(R.drawable.baseline_battery_1_bar_24)
            2 -> binding.hubBatteryIndicator.background = resources.getDrawable(R.drawable.baseline_battery_2_bar_24)
            3 -> binding.hubBatteryIndicator.background = resources.getDrawable(R.drawable.baseline_battery_3_bar_24)
            4 -> binding.hubBatteryIndicator.background = resources.getDrawable(R.drawable.baseline_battery_4_bar_24)
            5 -> binding.hubBatteryIndicator.background = resources.getDrawable(R.drawable.baseline_battery_5_bar_24)
            6 -> binding.hubBatteryIndicator.background = resources.getDrawable(R.drawable.baseline_battery_6_bar_24)
            7 -> binding.hubBatteryIndicator.background = resources.getDrawable(R.drawable.baseline_battery_full_24)
            8 -> binding.hubBatteryIndicator.background = resources.getDrawable(R.drawable.baseline_battery_charging_full_24)
            else -> binding.hubBatteryIndicator.background = resources.getDrawable(R.drawable.baseline_battery_unknown_24)
        }

        binding.waterLevelProgress.progress = sensorsData.waterLevel
        binding.waterLevelProgressText.text = "${sensorsData.waterLevel}%"

        binding.feedLevelProgress.progress = sensorsData.feedLevel
        binding.feedLevelProgressText.text = "${sensorsData.feedLevel}%"

        binding.progress1.progress = convertSensorValueToProgressbar(sensorsData.airTemperature.toInt())
        binding.progress1Data.text = "${sensorsData.airTemperature}°C"

        binding.progress2.progress = convertSensorValueToProgressbar(sensorsData.airHumidity)
        binding.progress2Data.text = "${sensorsData.airHumidity}%"

        binding.progress3.progress = convertSensorValueToProgressbar(sensorsData.airQuality)
        binding.progress3Data.text = "${sensorsData.airQuality}%"

        binding.progress4.progress = if (sensorsData.dangerousGasDetected) convertSensorValueToProgressbar(100) else convertSensorValueToProgressbar(0)
        binding.progress4Data.text = if (sensorsData.dangerousGasDetected) "Yes" else "No"

        binding.progress5.progress = convertSensorValueToProgressbar(sensorsData.co2InAir)
        binding.progress5Data.text = "${sensorsData.co2InAir}%"

        binding.progress6.progress = convertSensorValueToProgressbar(sensorsData.coAndCngGasInAir)
        binding.progress6Data.text = "${sensorsData.coAndCngGasInAir}%"
    }

    private fun showLoadingProgress() {
        binding.homeFragmentProgress.visibility = View.VISIBLE
    }

    private fun hideLoadingProgress() {
        binding.homeFragmentProgress.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        restoreButtonsProgressState()
    }

    private fun restoreButtonsProgressState() {
        binding.lockHubButtonProgress.visibility = View.INVISIBLE
        binding.setFeedingButtonProgress.visibility = View.INVISIBLE
        binding.hubCameraButtonProgress.visibility = View.INVISIBLE
        binding.hubSettingsButtonProgress.visibility = View.INVISIBLE
        binding.pourFeedButtonProgress.visibility = View.INVISIBLE
        binding.pourWaterButtonProgress.visibility = View.INVISIBLE

        binding.lockHubButtonImg.visibility = View.VISIBLE
        binding.setFeedingButtonImg.visibility = View.VISIBLE
        binding.hubCameraButtonImg.visibility = View.VISIBLE
        binding.hubSettingsButtonImg.visibility = View.VISIBLE
        binding.pourFeedButtonImg.visibility = View.VISIBLE
        binding.pourWaterButtonImg.visibility = View.VISIBLE
    }

    private fun convertSensorValueToProgressbar(value: Int): Int{
        val customProgressBarMaxValue: Int = 60
        val defaultProgressBarMaxValue: Int = 100
        var resultProgressValue: Int = 0

        resultProgressValue =  (value * customProgressBarMaxValue) / defaultProgressBarMaxValue

        return resultProgressValue
    }
}