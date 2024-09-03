package com.example.unsplashapp.presentation.feed.collections

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.unsplashapp.core.base.BaseFragment
import com.example.unsplashapp.data.remote.response.CollectionItemResponse
import com.example.unsplashapp.databinding.FragmentFeedCollectionItemPreviewPhotoBinding
import com.example.unsplashapp.presentation.feed.collections.adapter.CollectionItemPreviewPhotoAdapter
import com.example.unsplashapp.presentation.feed.collections.model.CollectionItemModel
import com.example.unsplashapp.presentation.feed.collections.model.CollectionItemPreviewPhotoModel

class FeedCollectionPreviewPhotosFragment :
    BaseFragment<FragmentFeedCollectionItemPreviewPhotoBinding>(
        inflate = FragmentFeedCollectionItemPreviewPhotoBinding::inflate
    ) {

    private var item: CollectionItemModel? = null
    private val collectionItemPreviewPhotoAdapter: CollectionItemPreviewPhotoAdapter by lazy {
        CollectionItemPreviewPhotoAdapter(Glide.with(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        item = arguments?.getSerializable("item") as? CollectionItemModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindModel()
        setUpViews()
    }

    private fun CollectionItemResponse.PreviewPhoto.toCollectionItemPreviewPhotoModel(): CollectionItemPreviewPhotoModel =
        CollectionItemPreviewPhotoModel(
            id = id, createdAt = createdAt, urls = urls
        )

    private fun bindModel() {
        binding.run {
            collectionItemTitle.text = item?.title
            collectionItemDescription.text = item?.description ?: "No description..."
            val previewPhotos: List<CollectionItemPreviewPhotoModel>? =
                item?.previewPhotos?.map { it.toCollectionItemPreviewPhotoModel() }
            collectionItemPreviewPhotoAdapter.submitList(previewPhotos)
        }
    }

    private fun setUpViews(): Unit = binding.collectionItemPreviewPhotos.run {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
        adapter = collectionItemPreviewPhotoAdapter
    }

    override fun onDestroyView() {
        item = null
        super.onDestroyView()
    }
}