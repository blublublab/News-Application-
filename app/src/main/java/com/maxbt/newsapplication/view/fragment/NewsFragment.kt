package com.maxbt.newsapplication.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.maxbt.newsapplication.R
import com.maxbt.newsapplication.databinding.FragmentNewsBinding
import com.maxbt.newsapplication.model.adapter.NewsPreviewAdapter
import com.maxbt.newsapplication.util.Resource
import com.maxbt.newsapplication.view.MainActivity
import com.maxbt.newsapplication.view.NewsViewModel

/**
 * This Fragment is showing preview news  for now
 */
class NewsFragment : Fragment(R.layout.fragment_news) {
    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter : NewsPreviewAdapter
    private lateinit var binding : FragmentNewsBinding
    val TAG = "BREAKING_NEWS_FRAGMENT"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setRecyclerView()
        viewModel.news.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    setLoadingGifVisibility(false)

                    response.data?.let{
                        newsAdapter.differ.submitList(it)
                    }
                }

                    is Resource.Error -> {
                    setLoadingGifVisibility(false)

                    response.message?.let {
                            Log.e(TAG, "An error occurred: $it")
                    }
                }

                is Resource.Loading -> setLoadingGifVisibility(true)
            }
        }
    }

    private fun setLoadingGifVisibility(isVisible : Boolean){
        binding.gifImageView.visibility = if(isVisible) View.VISIBLE else View.INVISIBLE
    }

    private fun setRecyclerView(){
        newsAdapter = NewsPreviewAdapter()
        binding.recyclerViewNews.apply{
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}