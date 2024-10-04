package com.example.unsplashapp.presentation.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.unsplashapp.core.base.BaseFragment
import com.example.unsplashapp.data.remote.repository.UnsplashRepository
import com.example.unsplashapp.databinding.FragmentSearchBinding
import com.example.unsplashapp.presentation.search.photos.SearchPhotosFragment
import com.example.unsplashapp.presentation.search.users.SearchUsersFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// @AndroidEntryPoint is a Hilt annotation that you can use on Android classes
//that need to receive dependencies from Hilt.

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(
  inflate = FragmentSearchBinding::inflate
) {
  @Inject
  internal lateinit var repository: UnsplashRepository
  
  private val viewModel: SearchViewModel by activityViewModels()
  
//  // activityViewModels -> to save view model
//  private val viewModel: SearchViewModel by activityViewModels<SearchViewModel>(factoryProducer = {
//    viewModelFactory {
//      addInitializer(SearchViewModel::class) {
//        SearchViewModel(repository = UnsplashServiceLocator.provideUnsplashRepository())
//      }
//    }
//  })

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
  
  private class SearchViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2
    
    override fun createFragment(position: Int): Fragment = // -> always return a new obj fragment
      when (position) {
        0 -> SearchPhotosFragment.newInstance()
        1 -> SearchUsersFragment.newInstance()
        else -> throw IllegalArgumentException("Invalid position: $position")
      }
  }
}