package non.shahad.stayhomegallery.data.model

import com.squareup.moshi.Json
import non.shahad.stayhomegallery.data.db.entity.Post

data class SearchResponse (
    @field:Json(name = "total_result")val totalResult: Long,
    @field:Json(name = "page")val page: Long,
    @field:Json(name = "posts")val posts: List<Post>
)