package com.example.unsplashapp.presentation.feed.photos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.unsplashapp.databinding.PhotoItemBinding
import com.example.unsplashapp.presentation.feed.photos.model.PhotoItemModel

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
          .transition(DrawableTransitionOptions.withCrossFade()).into(photoItemImage)
        
        photoItemDescription.text = item.description
        
        requestManager.load(item.user.profileImage.medium).fitCenter().centerCrop()
          .transition(DrawableTransitionOptions.withCrossFade()).into(photoItemUserImage)
        
        photoItemUserUsername.text = buildString {
          append("by ")
          append(item.user.username)
        }
      }
    }
  }
  
  private object PhotoItemCallBack : ItemCallback<PhotoItemModel>() {
    override fun areItemsTheSame(
      oldItem: PhotoItemModel, newItem: PhotoItemModel
    ): Boolean = oldItem.id == newItem.id
    
    override fun areContentsTheSame(
      oldItem: PhotoItemModel, newItem: PhotoItemModel
    ): Boolean = oldItem == newItem
  }
}