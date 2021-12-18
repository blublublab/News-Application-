package com.maxbt.newsapplication.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.maxbt.newsapplication.R
import com.maxbt.newsapplication.databinding.FragmentArticleBinding
import com.maxbt.newsapplication.view.MainActivity
import com.maxbt.newsapplication.view.NewsViewModel

class ArticleFragment : Fragment(R.layout.fragment_article){
    private lateinit var viewModel: NewsViewModel
    private val args : ArticleFragmentArgs by navArgs()
    private lateinit var binding: FragmentArticleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        val article = args.article
        val htmlData = article.content.content
        binding.displayWebView.apply {
           webViewClient = WebViewClient()
           settings.javaScriptEnabled = true
           loadData(htmlData, "text/html", "utf-8")
        }
    }
}
