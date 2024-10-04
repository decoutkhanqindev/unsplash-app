package com.example.unsplashapp.presentation.feed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.unsplashapp.R
import com.example.unsplashapp.core.base.BaseFragment
import com.example.unsplashapp.databinding.FragmentFeedsBinding
import com.example.unsplashapp.presentation.feed.collections.FeedCollectionsFragment
import com.example.unsplashapp.presentation.feed.photos.FeedPhotosFragment
import com.example.unsplashapp.presentation.search.SearchFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

// @AndroidEntryPoint is a Hilt annotation that you can use on Android classes
//that need to receive dependencies from Hilt.

@AndroidEntryPoint
class FeedsFragment : BaseFragment<FragmentFeedsBinding>(
  inflate = FragmentFeedsBinding::inflate
) {
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    
    binding.searchButton.setOnClickListener {
      parentFragmentManager.commit {
        setReorderingAllowed(true)
        addToBackStack(null)
        replace<SearchFragment>(
          containerViewId = R.id.main, tag = SearchFragment::class.simpleName
        )
      }
    }
    
    setUpViewPager()
  }
  
  private fun setUpViewPager() {
    binding.feedsViewPager.run {
      adapter = FeedsViewPagerAdapter(fragment = this@FeedsFragment)
      TabLayoutMediator(binding.feedsTabsLayout, this) { tab: TabLayout.Tab, position: Int ->
        tab.text = when (position) {
          0 -> "Collections"
          1 -> "Photos"
          else -> throw IllegalArgumentException("Invalid position: $position")
        }
      }
    }.attach() // -> attach FeedCollectionsFragment and FeedPhotosFragment together
  }
  
  private class FeedsViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2
    
    override fun createFragment(position: Int): Fragment = // -> always return a new obj fragment
      when (position) {
        0 -> FeedCollectionsFragment.newInstance()
        1 -> FeedPhotosFragment.newInstance()
        else -> throw IllegalArgumentException("Invalid position: $position")
      }
  }
}

