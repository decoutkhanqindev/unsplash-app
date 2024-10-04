package com.example.unsplashapp.presentation.search.users

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.unsplashapp.core.base.BaseFragment
import com.example.unsplashapp.databinding.FragmentSearchUsersBinding
import com.example.unsplashapp.presentation.search.SearchViewModel
import com.example.unsplashapp.presentation.search.users.adapter.UserItemAdapter
import com.example.unsplashapp.presentation.search.users.model.UserItemModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.Disposable

// @AndroidEntryPoint is a Hilt annotation that you can use on Android classes
//that need to receive dependencies from Hilt.

@AndroidEntryPoint
class SearchUsersFragment : BaseFragment<FragmentSearchUsersBinding>(
  inflate = FragmentSearchUsersBinding::inflate
) {
  
  companion object {
    fun newInstance(): SearchUsersFragment = SearchUsersFragment()
  }
  
  private lateinit var disposable: Disposable
  
  private val viewModel: SearchViewModel by activityViewModels()
  
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
    disposable = viewModel.searchUsersSubject.subscribe { items: List<UserItemModel> ->
      userItemAdapter.submitList(items)
    }
  }
  
  override fun onDestroyView() {
    disposable.dispose()
    super.onDestroyView()
  }
}