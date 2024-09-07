package com.example.unsplashapp.data.remote.response

import androidx.annotation.Keep
import com.squareup.moshi.Json

// model to receive response
@Keep
data class CollectionItemResponse(
	@Json(name = "id") val id: String, // W6bCWhwaXmw
	@Json(name = "title") val title: String, // Artisanal
	@Json(name = "description") val description: String?, // The creativity and inventiveness of skilled artisans transform basic materials like wood, glass, denim, and more into unique, Bohemian masterpieces.
	@Json(name = "published_at") val publishedAt: String, // 2024-08-20T13:48:14Z
	@Json(name = "last_collected_at") val lastCollectedAt: String, // 2024-08-20T13:48:36Z
	@Json(name = "updated_at") val updatedAt: String, // 2024-08-20T13:48:36Z
	@Json(name = "featured") val featured: Boolean, // true
	@Json(name = "total_photos") val totalPhotos: Int, // 50
	@Json(name = "private") val `private`: Boolean, // false
	@Json(name = "share_key") val shareKey: String, // 83c0b344ffde73ce8be8d65c288ede5d
	@Json(name = "links") val links: Links,
	@Json(name = "user") val user: User,
	@Json(name = "cover_photo") val coverPhoto: CoverPhoto,
	@Json(name = "preview_photos") val previewPhotos: List<PreviewPhoto>
) {
	@Keep
	data class Links(
		@Json(name = "self") val self: String, // https://api.unsplash.com/collections/W6bCWhwaXmw
		@Json(name = "html") val html: String, // https://unsplash.com/collections/W6bCWhwaXmw/artisan-invention-with-pantone
		@Json(name = "photos") val photos: String, // https://api.unsplash.com/collections/W6bCWhwaXmw/photos
		@Json(name = "related") val related: String // https://api.unsplash.com/collections/W6bCWhwaXmw/related
	)
	
	@Keep
	data class User(
		@Json(name = "id") val id: String, // iwi9-4OXLYY
		@Json(name = "updated_at") val updatedAt: String, // 2024-08-25T02:38:44Z
		@Json(name = "username") val username: String, // unsplashplus
		@Json(name = "name") val name: String, // Unsplash+ Collections
		@Json(name = "first_name") val firstName: String, // Unsplash+
		@Json(name = "last_name") val lastName: String?, // Collections
		@Json(name = "twitter_username") val twitterUsername: String?, // a_lishakov
		@Json(name = "portfolio_url") val portfolioUrl: String?, // https://popovich.io
		@Json(name = "bio") val bio: String?, // Creadir of Main Role production.Open for interesting projects and collaborations.
		@Json(name = "location") val location: String?, // currently in Kazakhstan
		@Json(name = "links") val links: Links,
		@Json(name = "profile_image") val profileImage: ProfileImage,
		@Json(name = "instagram_username") val instagramUsername: String?, // lishakov
		@Json(name = "total_collections") val totalCollections: Int, // 170
		@Json(name = "total_likes") val totalLikes: Int, // 247
		@Json(name = "total_photos") val totalPhotos: Int, // 0
		@Json(name = "total_promoted_photos") val totalPromotedPhotos: Int, // 0
		@Json(name = "total_illustrations") val totalIllustrations: Int, // 0
		@Json(name = "total_promoted_illustrations") val totalPromotedIllustrations: Int, // 0
		@Json(name = "accepted_tos") val acceptedTos: Boolean, // true
		@Json(name = "for_hire") val forHire: Boolean, // false
	) {
		@Keep
		data class Links(
			@Json(name = "self") val self: String, // https://api.unsplash.com/users/unsplashplus
			@Json(name = "html") val html: String, // https://unsplash.com/@unsplashplus
			@Json(name = "photos") val photos: String, // https://api.unsplash.com/users/unsplashplus/photos
			@Json(name = "likes") val likes: String, // https://api.unsplash.com/users/unsplashplus/likes
			@Json(name = "portfolio") val portfolio: String, // https://api.unsplash.com/users/unsplashplus/portfolio
			@Json(name = "following") val following: String, // https://api.unsplash.com/users/unsplashplus/following
			@Json(name = "followers") val followers: String // https://api.unsplash.com/users/unsplashplus/followers
		)
		
		@Keep
		data class ProfileImage(
			@Json(name = "small") val small: String, // https://images.unsplash.com/profile-1714421769490-6918cb0c83a9image?ixlib=rb-4.0.3&crop=faces&fit=crop&w=32&h=32
			@Json(name = "medium") val medium: String, // https://images.unsplash.com/profile-1714421769490-6918cb0c83a9image?ixlib=rb-4.0.3&crop=faces&fit=crop&w=64&h=64
			@Json(name = "large") val large: String // https://images.unsplash.com/profile-1714421769490-6918cb0c83a9image?ixlib=rb-4.0.3&crop=faces&fit=crop&w=128&h=128
		)
	}
	
	@Keep
	data class CoverPhoto(
		@Json(name = "id") val id: String, // o58yPfROubI
		@Json(name = "slug") val slug: String, // a-person-holding-a-paintbrush-on-top-of-a-rock-o58yPfROubI
		@Json(name = "created_at") val createdAt: String, // 2022-12-30T17:48:48Z
		@Json(name = "updated_at") val updatedAt: String, // 2024-08-22T21:49:39Z
		@Json(name = "promoted_at") val promotedAt: String?, // 2023-11-14T18:08:01Z
		@Json(name = "width") val width: Int, // 2764
		@Json(name = "height") val height: Int, // 4158
		@Json(name = "color") val color: String, // #EFEFEF
		@Json(name = "description") val description: String?, // 3D render in neutral colors.
		@Json(name = "urls") val urls: UrlsResponse,
		@Json(name = "links") val links: Links,
		@Json(name = "likes") val likes: Int, // 6
		@Json(name = "liked_by_user") val likedByUser: Boolean, // false
		@Json(name = "current_user_collections") val currentUserCollections: List<Any>,
		@Json(name = "sponsorship") val sponsorship: Any?, // null
		@Json(name = "asset_type") val assetType: String, // photo
		@Json(name = "user") val user: User
	) {
		
		@Keep
		data class Links(
			@Json(name = "self") val self: String, // https://api.unsplash.com/photos/a-person-holding-a-paintbrush-on-top-of-a-rock-o58yPfROubI
			@Json(name = "html") val html: String, // https://unsplash.com/photos/a-person-holding-a-paintbrush-on-top-of-a-rock-o58yPfROubI
			@Json(name = "download") val download: String, // https://unsplash.com/photos/o58yPfROubI/download
			@Json(name = "download_location") val downloadLocation: String // https://api.unsplash.com/photos/o58yPfROubI/download
		)
		
		@Keep
		data class User(
			@Json(name = "id") val id: String, // Z3VgUyE9iXY
			@Json(name = "updated_at") val updatedAt: String, // 2024-08-22T13:41:20Z
			@Json(name = "username") val username: String, // giuliasq
			@Json(name = "name") val name: String, // Giulia Squillace
			@Json(name = "first_name") val firstName: String, // Giulia
			@Json(name = "last_name") val lastName: String?, // Squillace
			@Json(name = "twitter_username") val twitterUsername: String?, // alexandermils
			@Json(name = "portfolio_url") val portfolioUrl: String?, // https://linktr.ee/alexshuperart
			@Json(name = "bio") val bio: String?, // Photography Manager @Unsplash
			@Json(name = "location") val location: String?, // Amsterdam
			@Json(name = "links") val links: Links,
			@Json(name = "profile_image") val profileImage: ProfileImage,
			@Json(name = "instagram_username") val instagramUsername: String?, // heygiul
			@Json(name = "total_collections") val totalCollections: Int, // 15
			@Json(name = "total_likes") val totalLikes: Int, // 268
			@Json(name = "total_photos") val totalPhotos: Int, // 472
			@Json(name = "total_promoted_photos") val totalPromotedPhotos: Int, // 7
			@Json(name = "total_illustrations") val totalIllustrations: Int, // 0
			@Json(name = "total_promoted_illustrations") val totalPromotedIllustrations: Int, // 0
			@Json(name = "accepted_tos") val acceptedTos: Boolean, // true
			@Json(name = "for_hire") val forHire: Boolean, // false
			@Json(name = "social") val social: Social
		) {
			@Keep
			data class Links(
				@Json(name = "self") val self: String, // https://api.unsplash.com/users/giuliasq
				@Json(name = "html") val html: String, // https://unsplash.com/@giuliasq
				@Json(name = "photos") val photos: String, // https://api.unsplash.com/users/giuliasq/photos
				@Json(name = "likes") val likes: String, // https://api.unsplash.com/users/giuliasq/likes
				@Json(name = "portfolio") val portfolio: String, // https://api.unsplash.com/users/giuliasq/portfolio
				@Json(name = "following") val following: String, // https://api.unsplash.com/users/giuliasq/following
				@Json(name = "followers") val followers: String // https://api.unsplash.com/users/giuliasq/followers
			)
			
			@Keep
			data class ProfileImage(
				@Json(name = "small") val small: String, // https://images.unsplash.com/profile-1651833664038-5f148b975d38image?ixlib=rb-4.0.3&crop=faces&fit=crop&w=32&h=32
				@Json(name = "medium") val medium: String, // https://images.unsplash.com/profile-1651833664038-5f148b975d38image?ixlib=rb-4.0.3&crop=faces&fit=crop&w=64&h=64
				@Json(name = "large") val large: String // https://images.unsplash.com/profile-1651833664038-5f148b975d38image?ixlib=rb-4.0.3&crop=faces&fit=crop&w=128&h=128
			)
			
			@Keep
			data class Social(
				@Json(name = "instagram_username") val instagramUsername: String?, // heygiul
				@Json(name = "portfolio_url") val portfolioUrl: String?, // https://linktr.ee/alexshuperart
				@Json(name = "twitter_username") val twitterUsername: String?, // alexandermils
				@Json(name = "paypal_email") val paypalEmail: Any? // null
			)
		}
	}
	
	@Keep
	data class PreviewPhoto(
		@Json(name = "id") val id: String, // o58yPfROubI
		@Json(name = "slug") val slug: String, // a-person-holding-a-paintbrush-on-top-of-a-rock-o58yPfROubI
		@Json(name = "created_at") val createdAt: String, // 2022-12-30T17:48:48Z
		@Json(name = "updated_at") val updatedAt: String, // 2024-08-22T21:49:39Z
		@Json(name = "asset_type") val assetType: String, // photo
		@Json(name = "urls") val urls: UrlsResponse
	)
}

@Keep
data class UrlsResponse(
	@Json(name = "raw") val raw: String, // https://plus.unsplash.com/premium_photo-1672287578336-c32928d2524d?ixlib=rb-4.0.3
	@Json(name = "full") val full: String, // https://plus.unsplash.com/premium_photo-1672287578336-c32928d2524d?ixlib=rb-4.0.3&q=85&fm=jpg&crop=entropy&cs=srgb
	@Json(name = "regular") val regular: String, // https://plus.unsplash.com/premium_photo-1672287578336-c32928d2524d?ixlib=rb-4.0.3&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max
	@Json(name = "small") val small: String, // https://plus.unsplash.com/premium_photo-1672287578336-c32928d2524d?ixlib=rb-4.0.3&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max
	@Json(name = "thumb") val thumb: String, // https://plus.unsplash.com/premium_photo-1672287578336-c32928d2524d?ixlib=rb-4.0.3&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max
	@Json(name = "small_s3") val smallS3: String // https://s3.us-west-2.amazonaws.com/images.unsplash.com/small/unsplash-premium-photos-production/premium_photo-1672287578336-c32928d2524d
)