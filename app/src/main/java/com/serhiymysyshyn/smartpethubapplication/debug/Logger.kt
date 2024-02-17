package com.serhiymysyshyn.smartpethubapplication.debug

import android.util.Log

class Logger {
    companion object {
        fun i (tag: CustomTags, msg: String) {
            Log.i(tag.name, msg)
        }

        fun e (tag: CustomTags, msg: String) {
            Log.e(tag.name, msg)
        }
    }

}