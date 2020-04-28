package non.shahad.stayhomegallery.data.local.mapper

import non.shahad.stayhomegallery.data.db.entity.Bookmark
import non.shahad.stayhomegallery.data.db.entity.Post
import java.util.*

fun Post.mapToBookmark() : Bookmark{
    return Bookmark(
        this.originalId,
        this.width,
        this.height,
        this.description,
        this.altDescription,
        this.from,
        this.color,
        this.likes,
        this.img,
        this.user,
        this.page,
        true
    )

}