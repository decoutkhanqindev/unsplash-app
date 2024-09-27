package com.example.unsplashapp.data.remote

import com.example.unsplashapp.data.remote.response.CollectionItemResponse
import com.example.unsplashapp.data.remote.response.PhotoItemResponse
import com.example.unsplashapp.data.remote.response.SearchPhotosResponse
import com.example.unsplashapp.data.remote.response.SearchUsersResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApiService {
  companion object {
    operator fun invoke(retrofit: Retrofit): UnsplashApiService = retrofit.create()
  }
  
  @GET("collections")
  suspend fun getCollections(
    @Query("page") page: Int, @Query("per_page") perPage: Int
  ): List<CollectionItemResponse>
  
  @GET("photos")
  suspend fun getPhotos(
    @Query("page") page: Int, @Query("per_page") perPage: Int
  ): List<PhotoItemResponse>

//  @GET("search/photos")
//  suspend fun searchPhotos(
//    @Query("query") query: String, @Query("page") page: Int, @Query("per_page") perPage: Int
//  ): SearchPhotosResponse

//  @GET("search/users")
//  suspend fun searchUsers(
//    @Query("query") query: String, @Query("page") page: Int, @Query("per_page") perPage: Int
//  ): SearchUsersResponse
  
  @GET("search/photos")
  fun searchPhotosByRxJava(
    @Query("query") query: String, @Query("page") page: Int, @Query("per_page") perPage: Int
  ): Single<SearchPhotosResponse>
  
  @GET("search/users")
  fun searchUsersByRxJava(
    @Query("query") query: String, @Query("page") page: Int, @Query("per_page") perPage: Int
  ): Single<SearchUsersResponse>
}