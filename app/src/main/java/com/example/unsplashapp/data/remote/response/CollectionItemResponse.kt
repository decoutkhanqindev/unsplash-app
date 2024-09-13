package com.example.unsplashapp.data.remote.response


import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import androidx.annotation.Keep

@Keep
data class CollectionItemResponse(
  @Json(name = "id") val id: String, // hhbDCBz39e4
  @Json(name = "title") val title: String?, // Fall Wallpapers
  @Json(name = "description") val description: String?, // Embrace the warmth of autumn with this curated collection, 'Fall Wallpapers', perfect for refreshing your mobile screen.
  @Json(name = "published_at") val publishedAt: String?, // 2024-09-13T13:18:08Z
  @Json(name = "last_collected_at") val lastCollectedAt: String?, // 2024-09-13T13:18:05Z
  @Json(name = "updated_at") val updatedAt: String?, // 2024-09-13T13:18:08Z
  @Json(name = "featured") val featured: Boolean, // true
  @Json(name = "total_photos") val totalPhotos: Int, // 60
  @Json(name = "private") val `private`: Boolean, // false
  @Json(name = "share_key") val shareKey: String?, // bab7fb3e006f2f1400960ad62aa9a6a6
  @Json(name = "tags") val tags: List<Tag>,
  @Json(name = "links") val links: Links,
  @Json(name = "user") val user: User,
  @Json(name = "cover_photo") val coverPhoto: CoverPhoto,
  @Json(name = "preview_photos") val previewPhotos: List<PreviewPhoto>
) {
  @Keep
  data class Tag(
    @Json(name = "type") val type: String?, // landing_page
    @Json(name = "title") val title: String?, // wallpaper
    @Json(name = "source") val source: Source?
  ) {
    @Keep
    data class Source(
      @Json(name = "ancestry") val ancestry: Ancestry,
      @Json(name = "title") val title: String?, // HD Wallpapers
      @Json(name = "subtitle") val subtitle: String?, // Download Free Wallpapers
      @Json(name = "description") val description: String?, // Choose from the highest quality selection of high-definition wallpapers–all submitted by our talented community of contributors. Free to download and use for your mobile and desktop screens.
      @Json(name = "meta_title") val metaTitle: String?, // Download Free HD Wallpapers [Mobile + Desktop] | Unsplash
      @Json(name = "meta_description") val metaDescription: String?, // Download the best HD and Ultra HD Wallpapers for free. Use them as wallpapers for your mobile or desktop screens.
      @Json(name = "cover_photo") val coverPhoto: CoverPhoto,
      @Json(name = "affiliate_search_query") val affiliateSearchQuery: Any? // null
    ) {
      @Keep
      data class Ancestry(
        @Json(name = "type") val type: Type,
        @Json(name = "category") val category: Category?,
        @Json(name = "subcategory") val subcategory: Subcategory?
      ) {
        @Keep
        data class Type(
          @Json(name = "slug") val slug: String?, // wallpapers
          @Json(name = "pretty_slug") val prettySlug: String? // HD Wallpapers
        )
        
        @Keep
        data class Category(
          @Json(name = "slug") val slug: String?, // nature
          @Json(name = "pretty_slug") val prettySlug: String? // Nature
        )
        
        @Keep
        data class Subcategory(
          @Json(name = "slug") val slug: String?, // fall
          @Json(name = "pretty_slug") val prettySlug: String? // Fall
        )
      }
      
      @Keep
      data class CoverPhoto(
        @Json(name = "id") val id: String, // VEkIsvDviSs
        @Json(name = "slug") val slug: String?, // a-mountain-with-a-pink-sky-above-the-clouds-VEkIsvDviSs
        @Json(name = "alternative_slugs") val alternativeSlugs: AlternativeSlugs,
        @Json(name = "created_at") val createdAt: String?, // 2018-10-23T05:38:21Z
        @Json(name = "updated_at") val updatedAt: String?, // 2024-09-08T00:21:26Z
        @Json(name = "promoted_at") val promotedAt: String?, // 2018-10-24T13:12:35Z
        @Json(name = "width") val width: Int, // 5000
        @Json(name = "height") val height: Int, // 3333
        @Json(name = "color") val color: String?, // #f3c0c0
        @Json(name = "blur_hash") val blurHash: String?, // LlLf,=%2WBax}nfhfkj[^iW.WBof
        @Json(name = "description") val description: String?, // Life is full of adventures. This image was created during one of my own adventures on the top of Fronalpstock in Switzerland. During the day thousands and thousands of tourists  where passing by this spot. But the last chairlift was running at 5:30pm. Suddently the place became very quiet and calm. The fog started to clear up and reveal the two peaks.  This image represents one of the most beautiful sunsets I ever saw.
        @Json(name = "alt_description") val altDescription: String?, // a mountain with a pink sky above the clouds
        @Json(name = "breadcrumbs") val breadcrumbs: List<Breadcrumb>,
        @Json(name = "urls") val urls: Urls,
        @Json(name = "links") val links: Links,
        @Json(name = "likes") val likes: Int, // 1189
        @Json(name = "liked_by_user") val likedByUser: Boolean, // false
        @Json(name = "current_user_collections") val currentUserCollections: List<Any?>,
        @Json(name = "sponsorship") val sponsorship: Any?, // null
        @Json(name = "topic_submissions") val topicSubmissions: TopicSubmissions?,
        @Json(name = "asset_type") val assetType: String?, // photo
//        @Json(name = "premium") val premium: Boolean, // false
//        @Json(name = "plus") val plus: Boolean, // false
        @Json(name = "user") val user: User
      ) {
        @Keep
        data class AlternativeSlugs(
          @Json(name = "en") val en: String?, // a-mountain-with-a-pink-sky-above-the-clouds-VEkIsvDviSs
          @Json(name = "es") val es: String?, // una-montana-con-un-cielo-rosado-por-encima-de-las-nubes-VEkIsvDviSs
          @Json(name = "ja") val ja: String?, // 雲の上にピンク色の空が広がる山-VEkIsvDviSs
          @Json(name = "fr") val fr: String?, // une-montagne-avec-un-ciel-rose-au-dessus-des-nuages-VEkIsvDviSs
          @Json(name = "it") val it: String?, // una-montagna-con-un-cielo-rosa-sopra-le-nuvole-VEkIsvDviSs
          @Json(name = "ko") val ko: String?, // 구름-위로-분홍빛-하늘이-있는-산-VEkIsvDviSs
          @Json(name = "de") val de: String?, // ein-berg-mit-einem-rosa-himmel-uber-den-wolken-VEkIsvDviSs
          @Json(name = "pt") val pt: String? // uma-montanha-com-um-ceu-rosa-acima-das-nuvens-VEkIsvDviSs
        )
        
        @Keep
        data class Breadcrumb(
          @Json(name = "slug") val slug: String?, // images
          @Json(name = "title") val title: String?, // 1,000,000+ Free Images
          @Json(name = "index") val index: Int, // 0
          @Json(name = "type") val type: String? // landing_page
        )
        
        @Keep
        data class Urls(
          @Json(name = "raw") val raw: String?, // https://images.unsplash.com/photo-1540270776932-e72e7c2d11cd?ixlib=rb-4.0.3
          @Json(name = "full") val full: String?, // https://images.unsplash.com/photo-1540270776932-e72e7c2d11cd?ixlib=rb-4.0.3&q=85&fm=jpg&crop=entropy&cs=srgb
          @Json(name = "regular") val regular: String?, // https://images.unsplash.com/photo-1540270776932-e72e7c2d11cd?ixlib=rb-4.0.3&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max
          @Json(name = "small") val small: String?, // https://images.unsplash.com/photo-1540270776932-e72e7c2d11cd?ixlib=rb-4.0.3&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max
          @Json(name = "thumb") val thumb: String?, // https://images.unsplash.com/photo-1540270776932-e72e7c2d11cd?ixlib=rb-4.0.3&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max
          @Json(name = "small_s3") val smallS3: String? // https://s3.us-west-2.amazonaws.com/images.unsplash.com/small/photo-1540270776932-e72e7c2d11cd
        )
        
        @Keep
        data class Links(
          @Json(name = "self") val self: String?, // https://api.unsplash.com/photos/a-mountain-with-a-pink-sky-above-the-clouds-VEkIsvDviSs
          @Json(name = "html") val html: String?, // https://unsplash.com/photos/a-mountain-with-a-pink-sky-above-the-clouds-VEkIsvDviSs
          @Json(name = "download") val download: String?, // https://unsplash.com/photos/VEkIsvDviSs/download
          @Json(name = "download_location") val downloadLocation: String? // https://api.unsplash.com/photos/VEkIsvDviSs/download
        )
        
        @Keep
        data class TopicSubmissions(
          @Json(name = "nature") val nature: Nature?,
          @Json(name = "wallpapers") val wallpapers: Wallpapers?
        ) {
          @Keep
          data class Nature(
            @Json(name = "status") val status: String?, // approved
            @Json(name = "approved_on") val approvedOn: String? // 2020-04-06T14:20:12Z
          )
          
          @Keep
          data class Wallpapers(
            @Json(name = "status") val status: String?, // approved
            @Json(name = "approved_on") val approvedOn: String? // 2020-04-06T14:20:09Z
          )
        }
        
        @Keep
        data class User(
          @Json(name = "id") val id: String, // 1oL7MvktvW4
          @Json(name = "updated_at") val updatedAt: String?, // 2024-08-16T01:55:48Z
          @Json(name = "username") val username: String?, // borisbaldinger
          @Json(name = "name") val name: String?, // Boris Baldinger
          @Json(name = "first_name") val firstName: String?, // Boris
          @Json(name = "last_name") val lastName: String?, // Baldinger
          @Json(name = "twitter_username") val twitterUsername: String?, // borisbaldinger
          @Json(name = "portfolio_url") val portfolioUrl: String?, // https://www.boris-baldinger.com
          @Json(name = "bio") val bio: String?, // Father of 3 | Business photographer with a fable for nature | Speaker | Teacher | Social Media Fan
          @Json(name = "location") val location: String?, // Switzerland
          @Json(name = "links") val links: Links,
          @Json(name = "profile_image") val profileImage: ProfileImage,
          @Json(name = "instagram_username") val instagramUsername: String?, // borisbaldinger
          @Json(name = "total_collections") val totalCollections: Int, // 3
          @Json(name = "total_likes") val totalLikes: Int, // 71
          @Json(name = "total_photos") val totalPhotos: Int, // 16
          @Json(name = "total_promoted_photos") val totalPromotedPhotos: Int, // 12
          @Json(name = "total_illustrations") val totalIllustrations: Int, // 0
          @Json(name = "total_promoted_illustrations") val totalPromotedIllustrations: Int, // 0
          @Json(name = "accepted_tos") val acceptedTos: Boolean, // true
          @Json(name = "for_hire") val forHire: Boolean, // true
          @Json(name = "social") val social: Social
        ) {
          @Keep
          data class Links(
            @Json(name = "self") val self: String?, // https://api.unsplash.com/users/borisbaldinger
            @Json(name = "html") val html: String?, // https://unsplash.com/@borisbaldinger
            @Json(name = "photos") val photos: String?, // https://api.unsplash.com/users/borisbaldinger/photos
            @Json(name = "likes") val likes: String?, // https://api.unsplash.com/users/borisbaldinger/likes
            @Json(name = "portfolio") val portfolio: String?, // https://api.unsplash.com/users/borisbaldinger/portfolio
            @Json(name = "following") val following: String?, // https://api.unsplash.com/users/borisbaldinger/following
            @Json(name = "followers") val followers: String? // https://api.unsplash.com/users/borisbaldinger/followers
          )
          
          @Keep
          data class ProfileImage(
            @Json(name = "small") val small: String?, // https://images.unsplash.com/profile-1552053169443-ad3a5339ce69?ixlib=rb-4.0.3&crop=faces&fit=crop&w=32&h=32
            @Json(name = "medium") val medium: String?, // https://images.unsplash.com/profile-1552053169443-ad3a5339ce69?ixlib=rb-4.0.3&crop=faces&fit=crop&w=64&h=64
            @Json(name = "large") val large: String? // https://images.unsplash.com/profile-1552053169443-ad3a5339ce69?ixlib=rb-4.0.3&crop=faces&fit=crop&w=128&h=128
          )
          
          @Keep
          data class Social(
            @Json(name = "instagram_username") val instagramUsername: String?, // borisbaldinger
            @Json(name = "portfolio_url") val portfolioUrl: String?, // https://www.boris-baldinger.com
            @Json(name = "twitter_username") val twitterUsername: String?, // borisbaldinger
            @Json(name = "paypal_email") val paypalEmail: Any? // null
          )
        }
      }
    }
  }
  
  @Keep
  data class Links(
    @Json(name = "self") val self: String?, // https://api.unsplash.com/collections/hhbDCBz39e4
    @Json(name = "html") val html: String?, // https://unsplash.com/collections/hhbDCBz39e4/fall-wallpapers
    @Json(name = "photos") val photos: String?, // https://api.unsplash.com/collections/hhbDCBz39e4/photos
    @Json(name = "related") val related: String? // https://api.unsplash.com/collections/hhbDCBz39e4/related
  )
  
  @Suppress("DEPRECATION")
  @Keep
  data class User(
    @Json(name = "id") val id: String, // iwi9-4OXLYY
    @Json(name = "updated_at") val updatedAt: String?, // 2024-09-13T15:54:50Z
    @Json(name = "username") val username: String?, // unsplashplus
    @Json(name = "name") val name: String?, // Unsplash+ Collections
    @Json(name = "first_name") val firstName: String?, // Unsplash+
    @Json(name = "last_name") val lastName: String?, // Collections
    @Json(name = "twitter_username") val twitterUsername: String?, // null
    @Json(name = "portfolio_url") val portfolioUrl: String?, // null
    @Json(name = "bio") val bio: String?, // null
    @Json(name = "location") val location: String?, // null
    @Json(name = "links") val links: Links,
    @Json(name = "profile_image") val profileImage: ProfileImage,
    @Json(name = "instagram_username") val instagramUsername: String?, // null
    @Json(name = "total_collections") val totalCollections: Int, // 172
    @Json(name = "total_likes") val totalLikes: Int, // 254
    @Json(name = "total_photos") val totalPhotos: Int, // 0
    @Json(name = "total_promoted_photos") val totalPromotedPhotos: Int, // 0
    @Json(name = "total_illustrations") val totalIllustrations: Int, // 0
    @Json(name = "total_promoted_illustrations") val totalPromotedIllustrations: Int, // 0
    @Json(name = "accepted_tos") val acceptedTos: Boolean, // true
    @Json(name = "for_hire") val forHire: Boolean, // false
    @Json(name = "social") val social: Social
  ) : Parcelable {
    
    constructor(parcel: Parcel) : this(
      parcel.readString()!!,
      parcel.readString()!!,
      parcel.readString()!!,
      parcel.readString()!!,
      parcel.readString()!!,
      parcel.readString()!!,
      parcel.readString(),
      parcel.readString(),
      parcel.readString(),
      parcel.readString(),
      parcel.readParcelable(Links::class.java.classLoader)!!,
      parcel.readParcelable(ProfileImage::class.java.classLoader)!!,
      parcel.readString(),
      parcel.readInt(),
      parcel.readInt(),
      parcel.readInt(),
      parcel.readInt(),
      parcel.readInt(),
      parcel.readInt(),
      parcel.readByte() != 0.toByte(),
      parcel.readByte() != 0.toByte(),
      parcel.readParcelable(Social::class.java.classLoader)!!
    )
    
    override fun writeToParcel(parcel: Parcel, flags: Int) {
      parcel.writeString(id)
      parcel.writeString(updatedAt)
      parcel.writeString(username)
      parcel.writeString(name)
      parcel.writeString(firstName)
      parcel.writeString(lastName)
      parcel.writeString(twitterUsername)
      parcel.writeString(portfolioUrl)
      parcel.writeString(bio)
      parcel.writeString(location)
      parcel.writeParcelable(links, flags)
      parcel.writeParcelable(profileImage, flags)
      parcel.writeString(instagramUsername)
      parcel.writeInt(totalCollections)
      parcel.writeInt(totalLikes)
      parcel.writeInt(totalPhotos)
      parcel.writeInt(totalPromotedPhotos)
      parcel.writeInt(totalIllustrations)
      parcel.writeInt(totalPromotedIllustrations)
      parcel.writeByte(if (acceptedTos) 1 else 0)
      parcel.writeByte(if (forHire) 1 else 0)
      parcel.writeParcelable(social, flags)
    }
    
    override fun describeContents(): Int {
      return 0
    }
    
    companion object CREATOR : Parcelable.Creator<User> {
      override fun createFromParcel(parcel: Parcel): User {
        return User(parcel)
      }
      
      override fun newArray(size: Int): Array<User?> {
        return arrayOfNulls(size)
      }
    }
    
    @Keep
    data class Links(
      @Json(name = "self") val self: String?, // https://api.unsplash.com/users/unsplashplus
      @Json(name = "html") val html: String?, // https://unsplash.com/@unsplashplus
      @Json(name = "photos") val photos: String?, // https://api.unsplash.com/users/unsplashplus/photos
      @Json(name = "likes") val likes: String?, // https://api.unsplash.com/users/unsplashplus/likes
      @Json(name = "portfolio") val portfolio: String?, // https://api.unsplash.com/users/unsplashplus/portfolio
      @Json(name = "following") val following: String?, // https://api.unsplash.com/users/unsplashplus/following
      @Json(name = "followers") val followers: String? // https://api.unsplash.com/users/unsplashplus/followers
    ) : Parcelable {
      constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
      )
      
      override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(self)
        parcel.writeString(html)
        parcel.writeString(photos)
        parcel.writeString(likes)
        parcel.writeString(portfolio)
        parcel.writeString(following)
        parcel.writeString(followers)
      }
      
      override fun describeContents(): Int {
        return 0
      }
      
      companion object CREATOR : Parcelable.Creator<Links> {
        override fun createFromParcel(parcel: Parcel): Links {
          return Links(parcel)
        }
        
        override fun newArray(size: Int): Array<Links?> {
          return arrayOfNulls(size)
        }
      }
    }
    
    @Keep
    data class ProfileImage(
      @Json(name = "small") val small: String?, // https://images.unsplash.com/profile-1714421769490-6918cb0c83a9image?ixlib=rb-4.0.3&crop=faces&fit=crop&w=32&h=32
      @Json(name = "medium") val medium: String?, // https://images.unsplash.com/profile-1714421769490-6918cb0c83a9image?ixlib=rb-4.0.3&crop=faces&fit=crop&w=64&h=64
      @Json(name = "large") val large: String? // https://images.unsplash.com/profile-1714421769490-6918cb0c83a9image?ixlib=rb-4.0.3&crop=faces&fit=crop&w=128&h=128
    ) : Parcelable {
      constructor(parcel: Parcel) : this(
        parcel.readString()!!, parcel.readString()!!, parcel.readString()!!
      )
      
      override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(small)
        parcel.writeString(medium)
        parcel.writeString(large)
      }
      
      override fun describeContents(): Int {
        return 0
      }
      
      companion object CREATOR : Parcelable.Creator<ProfileImage> {
        override fun createFromParcel(parcel: Parcel): ProfileImage {
          return ProfileImage(parcel)
        }
        
        override fun newArray(size: Int): Array<ProfileImage?> {
          return arrayOfNulls(size)
        }
      }
    }
    
    @Keep
    data class Social(
      @Json(name = "instagram_username") val instagramUsername: String?, // null
      @Json(name = "portfolio_url") val portfolioUrl: String?, // null
      @Json(name = "twitter_username") val twitterUsername: String?, // null
      @Json(name = "paypal_email") val paypalEmail: String? // null
    ) : Parcelable {
      constructor(parcel: Parcel) : this(
        parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString()
      )
      
      override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(instagramUsername)
        parcel.writeString(portfolioUrl)
        parcel.writeString(twitterUsername)
        parcel.writeString(paypalEmail)
      }
      
      override fun describeContents(): Int {
        return 0
      }
      
      companion object CREATOR : Parcelable.Creator<Social> {
        override fun createFromParcel(parcel: Parcel): Social {
          return Social(parcel)
        }
        
        override fun newArray(size: Int): Array<Social?> {
          return arrayOfNulls(size)
        }
      }
    }
  }
  
  @Keep
  data class CoverPhoto(
    @Json(name = "id") val id: String, // i0xBtZQLGnM
    @Json(name = "slug") val slug: String?, // a-forest-filled-with-lots-of-tall-trees-i0xBtZQLGnM
    @Json(name = "alternative_slugs") val alternativeSlugs: AlternativeSlugs,
    @Json(name = "created_at") val createdAt: String?, // 2022-11-06T10:21:23Z
    @Json(name = "updated_at") val updatedAt: String?, // 2024-09-13T16:46:22Z
    @Json(name = "promoted_at") val promotedAt: String?, // 2024-09-13T15:10:36Z
    @Json(name = "width") val width: Int, // 4724
    @Json(name = "height") val height: Int, // 7086
    @Json(name = "color") val color: String?, // #262626
    @Json(name = "blur_hash") val blurHash: String?, // LA8DIW-UoJWV}@ofR+fks;I?I=oL
    @Json(name = "description") val description: String?, // Autumn walk
    @Json(name = "alt_description") val altDescription: String?, // a forest filled with lots of tall trees
    @Json(name = "breadcrumbs") val breadcrumbs: List<Any?>,
    @Json(name = "urls") val urls: Urls,
    @Json(name = "links") val links: Links,
    @Json(name = "likes") val likes: Int, // 34
    @Json(name = "liked_by_user") val likedByUser: Boolean, // false
    @Json(name = "current_user_collections") val currentUserCollections: List<Any?>,
    @Json(name = "sponsorship") val sponsorship: Any?, // null
    @Json(name = "topic_submissions") val topicSubmissions: TopicSubmissions?,
    @Json(name = "asset_type") val assetType: String?, // photo
    @Json(name = "user") val user: User
  ) {
    @Keep
    data class AlternativeSlugs(
      @Json(name = "en") val en: String?, // a-forest-filled-with-lots-of-tall-trees-i0xBtZQLGnM
      @Json(name = "es") val es: String?, // un-bosque-lleno-de-muchos-arboles-altos-i0xBtZQLGnM
      @Json(name = "ja") val ja: String?, // 背の高い木々がたくさん生い茂る森-i0xBtZQLGnM
      @Json(name = "fr") val fr: String?, // une-foret-remplie-de-grands-arbres-i0xBtZQLGnM
      @Json(name = "it") val it: String?, // una-foresta-piena-di-molti-alberi-ad-alto-fusto-i0xBtZQLGnM
      @Json(name = "ko") val ko: String?, // 키-큰-나무들로-가득한-숲-i0xBtZQLGnM
      @Json(name = "de") val de: String?, // ein-wald-mit-vielen-hohen-baumen-i0xBtZQLGnM
      @Json(name = "pt") val pt: String? // uma-floresta-cheia-de-muitas-arvores-altas-i0xBtZQLGnM
    )
    
    @Keep
    data class Urls(
      @Json(name = "raw") val raw: String?, // https://plus.unsplash.com/premium_photo-1667579006165-16b6f04ccc78?ixlib=rb-4.0.3
      @Json(name = "full") val full: String?, // https://plus.unsplash.com/premium_photo-1667579006165-16b6f04ccc78?ixlib=rb-4.0.3&q=85&fm=jpg&crop=entropy&cs=srgb
      @Json(name = "regular") val regular: String?, // https://plus.unsplash.com/premium_photo-1667579006165-16b6f04ccc78?ixlib=rb-4.0.3&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max
      @Json(name = "small") val small: String?, // https://plus.unsplash.com/premium_photo-1667579006165-16b6f04ccc78?ixlib=rb-4.0.3&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max
      @Json(name = "thumb") val thumb: String?, // https://plus.unsplash.com/premium_photo-1667579006165-16b6f04ccc78?ixlib=rb-4.0.3&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max
      @Json(name = "small_s3") val smallS3: String? // https://s3.us-west-2.amazonaws.com/images.unsplash.com/small/unsplash-premium-photos-production/premium_photo-1667579006165-16b6f04ccc78
    )
    
    @Keep
    data class Links(
      @Json(name = "self") val self: String?, // https://api.unsplash.com/photos/a-forest-filled-with-lots-of-tall-trees-i0xBtZQLGnM
      @Json(name = "html") val html: String?, // https://unsplash.com/photos/a-forest-filled-with-lots-of-tall-trees-i0xBtZQLGnM
      @Json(name = "download") val download: String?, // https://unsplash.com/photos/i0xBtZQLGnM/download
      @Json(name = "download_location") val downloadLocation: String? // https://api.unsplash.com/photos/i0xBtZQLGnM/download
    )
    
    @Keep
    data class TopicSubmissions(
      @Json(name = "wallpapers") val wallpapers: Wallpapers?,
      @Json(name = "travel") val travel: Travel?,
      @Json(name = "nature") val nature: Nature?
    ) {
      @Keep
      data class Wallpapers(
        @Json(name = "status") val status: String?, // approved
        @Json(name = "approved_on") val approvedOn: String? // 2024-09-13T15:10:48Z
      )
      
      @Keep
      data class Travel(
        @Json(name = "status") val status: String? // rejected
      )
      
      @Keep
      data class Nature(
        @Json(name = "status") val status: String?, // approved
        @Json(name = "approved_on") val approvedOn: String? // 2022-11-09T13:07:11Z
      )
    }
    
    @Keep
    data class User(
      @Json(name = "id") val id: String, // ILVykUeXteY
      @Json(name = "updated_at") val updatedAt: String?, // 2024-09-13T15:12:40Z
      @Json(name = "username") val username: String?, // lauraadaiphoto
      @Json(name = "name") val name: String?, // laura adai
      @Json(name = "first_name") val firstName: String?, // laura
      @Json(name = "last_name") val lastName: String?, // adai
      @Json(name = "twitter_username") val twitterUsername: Any?, // null
      @Json(name = "portfolio_url") val portfolioUrl: String?, // https://www.lauraadaiphotography.com/
      @Json(name = "bio") val bio: String?, // Italian photographer based in Milano.signed fine art prints available
      @Json(name = "location") val location: String?, // italy, milano
      @Json(name = "links") val links: Links,
      @Json(name = "profile_image") val profileImage: ProfileImage,
      @Json(name = "instagram_username") val instagramUsername: Any?, // null
      @Json(name = "total_collections") val totalCollections: Int, // 99
      @Json(name = "total_likes") val totalLikes: Int, // 1824
      @Json(name = "total_photos") val totalPhotos: Int, // 2073
      @Json(name = "total_promoted_photos") val totalPromotedPhotos: Int, // 127
      @Json(name = "total_illustrations") val totalIllustrations: Int, // 0
      @Json(name = "total_promoted_illustrations") val totalPromotedIllustrations: Int, // 0
      @Json(name = "accepted_tos") val acceptedTos: Boolean, // true
      @Json(name = "for_hire") val forHire: Boolean, // true
      @Json(name = "social") val social: Social
    ) {
      @Keep
      data class Links(
        @Json(name = "self") val self: String?, // https://api.unsplash.com/users/lauraadaiphoto
        @Json(name = "html") val html: String?, // https://unsplash.com/@lauraadaiphoto
        @Json(name = "photos") val photos: String?, // https://api.unsplash.com/users/lauraadaiphoto/photos
        @Json(name = "likes") val likes: String?, // https://api.unsplash.com/users/lauraadaiphoto/likes
        @Json(name = "portfolio") val portfolio: String?, // https://api.unsplash.com/users/lauraadaiphoto/portfolio
        @Json(name = "following") val following: String?, // https://api.unsplash.com/users/lauraadaiphoto/following
        @Json(name = "followers") val followers: String? // https://api.unsplash.com/users/lauraadaiphoto/followers
      )
      
      @Keep
      data class ProfileImage(
        @Json(name = "small") val small: String?, // https://images.unsplash.com/profile-1649845710179-adb858d60d5fimage?ixlib=rb-4.0.3&crop=faces&fit=crop&w=32&h=32
        @Json(name = "medium") val medium: String?, // https://images.unsplash.com/profile-1649845710179-adb858d60d5fimage?ixlib=rb-4.0.3&crop=faces&fit=crop&w=64&h=64
        @Json(name = "large") val large: String? // https://images.unsplash.com/profile-1649845710179-adb858d60d5fimage?ixlib=rb-4.0.3&crop=faces&fit=crop&w=128&h=128
      )
      
      @Keep
      data class Social(
        @Json(name = "instagram_username") val instagramUsername: Any?, // null
        @Json(name = "portfolio_url") val portfolioUrl: String?, // https://www.lauraadaiphotography.com/
        @Json(name = "twitter_username") val twitterUsername: Any?, // null
        @Json(name = "paypal_email") val paypalEmail: Any? // null
      )
    }
  }
  
  @Suppress("DEPRECATION")
  @Keep
  data class PreviewPhoto(
    @Json(name = "id") val id: String, // i0xBtZQLGnM
    @Json(name = "slug") val slug: String?, // a-forest-filled-with-lots-of-tall-trees-i0xBtZQLGnM
    @Json(name = "created_at") val createdAt: String?, // 2022-11-06T10:21:23Z
    @Json(name = "updated_at") val updatedAt: String?, // 2024-09-13T16:46:22Z
    @Json(name = "blur_hash") val blurHash: String?, // LA8DIW-UoJWV}@ofR+fks;I?I=oL
    @Json(name = "asset_type") val assetType: String?, // photo
    @Json(name = "urls") val urls: Urls
  ) : Parcelable {
    
    constructor(parcel: Parcel) : this(
      parcel.readString()!!,
      parcel.readString()!!,
      parcel.readString()!!,
      parcel.readString()!!,
      parcel.readString()!!,
      parcel.readString()!!,
      parcel.readParcelable(Urls::class.java.classLoader)!!
    )
    
    override fun writeToParcel(parcel: Parcel, flags: Int) {
      parcel.writeString(id)
      parcel.writeString(slug)
      parcel.writeString(createdAt)
      parcel.writeString(updatedAt)
      parcel.writeString(blurHash)
      parcel.writeString(assetType)
      parcel.writeParcelable(urls, flags)
    }
    
    override fun describeContents(): Int {
      return 0
    }
    
    companion object CREATOR : Parcelable.Creator<PreviewPhoto> {
      override fun createFromParcel(parcel: Parcel): PreviewPhoto {
        return PreviewPhoto(parcel)
      }
      
      override fun newArray(size: Int): Array<PreviewPhoto?> {
        return arrayOfNulls(size)
      }
    }
    
    @Keep
    data class Urls(
      @Json(name = "raw") val raw: String?, // https://plus.unsplash.com/premium_photo-1667579006165-16b6f04ccc78?ixlib=rb-4.0.3
      @Json(name = "full") val full: String?, // https://plus.unsplash.com/premium_photo-1667579006165-16b6f04ccc78?ixlib=rb-4.0.3&q=85&fm=jpg&crop=entropy&cs=srgb
      @Json(name = "regular") val regular: String?, // https://plus.unsplash.com/premium_photo-1667579006165-16b6f04ccc78?ixlib=rb-4.0.3&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max
      @Json(name = "small") val small: String?, // https://plus.unsplash.com/premium_photo-1667579006165-16b6f04ccc78?ixlib=rb-4.0.3&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max
      @Json(name = "thumb") val thumb: String?, // https://plus.unsplash.com/premium_photo-1667579006165-16b6f04ccc78?ixlib=rb-4.0.3&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max
      @Json(name = "small_s3") val smallS3: String? // https://s3.us-west-2.amazonaws.com/images.unsplash.com/small/unsplash-premium-photos-production/premium_photo-1667579006165-16b6f04ccc78
    ) : Parcelable {
      
      constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
      )
      
      override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(raw)
        parcel.writeString(full)
        parcel.writeString(regular)
        parcel.writeString(small)
        parcel.writeString(thumb)
        parcel.writeString(smallS3)
      }
      
      override fun describeContents(): Int {
        return 0
      }
      
      companion object CREATOR : Parcelable.Creator<Urls> {
        override fun createFromParcel(parcel: Parcel): Urls {
          return Urls(parcel)
        }
        
        override fun newArray(size: Int): Array<Urls?> {
          return arrayOfNulls(size)
        }
      }
    }
  }
}