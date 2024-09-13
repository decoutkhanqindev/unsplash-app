package com.example.unsplashapp.presentation.feed.collections.model

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.example.unsplashapp.data.remote.response.CollectionItemResponse

// model to display data on UI for collection
@SuppressLint("ParcelCreator")
@Suppress("DEPRECATION")
data class CollectionItemModel(
  val id: String,
  val title: String,
  val description: String?,
  val coverPhotoUrl: String,
  val user: CollectionItemResponse.User,
  val previewPhotos: List<CollectionItemResponse.PreviewPhoto>
) : Parcelable {
  
  constructor(parcel: Parcel) : this(
    parcel.readString()!!,
    parcel.readString()!!,
    parcel.readString()!!,
    parcel.readString()!!,
    parcel.readParcelable(CollectionItemResponse.User::class.java.classLoader)!!,
    parcel.createTypedArrayList(CollectionItemResponse.PreviewPhoto.CREATOR)!!
  )
  
  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(id)
    parcel.writeString(title)
    parcel.writeString(description)
    parcel.writeString(coverPhotoUrl)
    parcel.writeParcelable(user, flags)
    parcel.writeTypedList(previewPhotos)
  }
  
  override fun describeContents(): Int {
    return 0
  }
  
  companion object CREATOR : Parcelable.Creator<CollectionItemModel> {
    override fun createFromParcel(parcel: Parcel): CollectionItemModel {
      return CollectionItemModel(parcel)
    }
    
    override fun newArray(size: Int): Array<CollectionItemModel?> {
      return arrayOfNulls(size)
    }
    
    fun CollectionItemResponse.toCollectionItemModel(): CollectionItemModel = CollectionItemModel(
      id = id,
      title = title,
      description = description,
      coverPhotoUrl = coverPhoto.urls.regular,
      user = user,
      previewPhotos = previewPhotos
    )
  }
}