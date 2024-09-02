package com.example.unsplashapp.presentation.feed.collections

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.unsplashapp.core.base.BaseFragment
import com.example.unsplashapp.databinding.FragmentFeedCollectionsBinding

class FeedCollectionsFragment : BaseFragment<FragmentFeedCollectionsBinding>(
    inflate = FragmentFeedCollectionsBinding::inflate
) {
    companion object {
        fun newInstance(): FeedCollectionsFragment = FeedCollectionsFragment()
    }

    private val viewModel: CollectionsViewModel by viewModels<CollectionsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel
    }
}