package com.example.unsplashapp.presentation.feed.collections.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.unsplashapp.databinding.CollectionsItemBinding
import com.example.unsplashapp.presentation.feed.collections.model.CollectionItemModel

class CollectionsItemAdapter(
    private val requestManager: RequestManager, // -> handle glide module
    private val onItemClick: (CollectionItemModel) -> Unit
) : ListAdapter<CollectionItemModel, CollectionsItemAdapter.VH>(CollectionItemCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH = VH(
        CollectionsItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: VH, position: Int): Unit = holder.bind(getItem(position))

    inner class VH(
        private val binding: CollectionsItemBinding
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
                    .transition(DrawableTransitionOptions.withCrossFade()).into(imageCollection)

                textTitle.text = item.title
                textDescription.text = item.description
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
}