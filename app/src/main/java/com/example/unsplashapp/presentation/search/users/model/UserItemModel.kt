package com.example.unsplashapp.presentation.search.users.model

import com.example.unsplashapp.data.remote.response.UserItemResponse

data class UserItemModel(
  val id: String,
  val username: String,
  val twitterUsername: String?,
  val instagramUsername: String?,
  val profileImage: UserItemResponse.ProfileImage,
  val totalLikes: Int,
  val totalPhotos: Int,
) {
  companion object {
    fun UserItemResponse.toUserItemModel(): UserItemModel = UserItemModel(
      id = id,
      username = username,
      twitterUsername = twitterUsername,
      instagramUsername = instagramUsername,
      profileImage = profileImage,
      totalLikes = totalLikes,
      totalPhotos = totalPhotos
    )
  }
}