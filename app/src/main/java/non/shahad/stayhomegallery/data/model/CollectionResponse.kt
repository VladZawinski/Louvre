package non.shahad.stayhomegallery.data.model

import com.squareup.moshi.Json
import non.shahad.stayhomegallery.data.db.entity.Collection

data class CollectionResponse (
    @field:Json(name = "page")
    val page : Long,
    @field:Json(name = "collections")
    val collections : List<Collection>
)