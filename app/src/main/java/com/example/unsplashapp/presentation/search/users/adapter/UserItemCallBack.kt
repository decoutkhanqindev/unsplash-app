package com.example.unsplashapp.presentation.search.users.adapter

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.example.unsplashapp.presentation.feed.photos.model.PhotoItemModel
import com.example.unsplashapp.presentation.search.users.model.UserItemModel

object UserItemCallBack : ItemCallback<UserItemModel>() {
  override fun areItemsTheSame(
    oldItem: UserItemModel, newItem: UserItemModel
  ): Boolean = oldItem.id == newItem.id
  
  override fun areContentsTheSame(
    oldItem: UserItemModel, newItem: UserItemModel
  ): Boolean = oldItem == newItem
}