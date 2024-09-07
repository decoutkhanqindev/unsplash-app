package com.example.unsplashapp.presentation.feed.photos.adapter

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.example.unsplashapp.presentation.feed.photos.model.PhotoItemModel

object PhotoItemCallBack : ItemCallback<PhotoItemModel>() {
	override fun areItemsTheSame(
		oldItem: PhotoItemModel, newItem: PhotoItemModel
	): Boolean = oldItem.id == newItem.id
	
	override fun areContentsTheSame(
		oldItem: PhotoItemModel, newItem: PhotoItemModel
	): Boolean = oldItem == newItem
}