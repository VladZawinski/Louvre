package non.shahad.stayhomegallery.data.db.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import non.shahad.stayhomegallery.data.db.entity.Post

@Dao
abstract class PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(posts : List<Post>)

    @Insert
    abstract suspend fun insertEach(post : Post)

    @Query("SELECT * FROM post WHERE page = :page AND NOT is_bookmark")
    abstract fun getPostsByPage(page : Long) : Flow<List<Post>>

    @Query("DELETE FROM post WHERE page = :page")
    abstract suspend fun deletePostsByPage(page : Long)

    @Query("DELETE FROM post")
    abstract suspend fun deleteAll()

    @Transaction
    open suspend fun insertByPage(page : Long,posts: List<Post>){
        deletePostsByPage(page)

        val postEntities : List<Post> = posts.mapIndexed { index, post ->
            post.page = page
            post
        }

        insertAll(postEntities)
    }

    @Transaction
    open suspend fun deleteAndInsert(posts : List<Post>,page : Long){
        deleteAll()
        insertAll(posts)
    }
}