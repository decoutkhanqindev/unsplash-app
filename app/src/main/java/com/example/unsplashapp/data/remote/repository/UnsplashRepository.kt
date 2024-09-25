package com.example.unsplashapp.data.remote.repository

import com.example.unsplashapp.data.remote.response.CollectionItemResponse
import com.example.unsplashapp.data.remote.response.PhotoItemResponse
import com.example.unsplashapp.data.remote.response.SearchPhotosResponse
import com.example.unsplashapp.data.remote.response.SearchUsersResponse

// abstractions data source
interface UnsplashRepository {
  suspend fun getCollections(page: Int, perPage: Int): List<CollectionItemResponse>
  
  suspend fun getPhotos(page: Int, perPage: Int): List<PhotoItemResponse>
  
  suspend fun searchPhotos(query: String, page: Int, perPage: Int): SearchPhotosResponse
  
  suspend fun searchUsers(query: String, page: Int, perPage: Int): SearchUsersResponse
}