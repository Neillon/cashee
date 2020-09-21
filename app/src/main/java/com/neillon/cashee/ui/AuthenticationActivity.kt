package com.neillon.cashee.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.neillon.cashee.databinding.ActivityAuthenticationBinding

class AuthenticationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthenticationBinding
    private lateinit var navController: NavController
    private val navHostFragment by lazy { supportFragmentManager.findFragmentById(binding.authenticationNavHostFragment.id)!! as NavHostFragment }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)

        setContentView(binding.root)

        navController = navHostFragment.findNavController()
        binding.authenticationToolbar.setupWithNavController(navController)
    }

    companion object {
        const val TAG = "AuthenticationActivity"
    }
}