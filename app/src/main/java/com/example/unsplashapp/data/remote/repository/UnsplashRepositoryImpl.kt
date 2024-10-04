package com.example.unsplashapp.data.remote.repository

import com.example.unsplashapp.data.remote.UnsplashApiService
import com.example.unsplashapp.data.remote.response.CollectionItemResponse
import com.example.unsplashapp.data.remote.response.PhotoItemResponse
import com.example.unsplashapp.data.remote.response.SearchPhotosResponse
import com.example.unsplashapp.data.remote.response.SearchUsersResponse
import com.example.unsplashapp.presentation.feed.collections.model.CollectionItemModel
import com.example.unsplashapp.presentation.feed.collections.model.CollectionItemModel.CREATOR.toCollectionItemModel
import com.example.unsplashapp.presentation.feed.photos.model.PhotoItemModel
import com.example.unsplashapp.presentation.feed.photos.model.PhotoItemModel.Companion.toPhotoItemModel
import com.example.unsplashapp.presentation.search.users.model.UserItemModel
import com.example.unsplashapp.presentation.search.users.model.UserItemModel.Companion.toUserItemModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/*
@Inject: This annotation tells Hilt that this constructor can be used to create an instance of
the class and Hilt will analyze the constructor parameters and try to provide the necessary dependencies.
*/

class UnsplashRepositoryImpl @Inject constructor(
  private val unsplashApiService: UnsplashApiService
) : UnsplashRepository {
  
  override fun getCollections(page: Int, perPage: Int): Single<List<CollectionItemModel>> =
    unsplashApiService.getCollections(page, perPage)
      .map { response: List<CollectionItemResponse> -> response.map { it.toCollectionItemModel() } }
  
  override fun getPhotos(page: Int, perPage: Int): Single<List<PhotoItemModel>> =
    unsplashApiService.getPhotos(page, perPage)
      .map { response: List<PhotoItemResponse> -> response.map { it.toPhotoItemModel() } }
  
  override fun searchPhotos(
    query: String, page: Int, perPage: Int
  ): Single<List<PhotoItemModel>> = unsplashApiService.searchPhotos(query, page, perPage)
    .map { response: SearchPhotosResponse -> response.results.map { it.toPhotoItemModel() } }
  
  override fun searchUsers(
    query: String, page: Int, perPage: Int
  ): Single<List<UserItemModel>> = unsplashApiService.searchUsers(query, page, perPage)
    .map { response: SearchUsersResponse -> response.results.map { it.toUserItemModel() } }
}
