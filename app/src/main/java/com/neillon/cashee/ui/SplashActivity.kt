package com.neillon.cashee.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.neillon.cashee.databinding.ActivitySplashBinding
import com.neillon.cashee.utils.goToActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        verifyLoggedUser()
    }

    private fun verifyLoggedUser() = lifecycleScope.launch {
        delay(1000L)
        val user = firebaseAuth.currentUser
        if (user != null)
            this@SplashActivity goToActivity MainActivity::class.java
        else
            this@SplashActivity goToActivity AuthenticationActivity::class.java
    }

    companion object {
        const val TAG = "SplashActivity"
    }

}