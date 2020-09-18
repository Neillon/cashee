package com.neillon.cashee.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.neillon.cashee.R
import com.neillon.cashee.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val navHostFragment by lazy { supportFragmentManager.findFragmentById(R.id.mainNavHostFragment)!! as NavHostFragment }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = navHostFragment.findNavController()
        binding.mainBottomNavigationView.setupWithNavController(navController)

    }
}