package com.maxbt.newsapplication.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.maxbt.newsapplication.R
import com.maxbt.newsapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController  = navHostFragment.navController

        val appBarConfig = AppBarConfiguration(setOf(
            R.id.navigation_news,
            R.id.navigation_settings
        ))

   /*     //testBadge
        val badge = binding.navView.getOrCreateBadge(R.id.navigation_news)
        badge.isVisible = true
        // An icon only badge will be displayed unless a number is set:
        badge.number = 3*/

        setupActionBarWithNavController(navController, appBarConfig)
        binding.navView.setupWithNavController(navController)
    }
}