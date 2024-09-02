package com.example.unsplashapp.presentation.feed.collections

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashapp.databinding.ItemCollectionUiModelBinding

class CollectionItemAdapter(diffCallback: CollectionItemCallBack) :
    ListAdapter<CollectionItemModel, CollectionItemAdapter.VH>(diffCallback) {
    private val items: List<CollectionItemModel> = emptyList()

    inner class VH(
        private val binding: ItemCollectionUiModelBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind() {

        }
    }

    private object CollectionItemCallBack : ItemCallback<CollectionItemModel>() {
        override fun areContentsTheSame(
            oldItem: CollectionItemModel, newItem: CollectionItemModel
        ): Boolean = oldItem == newItem

        override fun areItemsTheSame(
            oldItem: CollectionItemModel, newItem: CollectionItemModel
        ): Boolean = oldItem.id == newItem.id
    }
}