package com.example.unsplashapp.presentation.feed.collections.adapter

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.unsplashapp.databinding.CollectionItemPreviewPhotoBinding
import com.example.unsplashapp.presentation.feed.collections.model.CollectionItemPreviewPhotoModel
import java.util.Date

class CollectionItemPreviewPhotoAdapter(
  private val requestManager: RequestManager
) : ListAdapter<CollectionItemPreviewPhotoModel, CollectionItemPreviewPhotoAdapter.VH>(
  CollectionItemPreviewPhotoCallBack
) {
  
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH = VH(
    CollectionItemPreviewPhotoBinding.inflate(
      LayoutInflater.from(parent.context), parent, false
    )
  )
  
  override fun onBindViewHolder(holder: VH, position: Int): Unit = holder.bind(getItem(position))
  
  inner class VH(
    private val binding: CollectionItemPreviewPhotoBinding
  ) : RecyclerView.ViewHolder(binding.root) {
    
    fun bind(item: CollectionItemPreviewPhotoModel) {
      binding.run {
        requestManager.load(item.urls.regular).fitCenter().centerCrop()
          .transition(DrawableTransitionOptions.withCrossFade())
          .into(collectionItemPreviewPhotoImage)
        
        collectionItemPreviewPhotoDate.text = formatTimestamp(item.createdAt)
      }
    }
    
    @SuppressLint("SimpleDateFormat")
    private fun formatTimestamp(timestamp: String): String {
      val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
      inputFormat.timeZone = TimeZone.getTimeZone("UTC") // Set input time zone to UTC
      val outputFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
      val date: Date? = inputFormat.parse(timestamp)
      return if (date != null) outputFormat.format(date) else "Invalid Date"
    }
  }
}