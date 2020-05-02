package non.shahad.stayhomegallery.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import non.shahad.stayhomegallery.data.db.dao.BookmarkDao
import non.shahad.stayhomegallery.data.db.entity.Bookmark
import non.shahad.stayhomegallery.data.db.entity.Post
import javax.inject.Inject

class BookmarkRepository @Inject constructor(
    val bookmarkDao : BookmarkDao
) {
    suspend fun insertIntoBookmark(bookmark : Bookmark) = withContext(Dispatchers.Default){
        return@withContext bookmarkDao.insertIntoBookmark(bookmark)
    }

    suspend fun removeFromBookmark(id : String) = withContext(Dispatchers.Default){
        return@withContext bookmarkDao.removeFromBookmark(id)
    }

    suspend fun getAllBookmarks() : List<Bookmark> = withContext(Dispatchers.Default){
        return@withContext bookmarkDao.getAllBookmarks()
    }

    suspend fun isAlreadyBookmark(postId : String) : Boolean= withContext(Dispatchers.Default){
        return@withContext bookmarkDao.getBookmarkById(postId) != null
    }
}