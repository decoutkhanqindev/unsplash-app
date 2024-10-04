package com.example.unsplashapp.presentation.search.photos

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.unsplashapp.core.base.BaseFragment
import com.example.unsplashapp.data.remote.repository.UnsplashRepository
import com.example.unsplashapp.databinding.FragmentSearchPhotosBinding
import com.example.unsplashapp.presentation.feed.photos.adapter.PhotoItemAdapter
import com.example.unsplashapp.presentation.feed.photos.model.PhotoItemModel
import com.example.unsplashapp.presentation.search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

// @AndroidEntryPoint is a Hilt annotation that you can use on Android classes
//that need to receive dependencies from Hilt.

@AndroidEntryPoint
class SearchPhotosFragment : BaseFragment<FragmentSearchPhotosBinding>(
  inflate = FragmentSearchPhotosBinding::inflate
) {
  companion object {
    fun newInstance(): SearchPhotosFragment = SearchPhotosFragment()
  }
  
  private lateinit var disposable: Disposable
  
  @Inject
  internal lateinit var repository: UnsplashRepository
  
  private val viewModel: SearchViewModel by activityViewModels()
  
//  private val viewModel: SearchViewModel by activityViewModels<SearchViewModel>(factoryProducer = {
//    viewModelFactory {
//      addInitializer(SearchViewModel::class) {
//        SearchViewModel(repository = UnsplashServiceLocator.provideUnsplashRepository())
//      }
//    }
//  })
  
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
//    viewModel.searchPhotosLiveData.observe(viewLifecycleOwner) { items: List<PhotoItemModel> ->
//      photoItemAdapter.submitList(items)
//    }
    
    disposable = viewModel.searchPhotosSubject.subscribe {items: List<PhotoItemModel> ->
      photoItemAdapter.submitList(items)
    }
  }
  
  override fun onDestroyView() {
    super.onDestroyView()
    disposable.dispose()
  }
}