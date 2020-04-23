package non.shahad.stayhomegallery.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import non.shahad.stayhomegallery.data.db.entity.Post

@Dao
abstract class BookmarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertIntoBookmark(post: Post)

    @Query("SELECT * FROM post WHERE is_bookmark")
    abstract fun getAllBookmarks() : List<Post>
}