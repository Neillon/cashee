package com.neillon.cashee.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.transition.MaterialElevationScale
import com.neillon.cashee.R
import com.neillon.cashee.databinding.ActivityAuthenticationBinding

class AuthenticationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthenticationBinding
    private lateinit var navController: NavController
    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.authenticationNavHostFragment)!! as NavHostFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
    }

    override fun onNavigateUp(): Boolean = navController.navigateUp() || super.onNavigateUp()

    private fun setupNavigation() {
        navController = navHostFragment.findNavController()
        binding.authenticationToolbar.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.authenticationToolbar.title = ""
        }
    }

    companion object {
        const val TAG = "AuthenticationActivity"
    }
}