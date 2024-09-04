package com.example.unsplashapp.presentation.feed.photos

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
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
                    binding.progressCircular.isVisible = true
                    binding.buttonRetry.isVisible = false
                    photoItemAdapter.submitList(emptyList())
                }

                PhotosUiState.FirstPageError -> {
                    binding.progressCircular.isVisible = false
                    binding.buttonRetry.isVisible = true
                    photoItemAdapter.submitList(emptyList())
                }

                is PhotosUiState.Content -> {
                    binding.progressCircular.isVisible = false
                    binding.buttonRetry.isVisible = false
                    photoItemAdapter.submitList(uiState.items)
                }
            }
        }
    }

    override fun onDestroyView() {
        binding.photosRecyclerView.adapter = null
        super.onDestroyView()
    }
}