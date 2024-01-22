package com.serhiymysyshyn.smartpethubapplication.ui.error

import android.R.attr.height
import android.R.attr.width
import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import com.serhiymysyshyn.smartpethubapplication.R
import com.serhiymysyshyn.smartpethubapplication.databinding.ActivityErrorBinding


class ErrorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityErrorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityErrorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bitmap = Bitmap.createScaledBitmap(applicationContext.resources.getDrawable(R.drawable.cat_and_error).toBitmap(), 1000, 1000, true)
        binding.imageView6.setImageBitmap(bitmap)

    }
}