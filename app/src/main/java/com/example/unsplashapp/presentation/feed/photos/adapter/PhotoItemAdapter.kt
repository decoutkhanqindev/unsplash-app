package com.example.unsplashapp.presentation.feed.photos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.unsplashapp.databinding.PhotoItemBinding
import com.example.unsplashapp.presentation.feed.photos.model.PhotoItemModel
import com.example.unsplashapp.presentation.feed.photos.utils.PhotoItemCallBack

class PhotoItemAdapter(
    private val requestManager: RequestManager
) : ListAdapter<PhotoItemModel, PhotoItemAdapter.VH>(PhotoItemCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH = VH(
        PhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: VH, position: Int): Unit = holder.bind(getItem(position))

    inner class VH(private val binding: PhotoItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PhotoItemModel) {
            binding.run {
                requestManager.load(item.urls.regular).fitCenter().centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade()).into(imagePhotoItem)
                descriptionPhotoItem.text = item.description
            }
        }
    }
}