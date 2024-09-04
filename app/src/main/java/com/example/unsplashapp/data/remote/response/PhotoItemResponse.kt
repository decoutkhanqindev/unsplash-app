package com.example.unsplashapp.data.remote.response


import com.squareup.moshi.Json
import androidx.annotation.Keep

@Keep
data class PhotoItemResponse(
    @Json(name = "id") val id: String, // _zpj6jT5s5A
    @Json(name = "slug") val slug: String, // a-man-holding-a-camera-in-his-hands-_zpj6jT5s5A
    @Json(name = "alternative_slugs") val alternativeSlugs: AlternativeSlugs,
    @Json(name = "created_at") val createdAt: String, // 2024-07-02T16:19:28Z
    @Json(name = "updated_at") val updatedAt: String, // 2024-09-02T02:29:39Z
    @Json(name = "promoted_at") val promotedAt: Any?, // null
    @Json(name = "width") val width: Int, // 3600
    @Json(name = "height") val height: Int, // 2396
    @Json(name = "color") val color: String, // #262626
    @Json(name = "blur_hash") val blurHash: String, // LGB3+:?wiv4Tks?bS%Io%#%MMxIA
    @Json(name = "description") val description: Any?, // null
    @Json(name = "alt_description") val altDescription: String, // A man holding a camera in his hands
    @Json(name = "breadcrumbs") val breadcrumbs: List<Any>,
    @Json(name = "urls") val urls: Urls,
    @Json(name = "links") val links: Links,
    @Json(name = "likes") val likes: Int, // 33
    @Json(name = "liked_by_user") val likedByUser: Boolean, // false
    @Json(name = "current_user_collections") val currentUserCollections: List<Any>,
    @Json(name = "sponsorship") val sponsorship: Sponsorship,
    @Json(name = "topic_submissions") val topicSubmissions: TopicSubmissions,
    @Json(name = "asset_type") val assetType: String, // photo
    @Json(name = "user") val user: User
) {
    @Keep
    data class AlternativeSlugs(
        @Json(name = "en") val en: String, // a-man-holding-a-camera-in-his-hands-_zpj6jT5s5A
        @Json(name = "es") val es: String, // un-hombre-con-una-camara-en-las-manos-_zpj6jT5s5A
        @Json(name = "ja") val ja: String, // カメラを手にした男性-_zpj6jT5s5A
        @Json(name = "fr") val fr: String, // un-homme-tenant-un-appareil-photo-dans-ses-mains-_zpj6jT5s5A
        @Json(name = "it") val it: String, // un-uomo-che-tiene-in-mano-una-macchina-fotografica-_zpj6jT5s5A
        @Json(name = "ko") val ko: String, // 손에-카메라를-들고-있는-남자-_zpj6jT5s5A
        @Json(name = "de") val de: String, // ein-mann-mit-einer-kamera-in-der-hand-_zpj6jT5s5A
        @Json(name = "pt") val pt: String // um-homem-segurando-uma-camera-nas-maos-_zpj6jT5s5A
    )

    @Keep
    data class Urls(
        @Json(name = "raw") val raw: String, // https://images.unsplash.com/photo-1719937051058-63705ed35502?ixid=M3w2NDk1MTZ8MXwxfGFsbHwxfHx8fHx8Mnx8MTcyNTQzNzIyNnw&ixlib=rb-4.0.3
        @Json(name = "full") val full: String, // https://images.unsplash.com/photo-1719937051058-63705ed35502?crop=entropy&cs=srgb&fm=jpg&ixid=M3w2NDk1MTZ8MXwxfGFsbHwxfHx8fHx8Mnx8MTcyNTQzNzIyNnw&ixlib=rb-4.0.3&q=85
        @Json(name = "regular") val regular: String, // https://images.unsplash.com/photo-1719937051058-63705ed35502?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w2NDk1MTZ8MXwxfGFsbHwxfHx8fHx8Mnx8MTcyNTQzNzIyNnw&ixlib=rb-4.0.3&q=80&w=1080
        @Json(name = "small") val small: String, // https://images.unsplash.com/photo-1719937051058-63705ed35502?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w2NDk1MTZ8MXwxfGFsbHwxfHx8fHx8Mnx8MTcyNTQzNzIyNnw&ixlib=rb-4.0.3&q=80&w=400
        @Json(name = "thumb") val thumb: String, // https://images.unsplash.com/photo-1719937051058-63705ed35502?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w2NDk1MTZ8MXwxfGFsbHwxfHx8fHx8Mnx8MTcyNTQzNzIyNnw&ixlib=rb-4.0.3&q=80&w=200
        @Json(name = "small_s3") val smallS3: String // https://s3.us-west-2.amazonaws.com/images.unsplash.com/small/photo-1719937051058-63705ed35502
    )

    @Keep
    data class Links(
        @Json(name = "self") val self: String, // https://api.unsplash.com/photos/a-man-holding-a-camera-in-his-hands-_zpj6jT5s5A
        @Json(name = "html") val html: String, // https://unsplash.com/photos/a-man-holding-a-camera-in-his-hands-_zpj6jT5s5A
        @Json(name = "download") val download: String, // https://unsplash.com/photos/_zpj6jT5s5A/download?ixid=M3w2NDk1MTZ8MXwxfGFsbHwxfHx8fHx8Mnx8MTcyNTQzNzIyNnw
        @Json(name = "download_location") val downloadLocation: String // https://api.unsplash.com/photos/_zpj6jT5s5A/download?ixid=M3w2NDk1MTZ8MXwxfGFsbHwxfHx8fHx8Mnx8MTcyNTQzNzIyNnw
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
    class TopicSubmissions

    @Keep
    data class User(
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