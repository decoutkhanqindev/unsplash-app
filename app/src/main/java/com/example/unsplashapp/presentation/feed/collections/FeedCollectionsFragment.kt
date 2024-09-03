package com.example.unsplashapp.presentation.feed.collections

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unsplashapp.UnsplashServiceLocator
import com.example.unsplashapp.core.base.BaseFragment
import com.example.unsplashapp.databinding.FragmentFeedCollectionsBinding
import com.example.unsplashapp.presentation.feed.collections.adapter.CollectionsItemAdapter
import com.example.unsplashapp.presentation.feed.collections.model.CollectionItemModel
import com.example.unsplashapp.presentation.feed.collections.state.CollectionsUiState

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

    private val collectionsItemAdapter: CollectionsItemAdapter by lazy {
        CollectionsItemAdapter(
            requestManager = Glide.with(this), onItemClick = ::onItemClick
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViews()
        bindViewModel()
        handleBackStackToDisplayUi(binding.collectionsRecyclerView)
    }

    private fun setUpViews() {
        binding.collectionsRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = collectionsItemAdapter
        }
    }

    private fun bindViewModel() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState: CollectionsUiState ->
            when (uiState) {
                CollectionsUiState.FirstPageLoading -> { // -> show loading
                    binding.progressCircular.isVisible = true
                    binding.buttonRetry.isVisible = false
                    collectionsItemAdapter.submitList(emptyList())
                }

                CollectionsUiState.FirstPageError -> {  // -> show error
                    binding.progressCircular.isVisible = false
                    binding.buttonRetry.isVisible = true
                    collectionsItemAdapter.submitList(emptyList())
                }

                is CollectionsUiState.Content -> { // -> show content
                    binding.progressCircular.isVisible = false
                    binding.buttonRetry.isVisible = false
                    collectionsItemAdapter.submitList(uiState.items)
                }
            }
        }

        // config to load next page
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

    private fun onItemClick(item: CollectionItemModel) {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            addToBackStack(null)
            replace<FeedCollectionPreviewPhotosFragment>(containerViewId = binding.collectionItemPreviewPhoto.id,
                tag = FeedCollectionPreviewPhotosFragment::class.simpleName,
                args = Bundle().apply { putSerializable("item", item) })
        }
    }

    private fun handleBackStackToDisplayUi(view: View) {
        parentFragmentManager.addOnBackStackChangedListener {
            if (parentFragmentManager.backStackEntryCount == 0) {
                view.visibility = View.VISIBLE
            } else {
                view.visibility = View.GONE
            }
        }
    }
}