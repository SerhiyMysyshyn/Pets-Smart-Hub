package com.serhiymysyshyn.smartpethubapplication.ui.addNewHub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.serhiymysyshyn.smartpethubapplication.R
import com.serhiymysyshyn.smartpethubapplication.databinding.ActivityAddNewSmartHubBinding

class AddNewSmartHubActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNewSmartHubBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewSmartHubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.smartHubIdConnectButton.setOnClickListener {
            binding.smartHubIdConnectButton.background = resources.getDrawable(R.drawable.round_button_background_50dp)
            binding.smartHubIdConnectButtonProgress.visibility = View.VISIBLE
            binding.smartHubIdConnectButtonText.visibility = View.GONE
        }

        binding.backButton.setOnClickListener {
            onBackPressed()
        }

    }

    private fun restoreButtonState() {
        binding.smartHubIdConnectButton.background = resources.getDrawable(R.drawable.round_button_background_10dp)
        binding.smartHubIdConnectButtonProgress.visibility = View.INVISIBLE
        binding.smartHubIdConnectButtonText.visibility = View.VISIBLE
    }
}