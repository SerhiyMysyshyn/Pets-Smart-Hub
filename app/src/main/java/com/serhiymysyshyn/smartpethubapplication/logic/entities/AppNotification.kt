package com.serhiymysyshyn.smartpethubapplication.logic.entities

import android.graphics.drawable.Drawable
import java.io.Serializable

data class AppNotification(
    val type: String,
    val date: String,
    val time: String,
    val imgRes: Int?,
    val title: String,
    val content: String
): Serializable {

    override fun toString() = "Current notification type is: $type"

}
