package com.example.unsplashapp.presentation.feed.collections

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.unsplashapp.core.base.BaseFragment
import com.example.unsplashapp.data.remote.response.CollectionItemResponse
import com.example.unsplashapp.databinding.FragmentFeedCollectionItemPreviewPhotosBinding
import com.example.unsplashapp.presentation.feed.collections.adapter.CollectionItemPreviewPhotoAdapter
import com.example.unsplashapp.presentation.feed.collections.model.CollectionItemModel
import com.example.unsplashapp.presentation.feed.collections.model.CollectionItemPreviewPhotoModel

class FeedCollectionPreviewPhotosFragment :
    BaseFragment<FragmentFeedCollectionItemPreviewPhotosBinding>(
        inflate = FragmentFeedCollectionItemPreviewPhotosBinding::inflate
    ) {

    private var item: CollectionItemModel? = null
    private val collectionItemPreviewPhotoAdapter: CollectionItemPreviewPhotoAdapter? by lazy {
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

    // convert PreviewPhoto to CollectionItemPreviewPhotoModel
    private fun CollectionItemResponse.PreviewPhoto.toCollectionItemPreviewPhotoModel(): CollectionItemPreviewPhotoModel =
        CollectionItemPreviewPhotoModel(
            id = id, createdAt = createdAt, urls = urls
        )

    private fun bindModel() {
        binding.run {
            // map PreviewPhoto to CollectionItemPreviewPhotoModel
            val previewPhotoItems: List<CollectionItemPreviewPhotoModel>? =
                item?.previewPhotos?.map { it.toCollectionItemPreviewPhotoModel() }
            collectionItemPreviewPhotoAdapter!!.submitList(previewPhotoItems)
            collectionItemPreviewPhotosTitle.text = item?.title
            collectionItemPreviewPhotosDescription.text = item?.description
            collectionItemPreviewPhotosUserUsername.text = buildString {
                append("by ")
                append(item?.user?.username ?: "Unknown User")
            }
            Glide.with(requireParentFragment()).load(item?.user?.profileImage?.small).fitCenter()
                .centerCrop().into(collectionItemPreviewPhotosUserImage)
        }
    }

    private fun setUpViews(): Unit = binding.collectionItemPreviewPhotosRecyclerView.run {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
        adapter = collectionItemPreviewPhotoAdapter
    }

    override fun onDestroyView() {
        binding.collectionItemPreviewPhotosRecyclerView.adapter = null // -> avoid memory leak
        super.onDestroyView()
    }
}