package non.shahad.stayhomegallery.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "collection")
data class Collection(
    @field:Json(name = "curated")
    @ColumnInfo(name= "curated")
    val curated: Boolean,

    @field:Json(name = "description")
    @ColumnInfo(name= "description")
    val description: String? = "",

    @field:Json(name = "featured")
    @ColumnInfo(name= "featured")
    val featured: Boolean,

    @PrimaryKey
    @field:Json(name = "id")
    @ColumnInfo(name= "id")
    val id: Int,

    @Embedded
    val links: Links,

    @field:Json(name = "preview_photos")
    @ColumnInfo(name= "preview_photos")
    val previewPhotos: List<PreviewPhoto>,

    @field:Json(name = "title")
    @ColumnInfo(name= "title")
    val title: String,

    @field:Json(name = "total_photos")
    @ColumnInfo(name= "total_photos")
    val totalPhotos: Int,

    @Embedded
    val user: User
)

data class Links(
    @field:Json(name = "html")
    val html: String,
    @field:Json(name = "related")
    val related: String
)

data class PreviewPhoto(
    @field:Json(name = "created_at")
    val createdAt: String,
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "updated_at")
    val updatedAt: String,
    @field:Json(name = "urls")
    val urls: Urls
)

data class Urls(
    @field:Json(name = "full")
    val full: String,
    @field:Json(name = "raw")
    val raw: String,
    @field:Json(name = "regular")
    val regular: String,
    @field:Json(name = "small")
    val small: String,
    @field:Json(name = "thumb")
    val thumb: String
)