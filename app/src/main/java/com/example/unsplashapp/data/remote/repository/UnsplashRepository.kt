package com.example.unsplashapp.data.remote.repository

import com.example.unsplashapp.presentation.feed.collections.model.CollectionItemModel
import com.example.unsplashapp.presentation.feed.photos.model.PhotoItemModel
import com.example.unsplashapp.presentation.search.users.model.UserItemModel
import io.reactivex.rxjava3.core.Single

// abstractions data source
interface UnsplashRepository {
  fun getCollections(page: Int, perPage: Int): Single<List<CollectionItemModel>>
  
  fun getPhotos(page: Int, perPage: Int): Single<List<PhotoItemModel>>
  
  fun searchPhotos(query: String, page: Int, perPage: Int): Single<List<PhotoItemModel>>
  
  fun searchUsers(query: String, page: Int, perPage: Int): Single<List<UserItemModel>>
}