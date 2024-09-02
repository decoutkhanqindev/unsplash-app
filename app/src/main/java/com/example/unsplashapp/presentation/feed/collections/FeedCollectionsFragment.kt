package com.example.unsplashapp.presentation.feed.collections

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unsplashapp.UnsplashServiceLocator
import com.example.unsplashapp.core.base.BaseFragment
import com.example.unsplashapp.databinding.FragmentFeedCollectionsBinding

class FeedCollectionsFragment : BaseFragment<FragmentFeedCollectionsBinding>(
    inflate = FragmentFeedCollectionsBinding::inflate
) {
    companion object {
        fun newInstance(): FeedCollectionsFragment = FeedCollectionsFragment()
        private const val VISIBLE_THRESHOLD = 2 // -> 2 items is visible
    }

    private val viewModel: CollectionsViewModel by viewModels(factoryProducer = {
        viewModelFactory {
            addInitializer(CollectionsViewModel::class) {
                CollectionsViewModel(unsplashApiService = UnsplashServiceLocator.unsplashApiService)
            }
        }
    })

    private val collectionItemAdapter: CollectionItemAdapter by lazy {
        CollectionItemAdapter(requestManager = Glide.with(this))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViews()
        bindViewModel()
    }

    private fun setUpViews() {
        binding.collectionsRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = collectionItemAdapter
        }
    }

    private fun bindViewModel() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState: CollectionsUiState ->
            when (uiState) {
                CollectionsUiState.FirstPageLoading -> { // -> show loading
                    binding.progressCircular.isVisible = true
                    binding.buttonRetry.isVisible = false
                    collectionItemAdapter.submitList(emptyList())
                }

                CollectionsUiState.FirstPageError -> {  // -> show error
                    binding.progressCircular.isVisible = false
                    binding.buttonRetry.isVisible = true
                    collectionItemAdapter.submitList(emptyList())
                }

                is CollectionsUiState.Content -> { // -> show content
                    binding.progressCircular.isVisible = false
                    binding.buttonRetry.isVisible = false
                    collectionItemAdapter.submitList(uiState.items)
                }
            }
        }

        val linearLayoutManager: LinearLayoutManager =
            binding.collectionsRecyclerView.layoutManager as LinearLayoutManager
        binding.collectionsRecyclerView.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            // OnScrollListener designed to detect when the user scrolls near the bottom of the RecyclerView and potentially trigger an action like loading more data
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if ((dy > 0) && ((linearLayoutManager.findLastVisibleItemPosition() + VISIBLE_THRESHOLD) >= linearLayoutManager.itemCount)) {
                    // If there are 2 more elements from the last element, you can call loadNextPage()
                    viewModel.loadNextPage()
                }
            }
        })
    }

    override fun onDestroyView() {
        binding.collectionsRecyclerView.adapter = null // -> avoid memory leak
        super.onDestroyView()
    }
}