package com.example.unsplashapp.presentation.search

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.unsplashapp.presentation.search.photos.SearchPhotosFragment
import com.example.unsplashapp.presentation.search.users.SearchUsersFragment

class SearchViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
  override fun getItemCount(): Int = 2
  
  override fun createFragment(position: Int): Fragment = // -> always return a new obj fragment
    when (position) {
      0 -> SearchPhotosFragment.newInstance()
      1 -> SearchUsersFragment.newInstance()
      else -> throw IllegalArgumentException("Invalid position: $position")
    }
}