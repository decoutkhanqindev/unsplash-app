package com.example.unsplashapp.presentation.feed.collections

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.unsplashapp.UnsplashServiceLocator
import com.example.unsplashapp.core.base.BaseFragment
import com.example.unsplashapp.databinding.FragmentFeedCollectionItemBinding
import com.example.unsplashapp.presentation.feed.collections.model.CollectionItemModel

class FeedCollectionItemFragment : BaseFragment<FragmentFeedCollectionItemBinding>(
    inflate = FragmentFeedCollectionItemBinding::inflate
) {
    private var item: CollectionItemModel? = null
    private val viewModel: CollectionsViewModel by viewModels<CollectionsViewModel>(factoryProducer = {
        viewModelFactory {
            addInitializer(CollectionsViewModel::class) {
                CollectionsViewModel(UnsplashServiceLocator.unsplashApiService)
            }
        }
    })

    companion object {
        fun newInstance(): FeedCollectionItemFragment = FeedCollectionItemFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        item = arguments?.getSerializable("item") as? CollectionItemModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroyView() {
        item = null
        super.onDestroyView()
    }
}