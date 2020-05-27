package non.shahad.stayhomegallery.data.model

import com.squareup.moshi.Json
import non.shahad.stayhomegallery.data.db.entity.Post

data class CollectionDetailResponse (
    @field:Json(name = "page")val page: String,
    @field:Json(name = "photos")val photos: List<Post>
)