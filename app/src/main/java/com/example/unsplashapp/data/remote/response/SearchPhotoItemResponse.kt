package com.example.unsplashapp.data.remote.response


import com.squareup.moshi.Json
import androidx.annotation.Keep

@Keep
data class SearchPhotoItemResponse(
    @Json(name = "total") val total: Int, // 256
    @Json(name = "total_pages") val totalPages: Int, // 9
    @Json(name = "results") val results: List<PhotoItemResponse>
)