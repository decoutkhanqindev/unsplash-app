package com.example.unsplashapp.presentation.search.photos

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.unsplashapp.UnsplashServiceLocator
import com.example.unsplashapp.core.base.BaseFragment
import com.example.unsplashapp.databinding.FragmentSearchPhotosBinding
import com.example.unsplashapp.presentation.feed.photos.adapter.PhotoItemAdapter
import com.example.unsplashapp.presentation.feed.photos.model.PhotoItemModel
import com.example.unsplashapp.presentation.search.SearchViewModel

class SearchPhotosFragment : BaseFragment<FragmentSearchPhotosBinding>(
    inflate = FragmentSearchPhotosBinding::inflate
) {
    private val viewModel: SearchViewModel by viewModels<SearchViewModel>(factoryProducer = {
        viewModelFactory {
            addInitializer(SearchViewModel::class) {
                SearchViewModel(UnsplashServiceLocator.unsplashApiService)
            }
        }
    })

    private val photoItemAdapter: PhotoItemAdapter by lazy(LazyThreadSafetyMode.NONE) {
        PhotoItemAdapter(requestManager = Glide.with(this))
    }

    companion object {
        fun newInstance(): SearchPhotosFragment = SearchPhotosFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViews()
        bindViewModel()
    }

    private fun setUpViews(): Unit = binding.searchPhotosRecyclerView.run {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
        adapter = photoItemAdapter
    }

    private fun bindViewModel(): Unit =
        viewModel.searchPhotoLiveData.observe(viewLifecycleOwner) { items: List<PhotoItemModel> ->
            photoItemAdapter.submitList(items)
        }
}