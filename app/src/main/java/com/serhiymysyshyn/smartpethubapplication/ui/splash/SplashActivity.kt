package com.serhiymysyshyn.smartpethubapplication.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.serhiymysyshyn.smartpethubapplication.PetsSmartHubApplication.Companion.appComponent
import com.serhiymysyshyn.smartpethubapplication.databinding.ActivitySplashBinding
import com.serhiymysyshyn.smartpethubapplication.debug.CustomTags
import com.serhiymysyshyn.smartpethubapplication.debug.Logger
import com.serhiymysyshyn.smartpethubapplication.ui.greeting.GreetingActivity
import com.serhiymysyshyn.smartpethubapplication.ui.login.LoginActivity
import com.serhiymysyshyn.smartpethubapplication.ui.main.MainActivity
import java.util.*
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    companion object {
        private lateinit var timer: Timer
        private lateinit var mTimerTask: TimerTask
    }

    @Inject
    lateinit var viewModel: SplashViewModel
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.i(CustomTags.splash, "SplashActivity launched...")

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appComponent.inject(this)

        timer = Timer()
        mTimerTask = MyTimerTask(viewModel)
        timer.schedule(mTimerTask, 500)

        viewModel.isFirstLaunch.observe(this@SplashActivity) {
            if (!it) {
                if (Firebase.auth.currentUser != null) {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                } else {
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                }
            } else {
                startActivity(Intent(this@SplashActivity, GreetingActivity::class.java))
            }
        }
    }

    internal class MyTimerTask(viewModel: SplashViewModel) : TimerTask() {
        val _viewModel = viewModel
        override fun run() {
            timer.cancel()
            _viewModel.isFirstLaunch()
        }
    }
}