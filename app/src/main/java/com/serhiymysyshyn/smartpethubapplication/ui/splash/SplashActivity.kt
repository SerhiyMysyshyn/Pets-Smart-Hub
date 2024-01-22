package com.serhiymysyshyn.smartpethubapplication.ui.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.serhiymysyshyn.smartpethubapplication.databinding.ActivitySplashBinding
import com.serhiymysyshyn.smartpethubapplication.debug.CustomTags
import com.serhiymysyshyn.smartpethubapplication.debug.Logger
import com.serhiymysyshyn.smartpethubapplication.ui.greeting.GreetingActivity
import com.serhiymysyshyn.smartpethubapplication.ui.login.LoginActivity
import com.serhiymysyshyn.smartpethubapplication.ui.main.MainActivity
import java.util.*

class SplashActivity : AppCompatActivity() {

    companion object {
        private lateinit var timer: Timer
        private lateinit var mTimerTask: TimerTask
        private lateinit var viewModel: SplashViewModel
    }

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Logger.i(CustomTags.splash, "SplashActivity launched...")

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)

        //startActivity(Intent(this@SplashActivity, MainActivity::class.java))

        timer = Timer()
        mTimerTask = MyTimerTask()
        timer.schedule(mTimerTask, 1000)

        viewModel.isSavedUserExist.observe(this@SplashActivity) {
            startActivity(Intent(this@SplashActivity, GreetingActivity::class.java))

//            if (it) {
//                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
//            } else {
//                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
//            }
        }
    }

    internal class MyTimerTask : TimerTask() {
        override fun run() {
            timer.cancel()
            viewModel.isSavedUserExist()
        }
    }
}