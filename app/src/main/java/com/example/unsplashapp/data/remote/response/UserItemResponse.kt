package com.example.unsplashapp.data.remote.response

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
data class UserItemResponse(
  @Json(name = "id") val id: String, // HkHscxL8UJc
  @Json(name = "updated_at") val updatedAt: String, // 2024-09-08T10:01:55Z
  @Json(name = "username") val username: String, // sammoghadamkhamseh
  @Json(name = "name") val name: String, // Sam Moghadam Khamseh
  @Json(name = "first_name") val firstName: String, // Sam
  @Json(name = "last_name") val lastName: String, // Moghadam Khamseh
  @Json(name = "twitter_username") val twitterUsername: String?, // totalshit23
  @Json(name = "portfolio_url") val portfolioUrl: String?, // https://www.samhozan.com/
  @Json(name = "bio") val bio: String?, // itssammoqadam@gmail.com+989125650305
  @Json(name = "location") val location: String?, // Tehran, Iran
  @Json(name = "links") val links: Links,
  @Json(name = "profile_image") val profileImage: ProfileImage,
  @Json(name = "instagram_username") val instagramUsername: String?, // sam.moqadam
  @Json(name = "total_collections") val totalCollections: Int, // 1
  @Json(name = "total_likes") val totalLikes: Int, // 1422
  @Json(name = "total_photos") val totalPhotos: Int, // 504
  @Json(name = "total_promoted_photos") val totalPromotedPhotos: Int, // 19
  @Json(name = "total_illustrations") val totalIllustrations: Int, // 0
  @Json(name = "total_promoted_illustrations") val totalPromotedIllustrations: Int, // 0
  @Json(name = "accepted_tos") val acceptedTos: Boolean, // true
  @Json(name = "for_hire") val forHire: Boolean, // false
  @Json(name = "social") val social: Social,
  @Json(name = "followed_by_user") val followedByUser: Boolean, // false
  @Json(name = "photos") val photos: List<Photo>
) {
  @Keep
  data class Links(
    @Json(name = "self") val self: String, // https://api.unsplash.com/users/sammoghadamkhamseh
    @Json(name = "html") val html: String, // https://unsplash.com/@sammoghadamkhamseh
    @Json(name = "photos") val photos: String, // https://api.unsplash.com/users/sammoghadamkhamseh/photos
    @Json(name = "likes") val likes: String, // https://api.unsplash.com/users/sammoghadamkhamseh/likes
    @Json(name = "portfolio") val portfolio: String, // https://api.unsplash.com/users/sammoghadamkhamseh/portfolio
    @Json(name = "following") val following: String, // https://api.unsplash.com/users/sammoghadamkhamseh/following
    @Json(name = "followers") val followers: String // https://api.unsplash.com/users/sammoghadamkhamseh/followers
  )
  
  @Keep
  data class ProfileImage(
    @Json(name = "small") val small: String, // https://images.unsplash.com/profile-1683031693902-a2725a74b32bimage?ixlib=rb-4.0.3&crop=faces&fit=crop&w=32&h=32
    @Json(name = "medium") val medium: String, // https://images.unsplash.com/profile-1683031693902-a2725a74b32bimage?ixlib=rb-4.0.3&crop=faces&fit=crop&w=64&h=64
    @Json(name = "large") val large: String // https://images.unsplash.com/profile-1683031693902-a2725a74b32bimage?ixlib=rb-4.0.3&crop=faces&fit=crop&w=128&h=128
  )
  
  @Keep
  data class Social(
    @Json(name = "instagram_username") val instagramUsername: String?, // sam.moqadam
    @Json(name = "portfolio_url") val portfolioUrl: String?, // https://www.samhozan.com/
    @Json(name = "twitter_username") val twitterUsername: String?, // totalshit23
    @Json(name = "paypal_email") val paypalEmail: Any? // null
  )
  
  @Keep
  data class Photo(
    @Json(name = "id") val id: String, // KJ241ZAOYwU
    @Json(name = "slug") val slug: String, // man-in-black-jacket-standing-on-blue-lighted-room-KJ241ZAOYwU
    @Json(name = "created_at") val createdAt: String, // 2020-08-23T20:17:01Z
    @Json(name = "updated_at") val updatedAt: String, // 2024-09-12T09:39:22Z
    @Json(name = "blur_hash") val blurHash: String, // LR1$Wqaxi_bbdojEaykDZ#kCkWi_
    @Json(name = "asset_type") val assetType: String, // photo
    @Json(name = "urls") val urls: Urls
  ) {
    @Keep
    data class Urls(
      @Json(name = "raw") val raw: String, // https://images.unsplash.com/photo-1598209494655-b8e249540dfc?ixlib=rb-4.0.3
      @Json(name = "full") val full: String, // https://images.unsplash.com/photo-1598209494655-b8e249540dfc?ixlib=rb-4.0.3&q=85&fm=jpg&crop=entropy&cs=srgb
      @Json(name = "regular") val regular: String, // https://images.unsplash.com/photo-1598209494655-b8e249540dfc?ixlib=rb-4.0.3&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max
      @Json(name = "small") val small: String, // https://images.unsplash.com/photo-1598209494655-b8e249540dfc?ixlib=rb-4.0.3&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max
      @Json(name = "thumb") val thumb: String, // https://images.unsplash.com/photo-1598209494655-b8e249540dfc?ixlib=rb-4.0.3&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max
      @Json(name = "small_s3") val smallS3: String // https://s3.us-west-2.amazonaws.com/images.unsplash.com/small/photo-1598209494655-b8e249540dfc
    )
  }
}
