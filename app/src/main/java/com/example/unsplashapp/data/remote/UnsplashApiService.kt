package com.example.unsplashapp.data.remote

import com.example.unsplashapp.data.remote.response.CollectionItemResponse
import com.example.unsplashapp.data.remote.response.PhotoItemResponse
import com.example.unsplashapp.data.remote.response.SearchPhotosResponse
import com.example.unsplashapp.data.remote.response.SearchUsersResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApiService {
  @GET("collections")
  suspend fun getCollections(
    @Query("page") page: Int, @Query("per_page") perPage: Int
  ): List<CollectionItemResponse>
  
  @GET("photos")
  suspend fun getPhotos(
    @Query("page") page: Int, @Query("per_page") perPage: Int
  ): List<PhotoItemResponse>
  
  @GET("search/photos")
  fun searchPhotosByRxJava(
    @Query("query") query: String, @Query("page") page: Int, @Query("per_page") perPage: Int
  ): Single<SearchPhotosResponse>
  
  @GET("search/users")
  fun searchUsersByRxJava(
    @Query("query") query: String, @Query("page") page: Int, @Query("per_page") perPage: Int
  ): Single<SearchUsersResponse>
}