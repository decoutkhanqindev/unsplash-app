package com.example.unsplashapp.presentation.feed.photos

import com.example.unsplashapp.core.base.BaseFragment
import com.example.unsplashapp.databinding.FragmentFeedPhotosBinding

class FeedPhotosFragment : BaseFragment<FragmentFeedPhotosBinding>(
    inflate = FragmentFeedPhotosBinding::inflate
) {
    companion object {
        fun newInstance(): FeedPhotosFragment = FeedPhotosFragment()
    }
}