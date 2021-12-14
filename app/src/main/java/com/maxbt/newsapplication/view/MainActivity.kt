package com.maxbt.newsapplication.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.maxbt.newsapplication.R
import com.maxbt.newsapplication.databinding.ActivityMainBinding
import com.maxbt.newsapplication.model.db.NewsDatabase
import com.maxbt.newsapplication.model.repository.NewsRepository

/**
 * This is MainClass , where we instaniate our view model ,
 * our repos and evetyrhing else to get done before setting view
 *
 */
class MainActivity : AppCompatActivity() {
    lateinit var viewModel: NewsViewModel

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Init view model
        val newsRepository = NewsRepository(NewsDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)
            .get(NewsViewModel::class.java)


        //Init navigation
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController  = navHostFragment.navController

   /*     //testBadge
        val badge = binding.navView.getOrCreateBadge(R.id.navigation_news)
        badge.isVisible = true
        // An icon only badge will be displayed unless a number is set:
        badge.number = 3*/

        binding.navView.setupWithNavController(navController)
    }
}