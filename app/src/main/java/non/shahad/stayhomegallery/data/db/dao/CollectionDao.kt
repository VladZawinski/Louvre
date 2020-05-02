package non.shahad.stayhomegallery.data.db.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import non.shahad.stayhomegallery.data.db.entity.Collection
import non.shahad.stayhomegallery.data.db.entity.Post

@Dao
abstract class CollectionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(collections : List<Collection>)

    @Insert
    abstract suspend fun insertEach(collection : Collection)

    @Query("SELECT * FROM collection WHERE page = :page")
    abstract fun getCollectionsByPage(page : Long) : Flow<List<Collection>>

    @Query("DELETE FROM collection WHERE page = :page")
    abstract suspend fun deleteCollectionsByPage(page : Long)

    @Query("DELETE FROM collection")
    abstract suspend fun deleteAll()


    @Transaction
    open suspend fun insertByPage(page : Long,collections: List<Collection>){
        deleteCollectionsByPage(page)

        val collectionEntities : List<Collection> = collections.mapIndexed { index, collection ->
            collection.page = page
            collection
        }

        insertAll(collectionEntities)
    }

    @Transaction
    open suspend fun deleteAndInsert(collections: List<Collection>, page : Long){
        deleteAll()
        insertAll(collections)
    }


}