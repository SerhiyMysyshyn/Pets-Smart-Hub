package com.serhiymysyshyn.smartpethubapplication.ui.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.serhiymysyshyn.smartpethubapplication.databinding.ActivityRegisterBinding
import com.serhiymysyshyn.smartpethubapplication.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        binding.signInBtn.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }



    }
}