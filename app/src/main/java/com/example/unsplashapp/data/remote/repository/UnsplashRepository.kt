package com.example.unsplashapp.data.remote.repository

import com.example.unsplashapp.data.remote.response.CollectionItemResponse
import com.example.unsplashapp.data.remote.response.PhotoItemResponse
import com.example.unsplashapp.data.remote.response.SearchPhotosResponse
import com.example.unsplashapp.data.remote.response.SearchUsersResponse
import io.reactivex.rxjava3.core.Single

// abstractions data source
interface UnsplashRepository {
  fun getCollections(page: Int, perPage: Int): Single<List<CollectionItemResponse>>
  
  fun getPhotos(page: Int, perPage: Int): Single<List<PhotoItemResponse>>
  
  fun searchPhotos(query: String, page: Int, perPage: Int): Single<SearchPhotosResponse>
  
  fun searchUsers(query: String, page: Int, perPage: Int): Single<SearchUsersResponse>
}