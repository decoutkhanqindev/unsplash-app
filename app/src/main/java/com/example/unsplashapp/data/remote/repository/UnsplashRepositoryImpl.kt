package com.example.unsplashapp.data.remote.repository

import com.example.unsplashapp.data.remote.UnsplashApiService
import com.example.unsplashapp.data.remote.response.CollectionItemResponse
import com.example.unsplashapp.data.remote.response.PhotoItemResponse
import com.example.unsplashapp.data.remote.response.SearchPhotosResponse
import com.example.unsplashapp.data.remote.response.SearchUsersResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/*
@Inject: This annotation tells Hilt that this constructor can be used to create an instance of
the class and Hilt will analyze the constructor parameters and try to provide the necessary dependencies.
*/

class UnsplashRepositoryImpl @Inject constructor(
  private val unsplashApiService: UnsplashApiService
) : UnsplashRepository {
  
  override fun getCollections(page: Int, perPage: Int): Single<List<CollectionItemResponse>> =
    unsplashApiService.getCollections(page, perPage)
  
  override fun getPhotos(page: Int, perPage: Int): Single<List<PhotoItemResponse>> =
    unsplashApiService.getPhotos(page, perPage)
  
  override fun searchPhotos(
    query: String, page: Int, perPage: Int
  ): Single<SearchPhotosResponse> = unsplashApiService.searchPhotos(query, page, perPage)
  
  override fun searchUsers(
    query: String, page: Int, perPage: Int
  ): Single<SearchUsersResponse> = unsplashApiService.searchUsers(query, page, perPage)
}
