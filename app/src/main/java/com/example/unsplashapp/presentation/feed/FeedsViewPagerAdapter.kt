package com.example.unsplashapp.presentation.feed

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.unsplashapp.presentation.feed.collections.FeedCollectionsFragment
import com.example.unsplashapp.presentation.feed.photos.FeedPhotosFragment

class FeedsViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = // -> always return a new obj fragment
        when (position) {
            0 -> FeedCollectionsFragment.newInstance()
            1 -> FeedPhotosFragment.newInstance()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
}