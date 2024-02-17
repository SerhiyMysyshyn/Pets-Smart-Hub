package com.serhiymysyshyn.smartpethubapplication.ui.error

import android.R.attr.height
import android.R.attr.width
import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import com.serhiymysyshyn.smartpethubapplication.R
import com.serhiymysyshyn.smartpethubapplication.databinding.ActivityErrorBinding
import com.serhiymysyshyn.smartpethubapplication.logic.core.PicassoHelper


class ErrorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityErrorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityErrorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        PicassoHelper().loadDrawableToImageView(R.drawable.cat_and_error, binding.imageView6)
    }
}