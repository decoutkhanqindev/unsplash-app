package com.example.unsplashapp.presentation.feed.collections

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.unsplashapp.core.base.BaseFragment
import com.example.unsplashapp.data.remote.response.CollectionItemResponse
import com.example.unsplashapp.databinding.FragmentFeedCollectionPreviewPhotosBinding
import com.example.unsplashapp.presentation.feed.collections.adapter.CollectionItemPreviewPhotoAdapter
import com.example.unsplashapp.presentation.feed.collections.model.CollectionItemModel
import com.example.unsplashapp.presentation.feed.collections.model.CollectionItemPreviewPhotoModel

class FeedCollectionPreviewPhotosFragment :
    BaseFragment<FragmentFeedCollectionPreviewPhotosBinding>(
        inflate = FragmentFeedCollectionPreviewPhotosBinding::inflate
    ) {

    private var item: CollectionItemModel? = null
    private val collectionItemPreviewPhotoAdapter: CollectionItemPreviewPhotoAdapter? by lazy {
        CollectionItemPreviewPhotoAdapter(Glide.with(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("DEPRECATION")
        item = arguments?.getSerializable("item") as? CollectionItemModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindModel()
        setUpViews()
    }

    // convert PreviewPhoto to CollectionItemPreviewPhotoModel
    private fun CollectionItemResponse.PreviewPhoto.toCollectionItemPreviewPhotoModel(): CollectionItemPreviewPhotoModel =
        CollectionItemPreviewPhotoModel(
            id = id, createdAt = createdAt, urls = urls
        )

    private fun bindModel() {
        binding.run {
            // map PreviewPhoto to CollectionItemPreviewPhotoModel
            val previewPhotos: List<CollectionItemPreviewPhotoModel>? =
                item?.previewPhotos?.map { it.toCollectionItemPreviewPhotoModel() }
            collectionItemPreviewPhotoAdapter!!.submitList(previewPhotos)
            collectionItemTitle.text = item?.title
            collectionItemDescription.text = item?.description
            collectionItemUsername.text = buildString {
                append("by ")
                append(item?.user?.username ?: "Unknown User")
            }
            Glide.with(requireParentFragment()).load(item?.user?.profileImage?.small).fitCenter()
                .centerCrop().into(collectionItemUserImg)
        }
    }

    private fun setUpViews(): Unit = binding.collectionItemPreviewPhotos.run {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
        adapter = collectionItemPreviewPhotoAdapter
    }

    override fun onDestroyView() {
        binding.collectionItemPreviewPhotos.adapter = null // -> avoid memory leak
        super.onDestroyView()
    }
}