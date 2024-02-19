package com.serhiymysyshyn.smartpethubapplication.ui.addNewHub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.serhiymysyshyn.smartpethubapplication.databinding.ActivityAddNewSmartHubBinding

class AddNewSmartHubActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNewSmartHubBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewSmartHubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.smartHubIdConnectButton.setOnClickListener {
            binding.smartHubIdConnectButtonProgress.visibility = View.VISIBLE
            binding.smartHubIdConnectButtonText.visibility = View.GONE
        }

        binding.backButton.setOnClickListener {
            onBackPressed()
        }

    }
}