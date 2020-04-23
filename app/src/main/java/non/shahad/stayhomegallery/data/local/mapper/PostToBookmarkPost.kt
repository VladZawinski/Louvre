package non.shahad.stayhomegallery.data.local.mapper

import non.shahad.stayhomegallery.data.db.entity.Post
import java.util.*

fun Post.mapToBookmark() : Post{
    return this.also {
        it.originalId = UUID.randomUUID().toString()
        it.isBookmark = true
    }
}