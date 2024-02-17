package com.serhiymysyshyn.smartpethubapplication.logic.core

import android.net.Uri
import android.widget.ImageView
import com.squareup.picasso.Picasso
import javax.inject.Singleton

@Singleton
class PicassoHelper {

    fun loadDrawableToImageView(drawableId: Int, imageView: ImageView) {
        Picasso
            .get()
            .load(drawableId)
            .into(imageView)
    }

    fun loadDrawableToImageView(uri: Uri, imageView: ImageView) {
        Picasso
            .get()
            .load(uri)
            .into(imageView)
    }
}