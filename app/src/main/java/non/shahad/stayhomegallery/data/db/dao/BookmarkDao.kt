package non.shahad.stayhomegallery.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import non.shahad.stayhomegallery.data.db.entity.Bookmark
import non.shahad.stayhomegallery.data.db.entity.Post

@Dao
abstract class BookmarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertIntoBookmark(bookmark: Bookmark)

    @Query("SELECT * FROM bookmark WHERE is_bookmark")
    abstract fun getAllBookmarks() : List<Bookmark>

    @Query("SELECT * FROM bookmark WHERE originalId = :id")
    abstract fun getBookmarkById(id : String) : Bookmark?

    @Query("DELETE FROM bookmark WHERE originalId = :id")
    abstract fun removeFromBookmark(id : String)
}