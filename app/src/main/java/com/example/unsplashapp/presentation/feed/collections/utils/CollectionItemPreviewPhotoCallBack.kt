package com.example.unsplashapp.presentation.feed.collections.utils

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.example.unsplashapp.presentation.feed.collections.model.CollectionItemModel
import com.example.unsplashapp.presentation.feed.collections.model.CollectionItemPreviewPhotoModel

object CollectionItemPreviewPhotoCallBack : ItemCallback<CollectionItemPreviewPhotoModel>() {
    override fun areItemsTheSame(
        oldItem: CollectionItemPreviewPhotoModel, newItem: CollectionItemPreviewPhotoModel
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: CollectionItemPreviewPhotoModel, newItem: CollectionItemPreviewPhotoModel
    ): Boolean = oldItem == newItem
}