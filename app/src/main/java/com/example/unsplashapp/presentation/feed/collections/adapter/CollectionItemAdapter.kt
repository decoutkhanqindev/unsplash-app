package com.example.unsplashapp.presentation.feed.collections.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.unsplashapp.databinding.CollectionItemBinding
import com.example.unsplashapp.presentation.feed.collections.model.CollectionItemModel

class CollectionItemAdapter(
    private val requestManager: RequestManager, // -> handle glide module
    private val onItemClick: (CollectionItemModel) -> Unit
) : ListAdapter<CollectionItemModel, CollectionItemAdapter.VH>(CollectionItemCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH = VH(
        CollectionItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: VH, position: Int): Unit = holder.bind(getItem(position))

    inner class VH(
        private val binding: CollectionItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position: Int = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(getItem(position))
                }
            }
        }

        fun bind(item: CollectionItemModel) {
            binding.run {
                requestManager.load(item.coverPhotoUrl).fitCenter().centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade()).into(collectionItemImage)

                collectionItemTitle.text = item.title
                collectionItemDescription.text = item.description
            }
        }
    }
}