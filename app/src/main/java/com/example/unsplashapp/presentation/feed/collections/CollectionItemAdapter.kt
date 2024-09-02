package com.example.unsplashapp.presentation.feed.collections

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.example.unsplashapp.databinding.ItemCollectionUiModelBinding
import com.example.unsplashapp.presentation.feed.collections.model.CollectionItemModel

class CollectionItemAdapter(
    private val requestManager: RequestManager
) : ListAdapter<CollectionItemModel, CollectionItemAdapter.VH>(CollectionItemCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH = VH(
        ItemCollectionUiModelBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: VH, position: Int): Unit = holder.bind(getItem(position))

    inner class VH(
        private val binding: ItemCollectionUiModelBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CollectionItemModel) {
            binding.run {
                requestManager.load(item.coverPhotoUrl).fitCenter().centerCrop()
                    .transition(withCrossFade()).into(imageCollection)

                textTitle.text = item.title
                textDescription.text = item.description
            }
        }
    }
}

private object CollectionItemCallBack : ItemCallback<CollectionItemModel>() {
    override fun areItemsTheSame(
        oldItem: CollectionItemModel, newItem: CollectionItemModel
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: CollectionItemModel, newItem: CollectionItemModel
    ): Boolean = oldItem == newItem
}