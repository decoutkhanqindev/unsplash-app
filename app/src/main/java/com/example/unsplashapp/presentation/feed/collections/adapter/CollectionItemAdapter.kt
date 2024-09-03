package com.example.unsplashapp.presentation.feed.collections.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.unsplashapp.databinding.CollectionItemBinding
import com.example.unsplashapp.databinding.CollectionsItemBinding
import com.example.unsplashapp.presentation.feed.collections.model.CollectionItemModel
import com.example.unsplashapp.presentation.feed.collections.utils.CollectionItemCallBack

class CollectionItemAdapter(
    private val requestManager: RequestManager
) : ListAdapter<CollectionItemModel, CollectionItemAdapter.VH>(CollectionItemCallBack) {


    override fun onBindViewHolder(holder: VH, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        TODO("Not yet implemented")
    }

    inner class VH(
        private val binding: CollectionItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

    }
}