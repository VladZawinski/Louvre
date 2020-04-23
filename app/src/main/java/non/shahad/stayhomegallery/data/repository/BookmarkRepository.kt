package non.shahad.stayhomegallery.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import non.shahad.stayhomegallery.data.db.dao.BookmarkDao
import non.shahad.stayhomegallery.data.db.entity.Post
import javax.inject.Inject

class BookmarkRepository @Inject constructor(
    val bookmarkDao : BookmarkDao
) {
    suspend fun insertIntoBookmark(post: Post) = withContext(Dispatchers.Default){
        return@withContext bookmarkDao.insertIntoBookmark(post)
    }

    suspend fun getAllBookmarks() : List<Post> = withContext(Dispatchers.Default){
        return@withContext bookmarkDao.getAllBookmarks()
    }
}