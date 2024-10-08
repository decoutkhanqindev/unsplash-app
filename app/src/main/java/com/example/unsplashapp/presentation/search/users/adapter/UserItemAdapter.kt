package com.example.unsplashapp.presentation.search.users.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.unsplashapp.databinding.UserItemBinding
import com.example.unsplashapp.presentation.search.users.model.UserItemModel

class UserItemAdapter(
  private val requestManager: RequestManager
) : ListAdapter<UserItemModel, UserItemAdapter.VH>(UserItemCallBack) {
  
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH = VH(
    UserItemBinding.inflate(
      LayoutInflater.from(parent.context), parent, false
    )
  )
  
  override fun onBindViewHolder(holder: VH, position: Int): Unit = holder.bind(getItem(position))
  
  inner class VH(private val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bind(item: UserItemModel) {
      binding.run {
        userItemUsername.text = item.username
        
        requestManager.load(item.profileImage.medium)
          .fitCenter()
          .centerCrop()
          .transition(DrawableTransitionOptions.withCrossFade())
          .into(userItemImage)
        
        userItemTwitterUsername.text = item.twitterUsername
        userItemInstagramUsername.text = item.instagramUsername
        userItemTotalLikesText.text = item.totalLikes.toString()
        userItemTotalPhotosText.text = item.totalPhotos.toString()
      }
    }
  }
  
  private object UserItemCallBack : ItemCallback<UserItemModel>() {
    override fun areItemsTheSame(
      oldItem: UserItemModel, newItem: UserItemModel
    ): Boolean = oldItem.id == newItem.id
    
    override fun areContentsTheSame(
      oldItem: UserItemModel, newItem: UserItemModel
    ): Boolean = oldItem == newItem
  }
}