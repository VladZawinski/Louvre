package non.shahad.stayhomegallery.data.db.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import non.shahad.stayhomegallery.data.db.entity.Post
import non.shahad.stayhomegallery.data.model.SearchResponse
import non.shahad.stayhomegallery.utils.ext.timberD

@Dao
abstract class PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(posts : List<Post>)

    @Query("DELETE FROM post")
    abstract suspend fun deleteAll()

    @Query("SELECT * FROM post WHERE page = :page AND `query` = :q")
    abstract fun getPostsByQuery(
        page: Long,
        q: String
    ) : Flow<List<Post>>

    @Query("SELECT * FROM post WHERE page = :page AND order_by = :orderBy")
    abstract fun getPostsByPageAndOrder(
        page: Long,
        orderBy: String) : Flow<List<Post>>

    @Query("SELECT * FROM post WHERE page = :page AND collection_id = :id")
    abstract fun getPostsByPageAndCollectionID(
        id: Long,
        page: Long
    ) : Flow<List<Post>>

    @Query("DELETE FROM post WHERE page = :page AND collection_id = :id")
    abstract suspend fun clearPostAssociatedWithCollection(
        id: Long,
        page: Long
    )

    @Query("DELETE FROM post WHERE page = :page AND order_by = :orderBy")
    abstract suspend fun clearPostAssociatedWithOrder(
        page: Long,
        orderBy: String
    )

    @Query("DELETE FROM post WHERE page = :page AND `query` = :q")
    abstract fun clearPostsByQuery(
        page: Long,
        q: String
    )


    @Transaction
    open suspend fun insertByPageAndOrder(page: Long,orderBy: String,posts: List<Post>){
        clearPostAssociatedWithOrder(page,orderBy)

        val posts: List<Post> = posts.mapIndexed { _, post ->
            post.page = page
            post.query = ""
            post.orderBy = orderBy
            post
        }

        insertAll(posts)
    }

    @Transaction
    open suspend fun insertByPageAndQuery(page: Long,query: String,posts: List<Post>){
        clearPostsByQuery(page,query)

        if (!posts.isNullOrEmpty()){
            val posts: List<Post> = posts.mapIndexed { _, post ->
                post.page = page
                post.query = query
                post
            }
            insertAll(posts)
        }

    }

    @Transaction
    open suspend fun insertByPageAndCollectionID(id: Long,page: Long,posts: List<Post>){
        clearPostAssociatedWithCollection(id,page)

        if (!posts.isNullOrEmpty()){
            val posts: List<Post> = posts.mapIndexed { _, post ->
                post.page = page
                post.collectionID = id
                post
            }

            insertAll(posts)
        }

    }

}