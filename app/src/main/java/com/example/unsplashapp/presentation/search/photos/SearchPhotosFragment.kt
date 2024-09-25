package com.example.unsplashapp.presentation.search.photos

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.unsplashapp.core.base.BaseFragment
import com.example.unsplashapp.databinding.FragmentSearchPhotosBinding
import com.example.unsplashapp.di.UnsplashServiceLocator
import com.example.unsplashapp.presentation.feed.photos.adapter.PhotoItemAdapter
import com.example.unsplashapp.presentation.feed.photos.model.PhotoItemModel
import com.example.unsplashapp.presentation.search.SearchViewModel

class SearchPhotosFragment : BaseFragment<FragmentSearchPhotosBinding>(
  inflate = FragmentSearchPhotosBinding::inflate
) {
  companion object {
    fun newInstance(): SearchPhotosFragment = SearchPhotosFragment()
  }
  
  private val viewModel: SearchViewModel by activityViewModels<SearchViewModel>(factoryProducer = {
    viewModelFactory {
      addInitializer(SearchViewModel::class) {
        SearchViewModel(repository = UnsplashServiceLocator.provideUnsplashRepository())
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
    binding.searchPhotosRecyclerView.run {
      setHasFixedSize(true)
      layoutManager = LinearLayoutManager(context)
      adapter = photoItemAdapter
    }
  }
  
  private fun bindViewModel() {
    viewModel.searchPhotosLiveData.observe(viewLifecycleOwner) { items: List<PhotoItemModel> ->
      photoItemAdapter.submitList(items)
    }
  }
}