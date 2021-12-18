package com.maxbt.newsapplication.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.maxbt.newsapplication.R
import com.maxbt.newsapplication.databinding.FragmentSearchBinding
import com.maxbt.newsapplication.model.Constants
import com.maxbt.newsapplication.view.MainActivity
import com.maxbt.newsapplication.view.NewsViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SearchFragment : Fragment(R.layout.fragment_search) {
    private lateinit var viewModel: NewsViewModel
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSearchBinding.inflate(
            inflater,
            container,
            false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }

    //Moved from onViewCreated to onResume to attach only once
    //(even when fragment changed , addText not firing on viewCreate)
    override fun onResume() {
        super.onResume()
        var searchQueryJob: Job? = null
        binding.searchInputText.addTextChangedListener { inputTextSearch ->
            searchQueryJob?.cancel()
            searchQueryJob = lifecycleScope.launch {
                delay(Constants.SEARCH_QUERY_DELAY_TIME)

                if (inputTextSearch?.isNotEmpty() == true) {
                    viewModel.searchQuery = inputTextSearch.toString()
                } else {
                    viewModel.searchQuery = ""
                }
                viewModel.searchNews()
            }
        }

        binding.searchInputText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
                searchQueryJob?.cancel()
            }
            true
        }
    }
}