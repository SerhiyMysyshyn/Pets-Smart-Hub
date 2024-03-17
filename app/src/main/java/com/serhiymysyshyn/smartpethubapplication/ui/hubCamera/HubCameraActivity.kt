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

        val webSettings: WebSettings = binding.hubCameraWebView.settings
        webSettings.javaScriptEnabled = true
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        //webSettings.useWideViewPort = true;
        //webSettings.builtInZoomControls = true
        binding.hubCameraWebView.loadUrl("http://192.168.0.189:81/stream")

        binding.backButton.setOnClickListener {
            // Additional code here
            onBackPressed()
        }

        // Turn on/off hub light
        binding.cameraTurnOnOffLightButton.setOnClickListener {
            binding.hubCameraWebView.loadUrl("");
        }

        // Make snapshot using hub camera and save in gallery
        binding.cameraMakeSnapshotButton.setOnClickListener {
            binding.hubCameraWebView.loadUrl("")
        }

        // Reload WebView
        binding.reloadButton.setOnClickListener {
            binding.hubCameraWebView.reload()
        }

        // Open camera settings to change video quality or something else
        binding.cameraSettingsButton.setOnClickListener {

        }

        // Move camera left to 1 degree
        binding.cameraMoveLeftButton.setOnClickListener {
            binding.hubCameraWebView.loadUrl("")
        }

        // Move camera right to 1 degree
        binding.cameraMoveRightButton.setOnClickListener {
            binding.hubCameraWebView.loadUrl("")
        }

        // Move camera up to 1 degree
        binding.cameraMoveUpButton.setOnClickListener {
            binding.hubCameraWebView.loadUrl("")
        }

        // Move camera down to 1 degree
        binding.cameraMoveBottomButton.setOnClickListener {
            binding.hubCameraWebView.loadUrl("")
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this@HubCameraActivity.finish()
    }
}