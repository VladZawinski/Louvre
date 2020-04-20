package non.shahad.stayhomegallery.data.local.mapper

import non.shahad.stayhomegallery.data.db.entity.Post
import non.shahad.stayhomegallery.data.model.UnsplashResponse

fun List<Post>.mapToUnsplashResponse(page : String) : UnsplashResponse{
    return UnsplashResponse(
        page = page,
        posts = this
    )
}