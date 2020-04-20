package non.shahad.stayhomegallery.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "post")
data class Post (
    @PrimaryKey
    @field:Json(name = "original_id")
    val originalId : String,

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

    @ColumnInfo(name = "page")
    var page : Long
)

data class Img(
    @field:Json(name = "high")val high : String,
    @field:Json(name = "low")val low : String
)