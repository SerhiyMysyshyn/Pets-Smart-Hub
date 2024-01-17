package com.serhiymysyshyn.smartpethubapplication.ui.error

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.serhiymysyshyn.smartpethubapplication.databinding.ActivityErrorBinding

class ErrorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityErrorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityErrorBinding.inflate(layoutInflater)
        setContentView(binding.root)    }
}