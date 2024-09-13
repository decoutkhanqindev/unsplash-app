package com.example.unsplashapp.data.remote.response

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class SearchPhotosResponse(
  @Json(name = "total") val total: Int, // 256
  @Json(name = "total_pages") val totalPages: Int, // 9
  @Json(name = "results") val results: List<PhotoItemResponse>
)