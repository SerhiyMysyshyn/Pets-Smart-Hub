package com.serhiymysyshyn.smartpethubapplication.ui.hubCamera

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import com.serhiymysyshyn.smartpethubapplication.R
import com.serhiymysyshyn.smartpethubapplication.databinding.ActivityHubCameraBinding

class HubCameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHubCameraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHubCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            // Additional code here
            onBackPressed()
        }

        val webSettings: WebSettings = binding.hubCameraWebView.settings
        webSettings.javaScriptEnabled = true
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        //webSettings.useWideViewPort = true;
        //webSettings.builtInZoomControls = true
        binding.hubCameraWebView.loadUrl("http://192.168.0.189:81/stream")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this@HubCameraActivity.finish()
    }
}