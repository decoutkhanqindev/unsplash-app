package com.example.unsplashapp.presentation.feed.photos

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unsplashapp.core.base.BaseFragment
import com.example.unsplashapp.data.remote.repository.UnsplashRepository
import com.example.unsplashapp.databinding.FragmentFeedPhotosBinding
import com.example.unsplashapp.presentation.feed.FeedsViewModel
import com.example.unsplashapp.presentation.feed.photos.adapter.PhotoItemAdapter
import com.example.unsplashapp.presentation.feed.photos.model.PhotoItemModel
import com.example.unsplashapp.presentation.feed.state.FeedsUiState
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

// @AndroidEntryPoint is a Hilt annotation that you can use on Android classes
//that need to receive dependencies from Hilt.

@AndroidEntryPoint
class FeedPhotosFragment : BaseFragment<FragmentFeedPhotosBinding>(
  inflate = FragmentFeedPhotosBinding::inflate
) {

  companion object {
    fun newInstance(): FeedPhotosFragment = FeedPhotosFragment()
    private const val VISIBLE_THRESHOLD = 2 // -> 2 items is visible
  }
  
  private lateinit var disposable: Disposable
  
  @Inject
  internal lateinit var repository: UnsplashRepository
  
  private val viewModel: FeedsViewModel<PhotoItemModel> by viewModels(factoryProducer = {
    viewModelFactory {
      addInitializer(FeedsViewModel::class) {
        FeedsViewModel(getItems = { page: Int, perPage: Int ->
          repository.getPhotos(page, perPage)
        })
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
//    viewModel.feedsUiSubject.observe(viewLifecycleOwner) { photosUiState: FeedsUiState<PhotoItemModel> ->
//      when (photosUiState) {
//        FeedsUiState.FirstPageLoading -> {
//          binding.photosProgressCircular.isVisible = true
//          binding.photosButtonRetry.isVisible = false
//          photoItemAdapter.submitList(emptyList())
//        }
//
//        FeedsUiState.FirstPageError -> {
//          binding.photosProgressCircular.isVisible = false
//          binding.photosButtonRetry.isVisible = true
//          photoItemAdapter.submitList(emptyList())
//        }
//
//        is FeedsUiState.Content -> {
//          binding.photosProgressCircular.isVisible = false
//          binding.photosButtonRetry.isVisible = false
//          photoItemAdapter.submitList(photosUiState.items)
//        }
//      }
//    }
    
    disposable =
      viewModel.feedsUiSubject.subscribe { photosUiState: FeedsUiState<PhotoItemModel> ->
      when (photosUiState) {
        FeedsUiState.FirstPageLoading -> {
          binding.photosProgressCircular.isVisible = true
          binding.photosButtonRetry.isVisible = false
          photoItemAdapter.submitList(emptyList())
        }

        FeedsUiState.FirstPageError -> {
          binding.photosProgressCircular.isVisible = false
          binding.photosButtonRetry.isVisible = true
          photoItemAdapter.submitList(emptyList())
        }

        is FeedsUiState.Content -> {
          binding.photosProgressCircular.isVisible = false
          binding.photosButtonRetry.isVisible = false
          photoItemAdapter.submitList(photosUiState.items)
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
    disposable.dispose()
    super.onDestroyView()
  }
}