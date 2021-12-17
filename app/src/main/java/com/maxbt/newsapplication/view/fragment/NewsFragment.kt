package com.maxbt.newsapplication.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.maxbt.newsapplication.R
import com.maxbt.newsapplication.databinding.FragmentNewsBinding
import com.maxbt.newsapplication.model.Constants
import com.maxbt.newsapplication.model.adapter.NewsPreviewAdapter
import com.maxbt.newsapplication.model.entity.Category
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
        setNewsTabListener()

        viewModel.categories.observe(viewLifecycleOwner) { responseCategory ->
                when(responseCategory){
                    is Resource.Success -> {
                        responseCategory.data?.let { categories ->
                            categories.forEach { category ->
                                val tab = binding.tabLayout.newTab()
                                tab.tag = category.id
                                tab.text = category.name
                                tab.contentDescription = category.name
                                binding.tabLayout.addTab(tab)
                            }
                        }
                    }
                    is Resource.Error -> {
                        responseCategory.message?.let {
                            Log.e(Constants.NEWS_FRAGMENT_TAG, "An error occurred: $it")
                        }
                    }
                    is Resource.Loading -> {}
                }
        }
        viewModel.news.observe(viewLifecycleOwner) { responseNews ->
            when (responseNews) {
                is Resource.Success -> {
                    setLoadingGifVisibility(false)
                    binding.recyclerViewNews.visibility = View.VISIBLE
                    responseNews.data?.let{
                        newsAdapter.differ.submitList(it)
                    }
                }

                is Resource.Error -> {
                    setLoadingGifVisibility(false)

                    responseNews.message?.let {
                            Log.e(Constants.NEWS_FRAGMENT_TAG, "An error occurred: $it")
                    }
                }

                is Resource.Loading -> {
                    binding.recyclerViewNews.visibility = View.INVISIBLE
                    setLoadingGifVisibility(true)
                }
            }
        }

    }

    private fun setLoadingGifVisibility(isVisible : Boolean){
        binding.gifImageView.visibility = if(isVisible) View.VISIBLE else View.INVISIBLE
    }

    private fun setNewsTabListener(){
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val id =  tab?.tag?.toString()?.toLong() ?: Constants.DEFAULT_CATEGORY
                viewModel.getNewsByCategory(Category(
                    id = id,
                    name = tab?.text.toString(),
                    slug = tab?.text.toString(),
                    count = id.toInt()
                ))
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun setRecyclerView(){
        newsAdapter = NewsPreviewAdapter()
        binding.recyclerViewNews.apply{
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}