package non.shahad.stayhomegallery.data.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "post")
data class Post (
    @PrimaryKey
    @field:Json(name = "original_id")
    var originalId : String,

    @ColumnInfo(name = "width")
    @field:Json(name = "width")val width : Long,

    @ColumnInfo(name = "height")
    @field:Json(name = "height")val height : Long,

    @ColumnInfo(name = "description")
    @field:Json(name = "description")val description : String? = "",

    @ColumnInfo(name = "alt_description")
    @field:Json(name = "alt_description")val altDescription : String? = "",

    @ColumnInfo(name = "from")
    @field:Json(name = "from")val from : String,

//    @ColumnInfo(name = "tags")
//    @field:Json(name = "tags")val tags : List<String>?,

    @ColumnInfo(name = "color")
    @field:Json(name = "color") val color : String,

    @ColumnInfo(name = "likes")
    @field:Json(name = "likes")val likes : Long,

    @Embedded
    @field:Json(name = "img")val img : Img,

    @Embedded
    @field:Json(name ="user")val user : User,

    @ColumnInfo(name = "page")
    var page : Long,

    @ColumnInfo(name = "is_bookmark")
    var isBookmark : Boolean = false
) : Parcelable

@Parcelize
data class Img(
    @field:Json(name = "high")val high : String,
    @field:Json(name = "low")val low : String
) : Parcelable

@Parcelize
data class User(
    @field:Json(name ="bio")val bio: String? = "No Bio",
    @field:Json(name ="id")val id: String,
    @field:Json(name ="instagram_username")val instagramName: String? = "NaN",
    @field:Json(name ="name")val name: String,
    @field:Json(name ="portfolio_url")val portfolioUrl: String? = "NaN",
    @field:Json(name ="profile_image")val profileImage: String,
    @field:Json(name ="total_likes")val totalLikes: Int,
    @field:Json(name ="total_photos")val totalPhotos: Int,
    @field:Json(name ="twitter_username")val twitterUsername: String? = "NaN",
    @field:Json(name ="username")val username: String
) : Parcelable