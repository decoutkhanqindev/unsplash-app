package com.example.unsplashapp.presentation.search.users

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.unsplashapp.core.base.BaseFragment
import com.example.unsplashapp.databinding.FragmentSearchUsersBinding
import com.example.unsplashapp.di.UnsplashServiceLocator
import com.example.unsplashapp.presentation.search.SearchViewModel
import com.example.unsplashapp.presentation.search.users.adapter.UserItemAdapter
import com.example.unsplashapp.presentation.search.users.model.UserItemModel

class SearchUsersFragment : BaseFragment<FragmentSearchUsersBinding>(
  inflate = FragmentSearchUsersBinding::inflate
) {
  companion object {
    fun newInstance(): SearchUsersFragment = SearchUsersFragment()
  }
  
  private val viewModel: SearchViewModel by activityViewModels<SearchViewModel>(factoryProducer = {
    viewModelFactory {
      addInitializer(SearchViewModel::class) {
        SearchViewModel(UnsplashServiceLocator.unsplashApiService)
      }
    }
  })
  
  private val userItemAdapter: UserItemAdapter by lazy {
    UserItemAdapter(Glide.with(this))
  }
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    
    setUpViews()
    bindViewModel()
  }
  
  private fun setUpViews() {
    binding.searchUsersRecyclerView.run {
      setHasFixedSize(true)
      layoutManager = LinearLayoutManager(context)
      adapter = userItemAdapter
    }
  }
  
  private fun bindViewModel() {
    viewModel.searchUsersLiveData.observe(viewLifecycleOwner) { items: List<UserItemModel> ->
      userItemAdapter.submitList(items)
    }
  }
}