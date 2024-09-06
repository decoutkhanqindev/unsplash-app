package com.example.unsplashapp.presentation.feed.photos

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
import com.example.unsplashapp.databinding.FragmentFeedPhotosBinding
import com.example.unsplashapp.presentation.feed.photos.adapter.PhotoItemAdapter
import com.example.unsplashapp.presentation.feed.photos.state.PhotosUiState

class FeedPhotosFragment : BaseFragment<FragmentFeedPhotosBinding>(
    inflate = FragmentFeedPhotosBinding::inflate
) {
    companion object {
        fun newInstance(): FeedPhotosFragment = FeedPhotosFragment()
        private const val VISIBLE_THRESHOLD = 2 // -> 2 items is visible
    }

    private val viewModel: PhotosViewModel by viewModels<PhotosViewModel>(factoryProducer = {
        viewModelFactory {
            addInitializer(PhotosViewModel::class) {
                PhotosViewModel(UnsplashServiceLocator.unsplashApiService)
            }
        }
    })

    private val photoItemAdapter: PhotoItemAdapter by lazy(LazyThreadSafetyMode.NONE) {
        PhotoItemAdapter(requestManager = Glide.with(this))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViews()
        bindViewModel()
    }

    private fun setUpViews() {
        binding.photosRecyclerView.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = photoItemAdapter
        }
    }

    private fun bindViewModel() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState: PhotosUiState ->
            when (uiState) {
                PhotosUiState.FirstPageLoading -> {
                    binding.photosProgressCircular.isVisible = true
                    binding.photosButtonRetry.isVisible = false
                    photoItemAdapter.submitList(emptyList())
                }

                PhotosUiState.FirstPageError -> {
                    binding.photosProgressCircular.isVisible = false
                    binding.photosButtonRetry.isVisible = true
                    photoItemAdapter.submitList(emptyList())
                }

                is PhotosUiState.Content -> {
                    binding.photosProgressCircular.isVisible = false
                    binding.photosButtonRetry.isVisible = false
                    photoItemAdapter.submitList(uiState.items)
                }
            }
        }

        // config to load next page
        val linearLayoutManager: LinearLayoutManager =
            binding.photosRecyclerView.layoutManager as LinearLayoutManager
        binding.photosRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
        binding.photosRecyclerView.adapter = null
        super.onDestroyView()
    }
}