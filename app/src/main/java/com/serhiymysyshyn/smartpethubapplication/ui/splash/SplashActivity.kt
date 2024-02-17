package com.serhiymysyshyn.smartpethubapplication.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.serhiymysyshyn.smartpethubapplication.PetsSmartHubApplication
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
    private lateinit var application: PetsSmartHubApplication


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.i(CustomTags.splash, "SplashActivity launched...")

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        application = PetsSmartHubApplication.getInstance()

        timer = Timer()
        mTimerTask = MyTimerTask()
        timer.schedule(mTimerTask, 1000)

        viewModel.isFirstLaunch.observe(this@SplashActivity) {
            if (!it) {
                if (application.firebaseCurrentUser() != null) {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                } else {
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                }
            } else {
                startActivity(Intent(this@SplashActivity, GreetingActivity::class.java))
            }
        }
    }

    internal class MyTimerTask : TimerTask() {
        override fun run() {
            timer.cancel()
            viewModel.isFirstLaunch()
        }
    }
}