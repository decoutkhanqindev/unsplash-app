package com.example.unsplashapp.presentation.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.unsplashapp.UnsplashServiceLocator
import com.example.unsplashapp.core.base.BaseFragment
import com.example.unsplashapp.databinding.FragmentSearchBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class SearchFragment : BaseFragment<FragmentSearchBinding>(
  inflate = FragmentSearchBinding::inflate
) {
  // activityViewModels -> to save view model
  private val viewModel: SearchViewModel by activityViewModels<SearchViewModel>(factoryProducer = {
    viewModelFactory {
      addInitializer(SearchViewModel::class) {
        SearchViewModel(unsplashApiService = UnsplashServiceLocator.unsplashApiService)
      }
    }
  })
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    
    setUpViews()
    setUpViewPager()
    setUpTextChanges()
  }
  
  private fun setUpViews() {
    // handle to back stack
    binding.searchToolbar.setNavigationOnClickListener {// -> define the action to be
      // performed when the navigation icon -> back btn in the toolbar is clicked.
      parentFragmentManager.popBackStack()
    }
  }
  
  private fun setUpViewPager() {
    binding.searchViewPager.run {
      adapter = SearchViewPagerAdapter(this@SearchFragment)
      TabLayoutMediator(
        binding.searchTabsLayout, this
      ) { tab: TabLayout.Tab, position: Int ->
        tab.text = when (position) {
          0 -> "Photos"
          1 -> "Users"
          else -> throw IllegalArgumentException("Invalid position: $position")
        }
      }
    }.attach()
  }
  
  // handle text changes to query api
  private fun setUpTextChanges() {
    binding.searchTextInputEditText.addTextChangedListener(object : TextWatcher {
      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
      
      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
      
      override fun afterTextChanged(s: Editable?) {
        viewModel.setQuery(s.toString())
      }
    })
  }
}