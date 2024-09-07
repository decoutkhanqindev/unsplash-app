package com.example.unsplashapp.data.remote.response


import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class PhotoItemResponse(
	@Json(name = "id") val id: String, // IJolVhJKk7c
	@Json(name = "slug") val slug: String, // a-person-holding-a-nintendo-wii-game-controller-IJolVhJKk7c
	@Json(name = "alternative_slugs") val alternativeSlugs: AlternativeSlugs,
	@Json(name = "created_at") val createdAt: String, // 2024-07-18T19:49:40Z
	@Json(name = "updated_at") val updatedAt: String, // 2024-09-03T06:52:26Z
	@Json(name = "promoted_at") val promotedAt: String?, // 2024-09-03T14:06:32Z
	@Json(name = "width") val width: Int, // 8736
	@Json(name = "height") val height: Int, // 11648
	@Json(name = "color") val color: String, // #260c0c
	@Json(name = "blur_hash") val blurHash: String, // LLFXL_x]E1EM11f6s9kB4.M{kDso
	@Json(name = "description") val description: String?, // Image by Planet Volumes
	@Json(name = "alt_description") val altDescription: String, // A person holding a nintendo wii game controller
	@Json(name = "breadcrumbs") val breadcrumbs: List<Any>,
	@Json(name = "urls") val urls: Urls,
	@Json(name = "links") val links: Links,
	@Json(name = "likes") val likes: Int, // 89
	@Json(name = "liked_by_user") val likedByUser: Boolean, // false
	@Json(name = "current_user_collections") val currentUserCollections: List<Any>,
	@Json(name = "sponsorship") val sponsorship: Sponsorship?,
	@Json(name = "topic_submissions") val topicSubmissions: TopicSubmissions?,
	@Json(name = "asset_type") val assetType: String, // photo
	@Json(name = "user") val user: User
) {
	@Keep
	data class AlternativeSlugs(
		@Json(name = "en") val en: String, // a-person-holding-a-nintendo-wii-game-controller-IJolVhJKk7c
		@Json(name = "es") val es: String, // una-persona-sosteniendo-un-mando-de-juegos-de-nintendo-wii-IJolVhJKk7c
		@Json(name = "ja") val ja: String, // ニンテンドーwiiのゲームコントローラーを持っている人-IJolVhJKk7c
		@Json(name = "fr") val fr: String, // une-personne-tenant-une-manette-de-jeu-nintendo-wii-IJolVhJKk7c
		@Json(name = "it") val it: String, // una-persona-con-in-mano-un-controller-di-gioco-per-nintendo-wii-IJolVhJKk7c
		@Json(name = "ko") val ko: String, // 닌텐도-wii-게임-컨트롤러를-들고-있는-사람-IJolVhJKk7c
		@Json(name = "de") val de: String, // eine-person-die-einen-nintendo-wii-gamecontroller-in-der-hand-halt-IJolVhJKk7c
		@Json(name = "pt") val pt: String // uma-pessoa-segurando-um-controle-de-jogo-nintendo-wii-IJolVhJKk7c
	)
	
	@Keep
	data class Urls(
		@Json(name = "raw") val raw: String, // https://images.unsplash.com/photo-1721332154373-17e78d19b4a4?ixid=M3w2NDk1MTZ8MXwxfGFsbHwxfHx8fHx8Mnx8MTcyNTQ0NTE2NHw&ixlib=rb-4.0.3
		@Json(name = "full") val full: String, // https://images.unsplash.com/photo-1721332154373-17e78d19b4a4?crop=entropy&cs=srgb&fm=jpg&ixid=M3w2NDk1MTZ8MXwxfGFsbHwxfHx8fHx8Mnx8MTcyNTQ0NTE2NHw&ixlib=rb-4.0.3&q=85
		@Json(name = "regular") val regular: String, // https://images.unsplash.com/photo-1721332154373-17e78d19b4a4?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w2NDk1MTZ8MXwxfGFsbHwxfHx8fHx8Mnx8MTcyNTQ0NTE2NHw&ixlib=rb-4.0.3&q=80&w=1080
		@Json(name = "small") val small: String, // https://images.unsplash.com/photo-1721332154373-17e78d19b4a4?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w2NDk1MTZ8MXwxfGFsbHwxfHx8fHx8Mnx8MTcyNTQ0NTE2NHw&ixlib=rb-4.0.3&q=80&w=400
		@Json(name = "thumb") val thumb: String, // https://images.unsplash.com/photo-1721332154373-17e78d19b4a4?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w2NDk1MTZ8MXwxfGFsbHwxfHx8fHx8Mnx8MTcyNTQ0NTE2NHw&ixlib=rb-4.0.3&q=80&w=200
		@Json(name = "small_s3") val smallS3: String // https://s3.us-west-2.amazonaws.com/images.unsplash.com/small/photo-1721332154373-17e78d19b4a4
	)
	
	@Keep
	data class Links(
		@Json(name = "self") val self: String, // https://api.unsplash.com/photos/a-person-holding-a-nintendo-wii-game-controller-IJolVhJKk7c
		@Json(name = "html") val html: String, // https://unsplash.com/photos/a-person-holding-a-nintendo-wii-game-controller-IJolVhJKk7c
		@Json(name = "download") val download: String, // https://unsplash.com/photos/IJolVhJKk7c/download?ixid=M3w2NDk1MTZ8MXwxfGFsbHwxfHx8fHx8Mnx8MTcyNTQ0NTE2NHw
		@Json(name = "download_location") val downloadLocation: String // https://api.unsplash.com/photos/IJolVhJKk7c/download?ixid=M3w2NDk1MTZ8MXwxfGFsbHwxfHx8fHx8Mnx8MTcyNTQ0NTE2NHw
	)
	
	@Keep
	data class Sponsorship(
		@Json(name = "impression_urls") val impressionUrls: List<String>,
		@Json(name = "tagline") val tagline: String, // Memory for every endeavor
		@Json(name = "tagline_url") val taglineUrl: String, // https://www.samsung.com/us/memory-storage/
		@Json(name = "sponsor") val sponsor: Sponsor
	) {
		@Keep
		data class Sponsor(
			@Json(name = "id") val id: String, // eySMK9KwmJU
			@Json(name = "updated_at") val updatedAt: String, // 2024-09-03T20:00:39Z
			@Json(name = "username") val username: String, // samsungmemory
			@Json(name = "name") val name: String, // Samsung Memory
			@Json(name = "first_name") val firstName: String, // Samsung
			@Json(name = "last_name") val lastName: String, // Memory
			@Json(name = "twitter_username") val twitterUsername: String, // SamsungSemiUS
			@Json(name = "portfolio_url") val portfolioUrl: String, // http://www.samsung.com/us/computing/memory-storage/
			@Json(name = "bio") val bio: String, // Memory for every endeavor – get fast storage solutions that work seamlessly with your devices.
			@Json(name = "location") val location: Any?, // null
			@Json(name = "links") val links: Links,
			@Json(name = "profile_image") val profileImage: ProfileImage,
			@Json(name = "instagram_username") val instagramUsername: String, // samsungsemiconductor
			@Json(name = "total_collections") val totalCollections: Int, // 1
			@Json(name = "total_likes") val totalLikes: Int, // 0
			@Json(name = "total_photos") val totalPhotos: Int, // 880
			@Json(name = "total_promoted_photos") val totalPromotedPhotos: Int, // 38
			@Json(name = "total_illustrations") val totalIllustrations: Int, // 0
			@Json(name = "total_promoted_illustrations") val totalPromotedIllustrations: Int, // 0
			@Json(name = "accepted_tos") val acceptedTos: Boolean, // true
			@Json(name = "for_hire") val forHire: Boolean, // false
			@Json(name = "social") val social: Social
		) {
			@Keep
			data class Links(
				@Json(name = "self") val self: String, // https://api.unsplash.com/users/samsungmemory
				@Json(name = "html") val html: String, // https://unsplash.com/@samsungmemory
				@Json(name = "photos") val photos: String, // https://api.unsplash.com/users/samsungmemory/photos
				@Json(name = "likes") val likes: String, // https://api.unsplash.com/users/samsungmemory/likes
				@Json(name = "portfolio") val portfolio: String, // https://api.unsplash.com/users/samsungmemory/portfolio
				@Json(name = "following") val following: String, // https://api.unsplash.com/users/samsungmemory/following
				@Json(name = "followers") val followers: String // https://api.unsplash.com/users/samsungmemory/followers
			)
			
			@Keep
			data class ProfileImage(
				@Json(name = "small") val small: String, // https://images.unsplash.com/profile-1602741027167-c4d707fcfc85image?ixlib=rb-4.0.3&crop=faces&fit=crop&w=32&h=32
				@Json(name = "medium") val medium: String, // https://images.unsplash.com/profile-1602741027167-c4d707fcfc85image?ixlib=rb-4.0.3&crop=faces&fit=crop&w=64&h=64
				@Json(name = "large") val large: String // https://images.unsplash.com/profile-1602741027167-c4d707fcfc85image?ixlib=rb-4.0.3&crop=faces&fit=crop&w=128&h=128
			)
			
			@Keep
			data class Social(
				@Json(name = "instagram_username") val instagramUsername: String, // samsungsemiconductor
				@Json(name = "portfolio_url") val portfolioUrl: String, // http://www.samsung.com/us/computing/memory-storage/
				@Json(name = "twitter_username") val twitterUsername: String, // SamsungSemiUS
				@Json(name = "paypal_email") val paypalEmail: Any? // null
			)
		}
	}
	
	@Keep
	data class TopicSubmissions(
		@Json(name = "street-photography") val streetPhotography: StreetPhotography?,
		@Json(name = "nature") val nature: Nature?,
		@Json(name = "wallpapers") val wallpapers: Wallpapers?,
		@Json(name = "travel") val travel: Travel?,
		@Json(name = "archival") val archival: Archival?,
		@Json(name = "architecture-interior") val architectureInterior: ArchitectureInterior?,
		@Json(name = "experimental") val experimental: Experimental?,
		@Json(name = "spirituality") val spirituality: Spirituality?,
		@Json(name = "film") val film: Film?
	) {
		@Keep
		data class StreetPhotography(
			@Json(name = "status") val status: String, // approved
//            @Json(name = "approved_on") val approvedOn: String // 2024-09-02T13:36:17Z
		)
		
		@Keep
		data class Nature(
			@Json(name = "status") val status: String, // approved
//            @Json(name = "approved_on") val approvedOn: String // 2024-09-02T10:13:03Z
		)
		
		@Keep
		data class Wallpapers(
			@Json(name = "status") val status: String // rejected
		)
		
		@Keep
		data class Travel(
			@Json(name = "status") val status: String, // approved
//            @Json(name = "approved_on") val approvedOn: String? // 2024-09-02T14:52:12Z
		)
		
		@Keep
		data class Archival(
			@Json(name = "status") val status: String, // approved
//            @Json(name = "approved_on") val approvedOn: String // 2024-08-29T11:03:22Z
		)
		
		@Keep
		data class ArchitectureInterior(
			@Json(name = "status") val status: String // rejected
		)
		
		@Keep
		data class Experimental(
			@Json(name = "status") val status: String, // unevaluated
//            @Json(name = "approved_on") val approvedOn: String? // 2024-09-03T12:02:31Z
		)
		
		@Keep
		data class Spirituality(
			@Json(name = "status") val status: String // unevaluated
		)
		
		@Keep
		data class Film(
			@Json(name = "status") val status: String, // approved
//            @Json(name = "approved_on") val approvedOn: String // 2024-08-21T13:11:28Z
		)
	}
	
	@Keep
	data class User(
		@Json(name = "id") val id: String, // eySMK9KwmJU
		@Json(name = "updated_at") val updatedAt: String, // 2024-09-03T20:00:39Z
		@Json(name = "username") val username: String, // samsungmemory
		@Json(name = "name") val name: String, // Samsung Memory
		@Json(name = "first_name") val firstName: String, // Samsung
		@Json(name = "last_name") val lastName: String?, // Memory
		@Json(name = "twitter_username") val twitterUsername: String?, // SamsungSemiUS
		@Json(name = "portfolio_url") val portfolioUrl: String?, // http://www.samsung.com/us/computing/memory-storage/
		@Json(name = "bio") val bio: String?, // Memory for every endeavor – get fast storage solutions that work seamlessly with your devices.
		@Json(name = "location") val location: String?, // 🌎
		@Json(name = "links") val links: Links,
		@Json(name = "profile_image") val profileImage: ProfileImage,
		@Json(name = "instagram_username") val instagramUsername: String?, // samsungsemiconductor
		@Json(name = "total_collections") val totalCollections: Int, // 1
		@Json(name = "total_likes") val totalLikes: Int, // 0
		@Json(name = "total_photos") val totalPhotos: Int, // 880
		@Json(name = "total_promoted_photos") val totalPromotedPhotos: Int, // 38
		@Json(name = "total_illustrations") val totalIllustrations: Int, // 0
		@Json(name = "total_promoted_illustrations") val totalPromotedIllustrations: Int, // 0
		@Json(name = "accepted_tos") val acceptedTos: Boolean, // true
		@Json(name = "for_hire") val forHire: Boolean, // false
		@Json(name = "social") val social: Social
	) {
		@Keep
		data class Links(
			@Json(name = "self") val self: String, // https://api.unsplash.com/users/samsungmemory
			@Json(name = "html") val html: String, // https://unsplash.com/@samsungmemory
			@Json(name = "photos") val photos: String, // https://api.unsplash.com/users/samsungmemory/photos
			@Json(name = "likes") val likes: String, // https://api.unsplash.com/users/samsungmemory/likes
			@Json(name = "portfolio") val portfolio: String, // https://api.unsplash.com/users/samsungmemory/portfolio
			@Json(name = "following") val following: String, // https://api.unsplash.com/users/samsungmemory/following
			@Json(name = "followers") val followers: String // https://api.unsplash.com/users/samsungmemory/followers
		)
		
		@Keep
		data class ProfileImage(
			@Json(name = "small") val small: String, // https://images.unsplash.com/profile-1602741027167-c4d707fcfc85image?ixlib=rb-4.0.3&crop=faces&fit=crop&w=32&h=32
			@Json(name = "medium") val medium: String, // https://images.unsplash.com/profile-1602741027167-c4d707fcfc85image?ixlib=rb-4.0.3&crop=faces&fit=crop&w=64&h=64
			@Json(name = "large") val large: String // https://images.unsplash.com/profile-1602741027167-c4d707fcfc85image?ixlib=rb-4.0.3&crop=faces&fit=crop&w=128&h=128
		)
		
		@Keep
		data class Social(
			@Json(name = "instagram_username") val instagramUsername: String?, // samsungsemiconductor
			@Json(name = "portfolio_url") val portfolioUrl: String?, // http://www.samsung.com/us/computing/memory-storage/
			@Json(name = "twitter_username") val twitterUsername: String?, // SamsungSemiUS
			@Json(name = "paypal_email") val paypalEmail: Any? // null
		)
	}
}
