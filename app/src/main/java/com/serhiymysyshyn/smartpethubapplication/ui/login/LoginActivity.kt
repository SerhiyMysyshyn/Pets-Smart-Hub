package com.serhiymysyshyn.smartpethubapplication.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.serhiymysyshyn.smartpethubapplication.PetsSmartHubApplication
import com.serhiymysyshyn.smartpethubapplication.R
import com.serhiymysyshyn.smartpethubapplication.databinding.ActivityLoginBinding
import com.serhiymysyshyn.smartpethubapplication.debug.CustomTags
import com.serhiymysyshyn.smartpethubapplication.debug.Logger
import com.serhiymysyshyn.smartpethubapplication.logic.core.PicassoHelper
import com.serhiymysyshyn.smartpethubapplication.ui.main.MainActivity

class LoginActivity  : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var application: PetsSmartHubApplication

    private lateinit var launcher: ActivityResultLauncher<Intent>
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        PicassoHelper().loadDrawableToImageView(R.drawable.bot, binding.imageView5)

        application = PetsSmartHubApplication.getInstance()

        auth = application.firebaseAuth()

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    startAuthProcessWithGoogleAccount(account.idToken!!)
                }
            } catch (error: ApiException) {
                Log.e("AuthViaGoogle", error.message.toString() )
            }
        }

        binding.signInWithGoogleButton.setOnClickListener {
            binding.loginProgressBar.visibility = View.VISIBLE
            signInWithGoogle()
        }
    }

    private fun signInWithGoogle() {
        val signInClient = getClient()
        launcher.launch(signInClient.signInIntent)
    }
    
    private fun getClient(): GoogleSignInClient {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(this@LoginActivity, gso)
    }

    private fun startAuthProcessWithGoogleAccount(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                Logger.i(CustomTags.auth, "Successfully signedIn with user: ${auth.currentUser?.email}")
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                this@LoginActivity.finish()
            } else {
                Logger.i(CustomTags.auth, "An error occurred while trying to authenticate with Google")
            }
            if (it.isCanceled) {
                Toast.makeText(this@LoginActivity, "Authorization using a Google account has been canceled", Toast.LENGTH_SHORT).show()
            }
        }
    }
}