package com.maxbt.newsapplication.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.maxbt.newsapplication.R
import com.maxbt.newsapplication.databinding.ActivityMainBinding
import com.maxbt.newsapplication.model.Constants
import com.maxbt.newsapplication.model.db.NewsDatabase
import com.maxbt.newsapplication.model.repository.NewsRepository


/**
 * This is MainClass , where we instantiate our view model ,
 * our repos and everything else to get done before setting view
 */

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: NewsViewModel

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageViewWeb.setOnClickListener {
            openUrl(Constants.SITE_URL)
        }
        binding.imageViewFacebook.setOnClickListener {
            openUrl(Constants.FACEBOOK_URL)
        }

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

    //Function made to hide keyboard when input is somewhere above it
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

        return super.dispatchTouchEvent(ev)
    }

    private fun openUrl(address : String){
        val webIntent = Intent(Intent.ACTION_VIEW, address.toUri())
        startActivity(webIntent)
    }

}