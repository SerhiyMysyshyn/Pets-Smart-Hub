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
import com.serhiymysyshyn.smartpethubapplication.ui.addNewHub.AddNewSmartHubActivity
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


        showAddHubLayout()

        binding.lockHubButton.setOnClickListener {
            binding.lockHubButtonImg.visibility = View.INVISIBLE
            binding.lockHubButtonProgress.visibility = View.VISIBLE
            //startActivity(Intent(activity, HubCameraActivity::class.java))
        }

        binding.setFeedingButton.setOnClickListener {
            binding.setFeedingButtonImg.visibility = View.INVISIBLE
            binding.setFeedingButtonProgress.visibility = View.VISIBLE
            //startActivity(Intent(activity, HubCameraActivity::class.java))
        }

        binding.hubCameraButton.setOnClickListener {
            binding.hubCameraButtonImg.visibility = View.INVISIBLE
            binding.hubCameraButtonProgress.visibility = View.VISIBLE
            startActivity(Intent(activity, HubCameraActivity::class.java))
        }

        binding.hubSettingsButton.setOnClickListener {
            binding.hubSettingsButtonImg.visibility = View.INVISIBLE
            binding.hubSettingsButtonProgress.visibility = View.VISIBLE
            //startActivity(Intent(activity, HubCameraActivity::class.java))
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

        setSmartHubSensorsData()
    }

    private fun setSmartHubSensorsData() {
        viewModel.getSmartHubSensorsData()
    }

    fun showLoadingProgress() {
        binding.homeFragmentProgress.visibility = View.VISIBLE
    }

    fun hideLoadingProgress() {
        binding.homeFragmentProgress.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        restoreButtonsState()
    }

    private fun restoreButtonsState() {
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
}