package com.example.unsplashapp.presentation.feed.collections

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unsplashapp.UnsplashServiceLocator
import com.example.unsplashapp.core.base.BaseFragment
import com.example.unsplashapp.data.remote.UnsplashApiService
import com.example.unsplashapp.databinding.FragmentFeedCollectionsBinding
import com.example.unsplashapp.presentation.feed.FeedsViewModel
import com.example.unsplashapp.presentation.feed.collections.adapter.CollectionItemAdapter
import com.example.unsplashapp.presentation.feed.collections.model.CollectionItemModel
import com.example.unsplashapp.presentation.feed.collections.model.CollectionItemModel.Companion.toCollectionItemModel
import com.example.unsplashapp.presentation.feed.state.FeedsUiState

class FeedCollectionsFragment : BaseFragment<FragmentFeedCollectionsBinding>(
  inflate = FragmentFeedCollectionsBinding::inflate
) {
  companion object {
    fun newInstance(): FeedCollectionsFragment = FeedCollectionsFragment()
    private const val VISIBLE_THRESHOLD = 2 // -> 2 items is visible
  }
  
  private val unsplashApiService: UnsplashApiService = UnsplashServiceLocator.unsplashApiService
  
  private val viewModel: FeedsViewModel<CollectionItemModel> by viewModels(factoryProducer = {
    viewModelFactory {
      addInitializer(FeedsViewModel::class) {
        FeedsViewModel(getItems = { page: Int, perPage: Int ->
          unsplashApiService.getCollections(page, perPage).map { it.toCollectionItemModel() }
        })
      }
    }
  })
  
  private val collectionItemAdapter: CollectionItemAdapter by lazy {
    CollectionItemAdapter(
      requestManager = Glide.with(this), onItemClick = ::onItemClick
    )
  }
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    
    setUpViews()
    bindViewModel()
    handleBackStackToDisplayUi(binding.collectionsRecyclerView)
    handleBackStackForChildFragment()
  }
  
  private fun setUpViews() {
    binding.collectionsRecyclerView.apply {
      setHasFixedSize(true)
      layoutManager = LinearLayoutManager(context)
      adapter = collectionItemAdapter
    }
  }
  
  private fun bindViewModel() {
    viewModel.collectionsUiState.observe(viewLifecycleOwner) { collectionsUiState: FeedsUiState<CollectionItemModel> ->
      when (collectionsUiState) {
        FeedsUiState.FirstPageLoading -> { // -> show loading
          binding.collectionsProgressCircular.isVisible = true
          binding.collectionsButtonRetry.isVisible = false
          collectionItemAdapter.submitList(emptyList())
        }
        
        FeedsUiState.FirstPageError -> {  // -> show error
          binding.collectionsProgressCircular.isVisible = false
          binding.collectionsButtonRetry.isVisible = true
          collectionItemAdapter.submitList(emptyList())
        }
        
        is FeedsUiState.Content -> { // -> show content
          binding.collectionsProgressCircular.isVisible = false
          binding.collectionsButtonRetry.isVisible = false
          collectionItemAdapter.submitList(collectionsUiState.items)
        }
      }
    }
    
    // config to load next page
    val linearLayoutManager: LinearLayoutManager =
      binding.collectionsRecyclerView.layoutManager as LinearLayoutManager
    binding.collectionsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
      // OnScrollListener designed to detect when the user scrolls near the bottom of the RecyclerView and potentially trigger an action like loading more data
      override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if ((dy > 0) && ((linearLayoutManager.findLastVisibleItemPosition() + VISIBLE_THRESHOLD) >= linearLayoutManager.itemCount)) {
          // If there are 2 more elements from the last element, you can call loadNextPage()
          viewModel.loadNextPage()
        }
      }
    })
  }
  
  private fun onItemClick(item: CollectionItemModel) {
    childFragmentManager.commit {
      setReorderingAllowed(true)
      addToBackStack(null)
      replace<FeedCollectionPreviewPhotosFragment>(containerViewId = binding.collectionPreviewPhotosFragment.id,
        tag = FeedCollectionPreviewPhotosFragment::class.simpleName,
        args = Bundle().apply { putSerializable("item", item) })
    }
  }
  
  private fun handleBackStackToDisplayUi(view: View) {
    childFragmentManager.addOnBackStackChangedListener {
      if (childFragmentManager.backStackEntryCount == 0) {
        view.visibility = View.VISIBLE
      } else {
        view.visibility = View.GONE
      }
    }
  }
  
  private fun handleBackStackForChildFragment() {
    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
      if (childFragmentManager.backStackEntryCount > 0) {
        childFragmentManager.popBackStack() // Pop child fragment back stack
      } else {
        requireActivity().finish() // or super.onBackPressed() if in an activity
      }
    }
  }
  
  override fun onDestroyView() {
    binding.collectionsRecyclerView.adapter = null // -> avoid memory leak
    super.onDestroyView()
  }
}