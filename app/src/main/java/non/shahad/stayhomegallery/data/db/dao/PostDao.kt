package non.shahad.stayhomegallery.data.db.dao

import androidx.room.*
import non.shahad.stayhomegallery.data.db.entity.Post

@Dao
interface PostDao {
    @Insert
    suspend fun insertAll(posts : List<Post>)

    @Insert
    suspend fun insertEach(post : Post)

    @Query("SELECT * FROM post WHERE page = :page")
    suspend fun getPostsByPage(page : Long) : List<Post>

    @Query("DELETE FROM post WHERE page = :page")
    suspend fun deletePostsByPage(page : Long)

    @Query("DELETE FROM post")
    suspend fun deleteAll()

    @Transaction
    suspend fun deleteAndInsert(posts : List<Post>,page : Long){
        deleteAll()
        insertAll(posts)
    }
}