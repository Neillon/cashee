package com.neillon.cashee.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.neillon.cashee.databinding.ActivitySplashBinding
import com.neillon.cashee.store.TutorialDataStore
import com.neillon.cashee.ui.tutorial.TutorialActivity
import com.neillon.cashee.utils.callActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val tutorialDataStore by lazy { TutorialDataStore(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: Create feature authentication [login, register, forgot]
        //        // TODO: Inside LoginFragment add the signinButton

        // TODO: verify if user is logged in and redirect
        // TODO: Remove 2L delay after login verification is done
        lifecycleScope.launch { delay(2000L) }
    }

    companion object {
        const val TAG = "SplashActivity"
    }
}