package com.maxbt.newsapplication.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.maxbt.newsapplication.R
import com.maxbt.newsapplication.view.MainActivity
import com.maxbt.newsapplication.view.NewsViewModel

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    private lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }
}