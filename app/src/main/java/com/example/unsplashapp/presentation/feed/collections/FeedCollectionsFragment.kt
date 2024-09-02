package com.example.unsplashapp.presentation.feed.collections

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.unsplashapp.UnsplashServiceLocator
import com.example.unsplashapp.core.base.BaseFragment
import com.example.unsplashapp.data.remote.UnsplashApiService
import com.example.unsplashapp.databinding.FragmentFeedCollectionsBinding

class FeedCollectionsFragment : BaseFragment<FragmentFeedCollectionsBinding>(
    inflate = FragmentFeedCollectionsBinding::inflate
) {
    companion object {
        fun newInstance(): FeedCollectionsFragment = FeedCollectionsFragment()
    }

    private val viewModel: CollectionsViewModel by viewModels(factoryProducer = {
        viewModelFactory {
            addInitializer(CollectionsViewModel::class) {
                CollectionsViewModel(unsplashApiService = UnsplashServiceLocator.unsplashApiService)
            }
        }
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViewModel()
    }

    private fun bindViewModel() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState: CollectionsUiState ->
            when (uiState) {
                CollectionsUiState.FirstPageLoading -> { // -> show loading
                    binding.progressCircular.isVisible = true
                    binding.buttonRetry.isVisible = false
                }

                CollectionsUiState.FirstPageError -> {  // -> show error
                    binding.progressCircular.isVisible = false
                    binding.buttonRetry.isVisible = true
                }

                is CollectionsUiState.Content -> { // -> show content
                    binding.progressCircular.isVisible = false
                    binding.buttonRetry.isVisible = false
                }
            }
        }
    }
}