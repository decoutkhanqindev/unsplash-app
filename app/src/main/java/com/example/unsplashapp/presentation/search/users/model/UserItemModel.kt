package com.example.unsplashapp.presentation.search.users.model

import com.example.unsplashapp.data.remote.response.PhotoItemResponse
import com.example.unsplashapp.data.remote.response.UserItemResponse
import com.example.unsplashapp.presentation.feed.photos.model.PhotoItemModel

data class UserItemModel(
  val id: String,
  val username: String,
  val twitterUsername: String?,
  val instagramUsername: String?,
  val profileImage: UserItemResponse.ProfileImage,
) {
  companion object {
    fun UserItemResponse.toUserItemModel(): UserItemModel = UserItemModel(
      id = id,
      username = username,
      twitterUsername = twitterUsername,
      instagramUsername = instagramUsername,
      profileImage = profileImage
    )
  }
}