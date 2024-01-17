package com.serhiymysyshyn.smartpethubapplication.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.serhiymysyshyn.smartpethubapplication.databinding.ActivityLoginBinding
import com.serhiymysyshyn.smartpethubapplication.databinding.ActivityMainBinding
import com.serhiymysyshyn.smartpethubapplication.ui.main.MainActivity
import com.serhiymysyshyn.smartpethubapplication.ui.notification.NotificationViewModel
import com.serhiymysyshyn.smartpethubapplication.ui.registration.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.registerButton.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        binding.signInButton.setOnClickListener {
            if(binding.loginEmailAddress.text.toString() == "test") {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            }
        }

        //binding.loginEmailAddress.text

    }
}