package com.example.unsplashapp.data.remote.repository

import com.example.unsplashapp.data.remote.UnsplashApiService
import com.example.unsplashapp.data.remote.response.CollectionItemResponse
import com.example.unsplashapp.data.remote.response.PhotoItemResponse
import com.example.unsplashapp.data.remote.response.SearchPhotosResponse
import com.example.unsplashapp.data.remote.response.SearchUsersResponse

class UnsplashRepositoryImpl(
  private val unsplashApiService: UnsplashApiService
) : UnsplashRepository {
  override suspend fun getCollections(page: Int, perPage: Int): List<CollectionItemResponse> =
    unsplashApiService.getCollections(page, perPage)
  
  override suspend fun getPhotos(page: Int, perPage: Int): List<PhotoItemResponse> =
    unsplashApiService.getPhotos(page, perPage)
  
  override suspend fun searchPhotos(query: String, page: Int, perPage: Int): SearchPhotosResponse =
    unsplashApiService.searchPhotos(query, page, perPage)
  
  override suspend fun searchUsers(query: String, page: Int, perPage: Int): SearchUsersResponse =
    unsplashApiService.searchUsers(query, page, perPage)
}