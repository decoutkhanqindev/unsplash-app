package com.example.unsplashapp.presentation.feed.photos.adapter

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashapp.databinding.PhotoItemBinding
import com.example.unsplashapp.presentation.feed.photos.model.PhotoItemModel

class PhotoItemAdapter : ListAdapter<PhotoItemModel, PhotosItemAdapter.VH>() {

    inner class VH(binding: PhotoItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}