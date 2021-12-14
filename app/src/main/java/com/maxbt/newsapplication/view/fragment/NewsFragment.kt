package com.maxbt.newsapplication.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.maxbt.newsapplication.R
import com.maxbt.newsapplication.view.MainActivity
import com.maxbt.newsapplication.view.NewsViewModel

/**
 * This Fragment is showing preview news  for now
 */
class NewsFragment : Fragment(R.layout.fragment_news) {
    lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }

}