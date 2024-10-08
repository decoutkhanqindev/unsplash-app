package com.example.unsplashapp.data.remote.response

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class SearchUsersResponse(
  @Json(name = "total") val total: Int, // 10000
  @Json(name = "total_pages") val totalPages: Int, // 334
  @Json(name = "results") val results: List<UserItemResponse>
)