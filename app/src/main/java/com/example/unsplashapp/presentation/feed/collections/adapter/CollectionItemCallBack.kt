package com.example.unsplashapp.presentation.feed.collections.adapter

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.example.unsplashapp.presentation.feed.collections.model.CollectionItemModel

object CollectionItemCallBack : ItemCallback<CollectionItemModel>() {
    override fun areItemsTheSame(
        oldItem: CollectionItemModel, newItem: CollectionItemModel
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: CollectionItemModel, newItem: CollectionItemModel
    ): Boolean = oldItem == newItem
}